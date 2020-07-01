package com.ysj.dialoglibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class WebUrlActivity extends AppCompatActivity {
    private MyFixedWebView webView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_url);
        progressBar = findViewById(R.id.progressBar);
        webView = findViewById(R.id.web_view);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
////        设置字体
//        webSettings.setTextZoom(260);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setJavaScriptEnabled(true);
        //加载进度条
        loadProgressBar();
        webView.loadUrl(getIntent().getStringExtra("intent_url"));
    }

    private void loadProgressBar() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                显示进度
                progressBar.setProgress(newProgress);
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    public static void start(Context context, String url, String title) {
        Intent intent = new Intent(context, WebUrlActivity.class);
        intent.putExtra("intent_url", url);
//        intent.putExtra("title", title);
        context.startActivity(intent);
    }
}
