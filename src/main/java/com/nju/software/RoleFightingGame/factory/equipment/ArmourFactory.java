package com.nju.software.RoleFightingGame.factory.equipment;

import com.nju.software.RoleFightingGame.entity.equipment.Armour;
import com.nju.software.RoleFightingGame.entity.equipment.Equipment;

import java.util.HashMap;
import java.util.Map;

public class ArmourFactory implements EquipmentFactory {
    @Override
    public Equipment buyEquipment() {
        return new Armour();
    }

    @Override
    public Equipment buyEquipment(Map<String, Double> attr, Integer level, String name, String desc) {
        Map<String, Double> armourValue = new HashMap<>();
        String[] offense = new String[]{"physical","chemical"};

        for(String armour: offense){
            if(Math.random() < 0.6){
                armourValue.put(armour, Math.random() * 10 + 15);
            }
        }
        Armour armour = new Armour(armourValue, level, name, desc);
        for(int i = 1;i < level;i++){
            armour.levelUp();
        }
        return armour;
    }
}
