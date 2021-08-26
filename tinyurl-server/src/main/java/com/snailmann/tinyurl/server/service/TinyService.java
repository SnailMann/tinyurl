package com.snailmann.tinyurl.server.service;

/**
 * @author liwenjie
 */
public interface TinyService {

    /**
     * register a tiny url for original address
     *
     * @param address original address
     * @param ttl     expire second
     * @return tiny url
     */
    String register(String address, Long ttl);

    /**
     * get the original address according to tiny key
     *
     * @param tinyKey key of tiny url
     * @return original address
     */
    String getOriginalAddress(String tinyKey);
}
