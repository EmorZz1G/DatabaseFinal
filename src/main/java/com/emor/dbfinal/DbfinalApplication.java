package com.emor.dbfinal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableAsync
@EnableScheduling
@SpringBootApplication
@MapperScan("com.emor.dbfinal.dao")
public class DbfinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbfinalApplication.class, args);
    }

}
