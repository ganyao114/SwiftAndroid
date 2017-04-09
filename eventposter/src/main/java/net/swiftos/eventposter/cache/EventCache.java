package net.swiftos.eventposter.cache;

import net.swiftos.eventposter.template.IEventEntity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gy939 on 2016/10/3.
 */
public class EventCache {

    private static Map<String,IEventEntity> eventEntityMap = new HashMap<>();

    public static IEventEntity getEventEntity(Method method, Annotation annotation){
        return eventEntityMap.get(getKey(method, annotation));
    }

    public static IEventEntity getEventEntity(String key){
        return eventEntityMap.get(key);
    }

    public synchronized static void addEventEntity(Method method, Annotation annotation,IEventEntity eventEntity){
        eventEntityMap.put(getKey(method, annotation), eventEntity);
    }

    public static String getKey(Method method, Annotation annotation) {
        String methodHash = method.hashCode() + "";
        String annoHash = annotation.hashCode() + "";
        return methodHash + annoHash;
    }

}
