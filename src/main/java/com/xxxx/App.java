package com.xxxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *启动类
 *
 */
@SpringBootApplication
@MapperScan("com.xxxx.sms.dao")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
