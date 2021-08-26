package com.snailmann.tinyurl.server.storage;

import com.snailmann.tinyurl.server.storage.constant.RedisKeys;

/**
 * @author liwenjie
 */
public interface BucketStorage {

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
    default String format(int bucket) {
        return String.format(RedisKeys.BUCKET_KEY_FORMAT, bucket);
    }

}
