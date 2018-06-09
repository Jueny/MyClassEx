package com.example.pic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fruit> fruitList=new ArrayList<Fruit>();
    int[] imgIds=new int[]{R.drawable.apple_pic,R.drawable.banana_pic,R.drawable.cherry_pic,R.drawable.grape_pic,R.drawable.mango_pic,R.drawable.orange_pic,R.drawable.pear_pic,R.drawable.pineapple_pic,R.drawable.strawberry_pic,R.drawable.watermelon_pic};
    String[] name=new String[]{"apple","banana","cherry","grape","mango","orange","pear","pineapple","strawberry","watermelon"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);
        initData();
        FruitAdapter adapter=new FruitAdapter(this,R.layout.fruit_item,fruitList);
        listView.setAdapter(adapter);
    }

    private void initData() {
        for(int i=0;i<name.length;i++){
            Fruit fruit=new Fruit("这是一种"+name[i]+" ,它具有非常高的营养价值...",name[i],imgIds[i]);
            fruitList.add(fruit);
        }
    }
}
