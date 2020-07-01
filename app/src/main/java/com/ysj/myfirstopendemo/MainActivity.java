package com.ysj.myfirstopendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ysj.dialoglibrary.ProtocolDialog;
import com.ysj.dialoglibrary.WebUrlActivity;
import com.ysj.mergeadapterlibrary.TestMergeActivity;

public class MainActivity extends AppCompatActivity {
    private Button testDialog;
    private Button testMerge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDialog();
        testDialog = findViewById(R.id.test_dialog_library);
        testMerge = findViewById(R.id.test_merge_library);

        //测试DialogLibrary
        testDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
