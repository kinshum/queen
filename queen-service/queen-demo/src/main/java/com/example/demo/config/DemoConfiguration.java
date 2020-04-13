package com.example.demo.config;


import com.example.demo.props.DemoProperties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * 配置feign、mybatis包名、properties
 *
 * @author jensen
 */
@Configuration
@EnableFeignClients({"com.queen", "com.example"})
@MapperScan({"com.queen.**.mapper.**", "com.example.**.mapper.**"})
@EnableConfigurationProperties(DemoProperties.class)
public class DemoConfiguration {

}
