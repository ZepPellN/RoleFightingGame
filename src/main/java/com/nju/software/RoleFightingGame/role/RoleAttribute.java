package com.nju.software.RoleFightingGame.role;

import lombok.Data;

@Data
public class RoleAttribute implements AttributeDecorator{
    /**
     * 基本防御值
     */
    private Double basicDefendValue = 100.0;
    /**
     * 基本攻击值
     */
    private Double basicAttackValue = 20.0;
    /**
     * 初始血量
     */
    private Double healthPower = 200.0;
    /**
     * 真实血量
     */
    private Double hp = healthPower;
    /**
     * 技能值
     */
    private Double skillValue = 200.0;

    private Double realSkillValue = skillValue;

    private Double defend_physical = 10.0;

    private Double defend_chemical = 10.0;

    private Double defend_ice = 10.0;

    private Double defend_flame = 10.0;

    private Double accuracy = 0.5;

    public RoleAttribute() {
    }

    @Override
    public Double getAttribute(String attrName) {

        switch (attrName) {
            case "hp":
                return getHp();
            case "healthPower":
                return getHealthPower();
            case "skillValue":
                return getSkillValue();
            case "realSkillValue":
                return getRealSkillValue();
            case "basicDefendValue":
                return getBasicDefendValue();
            case "defend_physical":
                return getDefend_physical();
            case "defend_chemical":
                return getDefend_chemical();
            case "defend_ice":
                return getDefend_ice();
            case "defend_flame":
                return getDefend_flame();
            case "basicAttackValue":
                return getBasicAttackValue();
            case "accuracy":
                return getAccuracy();
            default:
                return 1.0;
        }
    }

    public void evenlyLevelUp(){
        healthPower *= 1.06;
        hp = healthPower;
        skillValue *= 1.06;
        realSkillValue = skillValue;
        defend_physical *= 1.06;
        defend_chemical *= 1.05;
        defend_ice *= 1.06;
        defend_flame *= 1.06;
        basicAttackValue *= 1.06;
        accuracy = Math.min(1.0, accuracy * 1.06);
    }
}
