package net.swiftos.feizhai.di.component;

import net.swiftos.common.di.scope.ActivityScope;
import net.swiftos.feizhai.di.module.LoginModule;
import net.swiftos.feizhai.di.module.RegisterModule;
import net.swiftos.feizhai.protocol.LoginProtocol;
import net.swiftos.feizhai.protocol.RegisterProtocol;

import dagger.Component;

/**
 * Created by ganyao on 2017/3/14.
 */
@Component(modules = RegisterModule.class, dependencies = FeiZhaiAPIComponent.class)
@ActivityScope
public interface RegisterComponent {

    void inject(RegisterProtocol.Presenter presenter);
    void inject(RegisterProtocol.View view);
    void inject(RegisterProtocol.Model view);

    RegisterProtocol.Presenter presenter();
}
