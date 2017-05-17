package net.swiftos.feizhai.model.net;

import net.swiftos.apiservice.model.UserInfo;
import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.model.net.BaseHttpModel;
import net.swiftos.common.model.net.CommonResponseAdapter;
import net.swiftos.common.model.net.IResponseAdapter;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.feizhai.protocol.RegisterProtocol;

/**
 * Created by ganyao on 2017/5/12.
 */

public class RegisterModel extends FeiZhaiHttpModel implements RegisterProtocol.Model {
    @Override
    protected IResponseAdapter setBaseReponse() {
        return CommonResponseAdapter.getInstance();
    }

    @Override
    public IAsyncSubject register(String name, String pass, String email, String phone, AsyncCallback<String> callback) {
        return baseModel.getAsyncSubject(api.register(name, pass).cache(), callback);
    }
}
