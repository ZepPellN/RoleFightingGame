package com.nju.software.RoleFightingGame.entity.equipment;

import java.util.Map;

public class Armour implements Equipment{

    private Map<String, Double> attr;

    private Integer level;

    private String name;

    private String desc;

    public Armour() {
        this.name = "初始武器装备";
        this.desc = "系统武器初始装备";
        this.level = 1;
    }

    public Armour(Map<String, Double> attr, Integer level, String name, String desc) {
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
        return attr.getOrDefault(attrName, 0.0);
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
