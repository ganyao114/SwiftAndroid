package net.swiftos.view.articleview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.swiftos.common.application.BaseApplication;

import java.util.List;

/**
 * Created by ganyao on 2017/3/24.
 */

public class ArticleView extends LinearLayout implements IArticleView, ArticleTextView.UrlClickListener
        , ArticleViewListener, View.OnClickListener, View.OnLongClickListener {

    private ArticleTextView.UrlClickListener urlClickListener;
    private OnClickListener onImageViewClickListener;
    private ArticleViewListener articleViewListener;

    public ArticleView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public ArticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public ArticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    public ArticleTextView.UrlClickListener getUrlClickListener() {
        return urlClickListener;
    }

    public void setUrlClickListener(ArticleTextView.UrlClickListener urlClickListener) {
        this.urlClickListener = urlClickListener;
    }

    public ArticleViewListener getArticleViewListener() {
        return articleViewListener;
    }

    public void setArticleViewListener(ArticleViewListener articleViewListener) {
        this.articleViewListener = articleViewListener;
    }

    @Override
    public void setArticle(List<IArticleViewAdapter> articleItems) {
        if (articleItems == null) return;
        for (IArticleViewAdapter item:articleItems) {
            switch (item.getType()) {
                case String:
                    ArticleTextView articleTextView = new ArticleTextView(getContext());
                    articleTextView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    articleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16); //16sp
                    articleTextView.setTextColor(Color.parseColor("#d1000000"));
                    articleTextView.showText(item.getContent());
                    articleTextView.setUrlClickListener(this);
                    addView(articleTextView);
                    break;
                case Img:
                    WindowManager wm = ((Activity)getContext()).getWindowManager();
                    int width = wm.getDefaultDisplay().getWidth();
                    ImageView imageView = new ImageView(getContext());
                    imageView.setAdjustViewBounds(true);//设置图片自适应，只是这句话必须结合下面的setMaxWidth和setMaxHeight才能有效果。
//下面必须使用LinearLayout，如果使用ViewGroup的LayoutParams，则会报空指针异常。
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            width, LinearLayout.LayoutParams.WRAP_CONTENT);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setMaxWidth(width);
                    imageView.setMaxHeight(width * 3);// 这里其实可以根据需求而定，我这里测试为最大宽度的5倍
                    imageView.setTag(item.getContent());
                    imageView.setOnClickListener(this);
                    imageView.setOnLongClickListener(this);
                    addView(imageView);
                    BaseApplication.getAppComponent()
                            .imageLoader()
                            .context(getContext())
                            .load(item.getContent(), imageView);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onUrlClick(String url) {
        if (urlClickListener != null) {
            urlClickListener.onUrlClick(url);
        }
    }

    @Override
    public void onImageClick(String url) {
        if (articleViewListener != null) {
            articleViewListener.onImageClick(url);
        }
    }

    @Override
    public void onImageLongClick(String url) {
        if (articleViewListener != null) {
            articleViewListener.onImageLongClick(url);
        }
    }

    @Override
    public void onClick(View v) {
        onImageClick((String) v.getTag());
    }

    @Override
    public boolean onLongClick(View v) {
        onImageLongClick((String) v.getTag());
        return false;
    }
}
