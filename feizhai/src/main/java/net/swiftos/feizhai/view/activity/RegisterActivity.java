package net.swiftos.feizhai.view.activity;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.buss.ServiceManager;
import net.swiftos.feizhai.di.component.DaggerRegisterComponent;
import net.swiftos.feizhai.di.component.RegisterComponent;
import net.swiftos.feizhai.di.module.RegisterModule;
import net.swiftos.feizhai.protocol.RegisterProtocol;
import net.swiftos.view.anime.CircularAnim;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ganyao on 2017/5/12.
 */

public class RegisterActivity extends BaseFeizhaiActivity<RegisterComponent> implements RegisterProtocol.View {

    RegisterProtocol.Presenter presenter;

    @Bind(R.id.btn_register)
    AppCompatButton registerBtn;
    @Bind(R.id.register_progress)
    ProgressBar registerProgress;

    @Bind(R.id.login_name)
    EditText loginName;
    @Bind(R.id.login_pass)
    EditText loginPass;
    @Bind(R.id.login_pass_check)
    EditText passCheck;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int barColor() {
        return R.color.alpha_gray;
    }


    @OnClick(R.id.btn_register)
    public void doRegister(View view) {
        presenter.register(loginName.getText().toString(), loginPass.getText().toString(), null, null);
    }

    @Override
    protected RegisterComponent setupActivityComponent() {
        return DaggerRegisterComponent.builder()
                .feiZhaiAPIComponent(ServiceManager.getFeiZhaiAPIComponent())
                .registerModule(new RegisterModule(this))
                .build();
    }

    @Override
    protected BasePresenter setPresenter() {
        presenter = component.presenter();
        return (BasePresenter) presenter;
    }

    @Override
    public void showRegisterSuccess() {

    }

    @Override
    public void showRegisterFailure(String msg) {

    }

    @Override
    public void showProgress() {
        CircularAnim.hide(registerBtn).go(() -> registerProgress.setVisibility(View.VISIBLE));
    }

    @Override
    public void dismissProgress() {
        CircularAnim.show(registerBtn).go(() -> registerProgress.setVisibility(View.GONE));
    }
}
