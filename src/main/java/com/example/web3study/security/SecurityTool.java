package com.example.web3study.security;


import cn.hutool.crypto.asymmetric.RSA;
import com.example.web3study.pojo.SystemInfo;
import com.example.web3study.service.SystemInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityTool {

    @Autowired
    SystemInfoService systemInfoService;

    public String generateJwt(jwtUser jwtUser){
        //TODO 需要redis缓存一下 可以更快
        SystemInfo systemInfo = systemInfoService.getSystemInfo();
        System.out.println(systemInfo);
        RSA rsa = new RSA(systemInfo.getSystemPrivateKey(),systemInfo.getSystemPublicKey());
        log.info(rsa.getPrivateKeyBase64());
        log.info(rsa.getPublicKeyBase64());
        return JwtUtils.generateTokenExpireInSeconds(jwtUser, rsa.getPrivateKey(), systemInfo.getTokenExpireSecond());
    }
}
