package com.snailmann.tinyurl.server.service.impl;

import com.snailmann.tinyurl.common.core.exception.AddressNotFoundException;
import com.snailmann.tinyurl.common.core.exception.TinyKeyConsistencyConflictException;
import com.snailmann.tinyurl.common.core.factory.TinyUrlFactory;
import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.common.util.TinyUtils;
import com.snailmann.tinyurl.server.service.TinyUrlService;
import com.snailmann.tinyurl.server.storage.TinyUrlStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author liwenjie
 */
@Slf4j
@Service
public class TimyUrlServiceImpl implements TinyUrlService {

    private final TinyUrlFactory tinyUrlFactory;

    private final TinyUrlStorage tinyUrlStorage;

    public TimyUrlServiceImpl(TinyUrlFactory tinyUrlFactory, TinyUrlStorage tinyUrlStorage) {
        this.tinyUrlFactory = tinyUrlFactory;
        this.tinyUrlStorage = tinyUrlStorage;
    }

    @Override
    public String register(String address, Long ttl) {
        String tinyKey = tinyUrlFactory.create(address);
        Optional<Meta> metaOptional = tinyUrlStorage.get(tinyKey);
        // the current version ignores concurrency
        if (metaOptional.isEmpty()) {
            tinyUrlStorage.add(address, tinyKey, ttl);
        } else {
            throw new TinyKeyConsistencyConflictException(
                    String.format("consistency conflict, address: %s, tinykey: %s", address, tinyKey));
        }
        return TinyUtils.format(tinyKey);
    }

    @Override
    public String getOriginalAddress(String tinyKey) {
        Optional<Meta> metaOptional = tinyUrlStorage.get(tinyKey);
        if (metaOptional.isEmpty()) {
            throw new AddressNotFoundException("not found");
        }
        return metaOptional.get().getOriginalAddress();
    }

}
