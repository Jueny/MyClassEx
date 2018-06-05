package com.example.ex10_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {
    TextView title,content;
    View view;//碎片布局对应的视图

    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //加载碎片布局，得到相应的视图
        view=inflater.inflate(R.layout.fragment_content, container, false);//布局填充器
        return view;
    }
    public void refresh(String titleString,String contentString){
        View layout=view.findViewById(R.id.visible_layout);
        layout.setVisibility(View.VISIBLE);//设置可见
        title=(TextView)view.findViewById(R.id.title);
        content=(TextView)view.findViewById(R.id.content);
        title.setText(titleString);
        content.setText(contentString);
    }
}
