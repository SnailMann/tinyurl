package com.snailmann.tinyurl.server.controller;

import com.snailmann.tinyurl.server.service.TinyUrlService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author liwenjie
 */
@Slf4j
@Controller
@RequestMapping("/tinyurl")
public class RedirectController {

    private final TinyUrlService tinyUrlService;

    public RedirectController(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @SneakyThrows
    @GetMapping("/{key}")
    public void tinyUrl(@PathVariable String key, HttpServletResponse response) {
        String originalAddress = tinyUrlService.getOriginalAddress(key);
        response.sendRedirect(originalAddress);
    }

}
