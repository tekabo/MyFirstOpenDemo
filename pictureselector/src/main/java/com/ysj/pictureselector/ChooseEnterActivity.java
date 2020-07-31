package com.ysj.pictureselector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseEnterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_activity,btn_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_enter);
        btn_activity = findViewById(R.id.btn_activity);
        btn_fragment = findViewById(R.id.btn_fragment);
        btn_activity.setOnClickListener(this);
        btn_fragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_activity) {
            startActivity(new Intent(ChooseEnterActivity.this, ChooseEnterActivity.class));
        } else if (id == R.id.btn_fragment) {

        }
    }
}
