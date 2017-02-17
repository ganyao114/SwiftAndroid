package net.swiftos.eventposter.entity;

import net.swiftos.eventposter.presenter.Presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 构建 Class 继承关系
 * Created by ganyao on 2016/10/20.
 */

public class ClassDependTree {

    private Map<Class,ClassEntity> tree = new HashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addClassDeep(Class clazz){
        try {
            lock.writeLock().lock();
            if (tree.containsKey(clazz)) return;
            Class<?> template = clazz;
            while (template != null && template != Object.class) {
                // 过滤掉基类 因为基类是不包含注解的
                String clazzName = template.getName();
                if (clazzName.startsWith("java.") || clazzName.startsWith("javax.")
                        || clazzName.startsWith("android.") || clazz.equals(Presenter.class)) {
                    break;
                }
                insert(template);
                template = template.getSuperclass();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void addClass(Class clazz){
        try {
            lock.writeLock().lock();
            if (tree.containsKey(clazz)) return;
            insert(clazz);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public synchronized void insertDeep(Class clazz){
        insertDeep(clazz,null);
    }


    //递归一下
    private void insertDeep(Class clazz,Class child){
        String clazzName = clazz.getName();
        if (clazzName.startsWith("java.") || clazzName.startsWith("javax.")
                || clazzName.startsWith("android.") || clazz.equals(Presenter.class)
                || clazz.equals(Object.class))
            return;
        ClassEntity entity = tree.get(clazz);
        if (entity == null){
            entity = new ClassEntity();
            tree.put(clazz,entity);
            entity.setSelf(clazz);
            entity.setParent(clazz.getSuperclass());
            if (child != null)
                entity.addChild(child);
            insertDeep(clazz.getSuperclass(),clazz);
        } else {
            if (child != null)
                entity.addChild(child);
        }
    }

    public List<Class> getDirectChildren(Class clazz){
        ClassEntity entity = tree.get(clazz);
        if (entity == null){
            return null;
        } else {
            return entity.getChildren();
        }
    }

    public List<Class> getAllChildren(Class clazz,List<Class> allchild){
        ClassEntity entity = tree.get(clazz);
        if (entity == null){
            return allchild;
        } else {
            if (allchild == null) allchild = new ArrayList<>();
            List<Class> directChildren = entity.getChildren();
            if (directChildren == null) return allchild;
            allchild.addAll(directChildren);
            for (Class ddirectChild:directChildren){
                getAllChildren(ddirectChild,allchild);
            }
            return allchild;
        }
    }

    private void insert(Class clazz){
        ClassEntity entity = new ClassEntity();
        Class parent = clazz.getSuperclass();
        entity.setSelf(clazz);
        entity.setParent(parent);
        tree.put(clazz,entity);
        ClassEntity parentEntity  = tree.get(parent);
        if (parentEntity == null){
            parentEntity = new ClassEntity();
            tree.put(parent,parentEntity);
        }
        parentEntity.addChild(clazz);
    }

}
