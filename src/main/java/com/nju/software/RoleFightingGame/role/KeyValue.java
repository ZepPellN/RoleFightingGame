package com.nju.software.RoleFightingGame.role;

public class KeyValue {

    String key;
    Double value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public KeyValue(String key, Double value) {
        this.key = key;
        this.value = value;
    }
}
