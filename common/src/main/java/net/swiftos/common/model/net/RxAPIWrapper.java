package net.swiftos.common.model.net;

import java.util.Observable;

/**
 * Created by ganyao on 2017/3/9.
 */

public class RxAPIWrapper implements IAPIWrapper<Observable> {

    private Observable observable;

    public RxAPIWrapper(Observable observable) {
        this.observable = observable;
    }

    @Override
    public Observable getEntity() {
        return observable;
    }
}
