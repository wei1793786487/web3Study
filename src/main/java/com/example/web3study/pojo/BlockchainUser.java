package com.example.web3study.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BlockchainUser {
    private Integer id;

    /**
    * 私钥
    */
    @JsonIgnore
    private String privateKey;

    /**
    *  虚拟币 在bsn中叫能量 在以太坊叫 以太币
    */
    private String virtualResources;

    /**
    * 系统自定义货币的名字
    */
    private String systemTokenName;

    /**
    * 系统自定义货币的拥有数量
    */
    private String systemTokenNumber;

    /**
    * 系统自定义货币的地址
    */
    private String systemTokenAddress;
}