package com.ysj.mpandroidchart.barchart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by tekabo
 * Created on 2020/8/4
 * PackageName com.ysj.mpandroidchart.barchart
 */
public class DecimalFormatter implements IAxisValueFormatter {
    private DecimalFormat format;

    public DecimalFormatter() {
        format = new DecimalFormat("###,###,##0.00");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return format.format(value) + "$";
    }

}
