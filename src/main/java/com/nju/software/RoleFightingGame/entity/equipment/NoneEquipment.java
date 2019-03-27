package com.nju.software.RoleFightingGame.entity.equipment;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class NoneEquipment implements Equipment{

    public String name;

    public String description;

    public Double defense;

    public Double healthPower;

    public Double level;

    public NoneEquipment() {
        this.name = "此时无装备";
        this.description = "此时无装备";
        this.defense = 0.0;
        this.healthPower = 0.0;
        this.level = 0.0;
    }


    @Override
    public Map<String, Double> getAttributes() {
        return new HashMap<>();
    }

    @Override
    public Double getAttribute(String attrName) {
        return 0.0;
    }

    @Override
    public Integer getLevel() {
        return this.getLevel();
    }

    @Override
    public String getName() {
        return "此时无装备";
    }

    @Override
    public String getDesc() {
        return "此时无装备";
    }

    @Override
    public void levelUp() {

    }
}
