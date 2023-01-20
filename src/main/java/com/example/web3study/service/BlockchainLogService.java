package com.example.web3study.service;

import com.example.web3study.pojo.BlockchainLog;

public interface BlockchainLogService {


    int deleteByPrimaryKey(Integer id);

    int insert(BlockchainLog record);

    int insertSelective(BlockchainLog record);

    BlockchainLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlockchainLog record);

    int updateByPrimaryKey(BlockchainLog record);

}



