package com.ysj.listrefreshlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;


public class RefreshActivity extends BaseListActivity  implements OnRefreshLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    private RecyclerView rv;
    private SmartRefreshLayout srfNews;
    private List<NewsInfoBean> falseData;

    public static void startMyActivity(Context context) {
        Intent intent = new Intent(context, RefreshActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        rv = findViewById(R.id.rv);
        srfNews= findViewById(R.id.srf_news);

        initLazyData();
        getNetData();
    }


    @Override
    protected SmartRefreshLayout getSmartRefreshLayout() {
        return srfNews;
    }


    @Override
    protected RecyclerView getRecyclerView() {
        return rv;
    }

    @Override
    protected RefreshMode getRefreshMode() {
        return RefreshMode.BOTH;
    }


    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        BaseQuickAdapter<NewsInfoBean,BaseViewHolder> adapter = new BaseQuickAdapter<NewsInfoBean, BaseViewHolder>(R.layout.news_item) {
            @Override
            protected void convert(BaseViewHolder helper, NewsInfoBean item) {
                helper.setText(R.id.tv_desc, item.getDesc());
                helper.setText(R.id.tv_title, item.getTitle());
            }
        };

        return adapter;
    }



    private void addFalseData() {
        falseData = new ArrayList<>();
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
        NewsInfoBean newsInfoBean5 = new NewsInfoBean();
        newsInfoBean5.setTime("2020年05月07日 17:57");
        newsInfoBean5.setTitle("天猫端午狂欢节这样过！");
        newsInfoBean5.setDesc("端午粽子来了");
        newsInfoBean5.setType("天猫商城");
        falseData.add(newsInfoBean5);
        NewsInfoBean newsInfoBean6 = new NewsInfoBean();
        newsInfoBean6.setTime("2020年09月12日 10:23");
        newsInfoBean6.setTitle("全场吃货三折起预热中...！");
        newsInfoBean6.setDesc("吃货比赛来了");
        newsInfoBean6.setType("618理想生活");
        falseData.add(newsInfoBean6);
        NewsInfoBean newsInfoBean7 = new NewsInfoBean();
        newsInfoBean7.setTime("2020年8月30日 22:23");
        newsInfoBean7.setTitle("让病毒消灭于天际成真啦！！！");
        newsInfoBean7.setDesc("疫苗研发成功，世界卫生组织提倡免费全球接种。。。");
        newsInfoBean7.setType("全球资讯");
        falseData.add(newsInfoBean7);
        NewsInfoBean newsInfoBean8 = new NewsInfoBean();
        newsInfoBean8.setTime("2020年8月30日 22:23");
        newsInfoBean8.setTitle("让病毒消灭于天际成真啦！！！");
        newsInfoBean8.setDesc("疫苗研发成功，世界卫生组织提倡免费全球接种。。。");
        newsInfoBean8.setType("全球资讯");
        falseData.add(newsInfoBean8);

    }


    @Override
    protected void getNetData() {
        addFalseData();
        updateUI(true,false,1,1,falseData);
    }

    @Override
    protected void beforeViewInit() {

    }




    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
