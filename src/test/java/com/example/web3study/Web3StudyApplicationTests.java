package com.example.web3study;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.SM2;
import com.example.web3study.controller.BlockChainInfoController;
import com.example.web3study.pojo.Nft;
import com.example.web3study.pojo.SystemInfo;
import com.example.web3study.security.JwtUtils;
import com.example.web3study.security.jwtUser;
import com.example.web3study.service.SystemInfoService;
import com.example.web3study.smartContract.NFT721;
import io.reactivex.functions.Consumer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.security.KeyPair;
import java.util.concurrent.ExecutionException;


@SpringBootTest
class Web3StudyApplicationTests {
    @Autowired
    private Web3j web3j;
    @Autowired
    private StaticGasProvider staticGasProvider;


    @Autowired
    TransactionManager transactionManager;

    @Autowired
    SystemInfoService systemInfoService;

    @Autowired
    com.example.web3study.service.NftService NftService;



    private static final Logger logger = LoggerFactory.getLogger(BlockChainInfoController.class);
    @Test
    void contextLoads() throws Exception {
        loadKey();
    }


    void  loadKey(){

        SystemInfo systemInfo = systemInfoService.getSystemInfo();
        System.out.println(systemInfo);
        RSA rsa = new RSA(systemInfo.getSystemPrivateKey(),systemInfo.getSystemPublicKey());
        System.out.println(rsa.getPrivateKeyBase64());
        System.out.println(rsa.getPublicKeyBase64());
        jwtUser lw = new jwtUser().setName("老王").setPhone("19212121").setUid(2);

        String s = JwtUtils.generateTokenExpireInSeconds(lw, rsa.getPrivateKey(), 300);
        System.out.println(s);
    }

    void  getSecuritu(){
        String text = "我是一段测试aaaa";
        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

    }

    public void  bshu() throws ExecutionException, InterruptedException {
        Nft nft = NftService.selectByPrimaryKey(39);
        String blocknumber = nft.getBlockchainLog().getBlocknumber();
        NFT721 MyToken = new NFT721(nft.getBlockchainLog().getContractAddress(), web3j, transactionManager, staticGasProvider);
        final EthFilter ethFilter = new EthFilter(DefaultBlockParameter.valueOf(new BigInteger(blocknumber)), DefaultBlockParameterName.LATEST, nft.getBlockchainLog().getContractAddress());
        String encode = EventEncoder.encode(NFT721.TRANSFER_EVENT);
//        String s = "0x" + TypeEncoder.encode(new Utf8String(""));
        String id = "0x" + TypeEncoder.encode(new Uint256(26));
        ethFilter.addSingleTopic(encode);
        ethFilter.addSingleTopic(null);
        ethFilter.addSingleTopic(null);
        ethFilter.addSingleTopic(id);

//        String s = "0x" + TypeEncoder.encode(new Utf8String("from"));
        MyToken.transferEventFlowable(ethFilter).subscribe(new Consumer<NFT721.TransferEventResponse>() {
            @Override
            public void accept(NFT721.TransferEventResponse transferEventResponse) throws Exception {
                logger.info(transferEventResponse.tokenId.toString());

            }
        });
    }
}
