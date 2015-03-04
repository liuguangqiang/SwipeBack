package com.liuguangqiang.swiplebacksample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

public class CommonActivity extends SwipeBackActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_common);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }

}
