package com.example.ex11_1;

import android.graphics.Color;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    PagerTabStrip strip;
    MyAdapter adapter;
    List<String> titles=new ArrayList<>();
    List<View> views=new ArrayList<>();
    int [] layouts=new int[]{
            R.layout.view1,R.layout.view2,R.layout.view3,R.layout.view4
    };
    String[] strTitle=new String[]{
        "推荐","财经","科技","娱乐"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        pager.setAdapter(adapter);
    }

    private void initData() {//（打气筒）布局填充器
        LayoutInflater inflater=LayoutInflater.from(this);
        for (int i=0;i<layouts.length;i++){
            View view=inflater.inflate(layouts[i],null);
            views.add(view) ;
            titles.add(strTitle[i]);
        }
        adapter=new MyAdapter(views,titles);
    }

    private void initView() {
        pager=(ViewPager)findViewById(R.id.view_pager);
        strip=(PagerTabStrip)findViewById(R.id.pager_tab_strip);
        strip.setTextColor(Color.WHITE);
        strip.setBackgroundColor(Color.RED);
        strip.setTabIndicatorColor(Color.YELLOW);//下划线颜色
        strip.setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
        strip.setDrawFullUnderline(true);
    }
}
