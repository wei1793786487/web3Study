package com.example.web3study.pojo;

import lombok.Data;

@Data
public class SystemInfo {
    private Integer id;

    private String systemPublicKey;

    private String systemPrivateKey;

    /**
     * token过期时间
     */
    private Integer tokenExpireSecond;
}