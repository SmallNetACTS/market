package com.taotao.manager.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

public class RedisUtilsClusterImpl implements RedisUtils {
    @Autowired
    private JedisCluster jedisCluster;
    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        String s = jedisCluster.get(key);
        return s;
    }

    @Override
    public void del(String key) {
        jedisCluster.del(key);
    }

    @Override
    public void expire(String key, Integer seconds) {
        jedisCluster.expire(key, seconds);
    }

    @Override
    public void set(String key, String value, Integer seconds) {
        jedisCluster.set(key, value);
        jedisCluster.expire(key, seconds);
    }

    @Override
    public Long incr(String key) {
        Long incr = jedisCluster.incr(key);
        return incr;
    }
}
