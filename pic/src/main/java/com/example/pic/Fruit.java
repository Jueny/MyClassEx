package com.example.pic;

/**
 * Created by john on 2018/4/2.
 */

public class Fruit {
    private String content;
    private String name;
    private int imgId;

    public Fruit(String content, String name, int imgId) {
        this.content = content;
        this.name = name;
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

}
