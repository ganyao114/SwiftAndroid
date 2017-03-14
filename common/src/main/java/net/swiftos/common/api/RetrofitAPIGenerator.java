package net.swiftos.common.api;

import net.swiftos.common.model.entity.APIServiceConfigs;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ganyao on 2017/3/9.
 */

public class RetrofitAPIGenerator implements IAPIGenerator {

    protected OkHttpClient okHttpClient;
    protected Retrofit retrofit;

    private HttpUrl BASE_URL;
    private Map<String,String> headers, pars;
    private APIServiceConfigs configs = new APIServiceConfigs();

    protected OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            if (configs == null) {
                configs = new APIServiceConfigs();
            }
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new BasicParamsInterceptor(headers, pars, null))
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

    @Override
    public void setUrl(String url) {
        BASE_URL = HttpUrl.parse(url);
    }

    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public void setPars(Map<String, String> pars) {
        this.pars = pars;
    }

    @Override
    public void setConfigs(APIServiceConfigs configs) {
        this.configs = configs;
    }

    @Override
    public <T> T getAPI(Class<T> t) {
        return getRetrofit().create(t);
    }
}
