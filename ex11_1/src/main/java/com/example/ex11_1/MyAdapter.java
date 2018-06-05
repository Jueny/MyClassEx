package com.example.ex11_1;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class MyAdapter extends PagerAdapter {
   List<View > views;//存放视图
    List<String> titles;//存放导航栏标题（看情况取舍）

    public MyAdapter(List<View> views, List<String> titles) {
        this.views = views;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//position第几个布局
        container.addView(views.get(position));//将当前布局加入到布局组container中
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//删除布局
        container.removeView(views.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
