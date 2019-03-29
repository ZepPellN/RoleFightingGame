package com.nju.software.RoleFightingGame.factory.role;

import com.nju.software.RoleFightingGame.constant.RoleTypeEnum;
import com.nju.software.RoleFightingGame.entity.equipment.Equipment;
import com.nju.software.RoleFightingGame.factory.equipment.ArmourFactory;
import com.nju.software.RoleFightingGame.factory.equipment.WeaponFactory;
import com.nju.software.RoleFightingGame.factory.skill.SkillFactory;
import com.nju.software.RoleFightingGame.role.*;

import java.util.HashMap;
import java.util.Random;

public class RoleFactory {

    private volatile  static RoleFactory roleFactory;

    private RoleFactory() {
    }

    public static RoleFactory getRoleFactory(){
        if (roleFactory == null) {
            synchronized (RoleFactory.class) {
                if (roleFactory == null) {
                    roleFactory = new RoleFactory();
                }
            }
        }
        return roleFactory;
    }

    public Role createRole(String name, Integer level){

        String[] types = new String[]{RoleTypeEnum.JEKYLL.getName(),RoleTypeEnum.HYDE.getName()};
        Random random = new Random();
        String type = types[random.nextInt(2)];
        return createRole(name, type, level);
    }

    public Role createRole(String name, String type,Integer level){
        RoleAttribute roleAttribute = new RoleAttribute();
        Role role = new Role(roleAttribute);
        RoleType roleType;
        if(type.equals(RoleTypeEnum.JEKYLL.getName())) {
            roleType = new JekyIIRoleType(role);
        } else if (type.equals(RoleTypeEnum.HYDE.getName())){
            roleType = new HydeRoleType(role);
        } else {
            roleType = new LucyRoleType(role);
        }
        role.setRoleType(roleType);
        role.setName(name);
        role.setExperience(10 * Math.pow(1.4, level));
        for(int i = 1;i < level; i++){
            role.levelUp();
        }
        Equipment weapon = new WeaponFactory().buyEquipment(new HashMap<>(), level, "初级武器", "初级武器");
        Equipment armour = new ArmourFactory().buyEquipment(new HashMap<>(), level, "初级护甲", "初级护甲");
        role.setArmour(armour);
        role.setWeapon(weapon);
        role.addSkill(SkillFactory.getInstance().createBaseSkill("ice",level));
        role.addSkill(SkillFactory.getInstance().createBaseSkill("flame",level));
        return role;

    }


}