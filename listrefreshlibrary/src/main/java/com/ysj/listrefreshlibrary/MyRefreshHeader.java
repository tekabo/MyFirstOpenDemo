package com.ysj.listrefreshlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

/**
 * Created by tekabo
 * Created on 2020/7/9
 * PackageName com.ysj.listrefreshlibrary
 */
public class MyRefreshHeader extends LinearLayout implements RefreshHeader {
    LottieAnimationView mAnimationView;
    TextView tv_success;
    TextView tv_fail;

    public MyRefreshHeader(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.loading_lottie,this);
        mAnimationView = view.findViewById(R.id.loading_lottie);
        tv_success = view.findViewById(R.id.tv_success);
        tv_fail = view.findViewById(R.id.tv_fail);
    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.FixedBehind;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        if(percent<0.03){
            tv_success.setVisibility(GONE);
            tv_fail.setVisibility(GONE);
            mAnimationView.setVisibility(VISIBLE);
        }

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        if(!mAnimationView.isAnimating()){
            mAnimationView.playAnimation();
        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        mAnimationView.setVisibility(GONE);
        if(success){
            tv_success.setVisibility(VISIBLE);
        }else{
            tv_fail.setVisibility(VISIBLE);
        }
        if(!mAnimationView.isAnimating()){
            mAnimationView.cancelAnimation();
        }

        return 1000;  //延迟1000毫秒之后再弹回
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }

    public TextView getSuccessTextView(){
        return tv_success;
    }
}
