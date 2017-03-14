package net.swiftos.feizhai.di.component;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.di.module.LoginModule;
import net.swiftos.feizhai.protocol.LoginProtocol;

import dagger.Component;

/**
 * Created by ganyao on 2017/3/14.
 */
@Component(modules = LoginModule.class, dependencies = FeiZhaiAPIComponent.class)
@ActivityScope
public interface LoginComponent {

    void inject(LoginProtocol.Presenter presenter);
    void inject(LoginProtocol.View view);
    void inject(LoginProtocol.Model view);

    LoginProtocol.Presenter presenter();
}
