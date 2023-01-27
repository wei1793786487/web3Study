package com.example.web3study.controller;

import com.example.web3study.service.CheckService;
import com.example.web3study.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sun
 */
@RestController
@Slf4j
public class CheckController {

    @Autowired
    private   CheckService checkService;

    @PostMapping("check")
    public String check(HttpServletRequest request, @RequestParam("token") String token){
        String ip = IpUtils.getIp(request);
        log.info("TOEKN is "+token);
        Boolean aBoolean = checkService.checkByGoogleRecaptcha(token, ip);
        return ip;
    }
}
