package net.swiftos.feizhai.view.adapter;

import android.content.Context;

import net.swiftos.feizhai.R;
import net.swiftos.feizhai.model.bean.Article;
import net.swiftos.feizhai.model.bean.ArticleInfo;
import net.swiftos.view.multipicture.MultyPicView;
import net.swiftos.view.recyclerview.CommonAdapter;
import net.swiftos.view.recyclerview.ViewHolder;

import java.util.List;

/**
 * Created by ganyao on 2017/5/1.
 */

public class ArticleListAdapter extends CommonAdapter<Article> {

    public ArticleListAdapter(Context context, int layoutId, List<Article> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Article article) {
        MultyPicView picView = holder.getView(R.id.topic_article_preicon);
        holder.setText(R.id.article_title, article.getTitle());
        holder.setText(R.id.writer_name, article.getCtName());
        holder.setText(R.id.article_position, article.getPosition());
        holder.setText(R.id.article_desc, article.getContent());
        picView.setImgs(article.getPics().toArray(new String[article.getPics().size()]));
    }
}
