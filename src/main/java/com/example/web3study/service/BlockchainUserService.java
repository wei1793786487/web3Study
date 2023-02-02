package com.example.web3study.service;

import com.example.web3study.pojo.Admin;
import com.example.web3study.pojo.BlockchainUser;
import com.example.web3study.pojo.Users;

public interface BlockchainUserService {


    int deleteByPrimaryKey(Integer id);

    int insert(BlockchainUser record);

    int insertSelective(BlockchainUser record);

    BlockchainUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlockchainUser record);

    int updateByPrimaryKey(BlockchainUser record);

    BlockchainUser findOneByPrivateKey(String privateKey);


    Admin findAdminByPrivateKey(String privateKey);

    Users findUsersByPrivateKey(String privateKey);
}


