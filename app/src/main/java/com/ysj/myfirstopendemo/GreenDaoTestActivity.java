package com.ysj.myfirstopendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class GreenDaoTestActivity extends AppCompatActivity {
    private DaoSession mDaoSession;
    private ArticalDao mArcticalDao;
    private static final String TAG = "GreenDaoTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_test);
        ButterKnife.bind(this);
    }

    public static void startMyActivity(Context context){
        Intent intent = new Intent(context,GreenDaoTestActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_db_create,R.id.btn_db_insert,R.id.btn_db_query,R.id.btn_db_update,R.id.btn_db_delete})
    public void onClickMyView(View view){
        switch (view.getId()){
            case R.id.btn_db_create:
                // 获取daosession对象
                mDaoSession = MyOwnApplication.getInstance().getDaoSession();
                mArcticalDao = mDaoSession.getArticalDao();
                Log.e(TAG,"创建数据库成功");
                break;
            case R.id.btn_db_insert:
                if(mDaoSession!=null){
                    Artical artical = new Artical();
                    artical.setName("小王子");
                    artical.setAuthor("思想者");
                    artical.setPages(90);
                    artical.setPrice(90.09);
                    mArcticalDao.insert(artical);
                    Log.e(TAG,"插入数据成功");
                }else {
                    Log.e(TAG,"插入数据失败");
                }
                break;
            case R.id.btn_db_query:
                if(mDaoSession!=null){
                    List<Artical> articals = mArcticalDao.loadAll();
                    for(Artical artical:articals){
                        Log.e(TAG,"查询数据成功");
                        Log.e(TAG,"书名是"+artical.getName());
                        Log.e(TAG,"书的作者是"+artical.getAuthor());
                        Log.e(TAG,"书的页数是"+artical.getPages());
                        Log.e(TAG,"书的价格是"+artical.getPrice());
                    }
                }else {
                    Log.e(TAG,"查询数据失败");
                }
                break;
            case R.id.btn_db_update:
                if(mDaoSession!=null){
                    Artical artical = new Artical();
                    artical.setId(0);
                    artical.setName("落花生的女儿");
                    artical.setAuthor("许燕吉");
                    artical.setPages(666);
                    artical.setPrice(11.11);
                    Log.e(TAG,"更新数据成功");
                }else {
                    Log.e(TAG,"数据更新失败");
                }
                break;

            case R.id.btn_db_delete:
                mArcticalDao.deleteAll();
                Log.e(TAG,"数据删除失败");
                break;
        }
    }
}
