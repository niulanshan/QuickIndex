package com.android.ls.quickindex;

import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/6/14.
 */

class Friend implements Comparable<Friend>{
    public String name ;
    public String pinyin;
    public Friend(String name) {
        this.name = name;
        pinyin = PinyinUtils.String2Pinyin(name);
    }

    @Override
    public int compareTo( Friend s) {
        return this.pinyin.compareTo(s.pinyin);
    }
}
