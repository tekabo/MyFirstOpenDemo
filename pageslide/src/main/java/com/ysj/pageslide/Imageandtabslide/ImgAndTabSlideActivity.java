package com.ysj.pageslide.Imageandtabslide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;

import com.google.android.material.tabs.TabLayout;
import com.ysj.pageslide.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImgAndTabSlideActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewpager;
    private Toolbar toolbar;
    private ImageView ivBg;
    private AppBarLayout appBarLayout;

    private String[] tabsTitles = new String[]{"pig","咘吖"};
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public static void startMyActivity(Context context){
        Intent intent = new Intent(context,ImgAndTabSlideActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_img_and_tab_slide);

        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.toolbar);
        ivBg = findViewById(R.id.iv_bg);
        appBarLayout = findViewById(R.id.appbar);

        init();
        setAvatorChange();
    }

    private void init() {
        for(int i=0;i<tabsTitles.length;i++){
            fragments.add(new PartClassifyFragment());
            tablayout.addTab(tablayout.newTab());
        }

        viewpager.setAdapter(new FmPagerAdapter(fragments,getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewpager);

        for(int j=0;j<tabsTitles.length;j++){
            tablayout.getTabAt(j).setText(tabsTitles[j]);
        }

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_man);
        ivBg.setImageBitmap(BlurImageView.BlurImages(bitmap));
    }


    /**
     * 渐变toolbar背景
     */
    private void setAvatorChange() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayouts, int verticalOffset) {
                //verticalOffset始终为0以下的负数
                float percent = (Math.abs(verticalOffset*1.9f)/appBarLayouts.getTotalScrollRange());
                toolbar.setBackgroundColor(changeAlpha(Color.WHITE,percent));
            }
        });
    }

    /** 根据百分比改变颜色透明度 */
    public int changeAlpha(int color,float fraction){
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color)*fraction);
        return Color.argb(alpha,red,green,blue);
    }
}
