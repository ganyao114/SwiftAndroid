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

    @Inject
    public IAPIGenerator apiGenerator;

    public Map<String,String> headers = new HashMap<>();
    public Map<String,String> pars = new HashMap<>();
    public APIServiceConfigs configs = new APIServiceConfigs();
    public String url = new String("www.ifanr.com");

    public BaseHttpModel() {
        BaseApplication.getAppComponent().inject(this);
        apiGenerator = BaseApplication.getAppComponent().apiGenerator();
        apiGenerator.setConfigs(configs);
        apiGenerator.setUrl(url);
        apiGenerator.setHeaders(headers);
        apiGenerator.setPars(pars);
    }

}
