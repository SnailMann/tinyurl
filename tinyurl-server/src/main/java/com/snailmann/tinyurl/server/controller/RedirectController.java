package com.snailmann.tinyurl.server.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author liwenjie
 */
@Slf4j
@Controller
@RequestMapping("/tinyurl")
public class RedirectController {

    @SneakyThrows
    @GetMapping("/{key}")
    public void tinyUrl(HttpServletResponse response) {
        response.sendRedirect("http://www.baidu.com");
    }

}
