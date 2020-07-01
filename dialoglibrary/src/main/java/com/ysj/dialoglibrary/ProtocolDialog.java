package com.ysj.dialoglibrary;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * Created by tekabo
 * Created on 2020/7/1
 * PackageName com.ysj.dialoglibrary
 * 协议专用：弹框提示需同意协议，支持点击查看协议内容
 */
public class ProtocolDialog extends AlertDialog {
    private Activity mActivity;

    public ProtocolDialog(Activity activity) {
        super(activity,R.style.AppTheme_Transparent);
        this.mActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_protocol, null);
        setContentView(view);

        // 外部点击取消
        setCanceledOnTouchOutside(false);

        //设置弹窗体大小
        Window window = getWindow();

        WindowManager.LayoutParams params = window.getAttributes();
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        // 将window的宽高信息保存在point中
        // 将设置后的大小赋值给window的宽高
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (display.getWidth() * 0.80); // 宽度设置为屏幕的0.95
        p.height = (int) (display.getHeight() * 0.45); // 高度设置为屏幕的0.6
        getWindow().setAttributes(p);
        window.setAttributes(params);

        initMyView();//涉及按钮可点击事件

    }

    private void initMyView() {
        //同意
        TextView confirm = findViewById(R.id.confirm_protocol);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnClickListener.agreeBtnClick();
            }
        });
        //不同意
        TextView exit = findViewById(R.id.exit_protocol);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnClickListener.exitBtnClick();
            }
        });
        //服务条款、隐私协议
        TextView protxt = findViewById(R.id.pro_tv);
        final SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append("在您使用哈哈哈App前，请您认真阅读并了解《服务条款》和《隐私政策》,点击“同意”即代表您和您的监护人已阅读并同意全部条款。");
       //设置部分可点击
        ClickableSpan clickTermsOfService = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if(mBtnClickListener!=null){
                    mBtnClickListener.theTermsOfServiceClick();
                }
            }
        };
        ClickableSpan clickPrivacy = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                if(mBtnClickListener!=null){
                    mBtnClickListener.privacyPolicyClick();
                }
            }
        };
        stringBuilder.setSpan(clickTermsOfService,22,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(clickPrivacy,29,33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        //设置部分文字颜色
        ForegroundColorSpan colorTermsOfService = new ForegroundColorSpan(Color.parseColor("#6495ED"));
        ForegroundColorSpan colorPrivacy = new ForegroundColorSpan(Color.parseColor("#6495ED"));

        stringBuilder.setSpan(colorTermsOfService,22,26,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(colorPrivacy,29,33,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //配置给TextView

        protxt.setMovementMethod(LinkMovementMethod.getInstance());
        protxt.setText(stringBuilder);
    }

    public interface BtnClickListener{
        void theTermsOfServiceClick();//服务条款

        void privacyPolicyClick();//隐私协议

        void agreeBtnClick();//同意

        void exitBtnClick();//退出
    }

    private BtnClickListener mBtnClickListener;

    public void setOnBtnClickListener(BtnClickListener btnClickListener){
        this.mBtnClickListener = btnClickListener;
    }
}
