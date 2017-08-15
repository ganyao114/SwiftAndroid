package net.swiftos.eventposter.modules.broadcastreceiver.entity;

import android.content.IntentFilter;

import net.swiftos.eventposter.exception.EventInvokeException;
import android.content.BroadcastReceiver;
import net.swiftos.eventposter.template.IEventEntity;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by gy939 on 2016/10/3.
 */
public class BroadCastEntity  implements IEventEntity {

    private Method method;
    private IntentFilter filter;
    private Map<Object,BroadcastReceiver> broadCastReceiverMap = new WeakHashMap<>();

    public IntentFilter getFilter() {
        return filter;
    }

    public void setFilter(IntentFilter filter) {
        this.filter = filter;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public Object invoke(Object invoker, Object... pars) throws EventInvokeException {
        return null;
    }

    public BroadcastReceiver getReceiver(Object o) {
        return broadCastReceiverMap.get(o);
    }

    public void putReceiver(Object object, BroadcastReceiver receiver) {
        broadCastReceiverMap.put(object, receiver);
    }

    public BroadcastReceiver removeReceiver(Object object) {
        return broadCastReceiverMap.remove(object);
    }



}
