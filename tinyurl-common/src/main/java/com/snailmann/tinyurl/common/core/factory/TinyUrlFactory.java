package com.snailmann.tinyurl.common.core.factory;

/**
 * @author liwenjie
 */
public interface TinyUrlFactory {

    /**
     * get key of tiny url from original address
     *
     * @param originalAddress original address
     * @return tiny key
     */
    String create(String originalAddress);

}
