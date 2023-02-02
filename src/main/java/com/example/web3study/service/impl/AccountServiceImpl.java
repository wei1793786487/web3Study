package com.example.web3study.service.impl;


import com.example.web3study.exception.XxException;
import com.example.web3study.mapper.UsersMapper;
import com.example.web3study.pojo.BlockchainUser;
import com.example.web3study.pojo.Users;
import com.example.web3study.pojo.Web3Account;
import com.example.web3study.pojo.common.CurrencyInformation;
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


    @Resource
    private UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String sendCode(String phone) {
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        log.debug("生成验证码{}" + code);
        redisUtils.setPhoneCodeReg(phone, code);
        //这里需要调用应用商的服务 没有接入 直接模拟了
        return code;
    }

    @Override

    public Web3Account registerUser(String phone, String password, String code) throws InvalidAlgorithmParameterException, CipherException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        Boolean aBoolean = phoneIsReg(phone);
        if (aBoolean){
            throw new XxException(ReturnCode.IS_REGISTER);
        }
        String phoneCode = redisUtils.getPhoneCodeReg(phone);
        if (ObjectUtils.isEmpty(phoneCode)) {
            throw new XxException(ReturnCode.REGISTER_PHONE_ERROR);
        }
        if (!phoneCode.equals(code)){
            throw new XxException(ReturnCode.VERIFICATION_CODE_ERROR);
        }
        redisUtils.removePhoneCodeReg(phone);

        String encodePassword = passwordEncoder.encode(password);
        Web3Account wallet = Web3Utils.createWallet();
        BlockchainUser blockchainUser = new BlockchainUser();
        blockchainUser.setPrivateKey(wallet.getPriKey());
        CurrencyInformation currencyInformation = redisUtils.getCurrencyInformation();
        blockchainUser.setVirtualResources(currencyInformation.getVirtualResources());
        blockchainUser.setSystemTokenAddress(currencyInformation.getAddress());
        blockchainUser.setAddress(blockchainUser.getAddress());
        blockchainUser.setSystemTokenName(currencyInformation.getName());
        blockchainUser.setSystemTokenNumber(currencyInformation.getTotalNumber().intValue());
        blockchainUserService.insertSelective(blockchainUser);
        Users users = new Users();
        users.setPhone(phone);
        users.setPassword(encodePassword);
        users.setCreateTime(new Date());
        users.setHeadImage("https://avatars.githubusercontent.com/u/54950332?v=4");
        users.setNickName("韭菜盒子");
        users.setBuid(blockchainUser.getId());
        usersService.insertSelective(users);

        return wallet;
    }

    @Override
    public Boolean phoneIsReg(String phone) {
        Integer integer = usersMapper.countByPhone(phone);
        return integer!=0;
    }
}
