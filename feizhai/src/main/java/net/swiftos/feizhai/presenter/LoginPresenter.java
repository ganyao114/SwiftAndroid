package net.swiftos.feizhai.presenter;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;
import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.model.entity.BaseAsyncCallback;
import net.swiftos.common.model.entity.Session;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.user.RegisteredEvent;
import net.swiftos.common.user.di.UserManagerComponent;
import net.swiftos.common.viewevent.ClickCheck;
import net.swiftos.feizhai.di.component.FeiZhaiAPIComponent;
import net.swiftos.feizhai.model.bean.User;
import net.swiftos.feizhai.protocol.LoginProtocol;
import net.swiftos.common.user.UserManager;

/**
 * Created by ganyao on 2017/3/14.
 */

public class LoginPresenter extends BasePresenter implements LoginProtocol.Presenter {


    LoginProtocol.Model model;
    LoginProtocol.View view;


    public LoginPresenter(LoginProtocol.Model model, LoginProtocol.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onViewInited() {
    }

    @Override
    public <T> void onNavigate(T data) {

    }

    @ClickCheck
    @Override
    public void login(String name, String pass) {
        addSubject(model.login(name, pass, new BaseAsyncCallback<UserInfo>() {

            @Override
            public void onSuccess(UserInfo userInfo) {

                Session session = view.getComponent().session();
                User user = new User();
                user.setSessionId(userInfo.getSessionId());
                user.setKey(userInfo.getLoginname());
                user.setUserInfo(userInfo);
                user.processSession(session);
                user.setSession(session);
                ComponentManager.getStaticComponent(UserManagerComponent.class)
                        .userManager()
                        .login(new User());
                view.showMessage("login success!");
                view.finish();
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
                view.unLockUI();
            }
        }));
        view.showProgress();
        view.lockUI();
    }

}
