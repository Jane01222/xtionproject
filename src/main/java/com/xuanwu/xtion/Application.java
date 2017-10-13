package com.xuanwu.xtion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan("com.xuanwu.xtion.dao")
public class Application {
    //启动spring boot的入口
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}