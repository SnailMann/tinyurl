package com.snailmann.tinyurl.server.storage.impl;

import com.snailmann.tinyurl.server.storage.BaseRedisStorage;
import com.snailmann.tinyurl.server.storage.IDGenStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author liwenjie
 */
@Slf4j
@Repository
public class IDGenStorageImpl extends BaseRedisStorage implements IDGenStorage {

    @Override
    public Long increase(Integer bucket) {
        String key = formatkey(bucket);
        return redisTemplate.opsForValue()
                .increment(key);
    }
}
