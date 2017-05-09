package com.cafe.library.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Frank on 2016/8/11.
 */
public class BottomTabLayout extends LinearLayout {

    private ArrayList<BottomTabInfo> tabs;
    private OnTabClickListener listener;
    private FragmentManager fragmentManager;
    private Fragment lastFragment;
    private Context context;
    private int tabLayoutHeight = 0;
    private OnClickListener onClickListener;
    private BottomTabView lastView;


    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        if (attrs != null)
            tabLayoutHeight = getTabLayoutHeight(context, attrs);
        init();
    }

    private int getTabLayoutHeight(Context context, AttributeSet attrs) {
        //创建对象
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomTabLayout);
        //获取属性
        int tabLayoutHeight = ta.getDimensionPixelOffset(R.styleable.BottomTabLayout_tablayout_height, 0);
        ta.recycle();
        return tabLayoutHeight;
    }

    private void init() {
        setOrientation(VERTICAL);
        initView();
        initListener();
    }

    private void initView() {
        //添加frameLayout
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setId(R.id.id_fragment);
        addView(frameLayout, params);
        //添加线
        View view = new View(context);
        view.setBackgroundColor(getResources().getColor(R.color.lineColor));
        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.tabLayout_Middle_Line_Height)));
        //添加底部TabLayout
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setId(R.id.id_tab_layout);
        addView(linearLayout, new LayoutParams(LayoutParams.MATCH_PARENT, tabLayoutHeight));
    }

    private void initListener() {
        listener = new OnTabClickListener() {
            @Override
            public void onTabClick(BottomTabInfo tabItem) {
            }
        };

        onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomTabInfo tabItem = (BottomTabInfo) v.getTag();
                Log.i("click", "click  " + tabItem.text);
                if (listener != null)
                    listener.onTabClick(tabItem);

                if (lastFragment == tabItem.fragment || tabItem.fragment == null || fragmentManager == null)
                    return;
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                if (tabItem.fragment.isAdded()) {
                    transaction.show(tabItem.fragment).hide(lastFragment).commit();
                } else {
                    transaction.add(R.id.id_fragment, tabItem.fragment).hide(lastFragment).commit();
                }
                lastFragment =  tabItem.fragment;

                if (tabItem.isShowImg()) {
                    ((BottomTabView) v).setImgRes(tabItem.selectedImageResId);
                    if (lastView != null)
                        lastView.setImgRes(((BottomTabInfo) lastView.getTag()).imageResId);
                }
                lastView = (BottomTabView) v;

            }
        };
    }

    public void initData(ArrayList<BottomTabInfo> tabs) {
        this.tabs = tabs;
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.id_tab_layout);

        if (tabs != null && tabs.size() > 0) {
            BottomTabView mTabView = null;
            for (int i = 0; i < tabs.size(); i++) {
                mTabView = new BottomTabView(getContext());
                mTabView.setTag(tabs.get(i));
                mTabView.initData(tabs.get(i));
                mTabView.setOnClickListener(onClickListener);
                if (i == 0 && tabs.get(0).isShowImg()) {
                    mTabView.setImgRes(tabs.get(0).selectedImageResId);
                    lastView = mTabView;
                }
                linearLayout.addView(mTabView, params);

            }
        } else {
            throw new IllegalArgumentException("tabs can not be empty");
        }
    }

    public void initFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.id_fragment, tabs.get(0).fragment).commit();
        lastFragment =  tabs.get(0).fragment;

    }

    public void setOnTabClickListener(@NonNull OnTabClickListener listener) {
        this.listener = listener;
    }

    public void addTabView(BottomTabInfo tabInfo) {
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);

        BottomTabView mTabView = new BottomTabView(getContext());
        mTabView.setTag(tabInfo);
        mTabView.initData(tabInfo);
        mTabView.setOnClickListener(onClickListener);
        addView(mTabView, params);
    }


    public interface OnTabClickListener {
        void onTabClick(BottomTabInfo tabItem);
    }

}
