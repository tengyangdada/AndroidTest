package com.example.hello;


public class Constellation {
    private String name;
    private int imageId;

    public Constellation(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
