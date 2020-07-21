package com.ysj.pageslide.searchboxslide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.ysj.pageslide.R;

public class SearchBoxSlideActivity extends AppCompatActivity {
    View mFLayout;

    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_box_slide);
        ViewUtils.setImmersionStateMode(this);

        AppBarLayout mAppBarLayout =  findViewById(R.id.appbar);
        mFLayout =  findViewById(R.id.fl_layout);
        mTextView =  findViewById(R.id.tv_info);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float percent = Float.valueOf(Math.abs(verticalOffset)) / Float.valueOf(appBarLayout.getTotalScrollRange());
                //第一种
                int toolbarHeight = appBarLayout.getTotalScrollRange();

                int dy = Math.abs(verticalOffset);

                if(dy<=toolbarHeight){
                    float scale = (float) dy / toolbarHeight;
                    float alpha = scale * 255;
                    if(alpha==0){
                        alpha =50;
                    }
                    mFLayout.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

                    mTextView.setText("setBackgroundColor(Color.argb((int) "+(int) alpha+", 255, 255, 255))\n"+"mFLayout.setAlpha("+percent+")");
                }
            }
        });

    }

    public static void startMyActivity(Context context){
        Intent intent = new Intent(context,SearchBoxSlideActivity.class);
        context.startActivity(intent);
    }
}
