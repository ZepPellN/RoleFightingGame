package com.nju.software.RoleFightingGame.role;

import com.nju.software.RoleFightingGame.entity.equipment.Equipment;

public class AttributeDecoratorImpl implements AttributeDecorator {

    private AttributeDecorator attributeDecorator;

    private Equipment equipment;

    @Override
    public Double getAttribute(String attrName) {
        return null;
    }

    public AttributeDecoratorImpl(AttributeDecorator attributeDecorator, Equipment equipment) {
        this.attributeDecorator = attributeDecorator;
        this.equipment = equipment;
    }
}
