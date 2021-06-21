package com.snailmann.tinyurl.server.storage.impl;

import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.server.storage.BaseRedisStorage;
import com.snailmann.tinyurl.server.storage.TinyUrlStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

/**
 * @author liwenjie
 */
@Slf4j
@Repository
public class TinyUrlStorageImpl extends BaseRedisStorage implements TinyUrlStorage {

    @Override
    public void add(String originalAddress, String tinyKey, long ttl) {
        Meta meta = Meta.builder().originalAddress(originalAddress)
                .tinyKey(tinyKey).ttl(ttl).creation(System.currentTimeMillis())
                .build();
        String data = gson.toJson(meta);
        redisTemplate.opsForValue()
                .set(formatKey(tinyKey), data, Duration.ofSeconds(ttl));
    }

    @Override
    public Optional<Meta> get(String tinyKey) {
        String data = redisTemplate.opsForValue().get(formatKey(tinyKey));
        if (StringUtils.isBlank(data)) {
            return Optional.empty();
        }
        return Optional.of(gson.fromJson(data, Meta.class));
    }

}