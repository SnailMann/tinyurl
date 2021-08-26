package com.snailmann.tinyurl.server.factory;

import com.snailmann.tinyurl.common.core.factory.KeyFactory;
import com.snailmann.tinyurl.common.util.Base62;
import com.snailmann.tinyurl.common.util.MurmurUtils;
import com.snailmann.tinyurl.server.storage.BucketStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liwenjie
 */
@Slf4j
@Component
public class DefaultKeyFactory implements KeyFactory {

    /**
     * 3844 = 62^2
     */
    private static final int BUCKET_COUNT = 62 ^ 2;

    private final Base62 base62 = Base62.createInstance();
    private final BucketStorage bucketStorage;

    public DefaultKeyFactory(BucketStorage bucketStorage) {
        this.bucketStorage = bucketStorage;
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

        // low 2-bit calculate bucket
        int bucket = (int) (hc % BUCKET_COUNT);
        long id = bucketStorage.increase(bucket);

        return new String(base62.encode((bucket + "").getBytes()))
                + new String(base62.encode((id + "").getBytes()));
    }

}
