package com.ysj.myfirstopendemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自动、智能地下采样(downsampling)和缓存(caching)，以最小化存储开销和解码次数；
 * 积极的资源重用，例如字节数组和Bitmap，以最小化昂贵的垃圾回收和堆碎片影响；
 * 深度的生命周期集成，以确保仅优先处理活跃的Fragment和Activity的请求，并有利于应用在必要时释放资源以避免在后台时被杀掉
 */
public class GlideAndFrescoTestActivity extends AppCompatActivity {


    @BindView(R.id.btn_load_image)
    Button btnLoadImage;
    @BindView(R.id.btn_unload_image)
    Button btnUnloadImage;
    @BindView(R.id.iv_standard)
    ImageView ivStandard;
    @BindView(R.id.btn_load_fresco_image)
    Button btnLoadFrescoImage;
    @BindView(R.id.iv_fresco)
    SimpleDraweeView ivFresco;

    private File mImageFile;

    public static void startMyActivity(Context context) {
        Intent intent = new Intent(context, GlideAndFrescoTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        ButterKnife.bind(this);

        /**
         * 构造一个File对象，将手机中的一张名为test.jpg的图片封装起来
         * （注意：这里获取路径名的api为context.getExternalFilesDir()，
         * 而没有用之前的Environment.getExternalStorageState()。
         * 这是因为Android X的新特性：作用域存储，即每个应用只能自己的应用路径下存放文件，
         * 而不是像之前一样直接在sdcard上，该局api可以获取到的路径为：/storage/emulated/0/Android/data/<包名>/files）
         */

        //1、加载本地图片
        // 为mImageFile赋予实例对象
        //mImageFile = new File(this.getExternalFilesDir(null) + File.separator + "test.jpg");

        //mImageFile = new File(Environment.getExternalStorageState() + File.separator + "test.jpg");
        ///storage/emulated/0/Android/data/com.ysj.myfirstopendemo/files/test.jpg

        //mImageFile = new File(getExternalCacheDir() + File.separator + "test.jpg");
        // /storage/emulated/0/Android/data/com.ysj.myfirstopendemo/cache/test.jpg


        mImageFile = new File(Environment.getExternalStorageDirectory() + File.separator + "sina" + File.separator + "weibo/微博动图" + File.separator + "ysj.gif");
        ///storage/emulated/0/Pictures/ysj/test.jpg
        //Environment.getExternalStorageDirectory()相当于手机中的“手机存储”，Pictures/ysj/test.jpg 自己手机图片的路径


        // glide 1.加载图片
        loadImage();

        // glide 2.取消图片加载
        unloadImage();

        //freso
        loadFresoImage();
    }

    private void loadFresoImage() {
        btnLoadFrescoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Uri mUri = Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595412562824&di=4a5e81394a62f76cdc636248be145bae&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg");
               ivFresco.setImageURI(mUri);
            }
        });
    }

    private void unloadImage() {
        btnUnloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext())
                        .clear(ivStandard);
            }
        });
    }

    //.load(mImageFile)、load(resource)、load(imagebyte)、load(imgUri)、.load(url)
    private void loadImage() {
        //2、加载应用资源
        int resource = R.drawable.icon_net_error;
        //3、加载二进制流
        //byte[] imagebyte = getImageBytes();
        //4、加载Uri对象
        //Uri imgUri = getImageUri();


        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext())
                        //.asGif()//加载动态图片，若现有图片为非gif图片，则直接加载错误占位图。
                        //.asBitmap()//只加载静态图片，如果是gif图片只加载第一帧
                        .load(mImageFile)//"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595412562824&di=4a5e81394a62f76cdc636248be145bae&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg"
//                        .placeholder(R.drawable.ic_launcher_background)//占位图，图片还未加载出来的时候，提前展示给用户的一张图片
                        .error(R.drawable.icon_net_error)//还有一种占位图，作为图片加载失败的另一张图片
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//关闭硬盘缓存机制
                        .skipMemoryCache(true)//传入参数为false时，则关闭内存缓存
//                        .override(100,80)//指定图片大小加载
                        .into(ivStandard);

                //DiskCacheStrategy.NONE： 表示不缓存任何内容。
                //DiskCacheStrategy.SOURCE： 表示只缓存原始图片。
                //DiskCacheStrategy.RESULT： 表示只缓存转换过后的图片（默认选项）。
                //DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
            }
        });
    }


}
