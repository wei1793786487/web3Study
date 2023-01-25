package com.example.web3study.service;

import com.example.web3study.pojo.BlockchainUser;
public interface BlockchainUserService{


    int deleteByPrimaryKey(Integer id);

    int insert(BlockchainUser record);

    int insertSelective(BlockchainUser record);

    BlockchainUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlockchainUser record);

    int updateByPrimaryKey(BlockchainUser record);

}
