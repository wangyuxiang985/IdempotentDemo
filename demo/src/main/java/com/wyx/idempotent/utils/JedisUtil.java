package com.wyx.idempotent.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * JedisUtil
 *
 * @author yuxiang
 * @date 2019/8/19 11:22
 **/
@Component
@Slf4j
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    private Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     *设置值
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @param key	key
     * @param value	value
     * @return java.lang.String
     */
    public String set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error:{}", key, value, e);
            return null;
        } finally {
            colse(jedis);
        }
    }

    /**
     *设置值-带过期时间
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @param key	key
     * @param value	value
     * @param expireTime 过期时间, 单位: s
     * @return java.lang.String
     */
    public String set(String key,String value,int expireTime){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key,expireTime,value);
        } catch (Exception e) {
            log.error("set key:{} value:{} expireTime:{} error:{}", key, value, expireTime, e);
            return null;
        } finally {
            colse(jedis);
        }
    }

    /**
     *取值
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @param key	key
     * @return java.lang.String
     */
    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error:{}", key, e);
            return null;
        } finally {
            colse(jedis);
        }
    }

    /**
     *删除key
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @param key	key
     * @return java.lang.String
     */
    public Long del(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error:{}", key, e);
            return null;
        } finally {
            colse(jedis);
        }
    }

    /**
     *判断key是否存在
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @param key	key
     * @return java.lang.String
     */
    public Boolean exists(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("exists key:{} error:{}", key, e);
            return null;
        } finally {
            colse(jedis);
        }
    }

    /**
     *设置key的过期时间
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @param key	key
     * @param expireTime 过期时间, 单位: s
     * @return java.lang.String
     */
    public Long expire(String key,int expireTime){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key,expireTime);
        } catch (Exception e) {
            log.error("expire key:{} error:{}", key, e);
            return null;
        } finally {
            colse(jedis);
        }
    }

    /**
     *获取key的剩余时间
     * <br/>
     * @author yuxiang
     * @date 2019/8/19
     * @param key	key
     * @return java.lang.String
     */
    public Long ttl(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("ttl key:{} error:{}", key, e);
            return null;
        } finally {
            colse(jedis);
        }
    }

    private void colse(Jedis jedis) {
        if(jedis != null){
            jedis.close();
        }
    }
}
