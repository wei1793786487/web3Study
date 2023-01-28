package com.example.web3study.service.impl;


import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.BlockchainUser;
import com.example.web3study.pojo.Users;
import com.example.web3study.pojo.Web3Account;
import com.example.web3study.pojo.common.ReturnCode;
import com.example.web3study.service.AccountService;
import com.example.web3study.service.BlockchainUserService;
import com.example.web3study.service.UsersService;
import com.example.web3study.utils.RedisUtils;
import com.example.web3study.utils.Web3Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.web3j.crypto.CipherException;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private BlockchainUserService blockchainUserService;

    @Resource
    private UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String sendCode(String phone) {
        String code = (int)((Math.random() * 9 + 1) * 100000) + "";
        log.debug("生成验证码{}"+code);
        redisUtils.setPhoneCode(phone,code);
        //这里需要调用应用商的服务 没有接入 直接模拟了
        return code;
    }

    @Override
    public String registerUser(String phone, String password) throws InvalidAlgorithmParameterException, CipherException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        String phoneCode = redisUtils.getPhoneCode(phone);
        if (ObjectUtils.isEmpty(phoneCode)){
            throw new XxException(ReturnCode.REGISTER_PHONE_ERROR);
        }else {
            String encodePassword = passwordEncoder.encode(password);
            Web3Account wallet = Web3Utils.createWallet();

            BlockchainUser blockchainUser = new BlockchainUser();
            blockchainUser.setPrivateKey(wallet.getPriKey());


            Users users = new Users();
            users.setPhone(phone);
            users.setPassword(encodePassword);
            users.setCreateTime(new Date());
            users.setHeadImage("https://avatars.githubusercontent.com/u/54950332?v=4");
            users.setNickName("韭菜盒子");

        }
        return null;
    }
}
