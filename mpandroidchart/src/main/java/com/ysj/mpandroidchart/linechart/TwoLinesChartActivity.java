package com.ysj.mpandroidchart.linechart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.ysj.mpandroidchart.R;

import java.util.List;

public class TwoLinesChartActivity extends AppCompatActivity {
    private LineChart lineChart1;
    private LineChartManager lineChartManager1;
    private List<IncomeBeans> incomeBeanList;
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
        lineChartManager1.showLineChart(incomeBeanList, "我的收益", getResources().getColor(R.color.blue));
        lineChartManager1.addLine(compositeIndexBeans, "上证指数", getResources().getColor(R.color.green));

    }

    private void initData() {
        //获取数据
        lineChartBean = LocalJsonAnalyzeUtil.JsonToObject(this,"line_chart.json", LineChartBean.class);
        incomeBeanList = lineChartBean.getGRID0().getResult().getIncomeBeans();
        compositeIndexBeans = lineChartBean.getGRID0().getResult().getCompositeIndex1();
    }
}
