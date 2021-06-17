package com.snailmann.tinyurl.common.util;

import com.facebook.util.digest.MurmurHash;

/**
 * @author liwenjie
 */
public class MurmurUtils {

    private static final MurmurHash HASH = new MurmurHash(48281);

    public static long smooth(String key) {
        long code = HASH.hash(HASH.hashToLong(key.getBytes()));
        if (code < 0) {
            code *= -1;
        }
        return code;
    }

}
