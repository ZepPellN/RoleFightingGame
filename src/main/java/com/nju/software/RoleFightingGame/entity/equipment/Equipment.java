package com.nju.software.RoleFightingGame.entity.equipment;

import java.util.Map;

/**
 * 装备
 */
public interface Equipment {

    Map<String, Double> getAttributes();

    Double getAttribute(String attrName);

    Integer getLevel();

    String getName();

    String getDesc();

    void levelUp();
}
