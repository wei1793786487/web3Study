package com.example.web3study.service;

import com.example.web3study.pojo.MyPageInfo;
import com.example.web3study.pojo.Nft;
import com.example.web3study.pojo.PageParam;

public interface NftService {

    int deleteByPrimaryKey(Integer id);

    int insert(Nft record);

    int insertSelective(Nft record);

    Nft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nft record);

    int updateByPrimaryKey(Nft record);

    String createNft721(Nft nft);

    int insertAndLogId(Nft record, Integer logId);

    MyPageInfo<Nft> selectAll(String name, String symbol, PageParam page);
}








