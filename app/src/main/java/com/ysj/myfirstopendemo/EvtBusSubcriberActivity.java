package com.ysj.myfirstopendemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvtBusSubcriberActivity extends AppCompatActivity {
    public static final String TAG = "EvBusPublisherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_subcriber);
        ButterKnife.bind(this);
        //订阅者-Subscriber需要绑定Eventbus
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //订阅者-Subcriber需要解绑EventBus
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(MyEventMessage message) {
        Log.e(TAG,"收到消息啦！" + "消息的类型为：" + message.getType() + "消息的内容为：" + message.getMessage());
        Toast.makeText(this,"succ",Toast.LENGTH_SHORT).show();
    };
}
