package com.qefee.pj.androidmemcache;

/**
 * CacheItem.
 * <ul>
 * <li>date: 2017/7/6</li>
 * </ul>
 *
 * @author tongjin
 */

public class CacheItem<K, V> {
    private K key;
    private V value;
    private long createTime;
    private long updateTime;
    private long deleteTime;

    public CacheItem(K key, V value, long createTime, long updateTime, long deleteTime) {
        this.key = key;
        this.value = value;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }
}
