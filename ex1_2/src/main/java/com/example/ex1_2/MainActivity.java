package com.example.ex1_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    MyAdapter adapter;
    LinearLayout layout;
    List<View> views=new ArrayList<>();
    int []imgIds=new int[]{
            R.drawable.slide1,R.drawable.slide2,R.drawable.slide3
    };
    ImageView[] dots=new ImageView[3];
    int curpage;//存放当前页（第几页
    SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spf= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirst=spf.getBoolean("isFirst",true);
        if(isFirst){
            SharedPreferences.Editor editor=spf.edit();
            editor.putBoolean("isFrist",false);
            editor.apply();
        }else{
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
        initView();
        initData();
        pager.setAdapter(adapter);
    }

    private void initData() {
        for(int i=0;i<imgIds.length;i++){
            ImageView image=new ImageView(this);
            image.setImageResource(imgIds[i]);
            views.add(image);
        }
        adapter=new MyAdapter(views);
    }

    public void setDots(int index) {
        if (index<0||index>dots.length||index==curpage){//curpage当前页
            return;
        }
        dots[index].setEnabled(false);
        dots[curpage].setEnabled(true);
        curpage=index;
    }
    public void setPage(int index) {
        if(index<0||index>imgIds.length){
            return;
        }
        pager.setCurrentItem(index);
    }

    private void initView() {
        pager=(ViewPager)findViewById(R.id.view_pager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//侧滑容器监听器
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        layout=(LinearLayout)findViewById(R.id.dots_layout);
        for(int i=0;i<dots.length;i++){
            dots[i]=(ImageView)layout.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setTag(i);
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=(int)v.getTag();
                    setDots(index);
                    setPage(index);

                }
            });
        }
        curpage=0;//当前页0下标
        dots[curpage].setEnabled(false);
    }
}
