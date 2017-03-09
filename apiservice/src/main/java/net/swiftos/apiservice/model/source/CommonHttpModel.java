package net.swiftos.apiservice.model.source;

import android.widget.Toast;

import net.swiftos.apiservice.api.ICommonAPI;
import net.swiftos.common.api.IAPIGenerator;
import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.model.bean.BaseResponse;
import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;
import net.swiftos.common.model.entity.HttpCallback;
import net.swiftos.common.model.net.BaseHttpModel;
import net.swiftos.common.presenter.IAsyncSubject;

/**
 * Created by ganyao on 2017/3/9.
 */

public class CommonHttpModel extends BaseHttpModel {

    ICommonAPI api;

    public CommonHttpModel() {
        super();
        api = apiGenerator.getAPI(ICommonAPI.class);
    }

    public IAsyncSubject sessionStart() {
        return baseModel.<String>getAsyncObservable(api.sessionStart().cache(), new HttpCallback<String>() {
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
