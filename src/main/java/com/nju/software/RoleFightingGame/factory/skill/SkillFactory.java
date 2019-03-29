package com.nju.software.RoleFightingGame.factory.skill;

import com.nju.software.RoleFightingGame.entity.equipment.skill.BasicSkill;
import com.nju.software.RoleFightingGame.entity.equipment.skill.Skill;

import java.util.HashMap;
import java.util.Map;

public class SkillFactory {

    private static SkillFactory instance = new SkillFactory();

    private SkillFactory() {
        if (instance == null) {
            synchronized (SkillFactory.class) {
                if (instance == null) {
                    instance = new SkillFactory();
                }
            }
        }
    }

    public static SkillFactory getInstance() {
        return instance;
    }

    public Skill createBaseSkill(String type) {
        return createBaseSkill(type, 1);
    }

    public Skill createBaseSkill(String type, Integer level) {
        Map<String, Double> value = new HashMap<>();
        value.put(type, 10.0);
        String name;
        if (type.equals("ice")) {
            name = "ice skill";
        } else if (type.equals("flame")) {
            name = "flame skill";
        } else {
            name = "basic skill";
        }
        Skill skill = new BasicSkill(value, name, 10.0);
        for (int i = 1; i < level; i++) {
            skill.levelUp();
        }
        return skill;
    }
}
