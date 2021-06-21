package com.snailmann.tinyurl.server.storage;

import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.server.storage.constant.RedisKeys;

import java.util.Optional;

/**
 * @author liwenjie
 */
public interface TinyUrlStorage {

    /**
     * add a meta
     *
     * @param originalAddress original address
     * @param tinyKey         key of tiny url
     * @param ttl             expire second
     */
    void add(String originalAddress, String tinyKey, long ttl);

    /**
     * get a meta by key of tiny url
     *
     * @param tinyKey key of tiny url
     * @return meta
     */
    Optional<Meta> get(String tinyKey);

    /**
     * redis key of tiny meta
     *
     * @param tinyKey key of tiny url
     * @return redis key
     */
    default String formatKey(String tinyKey) {
        return String.format(RedisKeys.TINY_URL_KEY, tinyKey);
    }


}
