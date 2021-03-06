package net.swiftos.eventposter.modules.broadcastreceiver.handler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.entity.DynamicHandler;
import net.swiftos.eventposter.entity.EventAnnoInfo;
import net.swiftos.eventposter.modules.broadcastreceiver.annotation.BroadCastReceiver;
import net.swiftos.eventposter.modules.broadcastreceiver.entity.BroadCastEntity;
import net.swiftos.eventposter.modules.broadcastreceiver.entity.IReciver;
import net.swiftos.eventposter.template.IHandler;

import java.lang.reflect.Proxy;

/**
 * Created by gy939 on 2016/10/3.
 */
public class BroadCastReceiverHandler implements IHandler<BroadCastEntity> {

    private static final String ProxyMethod = "onReceiver";
    private Class<?> inter = IReciver.class;

    @Override
    public void init(Object... objects) {

    }

    @Override
    public void destroy(Object... objects) {

    }

    @Override
    public BroadCastEntity parse(EventAnnoInfo annoInfo) {
        BroadCastReceiver receiver = (BroadCastReceiver) annoInfo.getAnnotation();
        BroadCastEntity broadCastEntity = new BroadCastEntity();
        broadCastEntity.setMethod(annoInfo.getMethod());
        IntentFilter filter = new IntentFilter();
        for (String str:receiver.actions())
            filter.addAction(str);
        for (String str:receiver.categories())
            filter.addCategory(str);
        for (String str:receiver.schemes())
            filter.addDataScheme(str);
        if (receiver.priority() >= 0)
            filter.setPriority(receiver.priority());
        broadCastEntity.setFilter(filter);
        return broadCastEntity;
    }

    @Override
    public void load(BroadCastEntity eventEntity, Object object) {
        DynamicHandler handler = new DynamicHandler(object);
        handler.addMethod(ProxyMethod, eventEntity.getMethod());
        final Object bussnessproxy = Proxy.newProxyInstance(inter.getClassLoader(), new Class<?>[]{inter}, handler);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ((IReciver) bussnessproxy).onReceiver(context,intent);
            }
        };
        EventPoster.getApp().registerReceiver(receiver,eventEntity.getFilter());
        eventEntity.putReceiver(object, receiver);
    }

    @Override
    public void unload(BroadCastEntity eventEntity, Object object) {
        BroadcastReceiver receiver = eventEntity.removeReceiver(object);
        if (receiver != null) {
            EventPoster.getApp().unregisterReceiver(receiver);
        }
    }

    @Override
    public void inject(Object object) {

    }

    @Override
    public void remove(Object object) {

    }
}
