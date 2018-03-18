package com.taotao.manager.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtilsPoolImpl implements RedisUtils {
    @Autowired
    private JedisPool jedisPool;
    @Override
    public void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        //用完后，把连接还给连接池
        jedis.close();
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get(key);
        //用完后，把连接还给连接池
        jedis.close();
        return s;
    }

    @Override
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        //用完后，把连接还给连接池
        jedis.close();
    }

    @Override
    public void expire(String key, Integer seconds) {
        Jedis jedis = jedisPool.getResource();
        jedis.expire(key, seconds);
        //用完后，把连接还给连接池
        jedis.close();
    }

    @Override
    public void set(String key, String value, Integer seconds) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedis.expire(key, seconds);
        //用完后，把连接还给连接池
        jedis.close();
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long incr = jedis.incr(key);
        //用完后，把连接还给连接池
        jedis.close();
        return incr;
    }
}
