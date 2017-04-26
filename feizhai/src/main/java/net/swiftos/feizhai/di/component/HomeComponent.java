package net.swiftos.feizhai.di.component;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.di.module.HomeModule;
import net.swiftos.feizhai.protocol.HomeProtocol;

import dagger.Component;

/**
 * Created by ganyao on 2017/3/15.
 */
@Component(modules = HomeModule.class, dependencies = FeiZhaiAPIComponent.class)
@ActivityScope
public interface HomeComponent {

    void inject(HomeProtocol.Presenter presenter);
    void inject(HomeProtocol.View view);
    void inject(HomeProtocol.Model view);

    HomeProtocol.Presenter presenter();

}
