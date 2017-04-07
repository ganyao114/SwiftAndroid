package net.swiftos.common.model.net;

import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.presenter.IAsyncSubject;

/**
 * Created by ganyao on 2017/4/7.
 */

public interface AsyncCallbackWithProgress<T> extends AsyncCallback<T> {
    void progress(int i);
}
