package com.example.web3study.conf;

import com.example.web3study.pojo.common.CurrencyInformation;
import com.example.web3study.smartContract.WWT;
import com.example.web3study.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.ens.EnsResolutionException;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class SystemTokenInit {
    //
    @Autowired
    private Web3j web3j;

    @Autowired
    private StaticGasProvider staticGasProvider;

    @Value("${web3j.system_token_address}")
    private String tokenAddress;

    @Value("${web3j.blockchain_resources_name}")
    private String resourcesName;

    @Autowired
    TransactionManager transactionManager;

    @Autowired
    RedisUtils redisUtils;

    @PostConstruct
    public void init() throws Exception {
        try {
            WWT wwt = WWT.load(tokenAddress,web3j, transactionManager, staticGasProvider);
            CurrencyInformation currencyInformation = new CurrencyInformation();
            currencyInformation.setAddress(tokenAddress);
            String name = wwt.name().send();
            BigInteger decimals = wwt.decimals().send();
            String owner = wwt.owner().send();
            BigInteger totalSupply = wwt.totalSupply().send();
            BigInteger divide = totalSupply.divide(new BigInteger("10").pow(decimals.intValue()));
            currencyInformation.setDecimals(decimals.longValue());
            currencyInformation.setName(name);
            currencyInformation.setOwner(owner);
            currencyInformation.setVirtualResources(resourcesName);
            currencyInformation.setTotalNumber(divide.longValue());
            log.info("初始化货币"+currencyInformation.toString());
            redisUtils.initCurrencyInformation(currencyInformation);
        } catch (Exception e) {
            if (e instanceof EnsResolutionException){
                log.error("货币地址可能出错了");
            }
            throw new RuntimeException(e);
        }
    }
}