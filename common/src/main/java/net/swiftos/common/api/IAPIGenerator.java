package net.swiftos.common.api;

import net.swiftos.common.model.entity.APIServiceConfigs;

import java.util.Map;

/**
 * Created by ganyao on 2017/3/9.
 */

public interface IAPIGenerator {
    void setUrl(String url);
    void setHeaders(Map<String,String> headers);
    void setPars(Map<String,String> pars);
    void setConfigs(APIServiceConfigs configs);
    <T> T getAPI(Class<T> t);
}
