package com.snailmann.tinyurl.server.factory;

import com.snailmann.tinyurl.common.core.factory.TinyUrlFactory;
import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.common.util.Base62;
import com.snailmann.tinyurl.server.storage.TinyUrlStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author liwenjie
 */
@Slf4j
@Component
public class HashTinyUrlFactory implements TinyUrlFactory {

    private final Base62 base62 = Base62.createInstance();

    private final TinyUrlStorage tinyUrlStorage;

    public HashTinyUrlFactory(TinyUrlStorage tinyUrlStorage) {
        this.tinyUrlStorage = tinyUrlStorage;
    }

    @Override
    public String create(String address) {
        long hc = 1;
        String key;
        for (int times = 1; ; times++) {
            key = key(times, hc);
            Optional<Meta> metaOptional = tinyUrlStorage.get(key);
            // return if empty
            if (metaOptional.isEmpty()) {
                break;
            }
            // continue if present and not equal
            Meta meta = metaOptional.get();
            if (address.equals(meta.getOriginalAddress())) {
                break;
            }
        }
        return key;
    }

    private String key(int times, long hc) {
        // maximum 16 hash conflicts
        int max = 16;
        if (times >= max) {
            throw new UnsupportedOperationException("maximum number of conflicts exceeded");
        }
        byte[] bytes;
        if (times <= 1) {
            bytes = base62(hc);
        } else {
            // hc = hc + 2^ times
            hc += Math.pow(2, times);
            bytes = base62(hc);
        }
        return new String(bytes);
    }

    private byte[] base62(long hc) {
        return base62.encode((hc + "").getBytes());
    }

}
