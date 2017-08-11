package net.swiftos.common.model.net;

import net.swiftos.common.application.BaseApplication;

import javax.inject.Inject;

/**
 * Created by ganyao on 2017/3/9.
 */

public abstract class BaseHttpModel {

    public IBaseHttpModel baseModel;

    public BaseHttpModel() {
        baseModel = BaseApplication.getAppComponent().getBaseHttpModel();
        baseModel.setBaseResponse(setBaseReponse());
    }

    protected IResponseAdapter setBaseReponse() {
        return CommonResponseAdapter.getInstance();
    }

}
