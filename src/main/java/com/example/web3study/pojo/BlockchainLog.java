package com.example.web3study.pojo;

import lombok.Data;

@Data
public class BlockchainLog {
    private Integer id;

    /**
     * 智能合约地址
     */
    private String contractAddress;

    /**
     * 智能合约部署者地址
     */
    private String mintAddress;

    /**
     * 花费的gas
     */
    private Long gasSpend;

    /**
     * 状态 0表示链操作  1表示成功 -1 表示失败
     */
    private Integer state;

    /**
     * 所属区块链
     */
    private String blockchain;

    /**
     * 交易完成的区块
     */
    private String blocknumber;

    /**
     * 区块hash
     */
    private String blockHash;

    /**
     * 交易hash
     */
    private String transactionHash;

    /**
     * 错误信息
     */
    private String errorInfo;
}