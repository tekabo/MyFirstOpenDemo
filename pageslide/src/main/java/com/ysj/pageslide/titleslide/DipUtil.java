package com.ysj.pageslide.titleslide;

import android.content.Context;

/**
 * Created by tekabo
 * Created on 2020/7/16
 * PackageName com.ysj.pageslide.titleslide
 */
public class DipUtil {
    public DipUtil(){

    }

    public static int dip2px(Context context,double dpValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * (double) density + 0.5D);
    }

    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
