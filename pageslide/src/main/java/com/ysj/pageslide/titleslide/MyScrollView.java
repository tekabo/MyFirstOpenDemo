package com.ysj.pageslide.titleslide;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by tekabo
 * Created on 2020/7/16
 * PackageName com.ysj.pageslide.titleslide
 */
public class MyScrollView extends ScrollView {
    private  MyScrollListener myScrollListener = null;

    public MyScrollView(Context context) {
        super(context);
    }
    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setMyScrollListener(MyScrollListener scrollListener){
        this.myScrollListener = scrollListener;
    }
    public interface MyScrollListener{
        //四个参数代表 移动前后的水平方向、垂直方向的坐标
        void onMyScollChanged(MyScrollView myScrollView,int l,int t,int oldl,int oldt);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(myScrollListener!=null){
            myScrollListener.onMyScollChanged(this,1,t,oldl,oldt);
        }
    }
}
