package com.tangzy.minedemo;

public class TextLocation {
    private String name;
    private float scale = -1;

    public TextLocation(String name, float scale) {
        this.name = name;
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
