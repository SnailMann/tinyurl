package com.snailmann.tinyurl.server.storage;

import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.server.storage.constant.RedisKeys;

import java.util.Optional;

/**
 * @author liwenjie
 */
public interface TinyStorage {

    /**
     * Add a meta
     *
     * @param originalAddress original address
     * @param tinyKey         key
     * @param ttl             expiration time in seconds
     */
    Boolean add(String originalAddress, String tinyKey, long ttl);

    /**
     * Get a meta by key of tiny url
     *
     * @param tinyKey key
     * @return meta
     */
    Optional<Meta> get(String tinyKey);

    /**
     * Redis key of tiny meta
     *
     * @param tinyKey key of tiny url
     * @return redis key
     */
    default String format(String tinyKey) {
        return String.format(RedisKeys.TINY_URL_KEY_FORMAT, tinyKey);
    }


}
