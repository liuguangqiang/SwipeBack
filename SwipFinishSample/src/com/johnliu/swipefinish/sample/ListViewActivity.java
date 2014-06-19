package com.johnliu.swipefinish.sample;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.johnliu.swipefinish.core.SwipeFinishActivity;

public class ListViewActivity extends SwipeFinishActivity {

	private ListView lv;

	ArrayList<HashMap<String, String>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		initData();
		initView();
	}

	private void initData() {

		data = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 30; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", "Test" + i);
			data.add(map);
		}
	}

	private void initView() {

		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
				data, R.layout.item_test, new String[] { "title" },
				new int[] { R.id.tv_test });

		lv = (ListView) this.findViewById(R.id.listview);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						data.get(position).get("title"), Toast.LENGTH_LONG)
						.show();
			}
		});
	}
}