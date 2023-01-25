package com.example.web3study.controller;


import com.example.web3study.pojo.Admin;
import com.example.web3study.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/nft")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @GetMapping("admin")
    public Admin admin(){
       return adminService.selectByPrimaryKey(1);
    }
}
