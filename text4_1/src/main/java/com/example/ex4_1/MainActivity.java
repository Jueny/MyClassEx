package com.example.ex4_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fruit> fruitList=new ArrayList<Fruit>();
    int [] imageIds=new int[]{R.drawable.apple_pic,R.drawable.banana_pic,R.drawable.cherry_pic,
            R.drawable.grape_pic,R.drawable.mango_pic,R.drawable.orange_pic,R.drawable.pear_pic,
            R.drawable.pineapple_pic,R.drawable.strawberry_pic,R.drawable.watermelon_pic};
    String[] name=new String[]{"apple","banana","cherry","grape","mango",
            "orange", "pear", "pineapple","strawberry","watermelon"};
//    String[] data=new String[]{"这是一行文字","这是第二行文字","这是一行文字",
//            "这是一行文字", "这是一行文字","这是一行文字","这是一行文字"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.ListView1);
        initData();
        /*
        * 1.第一个参数：上下文
        * 2.第二个参数：列表项的布局
        * 3.第三个参数：数据源
        * 绑定列表与数据适配器
        * */
//        ArrayAdapter<String > adapter=new ArrayAdapter<String>
//                (MainActivity.this,android.R.layout.simple_list_item_1,data);//<>指泛型
        FruitAdapter adapter=new FruitAdapter(this,fruitList,R.layout.fruit_item);
        listView.setAdapter(adapter);



    }
    private void initData(){
        for(int i=0;i<name.length;i++){
            Fruit fruit=new Fruit(name[i],"这是一种"+name[i]+" ,它具有非常高的营养价值...",imageIds[i]);

//            Fruit fruit=new Fruit(name[i],"This is "+name[i]+" which is a fruit，鲜美多汁，欢迎购买品尝",imageIds[i]);
            fruitList.add(fruit);
        }
    }
}
