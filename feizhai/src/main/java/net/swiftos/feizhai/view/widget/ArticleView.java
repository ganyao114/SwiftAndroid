package net.swiftos.feizhai.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.io.Serializable;

/**
 * Created by ganyao on 2017/3/15.
 */

public class ArticleView extends LinearLayout {

    public ArticleView(Context context) {
        super(context);
    }

    public ArticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public class Article implements Serializable {

    }
}
