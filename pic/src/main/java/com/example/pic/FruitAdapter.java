package com.example.pic;

/**
 * Created by john on 2018/4/2.
 */

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
 * Created by dell on 2018/4/2.
 */

public class FruitAdapter extends ArrayAdapter<Fruit>{
    private int resourceId;
    public FruitAdapter(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
        TextView fruitContent;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit=getItem(position);
        View view;
        ViewHolder holder=new ViewHolder();
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            holder.fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            holder.fruitName=(TextView)view.findViewById(R.id.fruit_name);
            holder.fruitContent=(TextView)view.findViewById(R.id.fruit_content);
            view.setTag(holder);
        }else{
            view=convertView;
            holder=(ViewHolder)view.getTag();
        }
        holder.fruitImage.setImageResource(fruit.getImgId());
        holder.fruitName.setText(fruit.getName());
        holder.fruitContent.setText(fruit.getContent());
        return view;
    }
}