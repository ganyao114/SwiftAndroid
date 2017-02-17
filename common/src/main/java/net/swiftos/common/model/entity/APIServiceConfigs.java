package net.swiftos.common.model.entity;

import net.swiftos.common.api.BasicParamsInterceptor;

/**
 * Created by gy939 on 2017/1/15.
 */

public class APIServiceConfigs {

    private Integer connectTimeout = 5 * 1000;
    private Integer readTimeout = 5 * 1000;
    private BasicParamsInterceptor.ICallback interceptor;

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public BasicParamsInterceptor.ICallback getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(BasicParamsInterceptor.ICallback interceptor) {
        this.interceptor = interceptor;
    }
}
