package net.swiftos.eventposter.presenter;

import android.os.Bundle;

/**
 * Created by gy939 on 2016/9/26.
 */
public interface IPresenter {
    public void onPresenterInit(IPresenter context);
    public void onPresenterDestory(IPresenter context);
    public void onPost(IPresenter context, Bundle data);
}
