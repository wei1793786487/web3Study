package com.example.web3study.controller;


import cn.hutool.core.thread.ThreadUtil;
import com.example.web3study.extend.MyPollingTransactionReceiptProcessor;
import com.example.web3study.smartContract.NFT721;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/smart")
public class smartController {
    @Autowired
    private Web3j web3j;

    private static final Logger logger = LoggerFactory.getLogger(BlockChainInfoController.class);


    @GetMapping("/set")
    public String doGetLatestBlockNumber() throws InterruptedException {


        Credentials credentials = Credentials.create("dc156b09431f68dec35e60d6a1882afdd787f22f416503264a580271e2c6acba");
        long chainIdOfPolygon = 1337;
        TransactionManager bridgeTokenTxManager = new RawTransactionManager(
                web3j, credentials, chainIdOfPolygon, new MyPollingTransactionReceiptProcessor(web3j));
        StaticGasProvider staticGasProvider = new StaticGasProvider(BigInteger.valueOf(6700000), BigInteger.valueOf(6721975));
        NFT721.deploy(web3j, bridgeTokenTxManager, staticGasProvider,
                "996", "lb", BigInteger.valueOf(5),
                "https://avatars.githubusercontent.com/u/54950332?s=96&v=4").sendAsync().whenCompleteAsync(new BiConsumer<NFT721, Throwable>() {
            @Override
            public void accept(NFT721 nft721, Throwable throwable) {
                System.out.println(nft721.getContractAddress());
                
            }
        });

        return "fff";

    }


}
