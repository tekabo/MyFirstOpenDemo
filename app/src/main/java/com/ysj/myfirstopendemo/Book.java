package com.ysj.myfirstopendemo;

import org.litepal.crud.LitePalSupport;

/**
 * Created by tekabo
 * Created on 2020/7/14
 * PackageName com.ysj.myfirstopendemo
 */
public class Book extends LitePalSupport {
    private int id;
    private String author;
    private double price;
    private int pages;
    private String name;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getAuthor(){
        return author;
    }
    public void setauthor(String author){
        this.author = author;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public int getPages(){
        return pages;
    }
    public void setPages(int pages){
        this.pages = pages;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
