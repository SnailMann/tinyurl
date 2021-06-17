package com.snailmann.tinyurl.server.controller;

import com.snailmann.tinyurl.common.core.factory.TinyUrlFactory;
import com.snailmann.tinyurl.common.model.dto.RegisterRequest;
import com.snailmann.tinyurl.common.model.dto.RegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwenjie
 */
@Slf4j
@RestController
@RequestMapping("/tinyurl-server/v1/register")
public class RegisterController {

    private final TinyUrlFactory tinyUrlFactory;

    public RegisterController(TinyUrlFactory tinyUrlFactory) {
        this.tinyUrlFactory = tinyUrlFactory;
    }

    @PostMapping("/")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        String address = request.getRealAddress();
        String res = tinyUrlFactory.produce(address);
        log.info("address: {}, res: {}", address, res);
        return RegisterResponse.builder().tinyUrl(res).build();
    }
}


