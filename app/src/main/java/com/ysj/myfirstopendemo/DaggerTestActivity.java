package com.ysj.myfirstopendemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger2是Dagger的升级版，同样是一个依赖注入框架
 * 依赖注入的原理,即将使用和创建分开,一个类想要使用另一个类，就只管用，而不管创建
 * 四大注解：@Inject、@cpmponent、@moudle、@provides
 */
public class DaggerTestActivity extends AppCompatActivity {
    private static final String TAG = "DaggerTestActivity";
    //Activity中对Man和Money也进行了@Inject的注解，意思是告诉Dagger2：这个类需要被注入，简单的说就是“这个类我要用，你帮我实例化”
    @Inject
    Man man;

    @Inject
    Money money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);
        //对注入器进行初始化
        DaggerYsjComponent.builder().build().inject(this);

        man.setName("小明明");
//        money.setToatal(122);
        man.setMoney(money);
        Log.e(TAG,"当前人物：" + man.getName() + "=========拥有钱数：" + man.getMoney().getToatal());
    }

    public static  void startMyActivity(Context context){
        Intent intent = new Intent(context,DaggerTestActivity.class);
        context.startActivity(intent);
    }


}
//被@Component标记的ManComponent 接口就是一个注入器
//标注头多了@Component(modules = ManModule.class)，表示告诉注入器如果你要注入的类没有找到构造函数，你就去ManModule.class中找
@Singleton
@Component(modules = ManModule.class)
interface  YsjComponent{
    //意思是DaggerTestActivity中要用到这个注入器，然后我们在DaggerTestActivity中对注入器进行初始化
    void inject(DaggerTestActivity daggerTestActivity);
}

class Money{
    private int toatal;
    //将@Inject去掉， 假设Money实体类就是某个不能被修改源码的框架，由于Man中使用到了Money，而Money没有被实例化，所以100%会报空指针异常
    public Money() {
    }

    public int getToatal() {
        return toatal;
    }

    public void setToatal(int toatal) {
        this.toatal = toatal;
    }
}

class Man{
    private String name;

    private Money money;

    //@Inject的注解，意思就是告诉Dagger2：如果有谁要使用Man和Money这个类，我标注的这个构造函数，你可以直接用来实例化该类
    @Inject
    public Man(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
@Module
class ManModule{
    /**
     * @Singleton：顾名思义，标注该实例化的对象为单例;
     * @Provide：用来标注一个方法，告诉注入器，我标注的方法你可以用来提供实例；
     */
    @Singleton
    @Provides
    Money provideMoney(){
        Money money = new Money();
        money.setToatal(10000);
        return  money;
    }

}
