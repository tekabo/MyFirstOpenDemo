package com.ysj.myfirstopendemo.selfview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ysj.myfirstopendemo.R;

import java.util.ArrayList;

public class MyselfPieActivity extends AppCompatActivity {
    private ArrayList<PieData> mData= new ArrayList<>();

    public static void startMyActivity(Context context) {
        Intent intent = new Intent(context, MyselfPieActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_view);

        PieView pieView = findViewById(R.id.test_pie);


        PieData pieData = new PieData();
        pieData.setAngle(30);
       pieData.setPercentage(10);
       pieData.setName("1号");
       pieData.setValue(30);
       mData.add(pieData);
        PieData pieData1 = new PieData();
        pieData1.setAngle(60);
        pieData1.setPercentage(20);
        pieData1.setName("2号");
        pieData1.setValue(60);
        mData.add(pieData1);
        PieData pieData2 = new PieData();
        pieData2.setAngle(90);
        pieData2.setPercentage(30);
        pieData2.setName("3号");
        pieData2.setValue(120);
        mData.add(pieData2);
        PieData pieData3 = new PieData();
        pieData3.setAngle(120);
        pieData3.setPercentage(120);
        pieData3.setName("4号");
        pieData3.setValue(120);
        mData.add(pieData3);
        pieView.setData(mData);

    }


}
