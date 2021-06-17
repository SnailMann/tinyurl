package com.snailmann.tinyurl.server.storage.impl;

import com.snailmann.tinyurl.server.storage.BaseRedisStorage;
import com.snailmann.tinyurl.server.storage.TinyUrlStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.Duration;

/**
 * @author liwenjie
 */
@Slf4j
@Repository
public class TinyUrlStorageImpl extends BaseRedisStorage implements TinyUrlStorage {

    @Override
    public void add(String address, String tiny, int ttl, Duration duration) {


    }

    @Override
    public String get(String address) {
        return null;
    }
}
