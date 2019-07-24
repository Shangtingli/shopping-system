package com.springboot.project.onlineShop.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Service
public class RedisService {

    @Resource
    JedisPool jedisPool;


}
