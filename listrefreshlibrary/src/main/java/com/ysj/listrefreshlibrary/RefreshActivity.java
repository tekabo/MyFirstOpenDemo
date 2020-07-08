package com.ysj.listrefreshlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;


public class RefreshActivity extends AppCompatActivity {

    private RecyclerView rv;
    private SmartRefreshLayout sf;

    private List<NewsInfoBean> falseData;

    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        rv = findViewById(R.id.rv);
        sf = findViewById(R.id.srf_news);
        falseData = new ArrayList<>();
        initData();

        newsAdapter = new NewsAdapter(falseData);
        rv.setAdapter(newsAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        newsAdapter.setMyClickListener(new NewsAdapter.MyClickListener() {
            @Override
            public void setOnClickListener(int i) {
                Toast.makeText(RefreshActivity.this, "点击了第 " + i + "个", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void setOnLongClickListener(int i) {
                Toast.makeText(RefreshActivity.this, "长按了第 " + i + "个", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void setTextClickListener(int i) {
                Toast.makeText(RefreshActivity.this, "点击了第 " + i + "个text", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        NewsInfoBean newsInfoBean1 = new NewsInfoBean();
        newsInfoBean1.setTime("2020年05月07日 17:57");
        newsInfoBean1.setTitle("天猫端午狂欢节这样过！");
        newsInfoBean1.setDesc("端午粽子来了");
        newsInfoBean1.setType("天猫商城");
        falseData.add(newsInfoBean1);
        NewsInfoBean newsInfoBean2 = new NewsInfoBean();
        newsInfoBean2.setTime("2020年09月12日 10:23");
        newsInfoBean2.setTitle("全场吃货三折起预热中...！");
        newsInfoBean2.setDesc("吃货比赛来了");
        newsInfoBean2.setType("618理想生活");
        falseData.add(newsInfoBean2);
        NewsInfoBean newsInfoBean3 = new NewsInfoBean();
        newsInfoBean3.setTime("2020年8月30日 22:23");
        newsInfoBean3.setTitle("让病毒消灭于天际成真啦！！！");
        newsInfoBean3.setDesc("疫苗研发成功，世界卫生组织提倡免费全球接种。。。");
        newsInfoBean3.setType("全球资讯");
        falseData.add(newsInfoBean3);
        NewsInfoBean newsInfoBean4 = new NewsInfoBean();
        newsInfoBean4.setTime("2020年8月30日 22:23");
        newsInfoBean4.setTitle("让病毒消灭于天际成真啦！！！");
        newsInfoBean4.setDesc("疫苗研发成功，世界卫生组织提倡免费全球接种。。。");
        newsInfoBean4.setType("全球资讯");
        falseData.add(newsInfoBean4);

    }

    public static void startMyActivity(Context context) {
        Intent intent = new Intent(context, RefreshActivity.class);
        context.startActivity(intent);
    }


}
