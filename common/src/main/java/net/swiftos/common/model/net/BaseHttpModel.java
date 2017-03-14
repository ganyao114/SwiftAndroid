package net.swiftos.common.model.net;

import net.swiftos.common.api.IAPIGenerator;
import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.model.entity.APIServiceConfigs;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ganyao on 2017/3/9.
 */

public class BaseHttpModel {

    @Inject
    public IBaseHttpModel baseModel;

    public BaseHttpModel() {
        BaseApplication.getAppComponent().inject(this);
    }

}
