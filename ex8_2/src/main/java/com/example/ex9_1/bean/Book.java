package com.example.ex9_1.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/5/8.
 */

public class Book extends DataSupport {


    private int id;
    private String bookName;
    private String author;
    private double price;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



}
