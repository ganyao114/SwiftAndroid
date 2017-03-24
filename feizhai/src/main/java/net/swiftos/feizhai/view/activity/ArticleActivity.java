package net.swiftos.feizhai.view.activity;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivity;
import net.swiftos.feizhai.di.component.ArticleComponent;
import net.swiftos.feizhai.protocol.ArticleProtocol;

/**
 * Created by ganyao on 2017/3/24.
 */

public class ArticleActivity extends BaseActivity<ArticleComponent> implements ArticleProtocol.View {


    @Override
    protected int getContentLayout() {
        return 0;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int barColor() {
        return 0;
    }

    @Override
    protected ArticleComponent setupActivityComponent() {
        return null;
    }

    @Override
    protected BasePresenter setPresenter() {
        return null;
    }
}
