package com.johnliu.swipefinish.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.johnliu.swipefinish.core.SwipeFinishActivity;

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