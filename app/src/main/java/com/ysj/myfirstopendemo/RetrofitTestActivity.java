package com.ysj.myfirstopendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit是一个现在比较火的网络请求框架
 * 通过注解配置网络请求的参数
 * 采用大量的设计模式来简化使用
 *支持同步和异步、支持多种数据的解析,（默认使用Gson），也支持RxJava
 * 性能好，处理速度快，代码简化等优势
 */
public class RetrofitTestActivity extends AppCompatActivity {
    private static final String URL = "https://tcc.taobao.com/cc/json/";
    private static final String TAG = "RetrofitTestActivity";
    private static final String LOCALURL = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
    }
    public static void startMyActivity(Context context) {
        Intent intent = new Intent(context, RetrofitTestActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.btn_get)
    public void clickView(View view){
        switch (view.getId()){
            case R.id.btn_get:
                getByRetrogit();
                break;
        }
    }

    private void getByRetrogit() {
        // 1.使用建造者模式创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();
        // 2.使用Retrofit对象调用create()创建服务实例（BookService）
        TestService testService = retrofit.create(TestService.class);
        // 3.通过服务实例创建Call对象
        Call<ResponseBody> call = testService.getInfoByHttp("15878896543");
        // 4.通过Call对象构建网络请求（异步）
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String output = response.body().string();
                    Log.i(TAG,"GET请求发送成功！获取到的信息为：" + output);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG,"GET请求发送失败");
            }
        });

    }
}

interface TestService{
    /**
     /**
     * 通过拼接网络参数的方式提交GET请求（下面的参数为https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=15878896543）
     * 注意：这种方式为http的参数传递方式，若使用这种方式请求，需要在注解上使用@Query而非@Path
     * @param tel
     * @return
     */
    @GET("mobile_tel_segment.htm")
    Call<ResponseBody> getInfoByHttp(@Query("tel") String tel);

}
