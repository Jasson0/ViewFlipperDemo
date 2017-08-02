package com.example.leon.textswitcherdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by leon on 2017/8/2.
 */

public class TextSwitcherView extends TextSwitcher implements ViewSwitcher.ViewFactory{
    private int index = -1;
    private Context context;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            index = next(); //取得下标值
            updateText();
        }
    };
    private String [] resources={
            "leonleonleonleon",
            "jassonjassonjasson",
    };
    private Timer timer;

    public TextSwitcherView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TextSwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        if(timer==null)
            timer = new Timer();
        this.setFactory(this);
        this.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.in_animation));
        this.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.out_animation));
    }

    public void setResources(String[] res){
        this.resources = res;
    }

    public void setTextStillTime(long time){
        if(timer==null){
            timer = new Timer();
        }else{
            timer.scheduleAtFixedRate(new MyTask(), 1, time);//每3秒更新
        }
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(1);
        }
    }

    private int next(){
        int flag = index+1;
        if(flag>resources.length-1){
            flag=flag-resources.length;
        }
        return flag;
    }
    private void updateText(){
        this.setText(resources[index]);
    }

    @Override
    public View makeView() {
        TextView tv =new TextView(context);
        tv.setTextSize(30);
        tv.setTextColor(Color.parseColor("#000000"));
        return tv;
    }
}
