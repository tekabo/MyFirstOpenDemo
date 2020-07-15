package com.ysj.myfirstopendemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.ysj.dialoglibrary.ProtocolDialog;
import com.ysj.dialoglibrary.WebUrlActivity;
import com.ysj.listrefreshlibrary.RefreshActivity;
import com.ysj.mergeadapterlibrary.TestMergeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在onCreate方法中添加MyObserver，那么MyObserver就可以观察到MainActivity的各个生命周期的变化
 * LifecycleOwner是一个接口，其内部只有一个方法getLifecycle()，getLifecycle方法用于获取Lifecycle
 * 默认实现了LifecycleOwner接口,MainActivity是被观察者
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //当Lifecycle的生命周期发生变化时，MyObserver就会观察到，或者说是感知到。MyObserver成为了一个Lifecycle的观察者
        getLifecycle().addObserver(new MyObserver());
    }

    @OnClick({R.id.test_dialog_library,R.id.test_merge_library,R.id.test_lifecycle,R.id.test_call,R.id.refresh_list,
            R.id.test_okhttp,R.id.test_retrofit,R.id.test_litepal,R.id.test_greendao,R.id.test_Dagger})
    public void onMyClickView(View view){
        switch (view.getId()){
            case R.id.test_dialog_library:
                //测试DialogLibrary
                showDialog();
                break;
            case R.id.test_merge_library:
                //测试MergeAdapterLibrary
                Intent intent = new Intent(MainActivity.this, TestMergeActivity.class);
                startActivity(intent);
                break;
            case R.id.test_lifecycle:
                //测试生命周期
                LifeCycleTestActivity.startMyActivity(MainActivity.this);
                break;
            case R.id.test_call:
                //测试打电话不同android版本权限
                //                callPhone();//Android6.0以前拨打电话
                newCall();//Android6.0以前拨打电话
                break;
            case R.id.refresh_list:
                //测试上拉刷新下拉加载
                RefreshActivity.startMyActivity(MainActivity.this);
                break;
            case R.id.test_okhttp:
                //测试okhttp
                OkhttpActivity.startMyActivity(MainActivity.this);
                break;

            case R.id.test_retrofit:
                //测试retrofit
                RetrofitTestActivity.startMyActivity(MainActivity.this);
                break;
            case R.id.test_litepal:
                //测试Litepal
                LitepalTestActivity.startMyActivity(MainActivity.this);
                break;
            case R.id.test_greendao:
                //测试greendao
                GreenDaoTestActivity.startMyActivity(MainActivity.this);
                break;
            case R.id.test_Dagger:
                DaggerTestActivity.startMyActivity(MainActivity.this);
                break;
        }
    }



    private void newCall() {
        //检测是否具有权限
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授予权限，去申请
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1000);
        } else {
            callPhone();
        }

    }

    private void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:1008611"));
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            //检测用户授权结果
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showDialog() {
        final ProtocolDialog protocolDialog = new ProtocolDialog(MainActivity.this);
        protocolDialog.show();

        protocolDialog.setOnBtnClickListener(new ProtocolDialog.BtnClickListener() {
            @Override
            public void theTermsOfServiceClick() {
                WebUrlActivity.start(MainActivity.this, "https://dova.me/privacy.html", "服务条款");
            }

            @Override
            public void privacyPolicyClick() {
                WebUrlActivity.start(MainActivity.this, "https://dova.me/privacy.html", "隐私协议");
            }

            @Override
            public void agreeBtnClick() {
                Toast.makeText(MainActivity.this, "跳转登录页", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void exitBtnClick() {
                protocolDialog.dismiss();
                System.exit(0);
            }
        });
    }


    public class MyObserver implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStarts() {
            Log.e(TAG, "Lifecycle call onStart");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResumes() {
            Log.e(TAG, "Lifecycle call onResume");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPauses() {
            Log.e(TAG, "Lifecycle call onPause");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }
}
