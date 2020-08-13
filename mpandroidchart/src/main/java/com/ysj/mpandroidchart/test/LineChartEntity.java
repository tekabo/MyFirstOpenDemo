package com.ysj.mpandroidchart.test;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;

/**
 * Created by tekabo
 * Created on 2020/8/13
 * PackageName com.ysj.mpandroidchart.test
 */
public class LineChartEntity extends BaseChartEntity<Entry>{
    public LineChartEntity(BarLineChartBase mChart, List<Entry>[] mEntries, String[] lables, int[] mChartColors, float mTextSize, int mValueColor) {
        super(mChart, mEntries, lables, mChartColors, mTextSize, mValueColor);
    }

    @Override
    protected void setChartData() {
        LineDataSet[] lineDataSets = new LineDataSet[mEntries.length];
        if(mChart.getData()!=null&&mChart.getData().getDataSetCount()==mEntries.length){
            for(int index=0,len=mEntries.length;index<len;index++){
                List<Entry> list = mEntries[index];
                lineDataSets[index] = (LineDataSet) mChart.getData().getDataSetByIndex(index);
                lineDataSets[index].setValues(list);
            }
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        }else{
            for(int index=0,len= mEntries.length;index<len;index++){
                lineDataSets[index] = new LineDataSet(mEntries[index],lables[index]);
                lineDataSets[index].setAxisDependency(YAxis.AxisDependency.LEFT);
                lineDataSets[index].setColor(mChartColors[index]);
                lineDataSets[index].setLineWidth(1.5f);
                lineDataSets[index].setCircleRadius(3.5f);
                lineDataSets[index].setCircleColor(mChartColors[index]);
                lineDataSets[index].setFillAlpha(25);
                //lineDataSets[index].enableDashedLine(10f,15f,0f);//虚线
                //lineDataSets[index].enableDashedHighlightLine(10f,15f,0f);
                lineDataSets[index].setDrawCircleHole(false);
                lineDataSets[index].setValueTextColor(mChartColors[index]);

                //lineDataSets[index].setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW,200));
            }
            // create a data object with the datasets
            LineData data = new LineData(lineDataSets);
            data.setValueTextSize(mTextSize);
            // set data
            mChart.setData(data);
            mChart.animateX(2000, Easing.EasingOption.EaseInOutQuad);

        }
    }

    @Override
    protected void initChart() {
        super.initChart();
        mChart.getAxisLeft().setDrawGridLines(true);
        mChart.getAxisLeft().enableGridDashedLine(10f,15f,0);//虚线
        mChart.getAxisLeft().setGridLineWidth(0.5f);
        mChart.getAxisLeft().setGridColor(Color.parseColor("#f5f5f5"));
        mChart.getAxisLeft().setDrawZeroLine(false);
        mChart.getAxisLeft().setZeroLineWidth(0f);
        mChart.getAxisLeft().setDrawAxisLine(false);

        mChart.getAxisRight().setDrawZeroLine(false);
        mChart.getAxisRight().setZeroLineWidth(0f);

    }

    /**
     * 填充曲线以下区域
     * @param drawables
     * @param filledColor
     * @param fill
     */
    public void toggleFilled(Drawable[] drawables,int[] filledColor,boolean fill){
        List<ILineDataSet> sets = mChart.getData().getDataSets();
        for(int index=0,len=sets.size();index<len;index++){
            LineDataSet set = (LineDataSet) sets.get(index);
            if(drawables!=null){
                set.setFillDrawable(drawables[index]);
            }else if(filledColor!=null){
                set.setFillColor(filledColor[index]);
            }
            set.setDrawFilled(fill);
        }
        mChart.invalidate();
    }

    /**
     * 绘制曲线上的点
     * @param draw
     */

    public void drawableCircle(boolean draw){
        List<ILineDataSet> sets = ((LineChart)mChart).getData().getDataSets();
        for(ILineDataSet iSet:sets){
            LineDataSet set = (LineDataSet) iSet;
            set.setDrawCircles(draw);
        }
        mChart.invalidate();
    }

    /**
     * 设置图标颜色值
     * @param mode
     */
    public void setLineMode(LineDataSet.Mode mode){
        List<ILineDataSet> sets = ((LineChart)mChart).getData().getDataSets();
        for(ILineDataSet iSet:sets){
            LineDataSet set = (LineDataSet) iSet;
            set.setMode(mode);
        }
        mChart.invalidate();
    }

    /**
     * 设置虚线展示与否
     * @param enableDashedLine
     */

    public void setEnableDashedLine(boolean enableDashedLine){
        List<ILineDataSet> sets = ((LineChart)mChart).getData().getDataSets();
        for(ILineDataSet iSet:sets){
            LineDataSet set = (LineDataSet) iSet;
            if(enableDashedLine){
                set.disableDashedLine();
            }else{
                set.setFormLineWidth(1f);
                set.setFormLineDashEffect(new DashPathEffect(new float[]{10f,5f},0f));
                set.setFormSize(15f);

                set.enableDashedLine(10f,5f,0f);
                set.enableDashedHighlightLine(10f,5f,0f);
            }
        }
        mChart.invalidate();
    }

    /**设置x缩放的最小最大值*/
    public void setMinMaxScaleX(float minScaleX,float maxScaleX){
        mChart.getViewPortHandler().setMinMaxScaleX(minScaleX,maxScaleX);
    }

    public void updateLineData(LineChart mChart){
        List<ILineDataSet> sets = mChart.getData().getDataSets();
        for(ILineDataSet iSet:sets){
            LineDataSet set = (LineDataSet) iSet;
            set.setFillAlpha(255);
            set.setDrawCircleHole(true);
        }
        mChart.getAxisLeft().setDrawGridLines(true);
        mChart.getAxisLeft().setGridLineWidth(0.5f);
        mChart.getAxisLeft().enableGridDashedLine(10f,0f,0f);
        mChart.getXAxis().setDrawGridLines(true);
        mChart.getXAxis().enableGridDashedLine(10f,0f,0f);
        mChart.getXAxis().setGridLineWidth(0.5f);
        mChart.getXAxis().setGridColor(Color.parseColor("#f5f5f5"));
        mChart.invalidate();
    }
}
