package com.ysj.listrefreshlibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by tekabo
 * Created on 2020/7/8
 * PackageName com.ysj.listrefreshlibrary
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    private List<NewsInfoBean> list;
    private MyClickListener myClickListener;

    public  void setMyClickListener(MyClickListener  myClickListener){
        this.myClickListener = myClickListener;
    }

    public NewsAdapter(List<NewsInfoBean> lists){
        this.list = lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //绑定子视图
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        NewsInfoBean newsInfoBean = list.get(position);
        holder.desc.setText(newsInfoBean.desc);
        holder.time.setText(newsInfoBean.time);
        holder.title.setText(newsInfoBean.title);
        holder.type.setText(newsInfoBean.type);

        TextView textView = holder.itemView.findViewById(R.id.tv_desc);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.setTextClickListener(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.setOnClickListener(position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myClickListener.setOnLongClickListener(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //定义视图管理器
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView desc;
        TextView time;
        TextView title;
        TextView type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.tv_desc);
            time = itemView.findViewById(R.id.news_time);
            title = itemView.findViewById(R.id.tv_title);
            type = itemView.findViewById(R.id.tv_type);
        }
    }
    //事件监听
    public interface MyClickListener{
        void setOnClickListener(int i);
        void setOnLongClickListener(int i);
        void setTextClickListener(int i);
    }
}
