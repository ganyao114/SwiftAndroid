package net.swiftos.common.workerqueue;

/**
 * Created by ganyao on 2017/3/30.
 */
public interface IWorkQueue<T> {
    boolean start();
    boolean stop();
    boolean taskStart();
    void taskEnd();
    boolean addTask(T t);
}
