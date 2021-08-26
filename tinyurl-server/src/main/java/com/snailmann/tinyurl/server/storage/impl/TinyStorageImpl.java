package com.snailmann.tinyurl.server.storage.impl;

import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.server.storage.BaseRedisStorage;
import com.snailmann.tinyurl.server.storage.TinyStorage;
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
public class TinyStorageImpl extends BaseRedisStorage implements TinyStorage {

    @Override
    public Boolean add(String originalAddress, String tinyKey, long ttl) {
        Meta meta = Meta.builder().originalAddress(originalAddress)
                .tinyKey(tinyKey).ttl(ttl).creation(System.currentTimeMillis())
                .build();
        String s = gson.toJson(meta);
        // avoid modifying existing values
        return redisTemplate.opsForValue()
                .setIfAbsent(format(tinyKey), s, Duration.ofSeconds(ttl));
    }

    @Override
    public Optional<Meta> get(String tinyKey) {
        String s = redisTemplate.opsForValue().get(format(tinyKey));
        if (StringUtils.isBlank(s)) {
            return Optional.empty();
        }
        return Optional.of(gson.fromJson(s, Meta.class));
    }

}
