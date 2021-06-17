package com.snailmann.tinyurl.server.storage;

import java.time.Duration;

/**
 * @author liwenjie
 */
public interface TinyUrlStorage {

    void add(String address, String tiny, int ttl, Duration duration);

    String get(String address);

}
