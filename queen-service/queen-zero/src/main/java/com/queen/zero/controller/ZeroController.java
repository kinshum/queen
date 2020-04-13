package com.queen.zero.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @author jensen
 */
@RestController
@RequestMapping("/api")
public class ZeroController {


    @GetMapping("/info")
    public String getInfo(String name) {
        return "Hello" + name;
    }


}
