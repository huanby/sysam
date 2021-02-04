package com.sysam.sysam_main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sysam.*"})
@MapperScan("com.sysam.*.dao")
public class SysamMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysamMainApplication.class, args);
    }

}
