package com.ysj.listrefreshlibrary;

/**
 * Created by tekabo
 * Created on 2020/7/8
 * PackageName com.ysj.listrefreshlibrary
 */
public class NewsInfoBean {
    public String newsId;
    public String title;
    public String avatar;//图片
    public String time;
    public String desc;
    public String type;

    public String getNewsId() {
        return newsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
