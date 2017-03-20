package net.swiftos.feizhai.presenter;

import android.view.View;
import android.widget.Toast;

import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;
import net.swiftos.common.model.entity.HttpCallback;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.common.viewevent.ClickCheck;
import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.impls.viewevent.annotation.OnClick;
import net.swiftos.eventposter.impls.viewevent.handler.ViewEventHandler;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.model.bean.User;
import net.swiftos.feizhai.protocol.LoginProtocol;
import net.swiftos.usermodule.UserManager;

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
        addSubject(model.login(name, pass, new HttpCallback<User>() {
            @Override
            public Object getTag() {
                return name;
            }

            @Override
            public void onSuccess(User user) {
                UserManager.login(user);
            }

            @Override
            public void onFailure(FailureEntity failure) {
                view.showMessage("success");
            }

            @Override
            public void onError(ErrorResponse error) {
                view.showMessage("error");
            }

            @Override
            public void onComplete() {
                view.dismissProgress();
                view.unLockUI();
            }
        }));
        view.showProgress();
        view.lockUI();
    }

}
