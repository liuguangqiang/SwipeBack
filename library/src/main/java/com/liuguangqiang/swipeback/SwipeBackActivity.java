package com.liuguangqiang.swipeback;

import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Eric on 15/3/3.
 */
public class SwipeBackActivity extends ActionBarActivity {

     SwipeBackLayout swipeBackLayout;

    @Override
    public void setContentView(int layoutResID) {
        swipeBackLayout = new SwipeBackLayout(this);
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        swipeBackLayout.addView(view);



        super.setContentView(swipeBackLayout);
    }

    private void initContent(){

    }

    public void setDragEdge(SwipeBackLayout.DragEdge dragEdge) {
        swipeBackLayout.setDragEdge(dragEdge);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackLayout;
    }

}
