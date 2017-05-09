package com.cafe.library.library;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Frank on 2016/8/11.
 */
public class BottomTabView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private ImageView mTabImage;
    private TextView mTabLable;
    private BottomTabInfo tabInfo;

    public BottomTabView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public BottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        //设置linearLayout属性
        //竖直
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.tab_view, this, true);
        mTabImage = (ImageView) findViewById(R.id.tab_image);
        mTabLable = (TextView) findViewById(R.id.tab_lable);

    }

    public void initData(BottomTabInfo tabInfo) {
        this.tabInfo = tabInfo;
        if (tabInfo.isShowImg())
            mTabImage.setImageResource(tabInfo.imageResId);
        if (!"".equals(tabInfo.text))
            mTabLable.setText(tabInfo.text);
    }

    @Override
    public void onClick(View v) {
    }

    public void setImgRes(@DrawableRes int id) {
        mTabImage.setImageResource(id);
    }


}
