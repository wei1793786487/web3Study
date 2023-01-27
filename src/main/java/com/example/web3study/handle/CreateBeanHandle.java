package com.example.web3study.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthChainId;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;

@Configuration
@Slf4j
public class CreateBeanHandle {


    @Value("${web3j.private_key}")
    private  String  mainPrivateKye;

    @Value("${web3j.gas_limit}")
    private  BigInteger  gasLimit;

    @Value("${web3j.gas_price}")
    private  String  gasPrice;
    @Autowired
    private Web3j web3j;

    @Bean
    public ObjectMapper ojectMapper(){
        return new ObjectMapper();
    }


    @Bean
    public Credentials credentials(){
        return Credentials.create(mainPrivateKye);
    }

    @Bean
    public StaticGasProvider staticGasProvider() throws IOException {
        BigInteger gasSpendPrice;
        if (!StringUtils.hasText(gasPrice)){
            EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
            gasSpendPrice= ethGasPrice.getGasPrice();
        }else {
            gasSpendPrice =new BigInteger(gasPrice);
        }
        log.info("Gas Price: {}",gasPrice);
        //todo 这里要先判断一下余额
        return new StaticGasProvider(gasSpendPrice,gasLimit);
    }

    @Bean
    public TransactionManager defaultTransactionManager() throws IOException {

        EthChainId ethChainId = web3j.ethChainId().send();
        BigInteger chainId = ethChainId.getChainId();
        log.info("Ethereum Chain Id: {}",chainId);
        return new RawTransactionManager(
                web3j, credentials(), chainId.longValue(), new MyPollingTransactionReceiptProcessor(web3j));
    }


    @Bean
    public PasswordEncoder createPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }










}
