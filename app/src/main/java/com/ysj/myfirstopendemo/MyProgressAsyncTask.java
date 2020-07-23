package com.ysj.myfirstopendemo;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by tekabo
 * Created on 2020/7/23
 * PackageName com.ysj.myfirstopendemo
 * AsyncTask<Params, Progress, Result>三个泛型参数
 * Params
 * 在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。本例中第一个泛型参数指定为Void，表示在执行AsyncTask的时候不需要传入参数给后台任务。
 * Progress
 * 后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。本例中第二个泛型参数指定为Integer，表示使用整型数据来作为进度显示单位。
 * Result
 * 当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。本例中第三个泛型参数指定为Boolean，则表示使用布尔型数据来反馈执行结果。
 */

public class MyProgressAsyncTask extends AsyncTask<Void,Integer,Boolean> {
    // 定义ProgressBar
    private ProgressBar pb_test;

    // 定义文本控件
    private TextView tv_test;

    public MyProgressAsyncTask(ProgressBar pb_test,TextView tv_test){
        super();
        this.pb_test = pb_test;
        this.tv_test = tv_test;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务。
     * 任务一旦完成就可以通过return语句来将任务的执行结果进行返回，如果AsyncTask的
     * 第三个泛型参数指定的是Void，就可以不返回任务执行结果。注意，在这个方法中是不
     * 可以进行UI操作的，如果需要更新UI元素，比如说反馈当前任务的执行进度，可以调用
     * publishProgress(Progress...)方法来完成。
     */
    @Override
    protected Boolean doInBackground(Void... voids) {
        for(int i=0;i<100;i++){
            try {
                //模拟耗时操作
                Thread.sleep(100);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * 当在后台任务中调用了publishProgress(Progress...)方法后，这个方法就很快会被调用，
     * 方法中携带的参数就是在后台任务中传递过来的。在这个方法中可以对UI进行操作，利用参
     * 数中的数值就可以对界面元素进行相应的更新。
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        pb_test.setProgress(value);
        tv_test.setText(value+"");
    }

    /**
     * 当后台任务执行完毕并通过return语句进行返回时，这个方法就很快会被调用。返回的数据
     * 会作为参数传递到此方法中，可以利用返回的数据来进行一些UI操作，比如说提醒任务执行
     * 的结果，以及关闭掉进度条对话框等。
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
       tv_test.setText("进度条加载完毕！");
    }
}
