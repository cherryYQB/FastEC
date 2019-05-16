package com.yqb.latte_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.yqb.latte_core.app.Latte;

/**
 * Author   yaoqinbao
 * Time     2019/5/10
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
