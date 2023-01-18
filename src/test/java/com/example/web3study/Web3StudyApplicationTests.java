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

    private static final Logger logger = LoggerFactory.getLogger(BlockChainInfoController.class);
    @Test
    void contextLoads() throws Exception {
        bshu();
    }


    public void  bshu() throws ExecutionException, InterruptedException {
        Credentials credentials = Credentials.create("3cf44c4d4252d9d9277f5503dac4e365f56c69cff5d1396da4f156dc115ddcac");
        BigInteger blockNumber = null;
        try {
            blockNumber = web3j.ethBlockNumber().sendAsync().get().getBlockNumber();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info("The BlockNumber is: {}",blockNumber);
        DefaultBlockParameterNumber defaultBlockParameterNumber = new DefaultBlockParameterNumber(blockNumber);
//        EthGetBalance ethGetBalance  = web3j.ethGetBalance(credentials.getAddress(),defaultBlockParameterNumber)
//                .sendAsync().get();
//        logger.info("Get Account Ether is: {}",ethGetBalance.getBalance());
        long chainIdOfPolygon = 1337;
        TransactionManager bridgeTokenTxManager = new RawTransactionManager(
                web3j, credentials, chainIdOfPolygon,new MyPollingTransactionReceiptProcessor(web3j));
        StaticGasProvider staticGasProvider = new StaticGasProvider(BigInteger.valueOf(284232177), BigInteger.valueOf(3000000));

//        NFT721 lb = NFT721.deploy(web3j, bridgeTokenTxManager, staticGasProvider,
//                "12134", "lb", BigInteger.valueOf(5),
//                "https://avatars.githubusercontent.com/u/54950332?s=96&v=4").sendAsync().get();
//        System.out.println(lb.getContractAddress());


//        BigInteger gasUsed1 = nft721.getTransactionReceipt().get().getGasUsed();
//        System.out.println(gasUsed1);
//        System.out.println(nft721.getContractAddress());
//
        NFT721 load = NFT721.load("0x82834eb16f98161fae59b5a84baf5b1c5746b960", web3j, bridgeTokenTxManager, staticGasProvider);
        load.safeMint().sendAsync().get();

//        System.out.println(55555);

//        System.out.println(s);
//        String s1 = load.tokenURI(BigInteger.valueOf(1)).sendAsync().get();
//        System.out.println(s1);
//        String s2 = load.name().sendAsync().get();
//        System.out.println(s2);
//        String s3 = load.ownerOf(BigInteger.valueOf(1)).sendAsync().get();
//        System.out.println(s3);
//        TransactionReceipt transactionReceipt = load.transferByAdmin("0x996d8524Be841AdDcDdE6A07b38aDf035E186ED6", BigInteger.valueOf(1)).sendAsync().get();
//        BigInteger gasUsed = transactionReceipt.getGasUsed();
//        System.out.println(gasUsed);


//      NFT721 MyToken = new NFT721("0xc61d30fece0347ae9be3e29b7edf4d61371a9432",web3j,bridgeTokenTxManager,staticGasProvider);
//      MyToken.transfer("0x996d8524Be841AdDcDdE6A07b38aDf035E186ED6",BigInteger.valueOf(996)).sendAsync().get();
//      BigInteger bigInteger = MyToken.balanceOf("0x996d8524Be841AdDcDdE6A07b38aDf035E186ED6").sendAsync().get();
//      System.out.println(bigInteger);
//
//        MyToken.transferEventFlowable(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST).subscribe(new Consumer<com.example.web3study.smartContract.MyToken.TransferEventResponse>() {
//            @Override
//            public void accept(com.example.web3study.smartContract.MyToken.TransferEventResponse transferEventResponse) throws Exception {
//                System.out.println(transferEventResponse.value);
//            }
//        });


    }
}
