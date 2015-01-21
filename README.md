SwipeBack
===========

SwipeBack is a android library that can finish a activity by using gesture.

You can set the swipe direction,such as left or top.

##Screenshots
![image](Images/swipeback.gif)


##Usage

###Gradle
```
dependencies {
   	compile 'com.github.liuguangqiang.swipeback:library:0.1.1@aar'
}
```

###Maven
```
<dependency>
  	<groupId>com.github.liuguangqiang.swipeback</groupId>
  	<artifactId>library</artifactId>
  	<version>0.1.0</version>
  	<type>aar</type>
</dependency>
```
###Layout and activity theme.
SwipeBackLayout must contains only one direct child.

```
<com.liuguangqiang.swipeback.SwipeBackLayout
	xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/swipeBackLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
	
	<!-- SwipeBackLayout must contains only one direct child -->

</com.liuguangqiang.swipeback.SwipeBackLayout>
```

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