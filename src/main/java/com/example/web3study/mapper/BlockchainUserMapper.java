package com.example.web3study.mapper;

import com.example.web3study.pojo.BlockchainUser;import org.apache.ibatis.annotations.Param;

public interface BlockchainUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlockchainUser record);

    int insertSelective(BlockchainUser record);

    BlockchainUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlockchainUser record);

    int updateByPrimaryKey(BlockchainUser record);

    BlockchainUser findOneByPrivateKey(@Param("privateKey") String privateKey);
}