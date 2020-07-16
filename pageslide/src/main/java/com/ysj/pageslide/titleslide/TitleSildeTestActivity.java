package com.ysj.pageslide.titleslide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ysj.pageslide.R;

public class TitleSildeTestActivity extends AppCompatActivity {
    private View v_titleBg, v_leftTop, tv_titleTop;
    private int mLastTop, maxTitleTopMargin, titleShowYOffset, minTitleTopMargin,
            leftShowYOffset, minLeftTopMargin, topAreaHeight;
    private int totalTitleYOffset;
    private int totalLeftYOffset;
    private View iv_back;

    // 标题栏文字params
    private LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
    );
    //红色方块字params
    private LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintAlpha(0f);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_silde_test);

        v_leftTop = findViewById(R.id.v_left_Top);
        tv_titleTop = findViewById(R.id.tv_titleTop);
        v_titleBg = findViewById(R.id.v_titleBg);
        iv_back = findViewById(R.id.iv_back);

        topAreaHeight = DipUtil.dip2px(this, 140);// 中间区域高度，210 - 70
        maxTitleTopMargin = DipUtil.dip2px(this, 45);// 标题最大上间距
        titleShowYOffset = DipUtil.dip2px(this, 155);// 标题Y轴最小开始偏移量 140 + 15
        minTitleTopMargin = DipUtil.dip2px(this, 11);// 标题最小上间距
        leftShowYOffset = topAreaHeight;// 红色方块Y轴最小开始偏移量
        minLeftTopMargin = DipUtil.dip2px(this, 15);// 红色方块最小上间距
        int titleHeight = DipUtil.dip2px(this, 45);// 标题高度

        leftParams.width = titleHeight - minLeftTopMargin;
        leftParams.height = minLeftTopMargin;

        MyScrollView scrollView = (MyScrollView) findViewById(R.id.my_scroll_view);
        scrollView.setMyScrollListener(new MyScrollView.MyScrollListener() {
            @Override
            public void onMyScollChanged(MyScrollView myScrollView, int l, int t, int oldl, int oldt) {
                // 计算偏移差
                int dy = t - mLastTop;
                // 赋值最新的偏移量
                mLastTop = t;
                // 以图片的高度为基准计算滑动比值
                float progress = (float) (mLastTop) / (float) (topAreaHeight);
                if (progress > 1) {
                    progress = 1;
                } else if (progress < 0) {
                    progress = 0;
                }
                // 根据比值设置背景色
                v_titleBg.setAlpha(progress);
                // 设置返回按钮的状态
                iv_back.setSelected(progress > 0.5f);
                // 计算总的标题开始偏移后的偏移量
                if (mLastTop >= titleShowYOffset) {
                    totalTitleYOffset += dy;
                } else {
                    totalTitleYOffset = 0;
                }
                int titleTopMargin = maxTitleTopMargin - totalTitleYOffset;
                // 判断是否和上次的值是否一样
                if (titleTopMargin != titleParams.topMargin) {
                    titleParams.topMargin = titleTopMargin;
                    titleParams.topMargin = titleParams.topMargin <= minTitleTopMargin
                            ? minTitleTopMargin : titleParams.topMargin;
                    tv_titleTop.setLayoutParams(titleParams);
                    tv_titleTop.requestLayout();
                }
                // 计算总的红色方块开始偏移后的偏移量
                if (mLastTop >= leftShowYOffset) {
                    totalLeftYOffset += dy;
                } else {
                    totalLeftYOffset = 0;
                }
                int leftTopMargin = maxTitleTopMargin - totalLeftYOffset;
                // 判断是否和上次的值是否一样
                if (leftParams.topMargin != leftTopMargin) {
                    leftParams.topMargin = leftTopMargin;
                    leftParams.topMargin = leftParams.topMargin <= minLeftTopMargin
                            ? minLeftTopMargin : leftParams.topMargin;
                    v_leftTop.setLayoutParams(leftParams);
                    v_leftTop.requestLayout();
                }
            }
        });

    }

    public static void startMyActivity(Context context){
        Intent intent = new Intent(context,TitleSildeTestActivity.class);
        context.startActivity(intent);
    }
}
