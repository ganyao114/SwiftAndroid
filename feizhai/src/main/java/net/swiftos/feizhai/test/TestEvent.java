package net.swiftos.feizhai.test;

import android.support.annotation.WorkerThread;

/**
 * Created by ganyao on 2017/4/9.
 */

public class TestEvent implements ITestEvent {

    @WorkerThread
    @Override
    public void dosth() {
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
