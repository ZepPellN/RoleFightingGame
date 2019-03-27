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
        Map<String, Double> value = new HashMap<>();
        String[] defences = new String[]{"def_chop","def_stab","def_crush","def_ice","def_flame","def_earth","def_lightning"};
        for(String defence:defences){
            if(Math.random()<0.5){
                value.put(defence, Math.random()*5+5);
            }
        }
        Armour armour = new Armour(value,1,"随机装备","随机装备，全凭运气");
        for(int i=1;i<level;i++){
            armour.levelUp();
        }
        return armour;
    }
}
