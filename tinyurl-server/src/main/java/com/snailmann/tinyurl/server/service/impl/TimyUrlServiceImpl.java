package com.snailmann.tinyurl.server.service.impl;

import com.snailmann.tinyurl.common.core.exception.AddressNotFoundException;
import com.snailmann.tinyurl.common.core.factory.TinyUrlFactory;
import com.snailmann.tinyurl.common.model.bo.Meta;
import com.snailmann.tinyurl.common.util.TinyUtils;
import com.snailmann.tinyurl.server.service.TinyUrlService;
import com.snailmann.tinyurl.server.storage.TinyUrlStorage;
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
public class TimyUrlServiceImpl implements TinyUrlService {

    private final TinyUrlFactory factoryA;
    private final TinyUrlFactory factoryB;
    private final TinyUrlStorage tinyUrlStorage;

    public TimyUrlServiceImpl(@Qualifier("IDTinyUrlFactory") TinyUrlFactory factoryA,
                              @Qualifier("hashTinyUrlFactory") TinyUrlFactory factoryB,
                              TinyUrlStorage tinyUrlStorage) {
        this.factoryA = factoryA;
        this.factoryB = factoryB;
        this.tinyUrlStorage = tinyUrlStorage;
    }

    @Override
    public String register(String address, Boolean one2many, Long ttl) {
        String tinyKey = one2many ? factoryA.create(address) : factoryB.create(address);
        if (StringUtils.isBlank(tinyKey)) {
            throw new UnsupportedOperationException("key is blank");
        }
        tinyUrlStorage.add(address, tinyKey, ttl);
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
