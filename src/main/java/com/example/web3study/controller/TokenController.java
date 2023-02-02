package com.example.web3study.controller;

import com.example.web3study.security.conf.AdminRestController;
import com.example.web3study.service.impl.Nft20ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sun
 */
@RestController
@RequestMapping("20")
public class TokenController {
    @Autowired
    Nft20ServiceImpl nft20Service;

    @PostMapping("receiveFree")
    public String  receiveToken20() throws Exception {
       nft20Service.receiveFree();
        return "领取成功";
    }
}
