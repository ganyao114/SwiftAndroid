package net.swiftos.common.workerqueue;

import java.util.Vector;

/**
 * Created by ganyao on 2017/3/30.
 */
public interface IWorkQueue<T> {
    boolean start();
    boolean stop();
    boolean taskStart();
    void taskEnd(T t);
    boolean addTask(T t);
    void disableQueue();
    void enableQueue();
    Vector<T> getTasksInQueue();
    Vector<T> getTasksProcessing();
}
