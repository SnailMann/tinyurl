package com.snailmann.tinyurl.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author liwenjie
 */
@Slf4j
@Configuration
public class TinyUrlCommonAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("ShortchainCommonAutoConfiguration Initialization");
    }

}
