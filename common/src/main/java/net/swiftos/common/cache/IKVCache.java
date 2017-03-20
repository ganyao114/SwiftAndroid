package net.swiftos.common.cache;

/**
 * Created by ganyao on 2017/3/9.
 */

public interface IKVCache<K,V> {
    void save(K key, V value);
    void save(K key, V value, Integer time);
    V get(K k);
    V remove(K k);
}
