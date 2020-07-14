package com.ysj.myfirstopendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import org.litepal.LitePal;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Litepal使用了ORM（对象关系映射）模型
 * Litepal几乎是无配置的，仅需极少的配置文件
 * Litepal几乎包括所有的CRUD操作，也支持多张表格的操作
 * Litepal可以仅调用api进行CRUD操作而避免编写sql语句
 * 这个类有问题，看书的时候解决吧
 */
public class LitepalTestActivity extends AppCompatActivity {
    public static final String TAG = "LitepalTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal_test);
        ButterKnife.bind(this);
    }

    public static void startMyActivity(Context context){
        Intent intent = new Intent(context,LitepalTestActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_db_create,R.id.btn_db_insert,R.id.btn_db_query,
            R.id.btn_db_update,R.id.btn_db_delete})
    public void clickMyView(View view){
        switch (view.getId()){
            case R.id.btn_db_create:
          LitePal.getDatabase();
                Log.e(TAG,"创建数据库成功");
                break;
            case R.id.btn_db_insert:
                insertDatabyLitePal();
                Log.e(TAG,"插入数据成功");
                break;
            case R.id.btn_db_query:
                queryDatabyLitePal();
                Log.e(TAG,"查询数据成功");
                break;
            case R.id.btn_db_update:
                updateDatabyLitePal();
                Log.i(TAG,"更新数据成功");
                break;
            case R.id.btn_db_delete:
                deleteDatabyLitePal();
                Log.i(TAG,"删除成功");
                break;
        }
    }

    private void deleteDatabyLitePal() {
        LitePal.deleteAll(Book.class,"price < ?","15");
    }

    private void updateDatabyLitePal() {
        //第一种
        /*Book book = new Book();
        book.setName("The Lost Symbol");
        book.setauthor("Dan Brown");
        book.setPages(510);
        book.setPrice(19.95); // 第一次设置商品价格
        book.save();
        book.setPrice(10.99); // 第二次设置商品价格
        book.save();*/
        //第二种
        Book book = new Book();
        book.updateAll("name = ? and author = ?","pig","star");


    }

    private void queryDatabyLitePal() {
        List<Book> books = LitePal.findAll(Book.class);
        for (Book book:books){
            Log.e(TAG,"书名是"+book.getName());
            Log.e(TAG,"作者是"+book.getAuthor());
            Log.e(TAG,"书的页数是"+book.getPages());
            Log.e(TAG,"书的价格是"+book.getPrice());
        }
    }

    private void insertDatabyLitePal() {
        Book book1 = new Book();
        book1.setName("pig");
        book1.setauthor("star");
        book1.setPages(356);
        book1.setPrice(16.97);
        book1.save();
//        Book book2 = new Book();
//        book2.setName("queen");
//        book2.setauthor("apple");
//        book2.setPages(289);
//        book2.setPrice(12);
//        book2.save();
    }

}
