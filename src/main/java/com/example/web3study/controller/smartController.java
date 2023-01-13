package com.example.web3study.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

@RestController
@RequestMapping("/smart")
public class smartController {
    @Autowired
    private Web3j web3j;

    private static final Logger logger = LoggerFactory.getLogger(BlockChainInfoController.class);


    @GetMapping("/set")
    public String doGetLatestBlockNumber()throws Exception{
        Credentials credentials = Credentials.create("0009e42aeecd0a3a71c800206b65ff855f607f356812587b4633b59a3dd67d77");
        BigInteger blockNumber = web3j.ethBlockNumber().sendAsync().get().getBlockNumber();
        logger.info("The BlockNumber is: {}",blockNumber);
        //生成请求参数
        DefaultBlockParameterNumber defaultBlockParameterNumber = new DefaultBlockParameterNumber(blockNumber);
        //根据请求参数获取余额
        EthGetBalance ethGetBalance  = web3j.ethGetBalance(credentials.getAddress(),defaultBlockParameterNumber)
                .sendAsync().get();
        logger.info("Get Account Ether is: {}",ethGetBalance.getBalance());
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        logger.info("ERC20 Contract Address: {}",contractGasProvider.getGasLimit());
        logger.info("ERC20 Contract Address: {}",contractGasProvider.getGasPrice());

        long chainIdOfPolygon = 1337;
        TransactionManager bridgeTokenTxManager = new RawTransactionManager(
                web3j, credentials, chainIdOfPolygon);

      return "";
    }
}
