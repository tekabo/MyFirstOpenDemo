package com.ysj.mpandroidchart.test;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;

import java.util.List;

/**
 * Created by tekabo
 * Created on 2020/8/12
 * PackageName com.ysj.mpandroidchart.test
 */
public class BaseChartEntity <T extends Entry>{
    protected BarLineChartBase mChart;
    protected List<T>[] mEntries;
    protected String[] lables;
    protected int[] mChartColors;
    protected float mTextSize;
    protected int mValueColor;

    public BaseChartEntity(BarLineChartBase mChart, List<T>[] mEntries, String[] lables, int[] mChartColors, float mTextSize, int mValueColor) {
        this.mChart = mChart;
        this.mEntries = mEntries;
        this.lables = lables;
        this.mChartColors = mChartColors;
        this.mTextSize = mTextSize;
        this.mValueColor = mValueColor;
    }

    /**
     * 初始化chart
     */
    protected void initChart(){
        initProperties();
        setChartData();
        initLend(Legend.LegendForm.LINE,mTextSize,mValueColor);
        initXAxis(mValueColor,mTextSize);
        initLeftAxis(mValueColor,mTextSize);
    }

    /**
     * 图例设置
     * @param line
     * @param mTextSize
     * @param mValueColor
     */
    private void initLend(Legend.LegendForm line, float mTextSize, int mValueColor) {
    }

    /**
     * 初始化Y轴
     * @param mValueColor
     * @param mTextSize
     */
    private void initLeftAxis(int mValueColor, float mTextSize) {
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(mValueColor);
        leftAxis.setTextSize(mTextSize);
        float yMax = mChart.getData().getYMax()==0? 100f:mChart.getData().getYMax();
        leftAxis.setAxisMaximum(yMax+yMax*0.007f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularityEnabled(false);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setLabelCount(6);
        leftAxis.setAxisLineWidth(1f);
        leftAxis.setAxisLineColor(mValueColor);

        mChart.getAxisRight().setEnabled(false);
    }

    /**
     * 初始化X轴
     * @param mValueColor
     * @param mTextSize
     */
    private void initXAxis(int mValueColor, float mTextSize) {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(mTextSize);
        xAxis.setTextColor(mValueColor);
        xAxis.setAxisMinimum(0);

        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineWidth(1f);
        xAxis.setAxisLineColor(mValueColor);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(8);

        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisMinimum(mChart.getData().getXMin());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }


    /**
     * 初始化属性信息
     */
    private void initProperties() {
        mChart.setNoDataText("");
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);//// 设置滑动减速摩擦系数
        mChart.setDragEnabled(true);
        mChart.setScaleXEnabled(false);

        mChart.setDrawGridBackground(false);
    }

    /**
     * 设置折线数据
     */
    private void setChartData() {
    }

}
