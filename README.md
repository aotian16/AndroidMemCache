# AndroidMemCache
A memory cache with expired time, base on LruCache.

## Install

[link](https://jitpack.io/#aotian16/AndroidMemCache/v0.0.2)

## Use

```java
MemCache<String, String> cache = new MemCache<>(20);
cache.get(key);
cache.put(key, value);
cache.put(key, value, DateUtil.TIME_UNIT_MINUTE * 3);
cache.remove(key);
```

## Version history

| No.  | Version | Detail        |
| ---- | ------- | ------------- |
| 1    | 0.0.2   | first version |

## [LICENSE](https://github.com/aotian16/AndroidMemCache/blob/master/LICENSE)

MIT