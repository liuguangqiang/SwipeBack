package com.liuguangqiang.swiplebacksample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListViewActivity extends SwipeBackActivity {

    private ListView lv;

    private List<HashMap<String, String>> data;

    private SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_list_view);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        initData();
        initView();
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", "Test" + i);
            data.add(map);
        }
    }

    private void initView() {
        swipeBackLayout = (SwipeBackLayout) findViewById(R.id.swipeBackLayout);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.TOP);

        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
                data, R.layout.item_test, new String[]{"title"},
                new int[]{R.id.tv_test});
        lv = (ListView) this.findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
