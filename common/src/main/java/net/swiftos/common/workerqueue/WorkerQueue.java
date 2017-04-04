package net.swiftos.common.workerqueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Productor & Customer Model
 * Created by ganyao on 2017/3/30.
 */
public class WorkerQueue<T> implements IWorkQueue<T>, Runnable {

    private BlockingDeque<T> blockingDeque = new LinkedBlockingDeque<T>();
    private Semaphore semaphore;
    private ITask<T> task;
    private AtomicBoolean running = new AtomicBoolean(false);

    /**
     *
     * @param thread
     * @param task
     */
    public WorkerQueue(int thread, ITask<T> task) {
        semaphore = new Semaphore(thread);
        this.task = task;
    }

    public WorkerQueue(ITask<T> task) {
        this(1, task);
    }

    public boolean taskStart() {
        try {
            semaphore.acquire();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * must invoke when task end
     */
    public void taskEnd() {
        semaphore.release();
    }

    public boolean addTask(T t) {
        return blockingDeque.offer(t);
    }

    private void looper() {
        while (running.get()) {
            try {
                T t = blockingDeque.take();
                if (taskStart()) {
                    task.runTask(t, this::taskEnd);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        looper();
    }

    @FunctionalInterface
    public interface ITask<T> {
        /**
         * @param t
         * @param callback declare task end: release lock
         */
        void runTask(T t, CallbackVoidNoVar callback);
    }

    /**
     * start queue
     * @return
     */
    public synchronized boolean start() {
        if (running.get()) {
            return false;
        } else {
            running.set(true);
            new Thread(this).start();
            return true;
        }
    }

    public synchronized boolean stop() {
        if (running.get()) {
            running.set(false);
            return true;
        } else {
            return false;
        }
    }

}
