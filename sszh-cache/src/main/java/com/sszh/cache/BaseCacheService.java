package com.sszh.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.sszh.common.util.date.ExpireTime;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class BaseCacheService {

    private RedisTemplate<Object, Object> redisTemplate;
    private StringRedisTemplate stringRedisTemplate;

    public BaseCacheService(RedisTemplate<Object, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
                stringRedisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
                stringRedisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 取得缓存（int型）
     *
     * @param key
     * @return
     */
    public Integer getInt(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        Integer value1 = null;
        if (StringUtils.isNotBlank(value)) {
            value1 = Integer.valueOf(value);
        }
        return value1;
    }

    /**
     * 取得缓存（字符串类型）
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * 取得缓存（字符串类型）
     *
     * @param key
     * @return
     */
    public String getStr(String key, boolean retain) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return value;
    }

    /**
     * 获取缓存<br>
     * 注：基本数据类型(Character除外)，请直接使用get(String key, Class<T> clazz)取值
     *
     * @param key
     * @return
     */
    public Object getObj(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存<br>
     * 注：java 8种基本类型的数据请直接使用get(String key, Class<T> clazz)取值
     *
     * @param key
     * @param retain
     *            是否保留
     * @return
     */
    public Object getObj(String key, boolean retain) {
        Object obj = redisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return obj;
    }

    /**
     * 获取缓存json对象<br>
     *
     * @param key
     *            key
     * @param clazz
     *            类型
     * @return
     */
    public <T> T getJson(String key, Class<T> clazz) {
        if (stringRedisTemplate.boundValueOps(key).get() != null) {
            return JSON.parseObject(stringRedisTemplate.boundValueOps(key).get(), clazz);
        }
        return null;
    }

    /**
     * 将value对象写入缓存
     *
     * @param key
     * @param value
     * @param seconds       失效时间(秒)
     */
    public void set(String key, Object value, Long seconds) {
        if (value.getClass().equals(String.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Integer.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Double.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Float.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Short.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Long.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Boolean.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
        if (seconds != null && seconds > 0) {
            stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        }
    }

    /**
     * 将value对象以JSON格式写入缓存
     *
     * @param key
     * @param value
     * @param expireTime        失效时间(秒)
     */
    public void setJson(String key, Object value, ExpireTime expireTime) {
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
        if (expireTime.getTime() > 0) {
            stringRedisTemplate.expire(key, expireTime.getTime(), TimeUnit.SECONDS);
        }
    }

    /**
     * 将value对象以JSON格式写入缓存
     *
     * @param key
     * @param value
     */
    public void setJson(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
    }

    /**
     * 更新key对象field的值
     *
     * @param key
     *            缓存key
     * @param field
     *            缓存对象field
     * @param value
     *            缓存对象field值
     */
    public void setJsonField(String key, String field, String value) {
        JSONObject obj = JSON.parseObject(stringRedisTemplate.boundValueOps(key).get());
        obj.put(field, value);
        stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
    }

    /**
     * 递减操作
     *
     * @param key
     * @param by
     * @return
     */
    public double decr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, -by);
    }

    /**
     * 递增操作
     *
     * @param key
     * @param by
     * @return
     */
    public double incr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 获取double类型值
     *
     * @param key
     * @return
     */
    public double getDouble(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (StringUtils.isNotBlank(value)) {
            return Double.valueOf(value);
        }
        return 0d;
    }

    /**
     * 设置double类型值
     *
     * @param key
     * @param value
     * @param time
     *            失效时间(秒)
     */
    public void setDouble(String key, double value, ExpireTime time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (time.getTime() > 0) {
            stringRedisTemplate.expire(key, time.getTime(), TimeUnit.SECONDS);
        }
    }

    /**
     * 设置double类型值
     *
     * @param key
     * @param value
     * @param time
     *            失效时间(秒)
     */
    public void setInt(String key, int value, ExpireTime time) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (time.getTime() > 0) {
            stringRedisTemplate.expire(key, time.getTime(), TimeUnit.SECONDS);
        }
    }

    public void setInt(String key, int value) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
    }

    /**
     * 将map写入缓存
     *
     * @param key
     * @param map
     * @param time
     *            失效时间(秒)
     */
    public <T> void setMap(String key, Map<String, T> map, ExpireTime time) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 将map写入缓存
     *
     * @param key
     * @param obj
     * @param time      失效时间(秒)
     */
    @SuppressWarnings("unchecked")
    public <T> void setMap(String key, T obj, ExpireTime time) {
        Map<String, String> map = (Map<String, String>) JSON.parseObject(JSON.toJSONString(obj), Map.class);
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key
     * @param map
     */
    public <T> void addMap(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key
     *            cache对象key
     * @param field
     *            map对应的key
     * @param value
     *            值
     */
    public void addMap(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key
     *            cache对象key
     * @param field
     *            map对应的key
     * @param obj
     *            对象
     */
    public <T> void addMap(String key, String field, T obj) {
        redisTemplate.opsForHash().put(key, field, obj);
    }

    /**
     * 获取map缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    public <T> Map<String, T> mget(String key, Class<T> clazz) {
        BoundHashOperations<Object, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }

    /**
     * 获取map缓存中的某个对象
     *
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapField(String key, String field, Class<T> clazz) {
        return (T) redisTemplate.boundHashOps(key).get(field);
    }

    /**
     * 删除map中的某个对象
     *
     * @author lh
     * @date 2016年8月10日
     * @param key
     *            map对应的key
     * @param field
     *            map中该对象的key
     */
    public void delMapField(String key, Object... field) {
        BoundHashOperations<Object, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.delete(field);
    }

    /**
     * 指定缓存的失效时间
     *
     * @author FangJun
     * @date 2016年8月14日
     * @param key
     *            缓存KEY
     * @param time
     *            失效时间(秒)
     */
    public void expire(String key, ExpireTime time) {
        if (time.getTime() > 0) {
            redisTemplate.expire(key, time.getTime(), TimeUnit.SECONDS);
        }
    }

    /**
     * 添加set
     *
     * @param key
     * @param value
     */
    public void sadd(String key, String... value) {
        stringRedisTemplate.boundSetOps(key).add(value);
    }

    /**
     * 删除set集合中的对象
     *
     * @param key
     * @param value
     */
    public void srem(String key, Object... value) {
        stringRedisTemplate.boundSetOps(key).remove(value);
    }

    /**
     * set重命名
     *
     * @param oldkey
     * @param newkey
     */
    public void srename(String oldkey, String newkey) {
        redisTemplate.boundSetOps(oldkey).rename(newkey);
    }

    /**
     * 短信缓存
     *
     * @author fxl
     * @date 2016年9月11日
     * @param key
     * @param value
     * @param time
     */
    public void setIntForPhone(String key, Object value, int time) {
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
        if (time > 0) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 模糊查询keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

}