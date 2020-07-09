package com.ysj.listrefreshlibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tekabo
 * Created on 2020/7/9
 * PackageName com.ysj.listrefreshlibrary
 */
public abstract class BaseListActivity extends AppCompatActivity implements OnRefreshLoadMoreListener, BaseQuickAdapter.OnItemClickListener{
    protected SmartRefreshLayout mSmartRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseQuickAdapter mBaseQuickAdapter;
    protected RefreshMode mRefreshMode = RefreshMode.REFRESH;//默认为只刷新
    protected int mPage = 1;//当前的页码
    protected boolean isRefresh = true;//默认是下拉刷新数据
    private TextView tv_refresh;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeViewInit();
//        initLazyData();
    }

    protected void initLazyData(){
        mSmartRefreshLayout = getSmartRefreshLayout();
        mRecyclerView = getRecyclerView();
        mLayoutManager =getRecyclerLayoutManager();
        mBaseQuickAdapter = getBaseQuickAdapter();
        if (mSmartRefreshLayout != null && mRecyclerView != null && mBaseQuickAdapter != null) {
            //1.设置mSmartRefreshLayout
            MyRefreshHeader myRefreshHeader = new MyRefreshHeader(this);
            mSmartRefreshLayout.setRefreshHeader(myRefreshHeader);
            tv_refresh = myRefreshHeader.getSuccessTextView();
            ClassicsFooter classicsFooter = new ClassicsFooter(this);
            mSmartRefreshLayout.setRefreshFooter(classicsFooter);
            mSmartRefreshLayout.setEnableAutoLoadMore(true);
            mSmartRefreshLayout.setHeaderMaxDragRate(1.5f);
            mSmartRefreshLayout.setHeaderTriggerRate(0.7f);
            //设置mSmartRefreshLayout刷新模式
            mRefreshMode = getRefreshMode() == null ? RefreshMode.NONE : getRefreshMode();
            switch (getRefreshMode()) {
                case BOTH:
                    mSmartRefreshLayout.setEnableRefresh(true);
                    mSmartRefreshLayout.setEnableLoadMore(true);
                    break;
                case REFRESH:
                    mSmartRefreshLayout.setEnableRefresh(true);
                    mSmartRefreshLayout.setEnableLoadMore(false);
                    break;
                case LOAD_MORE:
                    mSmartRefreshLayout.setEnableRefresh(false);
                    mSmartRefreshLayout.setEnableLoadMore(true);
                    break;
                case NONE:
                    mSmartRefreshLayout.setEnableRefresh(false);
                    mSmartRefreshLayout.setEnableLoadMore(false);
                    break;
                default:
                    mSmartRefreshLayout.setEnableRefresh(true);
                    mSmartRefreshLayout.setEnableLoadMore(true);
                    break;
            }

            //2.设置recyclerView
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mBaseQuickAdapter);
            //3.设置监听
            mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);
            mBaseQuickAdapter.setOnItemClickListener(this);

        }

    }
    // 1
    protected abstract void beforeViewInit();

    // 2
    protected abstract SmartRefreshLayout getSmartRefreshLayout();

    // 3
    protected abstract RefreshMode getRefreshMode();

    // 4
    protected abstract RecyclerView getRecyclerView();

    // 5
    protected abstract BaseQuickAdapter getBaseQuickAdapter();
    // 6
    protected abstract void getNetData();

    // 7
    protected RecyclerView.LayoutManager getRecyclerLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        isRefresh = false;
        mPage++;
        getNetData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        isRefresh = true;
        mPage = 1;
        getNetData();
    }

    /**
     * @param isSuccess   是否请求成功
     * @param isEmpty     是否空数据
     * @param currentPage 当前页数
     * @param lastPage    最后一页是哪页
     * @param falseData   数据
     * @param <T>
     */

    protected <T> void updateUI(boolean isSuccess, boolean isEmpty, int currentPage, int lastPage, List<T> falseData) {
        //配置加载更多时候没有更多的UI
        //配置请求成功上拉刷新的UI
        //配置请求成功加载更多的UI
        //配置请求失败的UI
        if(isSuccess){ //请求成功
            if(currentPage == lastPage){
                mSmartRefreshLayout.setNoMoreData(true);
            }else{
                mSmartRefreshLayout.setNoMoreData(false);
            }

            if(isRefresh){ //上拉刷新
                if(falseData==null){
                    falseData = new ArrayList<>();
                }
                if(falseData.isEmpty()){
                    tv_refresh.setText("已为您刷新信息");
                }else{
                    tv_refresh.setText("已为您刷新" + falseData.size() + "条信息");
                }

                mSmartRefreshLayout.finishRefresh(true);
                mBaseQuickAdapter.replaceData(falseData);

            }else{
                // 下拉加载
                mSmartRefreshLayout.finishLoadMore(true);
                mBaseQuickAdapter.addData(falseData);
            }

        }else {//请求失败
            if(isRefresh){
                if(!isEmpty){
                    mSmartRefreshLayout.finishRefresh(false);
                }else {
                    mSmartRefreshLayout.finishRefresh(true);
                }
            }else{
                mSmartRefreshLayout.finishLoadMore(false);
            }

            //错误处理
            if(isEmpty){
                if(isRefresh){
                    showEmptyView();
                }
            }else{
                showErrorView();
            }

        }

    }

    protected void showEmptyView() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_empty, null);
        mBaseQuickAdapter.setEmptyView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPage = 1;
                isRefresh = true;
                getNetData();
            }
        });
    }

    protected void showErrorView() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_error, null);
        mBaseQuickAdapter.setEmptyView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPage = 1;
                isRefresh = true;
                getNetData();
            }
        });
    }


}
