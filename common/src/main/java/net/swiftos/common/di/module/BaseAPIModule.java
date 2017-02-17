package net.swiftos.common.di.module;

import net.swiftos.common.api.BasicParamsInterceptor;
import net.swiftos.common.model.entity.APIServiceConfigs;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by gy939 on 2017/1/15.
 */

public abstract class BaseAPIModule {

    protected HttpUrl BASE_URL;
    private Map<String,String> headers, pars;
    private APIServiceConfigs configs;

    protected OkHttpClient okHttpClient;
    protected Retrofit retrofit;

    protected void init(String url) {
        BASE_URL = HttpUrl.parse(url);
    }

    public void setPars(Map<String, String> pars) {
        this.pars = pars;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setConfigs(APIServiceConfigs configs) {
        this.configs = configs;
    }

    protected <T> T  getAPIService(Class<T> tClass) {
        return getRetrofit().create(tClass);
    }

    protected OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            if (configs == null) {
                configs = new APIServiceConfigs();
            }
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new BasicParamsInterceptor(headers, pars, configs.getInterceptor()))
                    .connectTimeout(configs.getConnectTimeout(), TimeUnit.MILLISECONDS)
                    .readTimeout(configs.getReadTimeout(), TimeUnit.MILLISECONDS)
                    .build();
        }
        return okHttpClient;
    }

    protected Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().client(getOkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
