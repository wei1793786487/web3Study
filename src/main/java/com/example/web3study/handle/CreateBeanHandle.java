package com.example.web3study.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBeanHandle {


    @Bean
    public ObjectMapper user(){
        return new ObjectMapper();
    }
}
