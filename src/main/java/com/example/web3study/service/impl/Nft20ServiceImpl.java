package com.example.web3study.service.impl;

import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.Users;
import com.example.web3study.pojo.common.CurrencyInformation;
import com.example.web3study.pojo.common.ReturnCode;
import com.example.web3study.pojo.common.Web3TransactionError;
import com.example.web3study.service.Nft20Service;
import com.example.web3study.service.UsersService;
import com.example.web3study.smartContract.WWT;
import com.example.web3study.utils.MathUtils;
import com.example.web3study.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;

import static com.example.web3study.utils.Web3Utils.web3jErrorToPojo;

@Service
@Slf4j
public class Nft20ServiceImpl implements Nft20Service {

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private Web3j web3j;

    @Autowired
    private StaticGasProvider staticGasProvider;


    @Autowired
    TransactionManager transactionManager;

    @Autowired
    UsersService usersService;

    @Override
    public BigInteger getAdminBalance() throws Exception {
        CurrencyInformation currencyInformation = redisUtils.getCurrencyInformation();
        String address = currencyInformation.getAddress();
        WWT wwt = WWT.load(address,web3j, transactionManager, staticGasProvider);
        BigInteger send = wwt.balanceOf(currencyInformation.getOwner()).send();
        return send;
    }

    @Override
    public String tranceTokenByAdmin(String from, String to, Double number) throws Exception {
        CurrencyInformation currencyInformation = redisUtils.getCurrencyInformation();
        TransactionReceipt receipt = null;
        try {
        String address = currencyInformation.getAddress();
        WWT wwt = WWT.load(address,web3j, transactionManager, staticGasProvider);
        BigInteger decimals = wwt.decimals().send();
        BigDecimal numberTransactions = MathUtils.decimalsToMultiplyNumber(number, decimals);
        BigInteger haveTokens = wwt.balanceOf(from).sendAsync().get();
        if (haveTokens.compareTo(numberTransactions.toBigInteger())==-1){
             throw new XxException(ReturnCode.TOEKN_NOT_ENOUGH);
        }
            receipt = wwt.transferByAdmin(from, to, numberTransactions.toBigInteger()).sendAsync().get();
        } catch (Exception e) {
           if (e.getCause() instanceof TransactionException){
               Web3TransactionError web3TransactionError = web3jErrorToPojo(e.getCause().getMessage());
               log.error(web3TransactionError.toString());
               throw new XxException(ReturnCode.TRANSACTION_ERROR);
           }
           throw e;
        }
        return receipt.getTransactionHash();
    }


    @Override
    public String getTokenFromAdmin(String to) throws Exception {
        CurrencyInformation currencyInformation = redisUtils.getCurrencyInformation();
        String address = currencyInformation.getAddress();
        WWT load = WWT.load(address, web3j, transactionManager, staticGasProvider);
        TransactionReceipt send = load.claimToken(to).send();
        return send.getTransactionHash();
    }

    @Override
    public String setTokenFromAdminNumber(Double number) throws Exception {
        CurrencyInformation currencyInformation = redisUtils.getCurrencyInformation();
        String address = currencyInformation.getAddress();
        WWT wwt = WWT.load(address, web3j, transactionManager, staticGasProvider);
        BigInteger decimals = wwt.decimals().send();
        TransactionReceipt send = wwt.setClaimNumber(MathUtils.decimalsToMultiplyNumber(number, decimals).toBigInteger()).send();
        return send.getTransactionHash();
    }

    @Override
    public void receiveFree() throws Exception {
        try {
            String phone = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Users users = usersService.finOneByPhone(phone);
            String address = users.getBlockchainUser().getAddress();
            log.info("领取币"+address);
            getTokenFromAdmin(address);
        } catch (Exception e) {

            throw new XxException(ReturnCode.RECEIVE_ERROR);
        }
    }

}
