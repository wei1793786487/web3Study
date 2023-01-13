package com.example.web3study.pojo;


import lombok.Data;

@Data
public class Web3Account {

    public Web3Account(String address, String pubKey, String priKey, String word) {
        this.address = address;
        this.pubKey = pubKey;
        this.priKey = priKey;
        this.word = word;
    }

    private String address;

    private String pubKey;

    private String priKey;


    private String word;

}
