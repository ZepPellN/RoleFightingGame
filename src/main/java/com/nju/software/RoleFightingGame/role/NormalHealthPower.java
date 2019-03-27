package com.nju.software.RoleFightingGame.role;

public class NormalHealthPower implements HealthPowerState{
    @Override
    public Double getAttribute(String attrName) {
        return 1.0;
    }
}
