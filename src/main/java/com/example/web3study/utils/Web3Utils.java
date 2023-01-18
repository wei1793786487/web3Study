package com.example.web3study.utils;

import com.example.web3study.pojo.Web3Account;
import com.example.web3study.pojo.Web3TransactionError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import static org.web3j.crypto.Hash.sha256;

@Slf4j
@Component
public class Web3Utils {



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
        try {
            Gson gson = new Gson();
            Map map = gson.fromJson(errorMessage, Map.class);
            String hash = (String) map.keySet().toArray()[0];
            Map map1 = (Map) map.get(hash);
            return new Web3TransactionError(hash, (String) map1.get("error"), (Double) map1.get("program_counter"), (String) map1.get("return"), (String) map1.get("reason"));
        } catch (JsonSyntaxException e) {
            //有的链不返回错误码 返回的不是json数据 所以做一个报错处理
            return  new Web3TransactionError(errorMessage);
        }
    }

}
