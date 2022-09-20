package com.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "hello springboot module";
    }
}
