package net.swiftos.apiservice.model.source;

import android.widget.Toast;

import net.swiftos.apiservice.api.ICommonAPI;
import net.swiftos.apiservice.di.component.CommonAPIComponent;
import net.swiftos.apiservice.di.component.DaggerCommonAPIComponent;
import net.swiftos.apiservice.di.module.CommonAPIModule;
import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;
import net.swiftos.common.model.entity.HttpCallback;
import net.swiftos.common.model.entity.Session;
import net.swiftos.common.model.net.BaseHttpModel;
import net.swiftos.common.model.net.CommonResponseAdapter;
import net.swiftos.common.model.net.IResponseAdapter;
import net.swiftos.common.presenter.IAsyncSubject;

import javax.inject.Inject;

/**
 * Created by ganyao on 2017/3/9.
 */

public class CommonHttpModel extends BaseHttpModel {

    public ICommonAPI api;
    @Inject
    public Session session;

    public CommonHttpModel() {
        super();
        CommonAPIComponent component = DaggerCommonAPIComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .commonAPIModule(new CommonAPIModule("http://www.baidu.com"))
                .build();

        component.inject(this);
        api = component.getAPI();
    }

    @Override
    protected IResponseAdapter setBaseReponse() {
        return new CommonResponseAdapter();
    }

    public IAsyncSubject sessionStart() {
        return baseModel.<String>getAsyncSubject(api.sessionStart().cache(), new HttpCallback<String>() {
            @Override
            public Object getTag() {
                return null;
            }

            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onFailure(FailureEntity failure) {
                Toast.makeText(BaseApplication.getApplication(), "falure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(ErrorResponse error) {
                Toast.makeText(BaseApplication.getApplication(), "falure", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onComplete() {
                Toast.makeText(BaseApplication.getApplication(), "cm", Toast.LENGTH_LONG).show();
            }
        });
    }


}
