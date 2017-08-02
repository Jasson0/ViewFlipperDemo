package com.example.leon.textswitcherdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

/**
 * Created by leon on 2017/8/2.
 */

public class ListAdapter extends BaseAdapter {
    private ArrayList<String[]> dataList;
    private Context context;
    public class ViewHolder{
        LinearLayout mViewFlipper;
    }

    public ListAdapter (Context context, ArrayList<String[]> dataList) {
        this.dataList = dataList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listitem,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.mViewFlipper = view.findViewById(R.id.listitem);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mViewFlipper.removeAllViews();
        ViewFlipper viewFlipper = new ViewFlipper(context,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        viewHolder.mViewFlipper.addView(viewFlipper, lp);
        setViewFlipper(viewFlipper, i);
        return view;
    }
    private void setViewFlipper(ViewFlipper mViewFlipper, int position) {

        //添加要滚动的View
        for (String temp:dataList.get(position)) {
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
