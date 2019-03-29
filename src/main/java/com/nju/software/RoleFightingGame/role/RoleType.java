package com.nju.software.RoleFightingGame.role;

import com.nju.software.RoleFightingGame.entity.equipment.skill.Skill;

import java.util.Map;

public interface RoleType {

    String getType();

    Integer levelUp();

    Double weaponAttack(Role enemy);

    Double skillAttack(Skill skill, Role enemy);

    Double valueAttack(Map<String, Double> hurts, Role enemy);

    Integer typeRestriction(RoleType roleType);

}
