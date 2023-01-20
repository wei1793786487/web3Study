package com.example.web3study.utils;

import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.*;
import com.example.web3study.service.BlockchainLogService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import static org.web3j.crypto.Hash.sha256;

@Slf4j
@Component

public class Web3Utils {


    private  static Credentials credentials;


    private static String blockchainName;

    private static BlockchainLogService blockchainLogService;

    @Resource
    public void setCredentials(Credentials credentials) {
        Web3Utils.credentials = credentials;
    }

    @Resource
    public  void setBlockchainLogService(BlockchainLogService blockchainLogService) {
        Web3Utils.blockchainLogService = blockchainLogService;
    }

    @Value("${web3j.blockchain_name}")
    public void setBlockchainName(String blockchainName) {
        Web3Utils.blockchainName = blockchainName;
    }

    public static Web3Account getWalletByWords(String word){
        byte[] seed = MnemonicUtils.generateSeed(word, "");
        ECKeyPair ecKeyPair = ECKeyPair.create(sha256(seed));
        String priKeyWithPrefix = Numeric.toHexStringWithPrefix(ecKeyPair.getPrivateKey());
        String pubKeyWithPrefix = Numeric.toHexStringWithPrefix(ecKeyPair.getPublicKey());
        String address = Keys.getAddress(ecKeyPair);
        return new Web3Account(address,pubKeyWithPrefix,priKeyWithPrefix,word);
    }

    public static Web3Account createWallet(String SavaPath) throws CipherException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        String password="";
        Bip39Wallet wallet = WalletUtils.generateBip39Wallet(password,new File(SavaPath));
        //生成12个单词的助记词
        String memorizingWords = wallet.getMnemonic();
        //通过钱包密码与助记词获得钱包地址、公钥及私钥信息
        Credentials credentials = WalletUtils.loadBip39Credentials(password,
                wallet.getMnemonic());
        String address = credentials.getAddress();
        String publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);
        String PrivateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        return new Web3Account(address,publicKey,PrivateKey,memorizingWords);
    }

    public static Web3TransactionError web3jErrorToPojo(String errorMessage){
        System.out.println(errorMessage);
        Gson gson = new Gson();
        //todo 这里要做特殊处理 对待不同的区块链 如果为了简便可以直接返回errorMessage
        try {
            if ("测试私链".equals(blockchainName)){
    //  返回格式如下  {"hash":"0x5803ee1511c5ce7da602e3a90ac55dcc7f137b903cd00e01bce07e4bce4994a5","programCounter":0,"result":"0x5803ee1511c5ce7da602e3a90ac55dcc7f137b903cd00e01bce07e4bce4994a5","reason":null,"message":"Transaction's maxFeePerGas (1) is less than the block's baseFeePerGas (737164) (vm hf=merge -> block -> tx)"}
                HashMap<String,Object> map = gson.fromJson(errorMessage, HashMap.class);
                return new Web3TransactionError((String) map.get("hash"),(String)map.get("message"),(Double)map.get("programCounter"),(String)map.get("result"),(String)map.get("reason"));
            }
            if ("Bsn武汉链".equals(blockchainName)){
                Map map = gson.fromJson(errorMessage, Map.class);
                String hash = (String) map.keySet().toArray()[0];
                Map map1 = (Map) map.get(hash);
                return new Web3TransactionError(hash, (String) map1.get("error"), (Double) map1.get("program_counter"), (String) map1.get("return"), (String) map1.get("reason"));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Web3TransactionError(errorMessage);
        }
        return new Web3TransactionError(errorMessage);
    }

    public static Integer generateContractInformation ( Contract contract,Integer status){
        return generateContractInformation(contract,status,null);
    }
    /**
     *
     * @param contract
     * @param status  0是错误的 1是成功的
     * @return 插入的区块链日志的id
     */
    public static Integer generateContractInformation ( Contract contract,Integer status,String error){
        try {
            if(status!=1&&status!=0){
                throw new XxException(ReturnCode.PARAMETER_ERROR);
            }
            BlockchainLog blockchainLog = new BlockchainLog();
            blockchainLog.setMintAddress(credentials.getAddress());
            blockchainLog.setBlockchain(blockchainName);
            if (status==1){
                TransactionReceipt transactionReceipt = contract.getTransactionReceipt().get();
                String transactionHash = transactionReceipt.getTransactionHash();
                BigInteger blockNumber = transactionReceipt.getBlockNumber();
                String blockHash = transactionReceipt.getBlockHash();
                blockchainLog.setContractAddress(contract.getContractAddress());
                blockchainLog.setState(1);
                blockchainLog.setTransactionHash(transactionHash);
                blockchainLog.setBlocknumber(blockNumber+"");
                blockchainLog.setBlockHash(blockHash);
                blockchainLog.setGasSpend(transactionReceipt.getGasUsed().longValue());
            }else {
                blockchainLog.setErrorInfo(error);
                blockchainLog.setState(0);
            }
            //注意 这个时候返回的id是影响的条数 真正的id已经放在传入的实体类里面
            int id = blockchainLogService.insertSelective(blockchainLog);
            return blockchainLog.getId();
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }



}
