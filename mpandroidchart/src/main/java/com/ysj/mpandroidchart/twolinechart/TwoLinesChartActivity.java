package com.ysj.mpandroidchart.twolinechart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.ysj.mpandroidchart.R;

import java.util.List;

public class TwoLinesChartActivity extends AppCompatActivity {
    private LineChart lineChart1;
    private LineChartManager lineChartManager1;

    private List<IncomeBeans> incomeBeans;
    private List<CompositeIndexBean> compositeIndexBeans;
    private LineChartBean lineChartBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_lines_chart);
        initData();
        initView();


    }


    private void initView() {
        lineChart1 = findViewById(R.id.two_lines_chart);
        lineChartManager1 = new LineChartManager(lineChart1);
        //展示图表
        lineChartManager1.showLineChart(compositeIndexBeans, "事件" ,getResources().getColor(R.color.blue));
        lineChartManager1.addLine(incomeBeans, "办结", getResources().getColor(R.color.green));
       // lineChartManager1.addLine(incomeBeans, "办结", R.drawable.fade_green);
        lineChartManager1.setMarkerView(this);

        //设置曲线填充色 以及 MarkerView
//        Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
//        lineChartManager1.setChartFillDrawable(drawable);

    }

    private void initData() {
        //获取数据
        lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(this,"line_chart.json", LineChartBean.class);

        incomeBeans = lineChartBean.getGRID0().getResult().getIncomeBeans();
        compositeIndexBeans = lineChartBean.getGRID0().getResult().getCompositeIndex1();
    }
}
