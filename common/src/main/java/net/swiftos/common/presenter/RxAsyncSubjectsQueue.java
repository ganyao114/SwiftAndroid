package net.swiftos.common.presenter;

import rx.subscriptions.CompositeSubscription;
import rx.Subscription;

/**
 * Created by ganyao on 2017/3/9.
 */

public class RxAsyncSubjectsQueue implements IAsyncSubjectsQueue<Subscription> {

    private CompositeSubscription compositeSubscription;

    @Override
    public void addSubject(IAsyncSubject<Subscription> observer) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observer.getEntity());
    }

    @Override
    public void removeSubject(IAsyncSubject<Subscription> observer) {
        if (compositeSubscription != null) {
            compositeSubscription.remove(observer.getEntity());
        }
    }

    @Override
    public void destroyQueue() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

}
