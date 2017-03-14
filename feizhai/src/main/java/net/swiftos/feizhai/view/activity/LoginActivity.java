package net.swiftos.feizhai.view.activity;

import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.view.activity.BaseActivity;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.buss.ServiceManager;
import net.swiftos.feizhai.di.component.DaggerLoginComponent;
import net.swiftos.feizhai.di.component.LoginComponent;
import net.swiftos.feizhai.di.module.LoginModule;
import net.swiftos.feizhai.protocol.LoginProtocol;

import javax.inject.Inject;

/**
 * Created by ganyao on 2017/3/10.
 */

public class LoginActivity extends BaseActivity<LoginComponent> implements LoginProtocol.View {

    public LoginProtocol.Presenter presenter;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.login("gy", "123");
    }

    @Override
    protected int barColor() {
        return R.color.themeColor;
    }

    @Override
    protected LoginComponent setupActivityComponent() {
        return DaggerLoginComponent.builder()
                    .feiZhaiAPIComponent(ServiceManager.getFeiZhaiAPIComponent())
                    .loginModule(new LoginModule(this))
                    .build();
    }

    @Override
    protected BasePresenter setPresenter() {
        presenter = component.presenter();
        return (BasePresenter) presenter;
    }
}
