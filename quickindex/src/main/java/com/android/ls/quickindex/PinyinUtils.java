package com.android.ls.quickindex;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * Created by Administrator on 2017/6/14.
 */

public class PinyinUtils {
    public PinyinUtils() {

    }
    public static String String2Pinyin(String str){
        try {
            String pinyinString = PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);
            return pinyinString;
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getFirstLetter(String s){
        if(s != null){
            String firstLetter = String.valueOf(s.toUpperCase().charAt(0));
            return firstLetter;
        }
        return null;

    }
}
