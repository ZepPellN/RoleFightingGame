package com.nju.software.RoleFightingGame.entity.equipment.skill;

import java.util.Map;

public class BasicSkill implements Skill {

    private Map<String, Double> skillValue;

    private String name;

    private Double consumedSkillValue;

    private Integer level = 1;

    public BasicSkill(Map<String, Double> value, String name, Double consumedSkillValue) {
        this.consumedSkillValue = consumedSkillValue;
        this.skillValue = value;
        this.name = name;
    }

    @Override
    public void add(Skill skill) {
    }

    @Override
    public void remove(Skill skill) {

    }

    @Override
    public String getName() {
        return level + "级" + name;
    }

    @Override
    public Map<String, Double> getValue() {
        return skillValue;
    }

    @Override
    public Double getConsumedSkillValue() {
        return consumedSkillValue;
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public void levelUp() {
        level++;
        for(String key: skillValue.keySet()){
            skillValue.put(key, skillValue.get(key) * 1.04);
        }
    }
}
