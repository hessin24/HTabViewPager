package de.hessin.mvndroid.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class HTabViewPager extends LinearLayout {
    private final TabLayout tabLayout;
    private final ViewPager viewPager;
    private HTabViewPagerAdapter adapter;
    private OnEventChangeListener listener;
    public HTabViewPager(Context context) {
        this(context,null);
    }

    public HTabViewPager(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HTabViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tabLayout = new TabLayout(context,null,defStyleAttr);
        viewPager = new ViewPager(context,attrs);
        tabLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        viewPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        tabLayout.setTabMode(TabLayout.MODE_AUTO);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        addView(tabLayout);
        addView(viewPager);
    }

    public void setAdapter(HTabViewPagerAdapter adapter){
        this.adapter = adapter;
        for (int i=0; i < adapter.getCount(); i++) {
            String title = adapter.tabViewItems.titles[i];
            int res = adapter.tabViewItems.iconRes[i];
            TabLayout.Tab tab = tabLayout.newTab();
            if (title != null) tab.setText(title);
            if (res > -1) tab.setIcon(res);

            tabLayout.addTab(tab);
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

                @Override
                public void onPageSelected(int position) {
                    TabLayout.Tab selectTab = tabLayout.getTabAt(position);
                    if (selectTab !=null) {
                        tabLayout.selectTab(selectTab, true);
                        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
                        if (listener != null) listener.onChange(viewPager.getChildAt(position), position);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) { }
            });
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab != null) {
                        int posi = tab.getPosition();
                        viewPager.setCurrentItem(posi, true);
                        if (listener != null) listener.onChange(tab, posi);
                    }
                }

                @Override public void onTabUnselected(TabLayout.Tab tab) { }
                @Override public void onTabReselected(TabLayout.Tab tab) { }
            });
        }



    }

    public void setOnEventChangeListener(OnEventChangeListener listener){
        this.listener = listener;

    }
    public HTabViewPagerAdapter getAdapter(){
        return this.adapter;
    }

    public static interface OnEventChangeListener{
        void onChange(Object view, int position);
    }
}
