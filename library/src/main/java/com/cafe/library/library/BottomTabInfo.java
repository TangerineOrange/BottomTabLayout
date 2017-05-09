package com.cafe.library.library;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;


/**
 * Created by Frank on 2016/8/22.
 */
public class BottomTabInfo {
    /**
     * icon
     */
    @DrawableRes
    public int imageResId = -1;
    /**
     * checked icon
     */
    @DrawableRes
    public int selectedImageResId = -1;
    /**
     * 文本
     */
    public String text;

    public Fragment fragment;

    public boolean isShowImg() {
        return imageResId != -1 && selectedImageResId != -1;
    }

    public BottomTabInfo(int imageResId, int selectedImageResId, String text, Fragment fragment) {
        this.imageResId = imageResId;
        this.selectedImageResId = selectedImageResId;
        this.text = text;
        this.fragment = fragment;
    }

    public BottomTabInfo(String text, Fragment fragment) {
        this.text = text;
        this.fragment = fragment;
    }
}
