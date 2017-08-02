package com.example.leon.textswitcherdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

/**
 * Created by leon on 2017/8/2.
 */

public class RecyclerAdapter extends RecyclerView.Adapter{
    private ArrayList<String[]> dataList;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ViewFlipper mViewFlipper;

        public MyViewHolder(View itemView) {
            super(itemView);
            mViewFlipper = itemView.findViewById(R.id.flipper);
        }
    }

    public RecyclerAdapter(Context context, ArrayList<String[]> dataList) {
        this.dataList = dataList;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            setViewFlipper(myViewHolder.mViewFlipper, position);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    private void setViewFlipper(ViewFlipper mViewFlipper, int position) {

        //添加要滚动的View
        for (String temp : dataList.get(position)) {
            mViewFlipper.addView(getImageView(temp));
        }

        //设置开始和结束动画
        mViewFlipper.setInAnimation(context, R.anim.in_animation);
        mViewFlipper.setOutAnimation(context, R.anim.out_animation);

        //设置间隔时间
        mViewFlipper.setFlipInterval(3000);

        //动画的监听
        mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //重复
            }
        });

        //开始轮播
        mViewFlipper.startFlipping();
    }

    private TextView getImageView(String text) {
        TextView textView = new TextView(context);
        textView.setTextSize(30);
        textView.setText(text);
        return textView;
    }
}
