package com.nju.software.RoleFightingGame.role;

import com.nju.software.RoleFightingGame.constant.RoleTypeEnum;
import com.nju.software.RoleFightingGame.entity.equipment.skill.Skill;

import java.util.Map;

public class HydeRoleType implements RoleType{

    private Role owner;

    public HydeRoleType(Role owner) {
        this.owner = owner;
    }

    @Override
    public String getType() {
        return RoleTypeEnum.HYDE.getName();
    }

    @Override
    public Integer levelUp() {
        Double cost = 10 * Math.pow(1.2, owner.getLevel());
        if(cost < owner.getExperience()){
            owner.setLevel(owner.getLevel() + 1);
            RoleAttribute baseAttr = owner.getBasicRoleAttribute();
            baseAttr.evenlyLevelUp();
            baseAttr.setHealthPower(baseAttr.getHealthPower() * 1.0548372794);
        }
        return owner.getLevel();
    }

    @Override
    public Double weaponAttack(Role enemy) {
        return valueAttack(owner.getWeaponAttackValues(), enemy);
    }

    @Override
    public Double skillAttack(Skill skill, Role enemy) {
        return null;
    }

    @Override
    public Double valueAttack(Map<String, Double> hurts, Role enemy) {
        if(Math.random() < owner.getAttribute("accuracy")){
            Double restriction;
            String type = owner.getRoleType().getType();
            if(type.equals(RoleTypeEnum.JEKYLL.getName())) {
                restriction = 1.1;
            } else if (type.equals(RoleTypeEnum.HYDE.getName())){
                restriction = 1.0;
            } else {
                restriction = 0.9;
            }

            Double totalHurt = 0.0;
            for(Map.Entry<String, Double> entry : hurts.entrySet()){
                Double hurt = entry.getValue()*restriction-enemy.getAttribute("def_" + entry.getKey());
                if(hurt > 0){
                    enemy.setHealthPower(enemy.getAttribute("healthPower") - hurt);
                    totalHurt += hurt;
                }
            }
            return totalHurt;
        } else {
            return -1.0;
        }
    }

    @Override
    public Integer typeRestriction(RoleType roleType) {
        if(roleType instanceof JekyIIRoleType) {
            return 1;
        } else if(roleType instanceof HydeRoleType) {
            return 0;
        } else {
            return -1;
        }
    }
}
