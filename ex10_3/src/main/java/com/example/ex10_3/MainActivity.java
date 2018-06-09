package com.example.ex10_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    MyAdapter adapter;
    List<View> views=new ArrayList<>();
    //存放视图图片id
    int[] imgIds=new int[]{
            R.drawable.slide1,R.drawable.slide2
    };
    //存放圆点的图片控件对象
    ImageView[] dots=new ImageView[3];
    int curpage;
    SharedPreferences spf;
    boolean isFirst =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spf = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirst=spf.getBoolean("isFirst",true);
        if (isFirst){
            SharedPreferences.Editor editor=spf.edit();
            editor.putBoolean("isFirst",false);
            editor.apply();
        }else{
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
        initView();
        initData();
    }
    public void setDots(int index){
        if (index<0||index>dots.length||index==curpage){
            return;
        }
        dots[index].setEnabled(false);
        dots[curpage].setEnabled(true);
        curpage=index;
    }
    public void setPage(int index){
        if (index<0||index>dots.length){
            return;
        }
        pager.setCurrentItem(index);
    }
    private void initData() {
        for (int i=0;i<imgIds.length;i++){
            ImageView view=new ImageView(this);
            view.setImageResource(imgIds[i]);
            views.add(view);
        }
        LayoutInflater inflater=LayoutInflater.from(this);
        View view3=inflater.inflate(R.layout.view3_layout,null);
        Button login=(Button)view3.findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        views.add(view3);
        adapter=new MyAdapter(views);
        pager.setAdapter(adapter);
    }

    private void initView() {
        pager=(ViewPager)findViewById(R.id.view_pager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setDots(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        LinearLayout layout=(LinearLayout)findViewById(R.id.dots_layout);
        for (int i=0;i<dots.length;i++){
            dots[i]=(ImageView) layout.getChildAt(i);//小圆点的编号赋值给dots
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
        curpage=0;
        dots[curpage].setEnabled(false);
    }
}
