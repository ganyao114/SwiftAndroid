package net.swiftos.feizhai.model.net;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.feizhai.protocol.LoginProtocol;

/**
 * Created by ganyao on 2017/3/14.
 */

public class LoginModel extends FeiZhaiHttpModel implements LoginProtocol.Model {



    public LoginModel() {
        super();
    }

    @Override
    public IAsyncSubject login(String name, String pass, AsyncCallback<UserInfo> callback) {
        return baseModel.getAsyncSubject(api.login(name, pass).cache(), callback);
    }

}
