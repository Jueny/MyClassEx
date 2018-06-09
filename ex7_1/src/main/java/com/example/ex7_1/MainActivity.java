package com.example.ex7_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ex7_1.adapter.NewsAdapter;
import com.example.ex7_1.bean.News;
import com.example.ex7_1.task.JSONTask;
import com.example.ex7_1.utils.JSONUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListView newsList;
    View footerView;
    TextView load;
    NewsAdapter adapter;
    List<News> data;
    int curpage=1;
    boolean isDown,isLoad=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsList=(ListView)findViewById(R.id.news_list);
        footerView= LayoutInflater.from(this).inflate(R.layout.footview,null);
        load=(TextView)footerView.findViewById(R.id.load);
        newsList.addFooterView(footerView);
        data=new ArrayList<>();
        adapter=new NewsAdapter(this,R.layout.news_item,data);
        newsList.setAdapter(adapter);
        loadData();
        newsList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(isDown&&scrollState==SCROLL_STATE_IDLE){
                    //加载数据
                    loadData();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount){
                    isDown=true;
                }else{
                    isDown=false;
                }
            }
        });

    }
    public void loadData(){
        if(isLoad){
            load.setText("正在加载数据，请稍后...");
            isLoad=false;
            new JSONTask(new JSONTask.CallBack() {
                @Override
                public void getData(String result) {
                    data.addAll(JSONUtils.parseJson(result));
                    adapter.notifyDataSetChanged();
                }
            }).execute("http://litchiapi.jstv.com/api/GetFeeds?mach=phone&column=1&PageSize=20&pageIndex="+curpage+"&val=792F8BB2731D734F3A8EC63E1F9F3C36");
            curpage++;
            isLoad=true;
            load.setText("加载数据");
        }
    }
}
