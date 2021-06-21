package com.snailmann.tinyurl.server.factory;

import com.snailmann.tinyurl.common.core.factory.TinyUrlFactory;
import com.snailmann.tinyurl.common.util.Base62;
import com.snailmann.tinyurl.common.util.MurmurUtils;
import com.snailmann.tinyurl.server.storage.IDGenStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liwenjie
 */
@Slf4j
@Component
public class RedisTinyUrlFactory implements TinyUrlFactory {

    private static final int BUCKET_COUNT = 62 ^ 3;

    private final Base62 base62 = Base62.createInstance();

    private final IDGenStorage idGenStorage;

    public RedisTinyUrlFactory(IDGenStorage idGenStorage) {
        this.idGenStorage = idGenStorage;
    }


    @Override
    public String create(String originalAddress) {
        long hc = MurmurUtils.smooth(originalAddress);
        int bucket = (int) (hc % BUCKET_COUNT);
        long id = idGenStorage.increase(bucket);
        log.info("hc: {}, bucket: {}, id: {}", hc, bucket, id);
        return new String(base62.encode((bucket + "").getBytes()))
                + new String(base62.encode((id + "").getBytes()));
    }
}
