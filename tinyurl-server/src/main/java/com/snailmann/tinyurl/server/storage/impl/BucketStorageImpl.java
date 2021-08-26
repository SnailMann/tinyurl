package com.snailmann.tinyurl.server.storage.impl;

import com.snailmann.tinyurl.server.storage.BaseRedisStorage;
import com.snailmann.tinyurl.server.storage.BucketStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author liwenjie
 */
@Slf4j
@Repository
public class BucketStorageImpl extends BaseRedisStorage implements BucketStorage {

    @Override
    public Long increase(Integer bucket) {
        String key = format(bucket);
        return redisTemplate.opsForValue()
                .increment(key);
    }
}
