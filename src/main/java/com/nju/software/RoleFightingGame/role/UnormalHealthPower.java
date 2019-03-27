package com.nju.software.RoleFightingGame.role;

public class UnormalHealthPower implements HealthPowerState {
    @Override
    public Double getAttribute(String attrName) {
        switch (attrName){
            case "attack":
                return 0.8;
            case "accuracy":
                return 0.8;
            default:
                return 1.0;
        }
    }
}
