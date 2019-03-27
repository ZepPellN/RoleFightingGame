package com.nju.software.RoleFightingGame.factory.equipment;

import com.nju.software.RoleFightingGame.entity.equipment.Equipment;

import java.util.Map;

public interface EquipmentFactory {

    Equipment buyEquipment();

    Equipment buyEquipment(Map<String, Double> attr, Integer level, String name, String desc);
}
