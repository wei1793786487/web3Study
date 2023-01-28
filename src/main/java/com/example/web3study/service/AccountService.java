package com.example.web3study.service;

import com.example.web3study.pojo.Web3Account;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface AccountService {

    public String sendCode(String phone);

    Web3Account registerUser(String phone, String password, String code) throws InvalidAlgorithmParameterException, CipherException, IOException, NoSuchAlgorithmException, NoSuchProviderException;

    Boolean phoneIsReg(String phone);
}
