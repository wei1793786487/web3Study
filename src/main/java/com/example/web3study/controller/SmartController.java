package com.example.web3study.controller;


import com.example.web3study.pojo.common.Web3TransactionError;
import com.example.web3study.smartContract.NFT721;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.function.BiConsumer;

import static com.example.web3study.utils.Web3Utils.web3jErrorToPojo;

@RestController
@RequestMapping("/smart")
public class SmartController {
    @Autowired
    private Web3j web3j;


    @Autowired
    private StaticGasProvider staticGasProvider;


    @Autowired
    TransactionManager transactionManager;

    @GetMapping("/set")
    public String doGetLatestBlockNumber()  {
        NFT721.deploy(web3j, transactionManager, staticGasProvider,
                "laoang", "lb", BigInteger.valueOf(5),
                "https://avatars.githubusercontent.com/u/54950332?s=96&v=4").sendAsync().whenCompleteAsync(new BiConsumer<NFT721, Throwable>() {
            @Override
            public void accept(NFT721 nft721, Throwable throwable) {
                if (throwable!=null){
                    String message = throwable.getMessage();
                    Web3TransactionError web3TransactionError = web3jErrorToPojo(message);
                    System.out.println(web3TransactionError);
                }else {
                    String contractAddress = nft721.getContractAddress();
                    System.out.println(contractAddress);
                }

            }
        });
        return "操作成功";
    }

}
