package com.snailmann.tinyurl.server.service.impl;

import com.snailmann.tinyurl.common.core.exception.AddressNotFoundException;
import com.snailmann.tinyurl.common.core.exception.TinyKeyConsistencyConflictException;
import com.snailmann.tinyurl.common.core.factory.KeyFactory;
import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.server.service.TinyService;
import com.snailmann.tinyurl.server.storage.TinyStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author liwenjie
 */
@Slf4j
@Service
public class TinyServiceImpl implements TinyService {

    private static final String URL_TEMPLATE = "http://localhost:8080/tinyurl/%s";

    private final KeyFactory factory;
    private final TinyStorage storage;

    public TinyServiceImpl(@Qualifier("defaultKeyFactory") KeyFactory factory, TinyStorage storage) {
        this.factory = factory;
        this.storage = storage;
    }

    @Override
    public String register(String address, Long ttl) {
        String tinyKey = factory.create(address);
        if (StringUtils.isBlank(tinyKey)) {
            throw new UnsupportedOperationException("key is blank");
        }

        // repetition is not allowed.
        // small probability event, unnecessary judgment
        Optional<Meta> metaOptional = storage.get(tinyKey);
        if (metaOptional.isEmpty()) {
            storage.add(address, tinyKey, ttl);
        } else {
            throw new TinyKeyConsistencyConflictException(
                    String.format("consistency conflict, address: %s, key: %s", address, tinyKey));
        }
        return String.format(URL_TEMPLATE, tinyKey);
    }

    @Override
    public String getOriginalAddress(String tinyKey) {
        Optional<Meta> metaOptional = storage.get(tinyKey);
        if (metaOptional.isEmpty()) {
            throw new AddressNotFoundException("not found");
        }
        return metaOptional.get().getOriginalAddress();
    }

}
