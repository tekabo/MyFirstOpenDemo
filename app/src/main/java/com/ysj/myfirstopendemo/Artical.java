package com.ysj.myfirstopendemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tekabo
 * Created on 2020/7/15
 * PackageName com.ysj.myfirstopendemo
 */
@Entity
public class Artical {
    @Id(autoincrement = true)//设置自增长
    private long id;
    private String author;
    private double price;
    private int pages;
    @Unique//设置唯一性
    private String name;
    @Generated(hash = 413091338)
    public Artical(long id, String author, double price, int pages, String name) {
        this.id = id;
        this.author = author;
        this.price = price;
        this.pages = pages;
        this.name = name;
    }
    @Generated(hash = 818663979)
    public Artical() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getPages() {
        return this.pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
