package com.example.ex9_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ex9_1.bean.Book;

import org.litepal.crud.DataSupport;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(Button)findViewById(R.id.button);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.添加数据记录
                Book book1=new Book();
                book1.setBookName("android");
                book1.setAuthor("阿萨德");
                book1.setPrice(80);
                book1.save();//保存记录自动同步数据库
                Book book2=new Book();
                book2.setBookName("Java");
                book2.setAuthor("阿斯顿");
                book2.setPrice(180);
                book2.save();
                //2.查询全部记录
                List<Book> books=DataSupport.findAll(Book.class);
                ////方法1.迭代器遍历
//                Iterator<Book> it=books.iterator();//获取迭代器
//                while (it.hasNext()){
//                    Book book=it.next();
//                }
                ////方法2：for-each
//                for (Book book:books){//将books容器中的内容一个个拿出来放入bookd对象中
//                    Log.i("id",book.getId()+"");
//                Log.i("bookNane",book.getBookName()+"");
//                Log.i("author",book.getAuthor()+"");
//                Log.i("price",book.getPrice()+"");
//                }
                ////方法3：for循环
                for (int i=0;i<books.size();i++){
                    Book book=books.get(i);
                    Log.i("id",book.getId()+"");
                    Log.i("bookNane",book.getBookName()+"");
                    Log.i("author",book.getAuthor()+"");
                    Log.i("price",book.getPrice()+"");
                }
                //3.按条件查询
                List<Book> resuit=DataSupport.where("bookName=?","Java").find(Book.class);
                //4.更新数据
                Book book3=new Book();
                book3.setPrice(68);
                book3.updateAll("bookName=?","Java");
                //5.删除数据
                DataSupport.deleteAll(Book.class,"bookName=? and price>?","android","146");
            }
        });
    }
}
