package net.swiftos.view.selectwindow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import net.swiftos.view.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ganyao on 2017/8/16.
 */

public abstract class BaseSelectWindow extends PopupWindow implements View.OnClickListener {

    private View menuView;

    private Map<Integer, Button> views = new HashMap();

    private View.OnClickListener listener;

    public BaseSelectWindow(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        menuView = inflater.inflate(layoutId(), null);

        for (@IdRes int id:itemsId()) {
            Button button = menuView.findViewById(id);
            button.setOnClickListener(this);
            views.put(id, button);
        }

        this.setContentView(menuView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.PopupAnimation);
        ColorDrawable dw = new ColorDrawable(0x80000000);
        this.setBackgroundDrawable(dw);
        menuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = menuView.findViewById(popLayoutId()).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public Button getItem(@IdRes int id) {
        return views.get(id);
    }

    @Override
    public void onClick(View view) {
        dismiss();
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void show() {
        Activity activity = (Activity) menuView.getContext();
        showAtLocation(activity.getWindow().getDecorView(),
                Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    protected abstract @IdRes int layoutId();

    protected abstract @IdRes int popLayoutId();

    protected abstract @IdRes int[] itemsId();

}
