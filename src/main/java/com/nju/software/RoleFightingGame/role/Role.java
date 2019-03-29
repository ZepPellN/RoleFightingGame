package com.nju.software.RoleFightingGame.role;

import com.nju.software.RoleFightingGame.entity.equipment.Equipment;
import com.nju.software.RoleFightingGame.entity.equipment.NoneEquipment;
import com.nju.software.RoleFightingGame.entity.equipment.skill.Skill;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Role {
    /**
     * 姓名
     */
    protected String name;
    /**
     * 经验值
     */
    protected Double experience;
    /**
     * 等级
     */
    protected Integer level = 1;

    protected RoleAttribute basicRoleAttribute;

    protected AttributeDecorator advancedRoleAttribute;

    protected RoleType roleType;

    protected HealthPowerState healthPowerState = new NormalHealthPower();

    protected Equipment weapon = new NoneEquipment();

    protected Equipment armour = new NoneEquipment();

    protected List<Skill> skillList = new ArrayList<>();

    private Double money = 100.0;

    public Role(RoleAttribute baseAttr) {
        this.basicRoleAttribute = baseAttr;
        constructAdvancedAttribute();
    }

    public Double getAttribute(String attrName) {
        return healthPowerState.getAttribute(attrName) * advancedRoleAttribute.getAttribute(attrName);
    }

    public void constructAdvancedAttribute() {
        advancedRoleAttribute = new AttributeDecoratorImpl(basicRoleAttribute, weapon);
        advancedRoleAttribute = new AttributeDecoratorImpl(advancedRoleAttribute, armour);
    }

    public Integer typeRestriction(Role enemy) {
        return roleType.typeRestriction(enemy.getRoleType());
    }

    public Integer levelUp() {
        return roleType.levelUp();
    }

    public Map<String, Double> getWeaponAttackValues() {
        Map<String, Double> res = new HashMap<>();
        for (Map.Entry<String, Double> entry : weapon.getAttributes().entrySet()) {
            res.put(entry.getKey(), entry.getValue() + getAttribute("ap"));
        }
        return res;
    }

    public void setHealthPower(Double hp) {
        basicRoleAttribute.setHp(hp);
        if (hp / getAttribute("healthPower") < 0.5) {
            if (healthPowerState instanceof NormalHealthPower) {
                healthPowerState = new UnormalHealthPower();
            }
        } else {
            if (healthPowerState instanceof UnormalHealthPower) {
                healthPowerState = new NormalHealthPower();
            }
        }
    }

    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
        constructAdvancedAttribute();
    }

    public void setArmour(Equipment armour) {
        this.armour = armour;
        constructAdvancedAttribute();
    }

    public void addSkill(Skill skill) {
        this.skillList.add(skill);
    }

    public void setDefend(Double value) {
        basicRoleAttribute.setBasicDefendValue(value);
    }

    public Double weaponAttack(Role enemy) {
        return roleType.weaponAttack(enemy);
    }

    public Double skillAttack(Skill skill, Role enemy) {
        return roleType.skillAttack(skill, enemy);
    }

    public String getType() {
        return roleType.getType();
    }

    public void setSkillValue(Double skillValue) {
        basicRoleAttribute.setSkillValue(skillValue);
    }


    public Boolean canLevelUp() {
        return 10 * Math.pow(1.2, getLevel()) < getExperience();
    }


    public List<KeyValue> getAdvancedAttributes() {
        List<KeyValue> attr = new ArrayList<>();

        attr.add(new KeyValue("defend—physical", (double) Math.round(getAttribute("defend_physical") * 100) / 100));

        attr.add(new KeyValue("defend—chemical", (double) Math.round(getAttribute("defend_chemical") * 100) / 100));

        attr.add(new KeyValue("defend——ice", (double) Math.round(getAttribute("defend_ice") * 100) / 100));

        attr.add(new KeyValue("defend——flame", (double) Math.round(getAttribute("defend_flame") * 100) / 100));

        attr.add(new KeyValue("accuracy", (double) Math.round(getAttribute("accuracy") * 100) / 100));

        return attr;
    }

    public boolean canWeaponLevelUp() {
        return getWeapon().getLevel() * 10 < getMoney();
    }

    public boolean weaponLevelUp() {
        if (canWeaponLevelUp()) {
            setMoney(getMoney() - getWeapon().getLevel() * 10);
            getWeapon().levelUp();
            return true;
        }
        return false;
    }

    public boolean canArmourLevelUp() {
        return getArmour().getLevel() * 10 < getMoney();
    }

    public boolean armourLevelUp() {
        if (canArmourLevelUp()) {
            setMoney(getMoney() - getArmour().getLevel() * 10);
            getArmour().levelUp();
            return true;
        }
        return false;
    }

    public boolean canSkillLevelUp(Integer magicIndex) {
        return skillList.get(magicIndex).getLevel() * 10 < getMoney();
    }

    public boolean skillLevelUp(Integer magicIndex) {
        if (canSkillLevelUp(magicIndex)) {
            setMoney(getMoney() - skillList.get(magicIndex).getLevel() * 10);
            skillList.get(magicIndex).levelUp();
            return true;
        }
        return false;
    }


    public Skill getSkillByName(String name) {
        for (Skill s : skillList) {

            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
