package com.snailmann.tinyurl.server.model.entity;

import java.time.Duration;
import java.util.Date;

/**
 * @author liwenjie
 */
public class TinyUrl {

    private String key;

    private String originalUrl;

    private Date creation;

    private Long ttl;

    private Duration duration;


}
