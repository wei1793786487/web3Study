package com.example.web3study.pojo;

import java.util.Date;
import lombok.Data;

import javax.print.attribute.standard.PrinterURI;

@Data
public class Nft {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 发行方id
     */
    private Integer authorId;

    /**
     * 区块链日志的id
     */
    private Integer blockchainLogId;

    /**
     * 名称
     */
    private String name;

    /**
     * 1为7211 2为1155
     */
    private Integer type;

    /**
     * 初始铸造的数量
     */
    private Integer mintNumber;

    /**
     * 名称简写
     */
    private String symbol;

    /**
     * 当前的数量(包括增发与销毁)
     */
    private Integer currentNumber;

    /**
     * 项目的可读描述
     */
    private String description;

    /**
     * ipfs hash值
     */
    private String ipfsHash;

    /**
     * url地址
     */
    private String image;

    /**
     * 创建时间
     */
    private Date createTime=new Date();


    private BlockchainLog blockchainLog;
}