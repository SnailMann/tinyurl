package com.snailmann.tinyurl.server.controller;

import com.snailmann.tinyurl.common.model.dto.RegisterRequest;
import com.snailmann.tinyurl.common.model.dto.RegisterResponse;
import com.snailmann.tinyurl.server.service.TinyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
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

    private final TinyService tinyService;

    public RegisterController(TinyService tinyService) {
        this.tinyService = tinyService;
    }

    @PostMapping
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        check(request);
        String address = request.getAddress();
        long ttl = request.getTtl();

        try {
            String tinyUrl = tinyService.register(address, ttl);
            log.info("address: {}, res: {}", address, tinyUrl);
            return RegisterResponse.ok(tinyUrl);
        } catch (Exception e) {
            log.error("register error, address: {}", address, e);
            return RegisterResponse.error();
        }
    }

    private void check(RegisterRequest registerRequest) {
        Assert.notNull(registerRequest, "body is null");
        Assert.hasText(registerRequest.getAddress(), "original address is null");
        Assert.isTrue(registerRequest.getTtl() >= 1, "ttl must be greater than 1s");
    }
}


