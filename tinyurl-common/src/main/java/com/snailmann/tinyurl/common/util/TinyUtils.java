package com.snailmann.tinyurl.common.util;

/**
 * @author liwenjie
 */
public class TinyUtils {

    private static final String URL_TEMPLATE = "http://localhost:8080/tinyurl/%s";

    public static String format(String key) {
        return String.format(URL_TEMPLATE, key);
    }
}
