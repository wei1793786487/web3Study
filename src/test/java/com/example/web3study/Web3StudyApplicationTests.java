package com.example.web3study;
import cn.hutool.json.JSON;
import com.example.web3study.controller.BlockChainInfoController;
import com.example.web3study.extend.MyPollingTransactionReceiptProcessor;
import com.example.web3study.pojo.Web3TransactionError;
import com.example.web3study.smartContract.NFT721;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.functions.Consumer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

import static com.example.web3study.utils.Web3Utils.web3jErrorToPojo;


@SpringBootTest
class Web3StudyApplicationTests {
    @Autowired
    private Web3j web3j;
    @Autowired
    private StaticGasProvider staticGasProvider;


    @Autowired
    TransactionManager transactionManager;

    private static final Logger logger = LoggerFactory.getLogger(BlockChainInfoController.class);
    @Test
    void contextLoads() throws Exception {
        bshu();
    }


    public void  bshu() throws ExecutionException, InterruptedException {

        NFT721 MyToken = new NFT721("0xe4e14f91aa5863cb6e6803a0e57aad0aed12e9ef", web3j, transactionManager, staticGasProvider);

        MyToken.transferEventFlowable().subscribe(new Consumer<NFT721.TransferEventResponse>() {
            @Override
            public void accept(NFT721.TransferEventResponse transferEventResponse) throws Exception {

            }
        });
    }
}
