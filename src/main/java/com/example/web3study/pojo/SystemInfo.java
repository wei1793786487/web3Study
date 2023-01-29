package com.example.web3study.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SystemInfo implements Serializable {
    private Integer id;

    private String systemPublicKey;

    private String systemPrivateKey;



    /**
     * token过期时间
     */
    private Integer tokenExpireSecond;
}