package net.swiftos.common.cache;

import net.swiftos.common.application.BaseApplication;

import org.afinal.simplecache.ACache;

import java.io.Serializable;

/**
 * Created by ganyao on 2017/3/9.
 */

public class KVACacheImpl implements IKVDiskCache<String,Serializable> {

    private ACache aCache = BaseApplication.getAppComponent().aCache();

    @Override
    public void save(String key, Serializable value) {
        aCache.put(key, value);
    }

    @Override
    public void save(String key, Serializable value, Integer time) {
        aCache.put(key, value, time);
    }

    @Override
    public Serializable get(String s) {
        return (Serializable) aCache.getAsObject(s);
    }

    @Override
    public Serializable remove(String s) {
        Serializable res = get(s);
        if (res != null) {
            aCache.remove(s);
        }
        return res;
    }

}
