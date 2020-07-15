package com.ysj.myfirstopendemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 同步调用：编写简单，但是会阻塞主线程，一般不适用
 * 异步调用：回调函数是在子线程，我们不能在子线程更新UI，需要借助于runOnUiThread()方法或者Handler来处理
 */
public class OkhttpActivity extends AppCompatActivity {
    private static final String URLS = "";//http://10.0.2.2:8080/androidframelearn/
    private static final String URL = "http://wwww.baidu.com";
    private static final String TAG = "OkhttpActivity";

    public static void startMyActivity(Context context) {
        Intent intent = new Intent(context, OkhttpActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.get_syn, R.id.get_asy, R.id.post_syn, R.id.post_asy, R.id.btn_post_keyvalue,
            R.id.btn_post_string, R.id.btn_post_file, R.id.btn_post_form})
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.get_syn:
                getBySynchronized();
                break;
            case R.id.get_asy:
                getByAsynchronized();
                break;
            case R.id.post_syn:
                postBySynchronized();
                break;
            case R.id.post_asy:
                postByAsynchronized();
                break;
            case R.id.btn_post_keyvalue:
                postKeyValue();
                break;
            case R.id.btn_post_string:
                postString();
                break;
            case R.id.btn_post_file:
                postFile();
                break;
            case R.id.btn_post_form:
                postForm();
                break;
        }
    }
//    POST请求提交表单
    private void postForm() {
        // 1.构建Client对象
        OkHttpClient client = new OkHttpClient();
        // 2.构造RequestBody
        // 2.1 构造文件对象
        File file = new File(OkhttpActivity.this.getExternalFilesDir(null), "test.png");
        Log.i(TAG,Environment.getExternalStorageDirectory().toString());
        // 2.2 判断文件是否为空
        if (!file.exists()){
            Log.i(TAG,"文件为空，无法创建");
        }else{
            // 2.3 通过表单构造构造RequestBody对象
            RequestBody muiltipartBody = new MultipartBody.Builder()
                    //一定要设置这句
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("username", "admin")//
                    .addFormDataPart("password", "admin")//
                    .addFormDataPart("myfile", "test.png", RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();
            // 3.采用建造者模式和链式调用构建Request对象
            final Request request = new Request.Builder()
                    .url(URLS) // 请求URL
                    .post(muiltipartBody) // 默认就是get请求，可以不写
                    .build();
            // 4.通过1和3产生的Client和Request对象生成Call对象
            Call call = client.newCall(request);
            // 5.调用Call对象的enqueue()方法，并且实现一个回调实现类
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d(TAG, "发送post请求表单失败！");
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Log.d(TAG, "发送post请求表单成功！请求到的信息为：" + response.body().string());
                }
            });
        }
    }

    //    POST请求提交文件
    private void postFile() {
        // 1.构建Client对象
        OkHttpClient client = new OkHttpClient();
        // 2.构造RequestBody
        // 2.1 构造文件对象
        File file = new File(OkhttpActivity.this.getExternalFilesDir(null), "test.txt");
        Log.i(TAG, Environment.getExternalStorageDirectory().toString());//context.getExternalFilesDir()路径为： /storage/emulated/0/Android/data/<包名>/files


        // 2.2 判断文件是否为空
        if(!file.exists()){
            Log.i(TAG,"文件为空，无法创建");
        }else {
            // 2.3 通过文件构造构造RequestBody对象
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            // 3.采用建造者模式和链式调用构建Request对象
            final Request request = new Request.Builder()
                    .url(URLS) // 请求URL
                    .post(requestBody) // 默认就是get请求，可以不写
                    .build();
            // 4.通过1和3产生的Client和Request对象生成Call对象
            Call call = client.newCall(request);
            // 5.调用Call对象的enqueue()方法，并且实现一个回调实现类
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d(TAG, "发送post请求文件失败！");
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Log.d(TAG, "发送post请求文件成功！请求到的信息为：" + response.body().string());
                }
            });
        }

    }

    //    POST请求提交字符串
    private void postString() {
    // 1.构建Client对象
        OkHttpClient client = new OkHttpClient();
        // 2.构造RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), "{username:admin;password:123456}");
        // 3.采用建造者模式和链式调用构建Request对象
        final Request request = new Request.Builder()
                .url(URLS) // 请求URL
                .post(requestBody) // 默认就是get请求，可以不写
                .build();
        // 4.通过1和3产生的Client和Request对象生成Call对象
        Call call = client.newCall(request);
        // 5.调用Call对象的enqueue()方法，并且实现一个回调实现类
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "发送post请求字符串失败！");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "发送post请求字符串成功！请求到的信息为：" + response.body().string());
            }
        });
    }

    //    POST请求提交键值对
    private void postKeyValue() {
        // 1.构建Client对象
        OkHttpClient client = new OkHttpClient();
        // 2.采用建造者模式和链式调用构建键值对对象
        FormBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .build();
        // 3.采用建造者模式和链式调用构建Request对象
        final Request request = new Request.Builder()
                .url(URLS) // 请求URL
                .post(formBody) // 默认就是get请求，可以不写
                .build();
        // 4.通过1和3产生的Client和Request对象生成Call对象
        Call call = client.newCall(request);
        // 5.调用Call对象的enqueue()方法，并且实现一个回调实现类
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "发送post请求键值对失败！");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "发送post请求键值对成功！请求到的信息为：" + response.body().string());
            }
        });
    }


    //get同步请求
    private void getBySynchronized() {
        // 1.构建Client对象
        OkHttpClient client = new OkHttpClient();
        // 2.采用建造者模式和链式调用构建Request对象
        final Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();
        // 3.通过1和2产生的Client和Request对象生成Call对象
        final Call call = client.newCall(request);
        // 4.同步发送get请求需要使用execute()方法，并且为了防止主线程阻塞需要放在子线程中运行
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 6.构建response对象
                    Response response = call.execute();
                    Log.d(TAG, "同步发送get请求成功！请求到的信息为：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //get异步请求
    private void getByAsynchronized() {
        // 1.构建Client对象
        OkHttpClient client = new OkHttpClient();
        // 2.采用建造者模式和链式调用构建Request对象
        final Request request = new Request.Builder()
                .url(URL) // 请求URL
                .get() // 默认就是get请求，可以不写
                .build();
        // 3.通过1和2产生的Client和Request对象生成Call对象
        Call call = client.newCall(request);
        // 4.调用Call对象的enqueue()方法，并且实现一个回调实现类
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "异步发送get请求失败！");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "异步发送get请求成功！请求到的信息为：" + response.body().string());
            }
        });
    }

    //post同步请求
    private void postBySynchronized() {
        // 1.构建Client对象
        OkHttpClient client = new OkHttpClient();
        // 2.采用建造者模式和链式调用构建键值对对象
        FormBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .build();
        // 3.采用建造者模式和链式调用构建Request对象
        final Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();
        // 4.通过1和3产生的Client和Request对象生成Call对象
        final Call call = client.newCall(request);
        // 5.同步发送post请求需要使用execute()方法，并且为了防止主线程阻塞需要放在子线程中自行
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 6.构建response对象
                    Response response = call.execute();
                    Log.d(TAG, "同步发送post请求成功！请求到的信息为：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //post异步请求
    private void postByAsynchronized() {
        // 1.构建Client对象
        OkHttpClient client = new OkHttpClient();
        // 2.采用建造者模式和链式调用构建键值对对象
        FormBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .build();
        // 3.采用建造者模式和链式调用构建Request对象
        final Request request = new Request.Builder()
                .url(URL) // 请求URL
                .post(formBody) // 默认就是get请求，可以不写
                .build();
        // 4.通过1和3产生的Client和Request对象生成Call对象
        Call call = client.newCall(request);
        // 5.调用Call对象的enqueue()方法，并且实现一个回调实现类
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "异步发送post请求失败！");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "异步发送post请求成功！请求到的信息为：" + response.body().string());
            }
        });
    }


}
