package com.ysj.myfirstopendemo;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by tekabo
 * Created on 2020/7/3
 * PackageName com.ysj.myfirstopendemo
 * IPresenter接口继承自LifecycleObserver接口，MyPresenter又实现了IPresenter接口，这样MyPresenter成为了一个观察者。
 * 接在在LifeCycleTestActivity中加入MyPresenter
 */
public class MyPresenter implements IPresenter{
    private static final String TAG = "MyPresenter";

    @Override
    public void onMyStart() {
        Log.e(TAG,"Lifecycle call onStart");
    }

    @Override
    public void onMyResume() {
        Log.e(TAG,"Lifecycle call onResume");
    }

    @Override
    public void onMyPause() {
        Log.e(TAG,"Lifecycle call onPause");
    }
}

interface IPresenter extends LifecycleObserver{
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onMyStart();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onMyResume();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onMyPause();
}
