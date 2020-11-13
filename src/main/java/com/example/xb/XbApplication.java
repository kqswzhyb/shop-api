package com.example.xb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.example.xb.mapper")
public class XbApplication {

    public static void main(String[] args) {
        SpringApplication.run(XbApplication.class, args);
    }

}
