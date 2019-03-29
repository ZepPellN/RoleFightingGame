package com.nju.software.RoleFightingGame.role;

import com.nju.software.RoleFightingGame.entity.equipment.skill.Skill;
import com.nju.software.RoleFightingGame.factory.role.RoleFactory;

import java.util.Iterator;
import java.util.Observable;

/**
 * 单例模式
 */
public class PlayGame extends Observable {

    private volatile static PlayGame game;

    private Role enemy;

    //战斗胜利获得的经验
    private Double experience;

    private Double money;

    private boolean playerTurn = false;

    private PlayGame() {
    }

    private PlayGame(Role enemy, Double experience, Double money) {
        this.enemy = enemy;
        this.experience = experience;
        this.money = money;
        GameListener.setCurrentBattle(this);
        addObserver(GameListener.getBattleBoard());
        init();
    }

    public static PlayGame getPlayGame() {
        Integer level = GameListener.getPlayer().getLevel();
        Role role = RoleFactory.getRoleFactory().createRole("Enemy", level-1 > 0 ? level-1 : 1);
        if (game == null) {
            synchronized (PlayGame.class) {
                if (game == null) {
                    game = new PlayGame(role, level * 1.0, level *1.0);
                }
            }
        }
        return game;
    }

    public void init() {
        String information = "The battle game begin.\n";
        if (GameListener.getPlayer().typeRestriction(enemy) > -1) {
            information += "Player move first.\n";
        } else {
            information += "Enemy move first.\n";
        }
        setChanged();
        notifyObservers(information);

        if (GameListener.getPlayer().typeRestriction(enemy) > -1) {
            playerTurn = true;
        } else {
            enemyTurn();
        }
    }

    public Integer checkIfEnd() {
        if (GameListener.getPlayer().getAttribute("hp") <= 0) {
            return -1;
        } else if (enemy.getAttribute("hp") <= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void end(Integer result) {
        if (result > 0) {
            GameListener.getPlayer().setExperience(GameListener.getPlayer().getExperience() + experience);
            GameListener.getPlayer().setMoney(GameListener.getPlayer().getMoney() + money);
            GameListener.getPlayer().setHealthPower(GameListener.getPlayer().getAttribute("fullHp"));
            GameListener.getPlayer().setDefend(GameListener.getPlayer().getAttribute("fullMp"));
            String information = "The game end. Player wins.\nGain money" + money + "，experience" + experience + "。\n\n\n";
            GameListener.setCurrentBattle(null);
            setChanged();
            notifyObservers(information);
        } else {
            String information = "The player dies, the game end。\n";
            setChanged();
            notifyObservers(information);
            GameListener.setCurrentBattle(null);
            GameListener.setPlayer(null);
        }
    }


    public void enemyTurn() {
        if (!playerTurn) {
            String information = "Enemy turn。";
            Skill skill = null;
            Iterator<Skill> skills = enemy.getSkillList().iterator();
            while (skills.hasNext()) {
                skill = skills.next();
                if (Math.random() < 0.5) {
                    break;
                }
            }
            if (Math.random() < 0.5 && skill.getConsumedSkillValue() < enemy.getAttribute("skillValue")) {
                information += "The enemy use skill -" + skill.getName() + ",";
                Double hurt = enemy.skillAttack(skill, GameListener.getPlayer());
                if (hurt < 0) {
                    information += "No hit.No hurt。\n";
                } else {
                    information += "Result:" + hurt + "hurts。\n";
                }
            } else {
                information += "Enemy uses weapon to attack，";
                Double hurt = enemy.weaponAttack(GameListener.getPlayer());
                if (hurt < 0) {
                    information += "No hit.No hurt。\n";
                } else {
                    information += "Resul: " + hurt + "hurts。\n";
                }
            }
            setChanged();
            notifyObservers(information);

            playerTurn = true;
            Integer result = checkIfEnd();
            if (result != 0) {
                end(result);
            }
        }
    }

    public void playerWeaponTurn() {
        if (playerTurn) {
            String information = "Player first";
            Double hurt = GameListener.getPlayer().weaponAttack(enemy);
            if (hurt < 0) {
                information += "No hit, no damage\n";
            } else {
                information += "Cause hurt: " + hurt + "。\n";
            }
            setChanged();
            notifyObservers(information);
            playerTurn = false;
            Integer result = checkIfEnd();
            if (result == 0) {
                enemyTurn();
            } else {
                end(result);
            }
        }
    }

    public void playSkillTurn(Integer magicIndex) {
        if (playerTurn) {
            Skill skill = null;
            int i = 0;
            Iterator<Skill> skills = GameListener.getPlayer().getSkillList().iterator();
            while (skills.hasNext()) {
                skill = skills.next();
                if (i == magicIndex) {
                    break;
                }
                i++;
            }
            String information = "Player move，use skill -" + skill.getName() + "，";
            if (skill.getConsumedSkillValue() >= GameListener.getPlayer().getAttribute("skillValue")) {
                information += "Skill experience is not enough，can't use this skill.\n";
                setChanged();
                notifyObservers(information);
                return;
            } else {
                Double hurt = GameListener.getPlayer().skillAttack(skill, enemy);
                if (hurt < 0) {
                    information += "No hit, no hurt\n";
                } else {
                    information += "Cause hurt:" + hurt + ".\n";
                }
            }

            setChanged();
            notifyObservers(information);
            playerTurn = false;
            Integer result = checkIfEnd();
            if (result == 0) {
                enemyTurn();
            } else {
                end(result);
            }
        }
    }


}
