package com.snailmann.tinyurl.common.util;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @author liwenjie
 */
public class MurmurUtils {

    private static final HashFunction MURMUR_3_32 = Hashing.murmur3_32();

    private static final HashFunction MURMUR_3_128 = Hashing.murmur3_128();

    public static int hash32(String key) {
        return MURMUR_3_32.hashBytes(key.getBytes()).asInt();
    }

    public static long hash64(String key) {
        return MURMUR_3_128.hashBytes(key.getBytes()).asLong();
    }

}
