package com.liuguangqiang.swiplebacksample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Eric on 15/3/1.
 */
public class TestFragment extends Fragment {

    private static final String EXTRA_TITLE = "EXTRA_TITLE";

    public static TestFragment newInstance(String title) {
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TITLE, title);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        TextView tvTitle = (TextView) getView().findViewById(R.id.tv_test);

        Bundle bundle = getArguments();
        if (bundle.containsKey(EXTRA_TITLE)) {
            tvTitle.setText(bundle.getString(EXTRA_TITLE));
        }
    }

}
