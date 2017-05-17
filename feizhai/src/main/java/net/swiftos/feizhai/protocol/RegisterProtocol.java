package net.swiftos.feizhai.protocol;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.common.protocol.BaseProtocol;

/**
 * Created by ganyao on 2017/3/13.
 */

public interface RegisterProtocol {

    interface View extends BaseProtocol.View, BaseProtocol.ProgressView {
        void showRegisterSuccess();
        void showRegisterFailure(String msg);
    }

    interface Model {
        IAsyncSubject register(String name, String pass,String email
                , String phone, AsyncCallback<String> callback);
    }

    interface Presenter extends BaseProtocol.Presenter {
        void register(String name, String pass,String email
                , String phone);
    }

}
