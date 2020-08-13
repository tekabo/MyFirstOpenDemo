package com.ysj.myfirstopendemo.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by tekabo
 * Created on 2020/8/13
 * PackageName com.ysj.myfirstopendemo.selfview
 */
public class OperPicTxtCanvasView extends View {

    // 1.创建Picture
    private Picture mPicture = new Picture();

    // 3、在构造函数中初始化
    public OperPicTxtCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        recording();    // a2调用录制
    }
    // 2.录制内容方法
    private void recording() {
        Canvas canvas = mPicture.beginRecording(500,500);
        //1、创建画笔
         Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);

        //位移
        canvas.translate(250,250);
        //绘制一个圆
        canvas.drawCircle(0,0,100,mPaint);
        mPicture.endRecording();
    }


}
