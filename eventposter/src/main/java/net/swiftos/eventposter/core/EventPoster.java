package net.swiftos.eventposter.core;

import android.app.Application;

import net.swiftos.eventposter.factory.HandlerFactory;
import net.swiftos.eventposter.modules.activitylife.handler.ActivityLifeHandler;
import net.swiftos.eventposter.template.IHandler;

/**
 * Created by gy939 on 2016/10/3.
 */
public class EventPoster {

    private static Application app;

    public static <T extends IHandler> T with(Class<T> handlerType){
        IHandler handler = HandlerFactory.getHandler(handlerType);
        if (handler == null) return null;
        return (T) handler;
    }

    public static void register(Object object){
        Injecter.inject(object);
    }

    public static void unRegister(Object object){
        Injecter.remove(object);
    }

    public static void registerDeep(Object object){
        Injecter.injectDeep(object);
    }

    public static void unRegisterDeep(Object object){
        Injecter.removeDeep(object);
    }

    public static void init(Application application){
        app = application;
        HandlerFactory.getHandler(ActivityLifeHandler.class).init(application);
    }

    public static void destroy(Application application){
        app = null;
        HandlerFactory.getHandler(ActivityLifeHandler.class).destroy(application);
    }

    public static Application getApp(){
        return app;
    }

    public static void preLoad(final Class[] classes){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Class clazz:classes){
                    Injecter.load(null,clazz);
                }
            }
        }).start();
    }

    public static void preLoadDeep(final Class[] classes){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Class clazz:classes){
                    Injecter.loadDeep(null,clazz);
                }
            }
        }).start();
    }

}
