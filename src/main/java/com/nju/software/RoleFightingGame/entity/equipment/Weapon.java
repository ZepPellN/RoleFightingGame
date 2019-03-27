package com.nju.software.RoleFightingGame.entity.equipment;

import com.nju.software.RoleFightingGame.entity.equipment.Equipment;

import java.util.HashMap;
import java.util.Map;

/**
 * 武器
 */
public class Weapon implements Equipment {


    private Map<String, Double> attr = new HashMap<>();

    private Integer level;

    private String name;

    private String desc;

    public Weapon() {
        this.name = "初始武器装备";
        this.desc = "系统武器初始装备";
        this.level = 1;
    }

    public Weapon(Map<String, Double> attr, Integer level, String name, String desc) {
        this.attr = attr;
        this.level = level;
        this.name = name;
        this.desc = desc;
    }


    @Override
    public Map<String, Double> getAttributes() {
        return attr;
    }

    @Override
    public Double getAttribute(String attrName) {

        return attr.getOrDefault(attrName,0.0);

    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void levelUp() {
        level++;
        for(String key: attr.keySet()){
            attr.replace(key, getAttribute(key) * (1 + Math.random() / 10));
        }
    }
}
