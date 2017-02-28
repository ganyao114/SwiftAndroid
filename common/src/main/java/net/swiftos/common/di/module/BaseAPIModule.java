package net.swiftos.common.di.module;

import net.swiftos.common.api.BasicParamsInterceptor;
import net.swiftos.common.model.entity.APIServiceConfigs;

import java.util.HashMap;
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
    protected Session session;

    protected void init(String url) {
        BASE_URL = HttpUrl.parse(url);
    }

    public Session generateSession() {
        session = new Session();
        headers = session.getHeaders();
        pars = session.getPars();
        return session;
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

    protected Binder getBinder() {
        return new Binder();
    }

    public class Binder {
        public BaseAPIModule getModule() {
            return BaseAPIModule.this;
        }
    }

    public static class Session {

        private Map<String,String> pars, headers;

        public Session() {
            pars = new HashMap<>();
            headers = new HashMap<>();
        }

        public Map<String, String> getPars() {
            return pars;
        }

        public void setPars(Map<String, String> pars) {
            this.pars = pars;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

    }

}
