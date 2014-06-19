SwipeFinish
===========

A android library. Swipe to finish Activity.

##Quick Start
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
###With a ViewPager
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
