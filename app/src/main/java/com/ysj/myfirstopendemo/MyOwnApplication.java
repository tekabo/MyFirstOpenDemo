package com.ysj.myfirstopendemo;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by tekabo
 * Created on 2020/7/14
 * PackageName com.ysj.myfirstopendemo
 */
public class MyOwnApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
