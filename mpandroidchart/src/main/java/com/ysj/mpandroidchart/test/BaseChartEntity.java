package com.ysj.mpandroidchart.test;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;

import java.util.List;

/**
 * Created by tekabo
 * Created on 2020/8/12
 * PackageName com.ysj.mpandroidchart.test
 */
public abstract class BaseChartEntity <T extends Entry>{
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
        Legend l = mChart.getLegend();
        l.setForm(line);
        l.setTextColor(mValueColor);
        l.setTextSize(mTextSize);
        updateLegendOrientation(Legend.LegendVerticalAlignment.BOTTOM,Legend.LegendHorizontalAlignment.RIGHT,
                Legend.LegendOrientation.HORIZONTAL);
    }

    /**
     * 图例说明
     * @param vertical 垂直方向位置 默认底部
     * @param horizontal 水平方向位置 默认右边
     * @param orientation 显示方向 默认水平展示
     */
    private void updateLegendOrientation(Legend.LegendVerticalAlignment vertical, Legend.LegendHorizontalAlignment horizontal,
                                         Legend.LegendOrientation orientation) {
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(vertical);
        l.setHorizontalAlignment(horizontal);
        l.setOrientation(orientation);
        l.setDrawInside(false);



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
        leftAxis.setSpaceTop(0);    //Y轴坐标距顶有多少距离，即留白
        leftAxis.setSpaceBottom(0);    //Y轴坐标距底有多少距离，即留白
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

        xAxis.setGridColor(Color.rgb(145, 13, 64)); //X轴上的刻度竖线的颜色
        xAxis.setGridLineWidth(1); //X轴上的刻度竖线的宽 float类型
        xAxis.enableGridDashedLine(40, 3, 0); //虚线表示X轴上的刻度竖线(float lineLength, float spaceLength, float phase)三个参数，1.线长，2.虚线间距，3.虚线开始坐标

        xAxis.setDrawAxisLine(true);//是否绘制坐标轴的线，即含有坐标的那条线，默认是true
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
        mChart.setTouchEnabled(true);// 设置是否可以触摸
        mChart.setDragDecelerationFrictionCoef(0.9f);//// 设置滑动减速摩擦系数
        mChart.setDragEnabled(true);// 是否可以拖拽
        mChart.setScaleXEnabled(false);//是否可以缩放 仅x轴
        mChart.setPinchZoom(false); //设置x轴和y轴能否同时缩放。默认是否
        mChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）

        mChart.setDrawGridBackground(false);
        mChart.setVisibleXRangeMaximum(6);//范围最大值
        mChart.setScaleYEnabled(false);//是否可以缩放 仅Y轴
        mChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true

    }

    /**
     * 设置折线数据,抽象方法
     */
    protected abstract void setChartData();

    /**
     * 图标value显示开关
     */
    public void toggleChartValue(){
        List<BaseDataSet> sets = mChart.getData().getDataSets();
        for(BaseDataSet iSet:sets){
            iSet.setDrawValues(!iSet.isDrawValuesEnabled());
        }
        mChart.invalidate();
    }


    public void setMarkView(MarkerView markView){
        mChart.setMarker(markView);
        mChart.invalidate();
    }

    /**
     * x/ylabel显示样式
     * @param xValueFormatter x
     * @param leftValueFormatter y
     */
    public void setAxisFormatter(IAxisValueFormatter xValueFormatter,IAxisValueFormatter leftValueFormatter){
        mChart.getXAxis().setValueFormatter(xValueFormatter);
        mChart.getAxisLeft().setValueFormatter(leftValueFormatter);
        mChart.invalidate();
    }

    /**
     * value显示格式设置
     * @param valueFormatter
     */
    public void setDataValueFormatter(IValueFormatter valueFormatter){
        mChart.getData().setValueFormatter(valueFormatter);
    }

}
