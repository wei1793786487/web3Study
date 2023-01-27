package com.example.web3study.service;

/**
 * @author sun
 */
public interface CheckService {

    public Boolean checkByGoogleRecaptcha(String response,String ip);
}
