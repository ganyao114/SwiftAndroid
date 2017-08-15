package net.swiftos.common.model.net;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.di.component.AppComponent;
import net.swiftos.common.di.component.ComponentManager;
import net.swiftos.common.exception.CommonExceptionHandler;
import net.swiftos.common.exception.IExceptionHandler;

/**
 * Created by ganyao on 2017/3/9.
 */

public abstract class BaseHttpModel {

    public IBaseHttpModel baseModel;

    public BaseHttpModel() {
        baseModel = ComponentManager.getStaticComponent(AppComponent.class).getBaseHttpModel();
        baseModel.setBaseResponse(baseResponse());
        baseModel.setExceptionHandler(exceptionHandler());
    }

    protected IResponseAdapter baseResponse() {
        return CommonResponseAdapter.getInstance();
    }

    protected IExceptionHandler exceptionHandler () {
        return CommonExceptionHandler.getInstance();
    }

}
