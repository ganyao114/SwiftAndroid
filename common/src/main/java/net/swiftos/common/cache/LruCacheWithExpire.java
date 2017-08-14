package net.swiftos.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ganyao on 2017/8/14.
 */

public class LruCacheWithExpire<K,V> extends LruCache<K,V> {

    private Map<K,Long> expireTimes = new ConcurrentHashMap<K, Long>();

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public LruCacheWithExpire(int maxSize) {
        super(maxSize);
    }

    @Override
    public void save(K key, V value, Integer time) {
        save(key, value);
        expireTimes.put(key, System.currentTimeMillis() + time);
    }

    @Override
    public V get(K key) {
        V v = super.get(key);
        if (v == null)
            return null;
        long expireTime = expireTimes.get(key);
        if (expireTime == 0)
            return v;
        if (System.currentTimeMillis() >= expireTime) {
            remove(key);
            return null;
        } else {
            return v;
        }
    }

    @Override
    protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
        expireTimes.remove(key);
        super.entryRemoved(evicted, key, oldValue, newValue);
    }

}
