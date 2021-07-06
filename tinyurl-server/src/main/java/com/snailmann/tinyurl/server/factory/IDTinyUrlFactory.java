package com.snailmann.tinyurl.server.factory;

import com.snailmann.tinyurl.common.core.exception.TinyKeyConsistencyConflictException;
import com.snailmann.tinyurl.common.core.factory.TinyUrlFactory;
import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.common.util.Base62;
import com.snailmann.tinyurl.common.util.MurmurUtils;
import com.snailmann.tinyurl.server.storage.IDGenStorage;
import com.snailmann.tinyurl.server.storage.TinyUrlStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author liwenjie
 */
@Slf4j
@Component
public class IDTinyUrlFactory implements TinyUrlFactory {

    /**
     * 3844 = 62^2
     */
    private static final int BUCKET_COUNT = 62 ^ 2;

    private final Base62 base62 = Base62.createInstance();
    private final IDGenStorage idGenStorage;
    private final TinyUrlStorage tinyUrlStorage;

    public IDTinyUrlFactory(IDGenStorage idGenStorage, TinyUrlStorage tinyUrlStorage) {
        this.idGenStorage = idGenStorage;
        this.tinyUrlStorage = tinyUrlStorage;
    }

    /**
     * tiny key = bucket (62^2) + bucketId (62^6)
     *
     * @param address original address
     * @return key of tiny url
     */
    @Override
    public String create(String address) {
        // hash code of original address
        long hc = MurmurUtils.hash32(address);

        // bucket and bucket id
        int bucket = (int) (hc % BUCKET_COUNT);
        long id = idGenStorage.increase(bucket);
        String key = new String(base62.encode((bucket + "").getBytes()))
                + new String(base62.encode((id + "").getBytes()));

        // repetition is not allowed
        Optional<Meta> metaOptional = tinyUrlStorage.get(key);
        if (metaOptional.isEmpty()) {
            if (log.isInfoEnabled()) {
                log.info("hashcode:{}, bucket:{}, id:{}, address:{},",
                        hc, bucket, id, address);
            }
            return key;
        } else {
            throw new TinyKeyConsistencyConflictException(
                    String.format("consistency conflict, address: %s, key: %s", address, key));
        }
    }

}
