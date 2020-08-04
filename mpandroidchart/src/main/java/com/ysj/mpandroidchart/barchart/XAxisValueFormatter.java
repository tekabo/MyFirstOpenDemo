package com.ysj.mpandroidchart.barchart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by tekabo
 * Created on 2020/8/4
 * PackageName com.ysj.mpandroidchart.barchart
 * 自定义适配器类
 */
public class XAxisValueFormatter  implements IAxisValueFormatter {
    private String[] xStrs = new String[]{"咘吖", "兜兜", "秋", "冬"};

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int position = (int)value;
        if (position >= 4) {
            position = 0;
        }
        return xStrs[position];
    }
}
