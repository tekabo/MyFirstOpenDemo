package com.ysj.myfirstopendemo.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by tekabo
 * Created on 2020/7/30
 * PackageName com.ysj.myfirstopendemo
 */
public class MySelfView extends View {
    //1、创建画笔
   private Paint mPaint = new Paint();

    // 3、在构造函数中初始化
    public MySelfView(Context context, AttributeSet attributes) {
        super(context,attributes);
        initPaint();
    }

    public void initPaint() {
        //2、初始化画笔
        mPaint.setColor(Color.RED);//设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置画笔模式为填充
        mPaint.setStrokeWidth(10f);//设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制点
        canvas.drawPoint(100, 100, mPaint);     //在坐标(200,200)位置绘制一个点
        canvas.drawPoints(new float[]{          //绘制一组点，坐标位置由float数组指定
                200,200,
                300,200,
                400,200,
                500,200,
                400,300,
                370,400,
                370,500,
                370,600

        },mPaint);

        //绘制线
        canvas.drawLine(500,200,400,300,mPaint);// 在坐标(500,200)(400,300)之间绘制一条直线
        canvas.drawLines(new float[]{    // 绘制一组线 每四数字(两个点的坐标)确定一条线
                370,400,370,500,
                370,500,370,600
        },mPaint);

        //绘制矩形,采用左上角和右下角的两个点的坐标
        //第一种画矩形
        canvas.drawRect(500,300,600,400,mPaint);
        //第二种画矩形,Rect是int(整形)的
        Rect rect = new Rect(610,410,700,500);
        canvas.drawRect(rect,mPaint);
        //第三种画矩形,RectF是float(单精度浮点型)的
        RectF rectF = new RectF(700,500,750,600);
        canvas.drawRect(rectF,mPaint);

        //绘制圆角矩形
        //圆角矩形绘制-第一种
        RectF rectF1 = new RectF(100,700,800,1000);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(rectF,mPaint);
        mPaint.setColor(Color.GREEN);
        //实际上在rx为宽度的一半，ry为高度的一半时，刚好是一个椭圆
        canvas.drawRoundRect(rectF1,350,150,mPaint);//两个参数实际上是椭圆的两个半径,drawRoundRect对大于该数值的参数进行了限制(修正)，凡是大于一半的参数均按照一半来处理
        //圆角矩形绘制-第二种
        mPaint.setColor(Color.GRAY);
        canvas.drawRoundRect(700,100,900,200,50,30,mPaint);


        //绘制椭圆
        //椭圆绘制第一种
        RectF rectF2 = new RectF(100,1310,500,1400);
        canvas.drawOval(rectF2,mPaint);
        //椭圆绘制第二种(长宽相等即是圆形)
        canvas.drawOval(100,1100,300,1300,mPaint);

        //绘制圆形
        canvas.drawCircle(800,1400,200,mPaint);// 绘制一个圆心坐标在(800,1400)，半径为200 的圆。

        //绘制圆弧
        RectF rectF3 = new RectF(20,210,220,310);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(rectF3,mPaint);//绘制背景矩形
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF3,0,90,false,mPaint);//绘制圆弧

        RectF rectF4 = new RectF(-100,-100,100,100);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF4,mPaint);//绘制背景矩形
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF4,0,90,true,mPaint);

        RectF rectF5 = new RectF(20,450,120,550);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF5,mPaint);//绘制背景矩形
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF5,0,90,false,mPaint);

        RectF rectF6 = new RectF(130,450,330,650);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF6,mPaint);//绘制背景矩形
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF6,-90,130,true,mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF6,40,90,true,mPaint);//startAngle开始角度，sweepAngle旋转角度
        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF6,130,60,true,mPaint);
        mPaint.setColor(Color.DKGRAY);
        canvas.drawArc(rectF6,190,50,true,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF6,240,30,true,mPaint);
    }
}
