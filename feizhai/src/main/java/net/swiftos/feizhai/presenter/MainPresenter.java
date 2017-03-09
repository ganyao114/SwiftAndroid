package net.swiftos.feizhai.presenter;

import android.widget.Toast;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.presenter.BasePresenter;
import net.swiftos.eventposter.presenter.IPresenter;

/**
 * Created by ganyao on 2017/3/9.
 */

public class MainPresenter extends BasePresenter {

    @Override
    public void onPresenterInit(IPresenter context) {
        super.onPresenterInit(context);
        Toast.makeText(BaseApplication.getApplication(), BaseApplication.getAppComponent().generateSubscriber().hashCode() + "", Toast.LENGTH_LONG).show();
        Toast.makeText(BaseApplication.getApplication(), BaseApplication.getAppComponent().generateSubscriber().hashCode() + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public <T> void onNavigate(T data) {

    }
}
