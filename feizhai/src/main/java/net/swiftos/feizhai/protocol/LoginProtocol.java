package net.swiftos.feizhai.protocol;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.entity.HttpCallback;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.common.protocol.BaseProtocol;
import net.swiftos.feizhai.model.bean.User;

/**
 * Created by ganyao on 2017/3/13.
 */

public interface LoginProtocol {

    interface View extends BaseProtocol.View {

    }

    interface Model {
        IAsyncSubject login(String name, String pass, HttpCallback<User> callback);
    }

    interface Presenter {
        void login(String name, String pass);
    }

}
