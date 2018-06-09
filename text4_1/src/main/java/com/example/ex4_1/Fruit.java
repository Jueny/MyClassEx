package com.example.ex4_1;

/**
 * Created by Administrator on 2018/3/27.
 */

public class Fruit {
    private String name,content;
    private int imgId;

    public Fruit(String name, String content, int imgId) {
        this.name = name;
        this.imgId = imgId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
