package net.swiftos.feizhai.di.component;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.di.module.ArticleModule;
import net.swiftos.feizhai.di.module.TopicModule;
import net.swiftos.feizhai.protocol.ArticleProtocol;
import net.swiftos.feizhai.protocol.TopicProtocol;

import dagger.Component;

/**
 * Created by ganyao on 2017/3/14.
 */
@Component(modules = TopicModule.class, dependencies = FeiZhaiAPIComponent.class)
@ActivityScope
public interface TopicComponent {

    void inject(TopicProtocol.Presenter presenter);
    void inject(TopicProtocol.View view);
    void inject(TopicProtocol.Model view);

    TopicProtocol.Presenter presenter();
}
