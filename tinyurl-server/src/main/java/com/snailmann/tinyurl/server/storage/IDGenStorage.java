package com.snailmann.tinyurl.server.storage;

import com.snailmann.tinyurl.server.storage.constant.RedisKeys;

/**
 * @author liwenjie
 */
public interface IDGenStorage {

    /**
     * increase
     *
     * @param bucket bucket number
     * @return increased value
     */
    Long increase(Integer bucket);

    /**
     * redis key
     *
     * @param bucket bucket number
     * @return redis key
     */
    default String formatkey(int bucket) {
        return String.format(RedisKeys.GEN_ID_BUCKET_KEY, bucket);
    }

}
