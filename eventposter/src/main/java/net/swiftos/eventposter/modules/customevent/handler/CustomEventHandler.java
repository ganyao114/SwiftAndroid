package net.swiftos.eventposter.modules.customevent.handler;

import net.swiftos.eventposter.core.Injecter;
import net.swiftos.eventposter.entity.EventAnnoInfo;
import net.swiftos.eventposter.exception.EventInvokeException;
import net.swiftos.eventposter.modules.customevent.annotation.InjectEvent;
import net.swiftos.eventposter.modules.customevent.entity.CustomEventEntity;
import net.swiftos.eventposter.modules.customevent.entity.RunContextType;
import net.swiftos.eventposter.template.IHandler;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gy939 on 2016/10/5.
 */
public class CustomEventHandler implements IHandler<CustomEventEntity>{

    private Map<Class,Map<String,CustomEventEntity>> eventMap = new ConcurrentHashMap<>();

    private Map<Class,Object> stickyMap = new ConcurrentHashMap<>();

    @Override
    public void init(Object... objects) {

    }

    @Override
    public void destroy(Object... objects) {

    }

    @Override
    public CustomEventEntity parse(EventAnnoInfo annoInfo) {
        InjectEvent eventAnno = (InjectEvent) annoInfo.getAnnotation();
        int delay = eventAnno.delay();
        RunContextType type = eventAnno.runType();
        String name = eventAnno.name();
        Method method = annoInfo.getMethod();
        if (name== null||name.equals(""))
            name = method.getName() + "@" + method.hashCode();
        Class parType = method.getParameterTypes()[0];
        CustomEventEntity entity = new CustomEventEntity();
        entity.setMethod(method);
        entity.setContextType(type);
        entity.setDelay(delay);
        entity.setName(name);
        entity.setAnnotation(eventAnno);
        entity.setParType(parType);
        entity.setInvokeType(annoInfo.getClazz());
        entity.setSticky(eventAnno.sticky());
        Injecter.getClassTree().insertDeep(parType);
        return entity;
    }

    @Override
    public void load(CustomEventEntity eventEntity, Object object) {
        Map<String,CustomEventEntity> map = eventMap.get(eventEntity.getParType());
        if (map == null){
            map = new ConcurrentHashMap<>();
            eventMap.put(eventEntity.getParType(),map);
        }
        eventEntity.addCount();
        if (!map.containsKey(eventEntity.getName()));
            map.put(eventEntity.getName(),eventEntity);
        if (eventEntity.isSticky()){
            Object value = stickyMap.get(eventEntity.getParType());
            if (value != null) {
                try {
                    eventEntity.invoke(object,value);
                } catch (EventInvokeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unload(CustomEventEntity eventEntity, Object object) {
        Map<String,CustomEventEntity> map = eventMap.get(eventEntity.getParType());
        if (map == null) return;
        CustomEventEntity entity = map.get(eventEntity.getName());
        if (entity == null) return;
        if (entity.cutCount() <= 0) {
            entity.clearCount();
            map.remove(eventEntity.getName());
        }
    }

    @Override
    public void inject(Object object) {

    }

    @Override
    public void remove(Object object) {

    }

    /**
     * 直系 Event 广播
     * @param object
     */
    public void broadcast(Object object){
        broadcast(object, object.getClass());
    }

    /**
     * 直系 + 支系
     * @param object
     */
    public void broadcastAll(Object object) {
        for (Class<?> clazz:eventMap.keySet()) {
            if (clazz.isInstance(object)) {
                broadcast(object, clazz);
            }
        }
    }



    private void broadcast(Object object, Class clazz) {
        Map<String,CustomEventEntity> map = eventMap.get(clazz);
        if (map == null||map.size() == 0)
            return;
        for (Map.Entry<String,CustomEventEntity> entry:map.entrySet()){
            CustomEventEntity entity = entry.getValue();
            Vector invokers = Injecter.getAllInsts(entity.getInvokeType());
            if (invokers == null||invokers.size() == 0)
                continue;
            for (Object invoker:invokers){
                try {
                    entity.invoke(invoker,object);
                } catch (EventInvokeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void post(Object object, String name){
        post(object, object.getClass(), name);
    }

    public void postAll(Object object, String name) {
        for (Class<?> clazz:eventMap.keySet()) {
            if (clazz.isInstance(object)) {
                post(object, clazz, name);
            }
        }
    }

    private void post(Object object, Class clazz, String name) {
        Map<String,CustomEventEntity> map = eventMap.get(clazz);
        if (map == null)
            return;
        CustomEventEntity entity = map.get(name);
        if (entity == null)
            return;
        Vector invokers = Injecter.getAllInsts(entity.getInvokeType());
        if (invokers == null)
            return;
        for (Object invoker:invokers){
            try {
                entity.invoke(invoker,object);
            } catch (EventInvokeException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastSticky(Object object){
        stickyMap.put(object.getClass(),object);
        broadcast(object);
    }

    public void postSticky(Object object,String name){
        stickyMap.put(object.getClass(),object);
        post(object, name);
    }

    public void clearSticky(Class clazz){
        stickyMap.remove(clazz);
    }

    public ExtCall As(Object object){
        return new ExtCall(object);
    }

    public class ExtCall{
        private Object object;

        public ExtCall(Object object) {
            this.object = object;
        }

        public void BroadCast(){
            broadcast(object);
        }

        public void BroadCastSticky(){
            broadcastSticky(object);
        }

        public void PostSticky(String name){
            postSticky(object,name);
        }

        public void ClearSticky(Class clazz){
            clearSticky(clazz);
        }

        public void Post(String name){
            post(object,name);
        }

    }

}
