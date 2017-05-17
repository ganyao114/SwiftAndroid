package net.swiftos.feizhai.view.behavior;


import android.content.Context;
import android.graphics.Interpolator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import net.swiftos.feizhai.R;

/**
 * Created by ganyao on 2017/5/12.
 */

public class FlatButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private float viewY;//控件距离coordinatorLayout底部距离
    private boolean isAnimate;//动画是否在进行

    public FlatButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {

        if (child.getVisibility() == View.VISIBLE && viewY == 0) {
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewY = coordinatorLayout.getHeight() - child.getY();
        }

        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//判断是否竖直滚动
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
        //大于0是向上滚动 小于0是向下滚动

        if (dy >= 0 && !isAnimate && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (dy < 0 && !isAnimate && child.getVisibility() == View.GONE) {
            child.show();
        }
    }

}
