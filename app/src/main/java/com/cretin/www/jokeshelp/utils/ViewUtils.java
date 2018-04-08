package com.cretin.www.jokeshelp.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * Created by cretin on 16/8/8.
 * 功能1：测量状态栏的高度
 */
public class ViewUtils {
    /**
     * 获取状态栏的高度
     */
    public static int getStatusBarHeights(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch ( Exception e1 ) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
