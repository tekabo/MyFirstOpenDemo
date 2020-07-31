package com.ysj.myfirstopendemo.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tekabo
 * Created on 2020/7/31
 * PackageName com.ysj.myfirstopendemo.selfview
 */
public class OperCanvasView extends View {
    //宽高
    private int mWidth, mHeight;
    //1、创建画笔
    private Paint mPaint = new Paint();

    public void initPaint() {
        //2、初始化画笔
        mPaint.setColor(Color.RED);//设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);//设置画笔模式为填充
        mPaint.setStrokeWidth(10f);//设置画笔宽度为10px
    }

    // 3、在构造函数中初始化
    public OperCanvasView(Context context, AttributeSet attributes) {
        super(context, attributes);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //一、translate位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动
        //mPaint.setColor(Color.BLACK);
        //canvas.drawCircle(0, 0, 100, mPaint);
        //在坐标原点绘制一个蓝色圆形
        //mPaint.setColor(Color.BLUE);
        //canvas.translate(100, 200);
        //canvas.drawCircle(0, 0, 100, mPaint);
        //在坐标原点绘制一个黄色圆形
        //mPaint.setColor(Color.YELLOW);
        //canvas.translate(100, 200);
        //canvas.drawCircle(0, 0, 50, mPaint);

        //将坐标原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);
        //RectF rect = new RectF(0, -400, 400, 0);//矩形区域
        //mPaint.setColor(Color.BLACK);//绘制黑色矩形
        //canvas.drawRect(rect, mPaint);

        //设置缩放调整后的形状
        //canvas.scale(0.5f, 0.5f, 200, 0);// 画布缩放  <-- 缩放中心向右偏移了200个单位
        //canvas.scale(-0.5f,-0.5f,200,0);// 画布缩放  <-- 缩放中心向右偏移了200个单位,翻转
        //canvas.scale(0.5f,0.5f);//画布缩放
        //canvas.scale(0.5f,0.1f);//画布缩放与上可以叠加,，调用两次缩放则 x轴实际缩放为0.5x0.5=0.25 y轴实际缩放为0.5x0.1=0.05
        //canvas.scale(-0.5f,-0.5f);//画布缩放,当缩放比例为负数的时候会根据缩放中心轴进行翻转
        //mPaint.setColor(Color.YELLOW);//绘制黄色矩形
        //canvas.drawRect(rect, mPaint);

        //多次缩放展示
        RectF rect = new RectF(-400, -400, 400, 400);
        for (int i = 0; i <= 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rect, mPaint);
        }


    }
}
