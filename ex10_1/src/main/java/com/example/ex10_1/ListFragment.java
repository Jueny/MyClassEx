package com.example.ex10_1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    ListView list;
    List<String> data=new ArrayList<>();
    ArrayAdapter<String> adapter;
    View view;
    boolean isTwoPan;
    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不可以这样
//        list=(ListView)view.findViewById(R.id.listView);
        for (int i=0;i<20;i++){
            data.add("这是第"+i+"条代码");
            adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_list, container, false);
        list=(ListView)view.findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(isTwoPan){
                    ContentFragment fragment=(ContentFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.content_fragment);
                    fragment.refresh(data.get(position),"金夫人大榕树看视频开始跑酷视频扑克牌搜");

                }else {
                    Intent intent=new Intent(getActivity(),ContentActivity.class);
                    intent.putExtra("title",data.get(position));
                    intent.putExtra("content","金夫人大榕树看视频开始跑酷视频扑克牌搜");
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.content_fragment)!=null){
            isTwoPan=true;
        }else {
            isTwoPan=false;
        }
    }

}
