package net.swiftos.common.model.net;

import net.swiftos.common.application.BaseApplication;

import javax.inject.Inject;

/**
 * Created by ganyao on 2017/3/9.
 */

public abstract class BaseHttpModel {

    @Inject
    public IBaseHttpModel baseModel;

    public BaseHttpModel() {
        BaseApplication.getAppComponent().inject(this);
        baseModel.setBaseResponse(setBaseReponse());
    }

    protected abstract IResponseAdapter setBaseReponse();

}
