package de.hessin.mvndroid.widget


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

public class HTabViewPagerAdapter extends PagerAdapter {
    protected HTabViewItems tabViewItems;
    private final LayoutInflater inflater;
    private final int layoutRes;
    public HTabViewPagerAdapter(@NonNull Context context, int layoutRes, @NonNull HTabViewItems items){
        this.tabViewItems = items;
        inflater = LayoutInflater.from(context);
        this.layoutRes = layoutRes;
    }

    @Override
    public int getCount(){
        return this.tabViewItems.getCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(layoutRes,null);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabViewItems.titles[position];
    }

}