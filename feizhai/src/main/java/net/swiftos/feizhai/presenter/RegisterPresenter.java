package net.swiftos.feizhai.presenter;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;
import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.model.entity.BaseAsyncCallback;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.user.RegisteredEvent;
import net.swiftos.feizhai.protocol.RegisterProtocol;
import net.swiftos.feizhai.protocol.TopicProtocol;

/**
 * Created by ganyao on 2017/4/27.
 */

public class RegisterPresenter extends BasePresenter implements RegisterProtocol.Presenter {

    RegisterProtocol.Model model;
    RegisterProtocol.View view;

    public RegisterPresenter(RegisterProtocol.View view, RegisterProtocol.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public <T> void onNavigate(T data) {

    }

    @Override
    public void register(String name, String pass, String email, String phone) {
        view.showProgress();
        model.register(name, pass, null, null, new BaseAsyncCallback<String>() {
            @Override
            public Object getTag() {
                return name;
            }

            @Override
            public void onSuccess(String info) {
                view.showMessage(info);
                view.finish();
                ComponentManager.getStaticComponent(AppComponent.class)
                        .eventHub()
                        .post(new RegisteredEvent());
            }

            @Override
            public void onFailure(FailureEntity failure) {
                view.showMessage(failure.getCause().getMessage());
            }

            @Override
            public void onError(ErrorResponse error) {
                view.showMessage(error.getCause().getMessage());
            }

            @Override
            public void onDone(Object tag) {
                view.dismissProgress();
            }
        });
    }
}
