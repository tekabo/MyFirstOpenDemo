package com.ysj.myfirstopendemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ysj.dialoglibrary.ProtocolDialog;
import com.ysj.dialoglibrary.WebUrlActivity;
import com.ysj.mergeadapterlibrary.TestMergeActivity;

/**
 * 在onCreate方法中添加MyObserver，那么MyObserver就可以观察到MainActivity的各个生命周期的变化
 * LifecycleOwner是一个接口，其内部只有一个方法getLifecycle()，getLifecycle方法用于获取Lifecycle
 * 默认实现了LifecycleOwner接口,MainActivity是被观察者
 */
public class MainActivity extends AppCompatActivity {
    private Button testDialog;
    private Button testMerge;
    private Button testLifeCycle;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        当Lifecycle的生命周期发生变化时，MyObserver就会观察到，或者说是感知到。MyObserver成为了一个Lifecycle的观察者
        getLifecycle().addObserver(new MyObserver());

        testDialog = findViewById(R.id.test_dialog_library);
        testMerge = findViewById(R.id.test_merge_library);
        testLifeCycle = findViewById(R.id.test_lifecycle);

        //测试DialogLibrary
        testDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        //测试MergeAdapterLibrary
        testMerge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestMergeActivity.class);
                startActivity(intent);
            }
        });

        //测试生命周期
        testLifeCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            LifeCycleTestActivity.startMyActivity(MainActivity.this);
            }
        });

    }

    private void showDialog() {
        final ProtocolDialog protocolDialog = new ProtocolDialog(MainActivity.this);
        protocolDialog.show();

        protocolDialog.setOnBtnClickListener(new ProtocolDialog.BtnClickListener() {
            @Override
            public void theTermsOfServiceClick() {
                WebUrlActivity.start(MainActivity.this,"https://dova.me/privacy.html","服务条款");
            }

            @Override
            public void privacyPolicyClick() {
                WebUrlActivity.start(MainActivity.this,"https://dova.me/privacy.html","隐私协议");
            }

            @Override
            public void agreeBtnClick() {
                Toast.makeText(MainActivity.this,"跳转登录页",Toast.LENGTH_SHORT).show();
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
        void onStarts(){
            Log.e(TAG,"Lifecycle call onStart");
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResumes(){
            Log.e(TAG,"Lifecycle call onResume");
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPauses(){
            Log.e(TAG,"Lifecycle call onPause");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }
}
