package com.snailmann.tinyurl.server.controller;

import com.snailmann.tinyurl.server.service.TinyService;
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

    private final TinyService tinyService;

    public RedirectController(TinyService tinyService) {
        this.tinyService = tinyService;
    }

    @SneakyThrows
    @GetMapping("/{key}")
    public void tinyUrl(@PathVariable String key, HttpServletResponse response) {
        String originalAddress = tinyService.getOriginalAddress(key);
        response.sendRedirect(originalAddress);
    }

}
