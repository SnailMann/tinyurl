package com.snailmann.tinyurl.server.storage;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author liwenjie
 */
public class BaseRedisStorage {

    @Autowired
    protected StringRedisTemplate redisTemplate;

    @Autowired
    protected Gson gson;

}

