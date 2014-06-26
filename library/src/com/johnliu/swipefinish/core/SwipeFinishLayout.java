package com.johnliu.swipefinish.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.johnliu.swipefinish.R;

/**
 * SwipeFinishLayout
 * <p>
 * Use this Layout,you can make a Activity with swiping to finish.
 * <p>
 * Then thanks for xiaanming's blog.
 * 
 * Created by John on 2014-6-18
 */
public class SwipeFinishLayout extends FrameLayout implements OnTouchListener {

    private static final String TAG = "SwipeFinish";

    /**
     * If the speed of moving Activity is more than MIN_SPEED,then finish the Activity.
     */
    private static final int MIN_SPEED = 500;

    /**
     * The speed of moving at the horizontal direction.
     */
    private float speedX = 0;

    private float downX = 0;

    private float lastX = 0;

    private float lastY;

    private View root;

    private Scroller mScroller;

    private Activity mActivity;

    /**
     * A VelocityTracker.
     */
    private VelocityTracker mVelocityTracker = null;

    private ViewPager mViewPager;

    /**
     * Distance in pixels a touch can wander before we think the user is scrolling.
     * {@link ViewConfiguration#getScaledTouchSlop()}
     */
    private int mScaledTouchSlop;

    /**
     * Whether finish the Activity after scrolling.
     */
    private boolean isFinish = false;

    public SwipeFinishLayout(Context context) {
        super(context);
        init();
    }

    public SwipeFinishLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeFinishLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.setOnTouchListener(this);
        mScroller = new Scroller(getContext());
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public void init(Activity act) {
        // get the root view of Activity
        mActivity = act;
        root = (View) act.getWindow().getDecorView();
    }

    private Drawable myShadow;

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (root != null) {
            if (myShadow == null) myShadow = getResources().getDrawable(R.drawable.shadow_left);
            int left = -10;
            int right = -20;
            int top = root.getTop();
            int bottom = root.getBottom();
            myShadow.setBounds(left, top, right, bottom);
            myShadow.draw(canvas);
        }
    }

    /**
     * Intercept a ViewPager.
     * <p>
     * To avoid conflict.
     * 
     * @param viewPager
     */
    public void interceptViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mViewPager != null && mViewPager.getCurrentItem() != 0) {
                    // If the ViewPager isn't selected the first item .
                    // Return false.Then all events will passed to the ViewPager
                    // continue.
                    return false;
                }
                downX = event.getRawX();
                lastX = downX;
                lastY = event.getRawY();
                createVelocityTracker(event);
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = event.getRawX() - lastX;
                float deltaY = event.getRawY() - lastY;
                deltaY = Math.abs(deltaY);
                if (deltaX > mScaledTouchSlop && deltaY < mScaledTouchSlop) {
                    // Return true. Then all events will passed to the method
                    // "onTouch".
                    return true;
                }
                break;

            default:
                break;
        }

        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouch:ACTION_DOWN");
                downX = event.getRawX();
                lastX = downX;
                createVelocityTracker(event);
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouch:ACTION_UP");
                if (root.getScrollX() < -getWidth() / 2)
                    scrollToFinish();
                else {
                    if (speedX > MIN_SPEED)
                        scrollToFinish();
                    else
                        scrollToOrigin();
                }
                mVelocityTracker.recycle();
                break;
            case MotionEvent.ACTION_MOVE:
                float newX = event.getRawX();
                if (newX < downX) break;

                float deltaX = lastX - newX;
                lastX = newX;
                root.scrollBy((int) deltaX, 0);
                mVelocityTracker.computeCurrentVelocity(1000);
                mVelocityTracker.addMovement(event);
                if (mVelocityTracker.getXVelocity() > 0) speedX = mVelocityTracker.getXVelocity();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Create a VelocityTracker.
     * 
     * @param event
     */
    public void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * scroll to finish
     */
    private void scrollToFinish() {
        isFinish = true;
        final int delta = (getWidth() + root.getScrollX());
        mScroller.startScroll(root.getScrollX(), 0, -delta + 1, 0, Math.abs(delta));
        postInvalidate();
    }

    /**
     * scroll to origin
     */
    private void scrollToOrigin() {
        isFinish = false;
        final int delta = root.getScrollX();
        mScroller.startScroll(root.getScrollX(), 0, -delta, 0, Math.abs(delta));
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        /*
         * When call Scroller.startScroll, the method "Scroller.computeScrollOffset()" will return
         * true.
         */
        if (mScroller.computeScrollOffset()) {
            root.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
            if (mScroller.isFinished() && isFinish) {
                mActivity.finish();
            }
        }
    }

}
