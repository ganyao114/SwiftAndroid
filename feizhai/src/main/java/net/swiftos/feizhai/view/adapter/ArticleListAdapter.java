package net.swiftos.feizhai.view.adapter;

import android.content.Context;

import net.swiftos.feizhai.R;
import net.swiftos.feizhai.model.bean.ArticleInfo;
import net.swiftos.feizhai.view.widget.article.ArticleView;
import net.swiftos.view.multipicture.MultyPicView;
import net.swiftos.view.recyclerview.CommonAdapter;
import net.swiftos.view.recyclerview.ViewHolder;

import java.util.List;

/**
 * Created by ganyao on 2017/5/1.
 */

public class ArticleListAdapter extends CommonAdapter<ArticleInfo> {

    public ArticleListAdapter(Context context, int layoutId, List<ArticleInfo> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, ArticleInfo articleInfo) {
        MultyPicView picView = holder.getView(R.id.topic_article_preicon);
        picView.setMaxChildCount(articleInfo.getPreViewPics().length);
        picView.setImgs(articleInfo.getPreViewPics());
    }
}
