package com.nju.software.RoleFightingGame.role;

import lombok.Data;

@Data
public class RoleAttribute implements AttributeDecorator{
    /**
     * 基本防御值
     */
    protected Double basicDefendValue;
    /**
     * 基本攻击值
     */
    protected Double basicAttackValue;
    /**
     * 血量
     */
    protected Double healthPower;

    @Override
    public Double getAttribute(String attrName) {
        return null;
    }
}
