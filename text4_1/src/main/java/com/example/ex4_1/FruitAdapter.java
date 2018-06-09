package com.example.ex4_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private  int resourceId;
    public FruitAdapter(Context context, List<Fruit> objects, int resource) {
        super(context, resource, objects);
        resourceId=resource;
    }
    class ViewHoder{
        ImageView fruitImage;
        TextView fruitName;
        TextView fruiContent;
    }
    @NonNull
    @Override
    //当显示新的列表项时，getView（）被调用
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        * 1.position：要获取布局视图的列表项的编号
        * getContext():获取上下文
        * LayoutInflater.from(getContext())获取“打气筒”对象（布局填充器）
        *
        * */
        //获取要显示的列表项数据类对象
        Fruit fruit=getItem(position);
        //获取要显示的列表项数据视图
        View view;
        ViewHoder hoder=new ViewHoder();
        if(convertView==null){
             view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            hoder.fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            hoder.fruitName=(TextView)view.findViewById(R.id.fruit_name);
            hoder.fruiContent=(TextView)view.findViewById(R.id.fruit_content);
            view.setTag(hoder);//保存hoder对象
        }
        else{
            view=convertView;
            hoder= (ViewHoder) view.getTag();
        }

//        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        //从列表项视图获取相应控件
//        ImageView fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
//        TextView fruitName=(TextView)view.findViewById(R.id.fruit_name);
//        TextView fruiContent=(TextView)view.findViewById(R.id.fruit_content);
        //给各个控件设置要显示的内容
        hoder.fruitName.setText(fruit.getName());
        hoder.fruiContent.setText(fruit.getContent());
        hoder.fruitImage.setImageResource(fruit.getImgId());
        return view;
    }
}
