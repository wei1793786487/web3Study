package com.example.web3study.mapper;

import com.example.web3study.pojo.Nft;

public interface NftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nft record);

    int insertSelective(Nft record);

    Nft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nft record);

    int updateByPrimaryKey(Nft record);
}