package com.queen.schedule.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jensen
 * @description 测试类
 * @date 2020/1/10 15:44
 */
@RestController
@AllArgsConstructor
@RequestMapping("/test")
@Api(value = "测试类", tags = "测试类")
public class TestController {


    @GetMapping("/test")
    public String test(){
        return "test";
    }


}
