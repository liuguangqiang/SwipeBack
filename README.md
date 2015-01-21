SwipeBack
===========

SwipeBack is a android library that can finish a activity by using gesture.

You can set the swipe direction,such as left or top.

##Screenshots
![image](Images/swipeback.gif)


##Usage

###Layout
```
<com.liuguangqiang.swipeback.SwipeBackLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/swipeBackLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
	
	<!-- SwipeBackLayout must contains only one direct child -->

</com.liuguangqiang.swipeback.SwipeBackLayout>
```

###Theme
```
 <style name="Theme.Swipe.Back" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="windowActionBar">false</item>
        <item name="android:windowNoTitle">true</item>
 </style>
```

###Swipe Direction
You can set  DragEdge to change the swipe direction.

```
 public enum DragEdge {

        TOP,

        LEFT

    }
```