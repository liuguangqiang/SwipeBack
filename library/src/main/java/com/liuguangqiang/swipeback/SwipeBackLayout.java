package com.liuguangqiang.swipeback;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Swipe or Pull to finish a Activity.
 * <p/>
 * This layout must be a root layout and contains only one direct child view.
 * <p/>
 * The activity must use a theme that with translucent style.
 * <style name="Theme.Swipe.Back" parent="AppTheme">
 * <item name="android:windowIsTranslucent">true</item>
 * <item name="android:windowBackground">@android:color/transparent</item>
 * </style>
 * <p/>
 * Created by Eric on 15/1/8.
 */
public class SwipeBackLayout extends ViewGroup {

    private static final String TAG = "SwipeBackLayout";

    public enum DragEdge {

        TOP,

        LEFT

    }

    private DragEdge dragEdge = DragEdge.LEFT;

    public static int BACK_FACTOR = 3;

    private static final double AUTO_FINISHED_SPEED_LIMIT = 2000.0;

    private final ViewDragHelper viewDragHelper;

    private View target;

    private View scrollChild;

    private int verticalDragRange = 0;

    private int horizontalDragRange = 0;

    private int draggingState = 0;

    private int draggingOffset;

    /**
     * Whether allow to pull this layout.
     */
    private boolean enablePullToBack = true;

    /**
     * the anchor of calling finish.
     */
    private int finishAnchor = 0;

    /**
     * Whether allow to finish activty on fling the layout.
     */
    private boolean enableFlingBack = true;

    /**
     * Whether allow to finish activty on fling the layout.
     *
     * @param b
     */
    public void setEnableFlingBack(boolean b) {
        enableFlingBack = b;
    }

    private SwipeBackListener swipeBackListener;

    public void setOnPullToBackListener(SwipeBackListener listener) {
        swipeBackListener = listener;
    }

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallBack());
    }

    public void setEnablePullToBack(boolean b) {
        enablePullToBack = b;
    }

    private void ensureTarget() {
        if (target == null) {
            if (getChildCount() > 1) {
                throw new IllegalStateException("SwipeBackLayout must contains only one direct child");
            }
            target = getChildAt(0);

            if (scrollChild == null && target != null) {
                scrollChild = target;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (getChildCount() == 0) return;

        View child = getChildAt(0);

        int childWidth = width - getPaddingLeft() - getPaddingRight();
        int childHeight = height - getPaddingTop() - getPaddingBottom();
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int childRight = childLeft + childWidth;
        int childBottom = childTop + childHeight;
        child.layout(childLeft, childTop, childRight, childBottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 1) {
            throw new IllegalStateException("SwipeBackLayout must contains only one direct child.");
        }

        if (getChildCount() > 0) {
            int measureWidth = MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY);
            int measureHeight = MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY);
            getChildAt(0).measure(measureWidth, measureHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        verticalDragRange = h;
        horizontalDragRange = w;
        if (dragEdge == DragEdge.TOP) {
            finishAnchor = verticalDragRange / BACK_FACTOR;
        } else if (dragEdge == DragEdge.LEFT) {
            finishAnchor = horizontalDragRange / BACK_FACTOR;
        }
    }

    private int getDragRange() {
        switch (dragEdge) {
            case TOP:
                return verticalDragRange;
            case LEFT:
                return horizontalDragRange;
            default:
                return verticalDragRange;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean handled = false;
        ensureTarget();
        if (isEnabled()) {
            handled = viewDragHelper.shouldInterceptTouchEvent(ev);
        } else {
            viewDragHelper.cancel();
        }
        return !handled ? super.onInterceptTouchEvent(ev) : handled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (scrollChild instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) scrollChild;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return scrollChild.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(scrollChild, -1);
        }
    }

    public boolean canChildScrollDown() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            return ViewCompat.canScrollVertically(scrollChild, 1);
        } else {
            return ViewCompat.canScrollVertically(scrollChild, 1);
        }
    }

    private boolean canChildScrollRight() {
        return ViewCompat.canScrollHorizontally(scrollChild, -1);
    }

    private void finish() {
        Activity act = (Activity) getContext();
        act.finish();
        act.overridePendingTransition(0, android.R.anim.fade_out);
    }

    private class ViewDragHelperCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == target && enablePullToBack;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return verticalDragRange;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return horizontalDragRange;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {

            int result = 0;

            if (dragEdge == DragEdge.TOP && !canChildScrollUp() && top > 0) {
                final int topBound = getPaddingTop();
                final int bottomBound = verticalDragRange;
                result = Math.min(Math.max(top, topBound), bottomBound);
            }

            return result;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            int result = 0;

            if (dragEdge == DragEdge.LEFT && left > 0) {
                final int leftBound = getPaddingLeft();
                final int rightBound = horizontalDragRange;
                result = Math.min(Math.max(left, leftBound), rightBound);
            }

            return result;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            if (state == draggingState) return;

            if ((draggingState == ViewDragHelper.STATE_DRAGGING || draggingState == ViewDragHelper.STATE_SETTLING) &&
                    state == ViewDragHelper.STATE_IDLE) {
                // the view stopped from moving.
                if (draggingOffset == getDragRange()) {
                    finish();
                }
            }

            draggingState = state;
        }


        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {

            boolean verticalDraging = Math.abs(top) > Math.abs(left);
            boolean horizontalDraging = Math.abs(left) > Math.abs(top);

            if (dragEdge == DragEdge.TOP && verticalDraging)
                draggingOffset = Math.abs(top);
            else if (dragEdge == DragEdge.LEFT && horizontalDraging)
                draggingOffset = Math.abs(left);

            //滑动距离到退出锚点的比例。
            float fractionAnchor = (float) draggingOffset / (float) finishAnchor;
            if (fractionAnchor >= 1) fractionAnchor = 1;

            float fractionScreen = (float) draggingOffset / (float) getDragRange();
            if (fractionScreen >= 1) fractionScreen = 1;

            if (swipeBackListener != null) {
                swipeBackListener.onViewPositionChanged(fractionAnchor, fractionScreen);
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (draggingOffset == 0) return;

            if (draggingOffset == getDragRange()) return;

            boolean isBack = false;

            if (enableFlingBack && backBySpeed(xvel, yvel)) {
                isBack = !canChildScrollUp();
            } else if (draggingOffset >= finishAnchor) {
                isBack = true;
            } else if (draggingOffset < finishAnchor) {
                isBack = false;
            }

            if (dragEdge == DragEdge.TOP) {
                int finalTop = isBack ? verticalDragRange : 0;
                smoothScrollToY(finalTop);
            } else if (dragEdge == DragEdge.LEFT) {
                int finalLeft = isBack ? horizontalDragRange : 0;
                smoothScrollToX(finalLeft);
            }
        }
    }

    private boolean backBySpeed(float xvel, float yvel) {
        boolean isBack = false;
        if (dragEdge == DragEdge.TOP && Math.abs(yvel) > Math.abs(xvel)) {
            if (yvel > AUTO_FINISHED_SPEED_LIMIT) {
                isBack = !canChildScrollUp();
            }
        } else if (dragEdge == DragEdge.LEFT && Math.abs(xvel) > Math.abs(yvel)) {
            if (xvel > AUTO_FINISHED_SPEED_LIMIT) {
                isBack = !canChildScrollUp();
            }
        }
        return isBack;
    }

    private void smoothScrollToX(int finalLeft) {
        if (viewDragHelper.settleCapturedViewAt(finalLeft, 0)) {
            ViewCompat.postInvalidateOnAnimation(SwipeBackLayout.this);
        }
    }

    private void smoothScrollToY(int finalTop) {
        if (viewDragHelper.settleCapturedViewAt(0, finalTop)) {
            ViewCompat.postInvalidateOnAnimation(SwipeBackLayout.this);
        }
    }

    public interface SwipeBackListener {

        /**
         * Return scrolled fraction of the layout.
         *
         * @param fractionAnchor relative to finished anchor.
         * @param fractionScreen relative to the screen.
         */
        public void onViewPositionChanged(float fractionAnchor, float fractionScreen);

    }

}
