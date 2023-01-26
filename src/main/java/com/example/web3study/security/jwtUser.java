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

}
