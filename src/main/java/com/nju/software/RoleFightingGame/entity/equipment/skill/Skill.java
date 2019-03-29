package com.nju.software.RoleFightingGame.entity.equipment.skill;

import java.util.Map;

/**
 * 技能
 */
public interface Skill {

    void add(Skill skill);

    void remove(Skill skill);

    String getName();

    Map<String, Double> getValue();

    Double getConsumedSkillValue();

    Integer getLevel();

    void levelUp();
}
