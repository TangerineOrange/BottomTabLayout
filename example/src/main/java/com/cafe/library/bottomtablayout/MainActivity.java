package com.cafe.library.bottomtablayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cafe.library.library.BottomTabInfo;
import com.cafe.library.library.BottomTabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomTabLayout bottomTabLayout = (BottomTabLayout) findViewById(R.id.id_bottom_tab_layout);

        ArrayList<BottomTabInfo> bottomTabViews = new ArrayList<>();

        bottomTabViews.add(new BottomTabInfo(R.drawable.viewgallery, R.drawable.viewgallery_selected, "A", new AFragment()));
        bottomTabViews.add(new BottomTabInfo(R.drawable.auto, R.drawable.auto_selected, "B", new BFragment()));
        bottomTabViews.add(new BottomTabInfo(R.drawable.store, R.drawable.store_selected, "C", new CFragment()));
        bottomTabViews.add(new BottomTabInfo(R.drawable.personalcenter, R.drawable.personalcenter_selected, "D", new DFragment()));


        bottomTabLayout.initData(bottomTabViews);
        bottomTabLayout.initFragment((getSupportFragmentManager()));

    }
}
