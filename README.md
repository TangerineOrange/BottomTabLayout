# BottomTabLayout
一个底部导航控件，配合Fragment和底部Tab组合使用，点击底部Tab切换Fragment。<br>
A bottom navigation view, with Fragment and bottom Tab combination, click Tab at the bottom to switch Fragment.

Preview
-------
![](https://github.com/TangerineOrange/BottomTabLayout/tree/master/screenshot/GIF_20170509_160753.gif)  

How to use
--------
### build.gradle
```gradle
compile 'com.tangerineorange:bottomtablayout:1.0.0'  //(under review)
```

### Java
```Java
//1.create Tab List :ArrayList<BottomTabInfo>
//2.set list
//3.import FragmentManager
ArrayList<BottomTabInfo> bottomTabViews = new ArrayList<>();
//BottomTabInfo (defoult img,selected img, Tab Text , Corresponding  Fragment )
bottomTabViews.add(new BottomTabInfo(R.drawable.img, R.drawable.Img_selected, "Tab Text", new AFragment()));
      
bottomTabLayout.initData(bottomTabViews);
bottomTabLayout.initFragment((getSupportFragmentManager()));

```
### XML
```xml
<com.cafe.library.library.BottomTabLayout
        android:id="@+id/id_bottom_tab_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/white"
        app:tablayout_height="50dp" />
        
        <!--tablayout_height is the height of tab group at the bottom-->
```
