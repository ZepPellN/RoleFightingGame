package com.nju.software.RoleFightingGame.factory.equipment;

import com.nju.software.RoleFightingGame.entity.equipment.Equipment;
import com.nju.software.RoleFightingGame.entity.equipment.Weapon;

import java.util.HashMap;
import java.util.Map;

public class WeaponFactory implements EquipmentFactory{

    @Override
    public Equipment buyEquipment() {
        return new Weapon();
    }


    @Override
    public Equipment buyEquipment(Map<String, Double> attr, Integer level, String name, String desc) {
        Map<String, Double> value = new HashMap<>();
        String[] weaponAttacks = new String[]{"physical","chemical"};
        for(String attack: weaponAttacks){
            if(Math.random() < 0.6){
                value.put(attack, Math.random() * 10 + 15);
            }
        }
        Weapon weapon = new Weapon(value, level, name, desc);
        for(int i = 1;i < level;i++){
            weapon.levelUp();
        }
        return weapon;
    }
}
