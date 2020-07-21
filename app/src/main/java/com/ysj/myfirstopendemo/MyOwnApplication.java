package com.ysj.myfirstopendemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;
import org.litepal.LitePal;
import org.xutils.x;

/**
 * Created by tekabo
 * Created on 2020/7/14
 * PackageName com.ysj.myfirstopendemo
 */
public class MyOwnApplication extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MyOwnApplication instances;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);

        //给单例赋值
        instances = this;
        //初始化GreenDao
        initDatabase();
        //初始化xutils
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);//是否输出debug日志，开启debug会影响性能
    }

    //初始化greenDAO
    private void initDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this,"fighting.db",null);
//        db = mHelper.getWritableDatabase();//不加密的写法
        Database db = mHelper.getEncryptedWritableDb("aserbao");//加密的写法
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        Log.e("GreenDao","数据库初始化成功");
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public static MyOwnApplication getInstance(){
        return instances;
    }

}
