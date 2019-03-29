package com.nju.software.RoleFightingGame.entity.equipment.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MixedSkill implements Skill{

    private List<Skill> mixed = new ArrayList<>();

    @Override
    public void add(Skill skill) {
        mixed.add(skill);
    }

    @Override
    public void remove(Skill skill) {
        mixed.remove(skill);
    }

    @Override
    public String getName() {
        String name = "";
        for(Skill skill : mixed) {
            name += "+" + skill.getName();
        }

        name = name.substring(1);
        return name;
    }

    @Override
    public Map<String, Double> getValue() {
        Map<String, Double> result = new HashMap<>();

        Map<String, Double> temp;

        for (Skill skill : mixed) {
            temp = skill.getValue();
            for (String key : temp.keySet()) {
                result.put(key, result.getOrDefault(key, 0.0));
            }
        }
        return result;
    }

    @Override
    public Double getConsumedSkillValue() {
        Double skillValue = 0.0;
        for (Skill skill : mixed) {
            skillValue = skill.getConsumedSkillValue();
        }
        return skillValue * 0.9;
    }

    @Override
    public Integer getLevel() {
        int level = 0;
        for (Skill skill : mixed) {
            level += skill.getLevel();
        }
        return level;
    }

    @Override
    public void levelUp() {
        for (Skill skill : mixed) {
            skill.levelUp();
        }
    }
}
