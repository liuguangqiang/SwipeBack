package com.liuguangqiang.swiplebacksample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.liuguangqiang.swipeback.SwipeBackLayout;
import com.liuguangqiang.swiplebacksample.adapter.TestAdapter;

/**
 * Created by Eric on 15/2/27.
 */
public class ViewPagerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initViews();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_viewpager);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        SwipeBackLayout swipeBackLayout = (SwipeBackLayout) findViewById(R.id.swipeBackLayout);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_demo);
        TestAdapter adapter = new TestAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }

}
