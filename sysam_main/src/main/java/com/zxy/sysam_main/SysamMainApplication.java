package com.zxy.sysam_main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.zxy.*"})
@MapperScan("com.zxy.*.dao")
public class SysamMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysamMainApplication.class, args);
    }

}
