package com.nju.software.RoleFightingGame.role;

import com.nju.software.RoleFightingGame.entity.equipment.Equipment;
import com.nju.software.RoleFightingGame.entity.equipment.NoneEquipment;
import lombok.Data;

@Data
public abstract class Role {
    /**
     * 姓名
     */
    protected String name;
    /**
     * 经验值
     */
    protected Double experience;
    /**
     * 等级
     */
    protected Integer level = 1;

    protected RoleAttribute basicRoleAttribute;

    protected AttributeDecorator advancedRoleAttribute;

    protected  RoleType roleType;

    protected HealthPowerState healthPowerState = new NormalHealthPower();

    protected Equipment weapon = new NoneEquipment();

    public Double getAttribute(String attrName) {
        return healthPowerState.getAttribute(attrName) * advancedRoleAttribute.getAttribute(attrName);

    }

    public void constructAdvancedAttribute(){
        advancedRoleAttribute = new AttributeDecoratorImpl(basicRoleAttribute, weapon);
    }


}
