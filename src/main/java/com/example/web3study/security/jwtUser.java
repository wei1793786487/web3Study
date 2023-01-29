package com.example.web3study.security;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class jwtUser {

     private  Integer uid;

     private String name;

     private String phone;
     private String role;
     /**
      *      登录 类型0是账号密码登录  1是手机号登录 2是私钥登录
      */
     private Integer type;

     private String platform;
}
