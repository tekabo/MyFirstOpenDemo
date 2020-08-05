package com.ysj.mpandroidchart.piechart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.ysj.mpandroidchart.R;

public class PieChartActivity extends AppCompatActivity {
    private PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        pieChart = findViewById(R.id.my_pie_chart);

        initPieChart();
    }

    /**
     * 初始化饼图控件属性
     */
    private void initPieChart() {

    }
}
