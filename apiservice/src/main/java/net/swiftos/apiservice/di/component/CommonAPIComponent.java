package net.swiftos.apiservice.di.component;

import net.swiftos.apiservice.api.ICommonAPI;
import net.swiftos.apiservice.di.module.CommonAPIModule;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.module.BaseAPIModule;
import net.swiftos.common.di.scope.ServiceScope;
import net.swiftos.common.model.entity.Session;
import net.swiftos.common.model.net.BaseHttpModel;

import dagger.Component;

/**
 * Created by ganyao on 2017/3/9.
 */
@ServiceScope
@Component(modules = CommonAPIModule.class, dependencies = AppComponent.class)
public interface CommonAPIComponent {

    void inject(BaseHttpModel model);

    ICommonAPI getAPI();

    Session getSession();

}
