package com.nju.software.RoleFightingGame.factory.equipment;

import com.nju.software.RoleFightingGame.entity.equipment.Equipment;
import com.nju.software.RoleFightingGame.entity.equipment.NoneEquipment;

import java.util.Map;

public class NoneEquipmentFactory implements EquipmentFactory {
    @Override
    public Equipment buyEquipment() {
        return new NoneEquipment();
    }

    @Override
    public Equipment buyEquipment(Map<String, Double> attr, Integer level, String name, String desc) {
        return new NoneEquipment();
    }
}
