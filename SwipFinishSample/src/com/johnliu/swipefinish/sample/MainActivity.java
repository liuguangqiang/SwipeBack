package com.johnliu.swipefinish.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnTest;

	private Button btnListView;

	private Button btnViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_common:
			startActivity(new Intent(getApplicationContext(),
					CommonActivity.class));
			break;
		case R.id.btn_listview:
			startActivity(new Intent(getApplicationContext(),
					ListViewActivity.class));
			break;
		case R.id.btn_viewpager:
			startActivity(new Intent(getApplicationContext(),
					ViewPagerActivity.class));
			break;
		default:
			break;
		}
	}

	private void initViews() {
		btnTest = (Button) this.findViewById(R.id.btn_common);
		btnListView = (Button) this.findViewById(R.id.btn_listview);
		btnViewPager = (Button) this.findViewById(R.id.btn_viewpager);
		btnTest.setOnClickListener(this);
		btnListView.setOnClickListener(this);
		btnViewPager.setOnClickListener(this);
	}

}
