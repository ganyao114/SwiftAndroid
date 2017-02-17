package net.swiftos.eventposter.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ganyao on 2016/10/20.
 */

public class ClassEntity implements Cloneable{

    private Class self;
    private Class parent;
    private List<Class> children;

    public Class getSelf() {
        return self;
    }

    public void setSelf(Class self) {
        this.self = self;
    }

    public Class getParent() {
        return parent;
    }

    public void setParent(Class parent) {
        this.parent = parent;
    }

    public List<Class> getChildren() {
        return children;
    }

    public void setChildren(List<Class> children) {
        this.children = children;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ClassEntity entity = (ClassEntity) super.clone();
        self = null;
        parent = null;
        children = null;
        return entity;
    }

    public synchronized void addChild(Class clazz) {
        if (children == null)
            children = new CopyOnWriteArrayList<>();
        children.add(clazz);
    }
}
