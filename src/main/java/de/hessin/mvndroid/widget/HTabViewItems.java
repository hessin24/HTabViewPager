package de.hessin.mvndroid.widget;

import androidx.annotation.NonNull;

public class HTabViewItems {
    public String[] titles;
    public int[] iconRes;
    public HTabViewItems(@NonNull String[] titles,@NonNull int[] iconRes){
        this.titles = titles;
        this.iconRes = iconRes;
    }
    public int getCount(){
        return this.titles.length;
    }



}
