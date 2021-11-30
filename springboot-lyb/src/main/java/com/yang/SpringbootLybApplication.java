package com.yang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.yang.mapper")
@SpringBootApplication
public class SpringbootLybApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLybApplication.class, args);
    }

}
