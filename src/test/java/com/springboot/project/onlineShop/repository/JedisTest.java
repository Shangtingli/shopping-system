package com.springboot.project.onlineShop.repository;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

    @Test
    public void shouldConnect(){
        JedisPool pool = new JedisPool("localhost", 6379);
        Jedis jedis = pool.getResource();
    }
}
