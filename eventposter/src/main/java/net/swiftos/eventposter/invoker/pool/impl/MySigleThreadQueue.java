package net.swiftos.eventposter.invoker.pool.impl;

import net.swiftos.eventposter.invoker.pool.control.ThreadPoolControl;
import net.swiftos.eventposter.invoker.pool.strategy.SigleThreadPool;

/**
 * Created by gy on 2016/2/22.
 */
public class MySigleThreadQueue {

    private static ThreadPoolControl control;

    private static void initPool(){
        control = new ThreadPoolControl(new SigleThreadPool());
        control.poolstart();
    }

    public static void AddTask(Runnable runnable){
        synchronized (MySigleThreadQueue.class){
            if (control == null)
                initPool();
        }
        control.submit(runnable);
    }
}
