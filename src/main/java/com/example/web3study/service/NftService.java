package com.example.web3study.service;

import com.example.web3study.pojo.*;
import com.example.web3study.pojo.common.MyPageInfo;
import com.example.web3study.pojo.common.PageParam;
import com.example.web3study.smartContract.NFT721;

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

    BlockchainLog selectBlockLogByNftService(Integer id);

    void transactionNft721(NFT721 nft721, String to, Long tokenId);

    NFT721 getNft721Instance(String address);


}








