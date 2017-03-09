package net.swiftos.common.presenter;

import rx.Subscription;

/**
 * Created by ganyao on 2017/3/9.
 */

public class RxSubject implements IAsyncSubject<Subscription> {

    private Subscription subscription;

    public RxSubject(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public Subscription getEntity() {
        return subscription;
    }
}
