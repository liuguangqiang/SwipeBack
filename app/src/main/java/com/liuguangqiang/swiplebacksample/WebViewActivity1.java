package com.liuguangqiang.swiplebacksample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

/**
 * Created by Eric on 15/6/1.
 */
public class WebViewActivity1 extends SwipeBackActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        setDragEdge(SwipeBackLayout.DragEdge.TOP);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_webview);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://github.com");
    }

}
