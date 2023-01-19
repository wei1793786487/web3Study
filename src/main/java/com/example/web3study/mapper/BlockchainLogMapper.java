package com.example.web3study.mapper;

import com.example.web3study.pojo.BlockchainLog;

public interface BlockchainLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlockchainLog record);

    int insertSelective(BlockchainLog record);

    BlockchainLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlockchainLog record);

    int updateByPrimaryKey(BlockchainLog record);
}