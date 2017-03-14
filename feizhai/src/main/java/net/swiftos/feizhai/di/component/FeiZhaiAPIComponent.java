package net.swiftos.feizhai.di.component;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.scope.ServiceScope;
import net.swiftos.common.model.entity.Session;
import net.swiftos.common.model.net.BaseHttpModel;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivity;
import net.swiftos.feizhai.api.IFeiZhaiAPI;
import net.swiftos.feizhai.di.module.FeiZhaiAPIModule;

import dagger.Component;

/**
 * Created by ganyao on 2017/3/14.
 */
@Component(modules = FeiZhaiAPIModule.class, dependencies = AppComponent.class)
@ServiceScope
public interface FeiZhaiAPIComponent {

    void inject(BaseHttpModel model);

    void inject(BasePresenter presenter);

    IFeiZhaiAPI getAPI();

    Session getSession();

}
