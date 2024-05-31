package com.example.a24pointsgame;

public class Function {
    private String name;
    private int imageId;

    public Function(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
//定义在FunctionAdapter上需要的函数
    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}