package com.example.leon.textswitcherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RecyclerView recyclerView;
    private ArrayList<String[]> dataList = new ArrayList<>();
    private ListAdapter listAdapter;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.leon);
        String[] temp = new String[3];
        for (int j = 0; j < 3; j++) {
            temp[j] = "leonleon" + j;
        }
        dataList.add(temp);
        String[] temp1 = new String[3];
        for (int j = 0; j < 3; j++) {
            temp1[j] = "jassonjasson" + j;
        }
        dataList.add(temp1);
        String[] temp2 = new String[3];
        for (int j = 0; j < 3; j++) {
            temp2[j] = "lululu" + j;
        }
        dataList.add(temp2);
        //listAdapter = new ListAdapter(this, dataList);
        recyclerAdapter = new RecyclerAdapter(this, dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }

}
