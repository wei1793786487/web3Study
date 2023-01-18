package com.example.web3study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.web3study.mapper")
public class Web3StudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(Web3StudyApplication.class, args);
    }

}
