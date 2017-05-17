package net.swiftos.feizhai.protocol;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.common.protocol.BaseProtocol;
import net.swiftos.feizhai.di.component.LoginComponent;

/**
 * Created by ganyao on 2017/3/13.
 */

public interface LoginProtocol {

    interface View extends BaseProtocol.View<LoginComponent>, BaseProtocol.ProgressView {
        void showLoginSuccess();
        void showLoginFailure(String msg);
    }

    interface Model {
        IAsyncSubject login(String name, String pass, AsyncCallback<UserInfo> callback);
    }

    interface Presenter {
        void login(String name, String pass);
    }

}
