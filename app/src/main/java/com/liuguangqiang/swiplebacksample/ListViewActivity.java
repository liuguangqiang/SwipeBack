package com.liuguangqiang.swiplebacksample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
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
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }

    private void initData() {
        data = new ArrayList<>();
        HashMap<String, String> map;
        for (int i = 0; i < 30; i++) {
            map = new HashMap<>();
            map.put("title", "Test" + i);
            data.add(map);
        }
    }

    float lastY = 0;
    float newY = 0;
    float offsetY = 0;

    float lastX = 0;
    float newX = 0;
    float offsetX = 0;

    private void initView() {
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

//        lv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    lastY = motionEvent.getRawY();
//                    lastX = motionEvent.getRawX();
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
//                    newY = motionEvent.getRawY();
//                    lastX = motionEvent.getRawX();
//
//                    offsetY = Math.abs(newY - lastY);
//                    lastY = newY;
//
//                    offsetX = Math.abs(newX - lastX);
//                    lastX = newX;
//
//                    getSwipeBackLayout().setEnablePullToBack(offsetY < offsetX);
//                }
//
//                return false;
//            }
//        });


    }

}
