package net.swiftos.common.api;

import net.swiftos.utils.ValidateUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gy939 on 2017/1/15.
 */

public class BasicParamsInterceptor implements Interceptor {

    private Map<String,String> headers;
    private Map<String,String> pars;
    private ICallback callback;

    public BasicParamsInterceptor(Map<String, String> headers, Map<String, String> pars, ICallback callback) {
        this.headers = headers;
        this.pars = pars;
        this.callback = callback;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();

        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

        if (!ValidateUtil.isEmpty(pars)) {
            for (Map.Entry<String, String> entry : pars.entrySet()) {
                authorizedUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        Request.Builder newRequestBuilder = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build());

        if (!ValidateUtil.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                newRequestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if (callback != null) {
            return chain.proceed(callback.covert(chain, newRequestBuilder));
        } else {
            return chain.proceed(newRequestBuilder.build());
        }
    }

    public interface ICallback {
        Request covert(Chain chain, Request.Builder builder);
    }

}
