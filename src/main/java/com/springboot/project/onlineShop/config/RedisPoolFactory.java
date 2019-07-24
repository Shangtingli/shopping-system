package com.springboot.project.onlineShop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConf;

    @Bean
    private JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConf.getPoolMaxIdle());
        jedisPoolConfig.setMaxTotal(redisConf.getPoolMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisConf.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(redisConf.getHost(), redisConf.getPort());
        return jp;
    }
}