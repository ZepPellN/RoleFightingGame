package com.nju.software.RoleFightingGame.role;

import java.util.Observer;

public class GameListener {

    private static Role player;

    private static PlayGame currentBattle;

    private static Observer battleBoard;

    public static Role getPlayer() {
        return player;
    }

    public static void setPlayer(Role player) {
        GameListener.player = player;
    }

    public static PlayGame getCurrentBattle() {
        return currentBattle;
    }

    public static void setCurrentBattle(PlayGame currentBattle) {
        GameListener.currentBattle = currentBattle;
    }

    public static Observer getBattleBoard() {
        return battleBoard;
    }

    public static void setInformationBoard(Observer battleBoard) {
        GameListener.battleBoard = battleBoard;
    }
}