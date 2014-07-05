SwipeFinish
===========

A small android library for swiping to finish activity. 

##Screenshots
![image](Images/swipe_finish.gif)

![ScreenShot](Images/swipe.avi)

<object width="480" height="385"><param name="movie" value="Images/swipe.avi;hl=en_US&amp;fs=1"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="http://www.youtube.com/v/jJECepNeCJ0&amp;hl=en_US&amp;fs=1" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="480" height="385"></embed>
</object>

##Quick Start
###Application
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.johnliu.swipefinish.sample"
    android:versionCode="1"
    android:versionName="1.0.0" >
    
    ...
    
    <application
        android:allowBackup="true" ... >
      	...
        <activity
            android:name=".CommonActivity"
            android:theme="@style/Theme.Transparent" >
        </activity>
    </application>

</manifest>
```
###Style
```
<style name="Theme.Transparent" parent="AppTheme">
     <item name="android:windowBackground">@android:color/transparent</item>
     <item name="android:colorBackgroundCacheHint">@null</item>
     <item name="android:windowIsTranslucent">true</item>
</style>
```
###Common Activity.
```
public class CommonActivity extends SwipeFinishActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common);
	}
	
}
```
###With a ViewPager.
```
public class ViewPagerActivity extends SwipeFinishActivity {

	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		initView();
	}

	private void initView() {
		SimplePagerAdapter adapter = new SimplePagerAdapter(
				getSupportFragmentManager());
		viewPager = (ViewPager) this.findViewById(R.id.view_pager);
		viewPager.setAdapter(adapter);
		interceptViewPager(viewPager);
	}

	private class SimplePagerAdapter extends FragmentPagerAdapter {

		public SimplePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return new TestFragment();
		}

		@Override
		public int getCount() {
			return 4;
		}
	}

}
```
