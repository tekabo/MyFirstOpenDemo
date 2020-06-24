package com.ysj.tkbtoastlibrary;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tekabo
 * Created on 2020/6/24
 * PackageName com.ysj.tkbtoastlibrary
 */
public class ToastUtils {
    /**
     * 显示吐司
     */
    public static void showMyToast(Context context, String str){
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
}
