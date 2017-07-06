package com.qefee.pj.androidmemcache;

import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import com.qefee.pj.androidmemcache.util.DateUtil;

import java.util.Date;

/**
 * MemCache.
 * <ul>
 * <li>date: 2017/7/6</li>
 * </ul>
 *
 * @author tongjin
 */

public class MemCache<K, V> {

    private long defaultDuring = DateUtil.TIME_UNIT_SECOND * 10;
    private final LruCache<K, CacheItem<K, V>> lruCache;

    /**
     * construct.
     *
     * @param maxSize maxSize
     */
    public MemCache(int maxSize) {
        lruCache = new LruCache<>(maxSize);
    }

    /**
     * construct
     *
     * @param maxSize       maxSize
     * @param defaultDuring defaultDuring(milliseconds)
     */
    public MemCache(int maxSize, long defaultDuring) {
        this(maxSize);
        this.defaultDuring = defaultDuring;
    }

    /**
     * get value by key
     *
     * @param key key
     * @return value, return null when not found or value expired
     */
    public V get(@NonNull K key) {
        CacheItem<K, V> cacheItem = lruCache.get(key);

        if (cacheItem == null) {
            return null;
        }

        if (DateUtil.isCacheItemAlive(cacheItem)) {
            return cacheItem.getValue();
        } else {
            lruCache.remove(key);
            return null;
        }
    }

    /**
     * put a value by key
     *
     * @param key    key
     * @param value  value
     * @param during during(milliseconds)
     * @return previous value, return null if not found
     */
    public V put(@NonNull K key, @NonNull V value, long during) {
        if (during < 0) {
            throw new IllegalArgumentException("during should >= 0");
        }

        Date date = new Date();
        long time = date.getTime();
        CacheItem<K, V> cacheItem = new CacheItem<>(key, value, time, time, time + during);

        CacheItem<K, V> previous = lruCache.put(key, cacheItem);

        if (previous != null) {
            cacheItem.setCreateTime(previous.getCreateTime());
            return cacheItem.getValue();
        } else {
            return null;
        }
    }

    /**
     * put a value by key(during = defaultDuring)
     *
     * @param key   key
     * @param value value
     * @return previous value, return null if not found
     */
    public V put(@NonNull K key, @NonNull V value) {
        return put(key, value, defaultDuring);
    }

    /**
     * remove value by key
     * @param key key
     * @return removed value, return null if not found
     */
    public V remove(@NonNull K key) {
        CacheItem<K, V> remove = lruCache.remove(key);
        if (remove == null) {
            return null;
        }

        return remove.getValue();
    }
}
