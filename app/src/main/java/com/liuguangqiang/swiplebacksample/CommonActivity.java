package com.liuguangqiang.swiplebacksample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.liuguangqiang.swipeback.SwipeBackLayout;

public class CommonActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private SwipeBackLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_common);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        layout = (SwipeBackLayout) findViewById(R.id.swipe_layout);
        layout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }

}
