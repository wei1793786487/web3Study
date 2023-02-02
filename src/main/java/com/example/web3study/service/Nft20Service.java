package com.example.web3study.service;

import java.math.BigInteger;

public interface Nft20Service {

     BigInteger getAdminBalance() throws Exception;




     String tranceTokenByAdmin(String from, String to, Double number) throws Exception;

     String getTokenFromAdmin(String to) throws Exception;

     String setTokenFromAdminNumber(Double number) throws Exception;

    void receiveFree() throws Exception;
}
