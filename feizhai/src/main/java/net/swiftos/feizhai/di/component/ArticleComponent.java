package net.swiftos.feizhai.di.component;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.di.module.ArticleModule;
import net.swiftos.feizhai.di.module.LoginModule;
import net.swiftos.feizhai.protocol.ArticleProtocol;

import dagger.Component;

/**
 * Created by ganyao on 2017/3/14.
 */
@Component(modules = ArticleModule.class, dependencies = FeiZhaiAPIComponent.class)
@ActivityScope
public interface ArticleComponent {

    void inject(ArticleProtocol.Presenter presenter);
    void inject(ArticleProtocol.View view);
    void inject(ArticleProtocol.Model view);

    ArticleProtocol.Presenter presenter();
}
