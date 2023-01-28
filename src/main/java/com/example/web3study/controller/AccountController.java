package com.example.web3study.controller;

import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.Web3Account;
import com.example.web3study.pojo.common.ReturnCode;
import com.example.web3study.service.AccountService;
import com.example.web3study.service.CheckService;
import com.example.web3study.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author sun
 */
@RestController
@RequestMapping("users")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CheckService checkService;

    @PostMapping ("register")
    public Web3Account registerUser(@RequestParam("phone") String phone,
                                    @RequestParam("password") String password,
                                    @RequestParam("code") String code
                               ) throws InvalidAlgorithmParameterException, CipherException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        return accountService.registerUser(phone,password,code);
    }

    @PostMapping ("sendCode")
    public String sendCode(@RequestParam("phone") String phone,
                           @RequestParam("token") String token,
                           HttpServletRequest request){
        Boolean aBoolean = accountService.phoneIsReg(phone);
        if (aBoolean){
            throw new XxException(ReturnCode.IS_REGISTER);
        }
        //todo 手机号格式
        String ip = IpUtils.getIp(request);
        Boolean ischeck = checkService.checkByGoogleRecaptcha(token, ip);
        if (ischeck){
            return accountService.sendCode(phone);
        }else {
            throw new XxException(ReturnCode.RC999);
        }
    }
}
