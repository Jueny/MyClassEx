package com.example.ex7_1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ex7_1.task.ImageTask;
import com.example.ex7_1.R;
import com.example.ex7_1.bean.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/17.
 * 数据适配器
 * 泛型根据数据模型而定
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private int resourseId;//列表项布局
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        resourseId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //picture为缓存
        final Map<String,Bitmap> map=new HashMap<>();
        View view=null;
        ViewHolder holder=new ViewHolder();
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourseId,parent,false);
            holder.newsImage=(ImageView)view.findViewById(R.id.news_image);
            holder.newsTitle=(TextView)view.findViewById(R.id.news_title);
            holder.NewContent=(TextView)view.findViewById(R.id.news_content);
            view.setTag(holder);
        }else{
            view=convertView;
            holder=(ViewHolder)view.getTag();
        }
        News news=getItem(position);
        holder.newsTitle.setText(news.getNewsTitle());
        holder.NewContent.setText(news.getNewsContent());
        final String url=news.getImgUrl();
        //执行异步任务得到图片
        Bitmap bitmap=map.get(url);
        if(bitmap!=null){
            holder.newsImage.setImageBitmap(bitmap);
        }else{
            //final只能赋值一次
            final ViewHolder finalHolder = holder;
            new ImageTask(new ImageTask.CallBack() {
                @Override
                public void getData(Bitmap pic) {
                    finalHolder.newsImage.setImageBitmap(pic);
                    //放入缓存
                    map.put(url,pic);
                }
            }).execute("http://litchiapi.jstv.com"+url);
        }
        return view;
    }
    public class ViewHolder{
        //属性根据列表项控件而定
        ImageView newsImage;
        TextView newsTitle;
        TextView NewContent;
    }
}
