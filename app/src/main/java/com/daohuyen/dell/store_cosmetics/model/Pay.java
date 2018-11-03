package com.daohuyen.dell.store_cosmetics.model;

import java.io.Serializable;

public class Pay implements Serializable {
    private String name;
    private int image;

    public Pay() {
    }

    public Pay(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public Pay(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
