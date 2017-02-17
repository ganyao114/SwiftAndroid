package net.swiftos.eventposter.invoker.pool.impl;


import net.swiftos.eventposter.invoker.pool.control.ThreadPoolControl;
import net.swiftos.eventposter.invoker.pool.strategy.ExecutorThreadPool;

import java.util.concurrent.Future;

public class MyWorkThreadQueue {

    private static ThreadPoolControl mythreadpool;

    private static void initPool() {
        mythreadpool = new ThreadPoolControl(new ExecutorThreadPool());
        mythreadpool.poolstart();
    }


    public static Future AddTask(Runnable r) {
        if (mythreadpool == null)
            initPool();
        return mythreadpool.submit(r);
    }

}
