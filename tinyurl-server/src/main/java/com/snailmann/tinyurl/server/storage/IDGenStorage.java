package com.snailmann.tinyurl.server.storage;

import com.snailmann.tinyurl.server.storage.constant.RedisKeys;

/**
 * @author liwenjie
 */
public interface IDGenStorage {

    Long increase(Integer bucket);

    default String formatkey(int bucket) {
        return String.format(RedisKeys.GEN_ID_BUCKET_KEY, bucket);
    }

}
