package net.swiftos.common.workerqueue;

import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Productor & Customer Model
 * Created by ganyao on 2017/3/30.
 */
public class WorkerQueue<T> implements IWorkQueue<T>, Runnable {

    private BlockingDeque blockingQueue = new LinkedBlockingDeque();

    private Vector<T> tasksInQueue = new Vector<T>();
    private Vector<T> tasksProcessing = new Vector<T>();

    private Semaphore semaphore;
    private ITask<T> task;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean enabled = new AtomicBoolean(true);

    /**
     *
     * @param thread
     * @param task
     */
    public WorkerQueue(int thread, ITask<T> task) {
        semaphore = new Semaphore(thread);
        this.task = task;
    }

    /**
     * @param thread
     */
    public WorkerQueue(int thread) {
        semaphore = new Semaphore(thread);
    }

    public void setTask(ITask<T> task) {
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
    public void taskEnd(T t) {
        tasksProcessing.remove(t);
        semaphore.release();
    }

    public boolean addTask(T t) {
        if (t == null) {
            return false;
        }
        if (tasksInQueue.contains(t) || tasksProcessing.contains(t) || !enabled.get()) {
            return false;
        }
        tasksInQueue.add(t);
        if (blockingQueue.offer(t)) {
            return true;
        } else {
            tasksInQueue.remove(t);
            return false;
        }
    }

    @Override
    public void disableQueue() {
        enabled.set(false);
        if (blockingQueue.size() == 0) {
            stop();
        }
    }

    @Override
    public void enableQueue() {
        enabled.set(true);
    }

    private void looper() {
        while (running.get()) {
            T t = null;
            Object object = null;
            try {
                //队列关闭 当无数据时结束循环
                if (!enabled.get() && blockingQueue.size() == 0) {
                    break;
                }
                object = blockingQueue.take();
                if (!running.get()) {
                    break;
                }
                if (taskStart()) {
                    t = (T) object;
                    tasksInQueue.remove(t);
                    tasksProcessing.add(t);
                    task.runTask(t, this::taskEnd);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (t != null) {
                    tasksInQueue.remove(t);
                }
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
        void runTask(T t, CallbackVoid<T> callback);
    }

    /**
     * start queue
     * @return
     */
    public synchronized boolean start() {
        if (task == null) {
            return false;
        }
        if (running.get()) {
            return false;
        } else {
            running.set(true);
            new Thread(this).start();
            return true;
        }
    }

    public boolean stop() {
        if (running.get()) {
            running.set(false);
            synchronized (blockingQueue) {
                if (blockingQueue.size() == 0) {
                    blockingQueue.offer(new Object());
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Vector<T> getTasksInQueue() {
        return tasksInQueue;
    }

    @Override
    public Vector<T> getTasksProcessing() {
        return tasksProcessing;
    }
}
