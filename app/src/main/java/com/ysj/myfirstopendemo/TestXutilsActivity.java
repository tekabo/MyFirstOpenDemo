package com.ysj.myfirstopendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
xUtils主要有4个模块：注解模块，网络模块，图片加载模块，数据库模块。
包括我们之前学习过的ButterKnife和Dagger，
xUtils算是我们接触的第三个具有依赖注入的框架了。
*/
@ContentView(R.layout.activity_test_xutils)
public class TestXutilsActivity extends AppCompatActivity {
    private static final String TAG = "TestXutilsActivity";
    @ViewInject(R.id.btn_test)
    private Button btn_test;
    @ViewInject(R.id.btn_test2)
    private Button btn_test2;
    @ViewInject(R.id.tv_test)
    private TextView tv_test;

    @ViewInject(R.id.iv1)
    ImageView image01;
    @ViewInject(R.id.iv2)
    ImageView image02;
    @ViewInject(R.id.iv3)
    ImageView image03;
    @ViewInject(R.id.iv4)
    ImageView image04;
    @ViewInject(R.id.iv5)
    ImageView image05;
    @ViewInject(R.id.lv1)
    ListView lv1;

    private  DbManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);


//        setImg();
    }

    //加载图片
    private void setImg() {
        String[] imgUrls = {
                "http://img4.imgtn.bdimg.com/it/u=3182769660,1810895318&fm=23&gp=0.jpg",
                "http://img2.imgtn.bdimg.com/it/u=1278435851,1308167727&fm=23&gp=0.jpg",
                "http://img2.3lian.com/2014/f4/199/d/6.jpg",
                "http://pic1.win4000.com/wallpaper/4/584b9ea3a511c.jpg",
                "http://img4.imgtn.bdimg.com/it/u=3182769660,1810895318&fm=23&gp=0.jpg",
        };
        ImageOptions options = new ImageOptions.Builder().setFadeIn(true).build();//淡入效果
        //ImageOptions.Builder()的一些其他属性：

        //.setCircular(true) //设置图片显示为圆形
        //.setSquare(true) //设置图片显示为正方形
        //setCrop(true).setSize(200,200) //设置大小
        //.setAnimation(animation) //设置动画
        //.setFailureDrawable(Drawable failureDrawable) //设置加载失败的动画
        //.setFailureDrawableId(int failureDrawable) //以资源id设置加载失败的动画
        //.setLoadingDrawable(Drawable loadingDrawable) //设置加载中的动画
        //.setLoadingDrawableId(int loadingDrawable) //以资源id设置加载中的动画
        //.setIgnoreGif(false) //忽略Gif图片
        //.setParamsBuilder(ParamsBuilder paramsBuilder) //在网络请求中添加一些参数
        //.setRaduis(int raduis) //设置拐角弧度
        //.setUseMemCache(true) //设置使用MemCache，默认true
        /**
         * 加载图片的4个bind方法
         */
        x.image().bind(image01, imgUrls[0]);
        x.image().bind(image02, imgUrls[1], options);
        x.image().bind(image03, imgUrls[2], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        x.image().bind(image04, imgUrls[3], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        /**
         * loadDrawable()方法加载图片
         */
        Callback.Cancelable cancelable = x.image().loadDrawable(imgUrls[4], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                image05.setImageDrawable(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        //主动取消loadDrawable()方法
        //cancelable.cancel();

        /**
         * loadFile()方法
         * 应用场景：当我们通过bind()或者loadDrawable()方法加载了一张图片后，
         * 它会保存到本地文件中，那当我需要这张图片时，就可以通过loadFile()方法进行查找。
         * urls[0]：网络地址
         */
        x.image().loadFile(imgUrls[0], options, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                //在这里可以做图片另存为等操作
                Log.i(TAG, "file:" + result.getPath() + result.getName());
                return true;//相信本地缓存返回true
            }

            @Override
            public void onSuccess(File result) {
                Log.i(TAG, "file");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public static void startMyActivity(Context context) {
        Intent intent = new Intent(context, TestXutilsActivity.class);
        context.startActivity(intent);
    }

    @Event(value = {R.id.btn_test, R.id.btn_test2}, type = View.OnClickListener.class)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                Log.i(TAG,"哈哈哈哈1");
                tv_test.setText("修改成了测试文本");
                break;
            case R.id.btn_test2:
                Log.i(TAG,"哈哈哈哈2");
                tv_test.setText("修改成了测试文本2");
                break;
        }
    }


   /* @Event(value = {R.id.btn_test, R.id.btn_test2, R.id.btn_get, R.id.btn_post, R.id.btn_cache,
            R.id.btn_upload, R.id.btn_download, R.id.btn_db_operation}, type = View.OnClickListener.class)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                //get请求
                String url = "http://10.0.0.2:8080/login";
                RequestParams params = new RequestParams(url);
                params.addQueryStringParameter("username", "abc");
                params.addQueryStringParameter("password", "123");
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(x.app(), result, Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onSuccess result:" + result);
                    }

                    //请求异常后的回调方法
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    //主动调用取消请求的回调方法
                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                break;

            case R.id.btn_post:
                //post请求
                String urls = "http://10.0.0.2:8080/login";
                RequestParams params1 = new RequestParams(urls);
                params1.addParameter("username", "abc");
                params1.addParameter("password", "123");
                x.http().request(HttpMethod.PUT, params1, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(x.app(), result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                break;
            case R.id.btn_cache:
                //使用缓存
                String url1 = "http://10.0.0.2:8080/login";
                RequestParams params2 = new RequestParams(url1);
                params2.setCacheMaxAge(1000 * 60);//为请求添加缓存时间
                Callback.Cancelable cancelable = x.http().get(params2, new Callback.CacheCallback<Object>() {
                    @Override
                    public void onSuccess(Object result) {
                        Log.i("JAVA", "onSuccess:" + result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

                    //result：缓存内容
                    @Override
                    public boolean onCache(Object result) {
                        //在setCacheMaxAge设置范围（上面设置的是60秒）内，如果再次调用GET请求，
                        //返回true：缓存内容被返回，相信本地缓存，返回false：缓存内容被返回，不相信本地缓存，仍然会请求网络
                        return true;
                    }
                });
                break;
            case R.id.btn_upload:
                //上传文件
                String url2 = "http://10.0.0.2:8080/login";
                String path = "/mnt/sdcard/Download/icon.jpg";
                RequestParams params3 = new RequestParams(url2);
                params3.setMultipart(true);
                params3.addBodyParameter("file", new File(path));
                x.http().post(params3, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                break;
            case R.id.btn_download:
                //下载文件
                String url3 = "http://10.0.0.2:8080/server/nfctest.apk";
                RequestParams params4 = new RequestParams(url3);
                //自定义保存路径，Environment.getExternalStorageDirectory()：SD卡的根目录
                //注意，如果你的Android版本为Android X，由于其作用域存储的特性，应使用context.getExternalFilesDir()来获取关联目录
                params4.setSaveFilePath(Environment.getExternalStorageDirectory() + "/myapp/");
                //自动为文件命名
                params4.setAutoRename(true);

                x.http().post(params4, new Callback.ProgressCallback<File>() {
                    @Override
                    public void onSuccess(File result) {
                        //apk下载完成后，调用系统的安装方法
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

                    //网络请求之前回调
                    @Override
                    public void onWaiting() {

                    }

                    //网络请求开始的时候回调
                    @Override
                    public void onStarted() {

                    }

                    //下载的时候不断回调该方法
                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        //当前进度和文件总大小
                        Log.i(TAG, "current" + current + ",total:" + total);
                    }
                });
                break;
            case R.id.btn_db_operation:
                Log.i(TAG, "哈哈哈哈");
                try {
                    operateDb();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;


        }
    }
*/
    //创建数据库
 /*   private void operateDb() throws DbException {
        DbManager dbManager;
        //先要配置DaoConfig，作为数据库的配置信息，以此来获取xUtils提供的DbManager对象
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                //设置数据库名，默认xutils.db
                .setDbName("pig.db")
                // 不设置dbDir时, 默认存储在app的私有目录
                // "sdcard"的写法并非最佳实践, 这里为了简单, 先这样写了.
                .setDbDir(new File("/sdcard"))
                .setDbVersion(1) //数据库版本
                //设置是否允许事务，默认true
                //.setAllowTransaction(true)

                //设置表创建的监听
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table) {
                        Log.i("JAVA", "onTableCreated：" + table.getName());
                    }
                })
                //设置数据库更新的监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) throws DbException {

                    }
                })
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) throws DbException {
                        //开启数据库支持多线程操作，提升性能
                        db.getDatabase().enableWriteAheadLogging();
                    }
                });

        dbManager = x.getDb(daoConfig);

        //然后创建一个名为Person的实体类，以此来创建一张数据表，在下方

        //基于以上，接下来创建数据库
          //用集合向Person表中插入多条数据
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("张三"));
        people.add(new Person("李四"));
        people.add(new Person("刘能"));

        //db.save()方法不仅可以插入单个对象，还能插入集合
        dbManager.save(people);

     *//*   //增加数据
        //用集合向Person表中插入多条数据
        ArrayList<Person> person = new ArrayList<>();
        person.add(new Person("张三"));
        person.add(new Person("李四"));
        person.add(new Person("赵六"));

        //db.save()方法不仅可以插入单个对象，还能插入集合
        dbManager.save(person);

        //删除数据
        //第一种写法：
        dbManager.delete(Person.class);//child_info表中数据将被全部删除
        //第二种写法，添加删除条件：
        WhereBuilder b = WhereBuilder.b();
        b.and("id",">",2);//构造修改的条件
        b.and("id","<",4);
        dbManager.delete(Person.class,b);

        //删除数据表
        dbManager.dropTable(Person.class);

        //删除数据库
        dbManager.dropDb();


        //修改数据
        //第一种写法
        Person first = dbManager.findFirst(Person.class);
        first.setName("张三01");
        dbManager.update(first,"c_name");//c_name表中的字段名

        //第二种写法
        WhereBuilder wb = WhereBuilder.b();
        wb.and("id","=",first.getId());//构造修改的条件
        KeyValue name = new KeyValue("c_name","张三02");
        dbManager.update(Person.class,b,name);

        //第三种写法
        first.setName("张三修改");
        dbManager.saveOrUpdate(first);
        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();


        //查询数据

        //查询数据库表中第一条数据
        Person p = dbManager.findFirst(Person.class);
        Log.i(TAG,p.toString());
        //findAll()：查询所有结果
        List<Person> peopleAll = dbManager.findAll(Person.class);
        List<String> list = new ArrayList<String>();
        for(int i=0;i<peopleAll.size();i++){
            list.add(peopleAll.get(i).toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TestXutilsActivity.this,
                android.R.layout.simple_list_item_1,list);
        lv1.setAdapter(arrayAdapter);

        //添加查询条件查询
        WhereBuilder builder = WhereBuilder.b();
        builder.and("id",">",2);//构造修改条件
        builder.and("id","<",4);
        List<Person> all = dbManager.selector(Person.class).where(builder).findAll();
        //第二种写法
        List<Person> all2 = dbManager.selector(Person.class).where("id",">",2).and("id","<",4).findAll();
        for(Person persons:all2){
            Log.i(TAG,persons.toString());
        }
*//*
    }
*/
}


