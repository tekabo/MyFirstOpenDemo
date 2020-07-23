package com.ysj.myfirstopendemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvtBusPublisherActivity extends AppCompatActivity {
    public static final String TAG = "EventBusSucriberActivit";
    @BindView(R.id.btn_next_activity)
    Button btnNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test);
        ButterKnife.bind(this);



        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //声明发布者——Publisher
                Intent intent = new Intent(EvtBusPublisherActivity.this, EvtBusSubcriberActivity.class);
                startActivity(intent);
                MyEventMessage myEventMessage = new MyEventMessage("测试消息","你好呀！");
                EventBus.getDefault().postSticky(myEventMessage);

            }
        });
    }





    public static void startMyActivity(Context context) {
        Intent intent = new Intent(context, EvtBusPublisherActivity.class);
        context.startActivity(intent);

    }





}

