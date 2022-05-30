package com.gabe.collapsetoolbar;

import android.content.Context;

public class UiUtil {

    public static int getpixels(Context cContext, int dp) {
        final float scale = cContext.getResources().getDisplayMetrics().density;
        int px = (int) (dp * scale + 0.5f);

        return px;
    }

    public static int dpToPx(Context aContect, int dp) {
        return (int) (dp * aContect.getResources().getDisplayMetrics().density);
    }

    public static int px2dp(Context aContext, int px) {
        return (int) (px / aContext.getResources().getDisplayMetrics().density);

    }
//    fun Context.dpToPx(dp: Int): Int {
//        return (dp * resources.displayMetrics.density).toInt()
//    }
//
//    fun Context.pxToDp(px: Int): Int {
//        return (px / resources.displayMetrics.density).toInt()
//    }
}
