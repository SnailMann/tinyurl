package com.snailmann.tinyurl.server.factory;

import com.snailmann.tinyurl.common.core.factory.TinyUrlFactory;
import com.snailmann.tinyurl.common.util.MurmurUtils;
import com.snailmann.tinyurl.server.storage.IDGenStorage;
import com.snailmann.tinyurl.server.storage.TinyUrlStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;

/**
 * @author liwenjie
 */
@Slf4j
@Component
public class RedisTinyUrlFactory implements TinyUrlFactory {


    private static final int BUCKET_COUNT = 62 ^ 3;

    private final TinyUrlStorage tinyUrlStorage;

    private final IDGenStorage idGenStorage;

    public RedisTinyUrlFactory(TinyUrlStorage tinyUrlStorage, IDGenStorage idGenStorage) {
        this.tinyUrlStorage = tinyUrlStorage;
        this.idGenStorage = idGenStorage;
    }

    @Override
    public String produce(String address) {
        long hc = MurmurUtils.smooth(address);
        int bucket = (int) (hc % BUCKET_COUNT);
        long id = idGenStorage.increase(bucket);
        log.info("hc: {}, bucket: {}, id: {}", hc, bucket, id);
        return new String(Base64.getEncoder().encode((bucket + "").getBytes()))
                + new String(Base64.getEncoder().encode((id + "").getBytes()));
    }
}
