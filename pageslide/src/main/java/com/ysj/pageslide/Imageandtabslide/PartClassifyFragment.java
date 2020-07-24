package com.ysj.pageslide.Imageandtabslide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ysj.pageslide.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

/**
 * Created by tekabo
 * Created on 2020/7/24
 * PackageName com.ysj.pageslide.Imageandtabslide
 */
public class PartClassifyFragment extends Fragment{
    private RecyclerView rvPartClassify;
    private ArrayList<String> datas = new ArrayList<>();
    private CommonAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_part_classify,container,false);
        rvPartClassify = view.findViewById(R.id.rv_part_classify);

       init();
        return view;
    }

    private void init() {
        for(int i=0;i<15;i++){
            datas.add("");
            ImageView imageView = new ImageView(getActivity());
            Bitmap bitmap = BitmapFactory.decodeFile("");
            imageView.setImageBitmap(bitmap);
        }

        rvPartClassify.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new CommonAdapter<String>(getActivity(),R.layout.item_part_classify,datas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        rvPartClassify.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });


    }
}
