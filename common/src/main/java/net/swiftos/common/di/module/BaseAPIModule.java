package net.swiftos.common.di.module;

import net.swiftos.common.api.BasicParamsInterceptor;
import net.swiftos.common.api.IAPIGenerator;
import net.swiftos.common.application.BaseApplication;
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

    public IAPIGenerator apiGenerator;

    protected String BASE_URL;
    private Map<String,String> headers, pars;
    private APIServiceConfigs configs;


    protected Session session;

    protected void init(String url) {
        BASE_URL = url;
        apiGenerator = BaseApplication.getAppComponent().apiGenerator();
        apiGenerator.setUrl(url);
    }

    public Session generateSession() {
        session = new Session();
        setHeaders(session.getHeaders());
        setPars(session.getPars());
        return session;
    }

    public void setPars(Map<String, String> pars) {
        this.pars = pars;
        apiGenerator.setPars(pars);
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
        apiGenerator.setHeaders(headers);
    }

    public void setConfigs(APIServiceConfigs configs) {
        this.configs = configs;
        apiGenerator.setConfigs(configs);
    }

    protected <T> T getAPIService(Class<T> tClass) {
        return apiGenerator.getAPI(tClass);
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
