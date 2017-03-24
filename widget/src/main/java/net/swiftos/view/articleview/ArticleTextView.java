package net.swiftos.view.articleview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ganyao on 2017/3/24.
 */

public class ArticleTextView extends HttpTextView {

    public ArticleTextView(Context context) {
        super(context);
        init();
    }

    public ArticleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArticleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Emojiconize.view(this).go();
    }

    public void showText(CharSequence str) {
        setUrlText(str);
    }

}
