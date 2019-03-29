package com.nju.software.RoleFightingGame.constant;


public enum RoleTypeEnum {

    JEKYLL("JEKYLL"),
    HYDE("HYDE");

    private String name;

    RoleTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}
