package com.example.web3study.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.web3study.pojo.Nft;

public interface NftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nft record);

    int insertSelective(Nft record);

    Nft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nft record);

    int updateByPrimaryKey(Nft record);

     List<Nft> selectAll();


   List<Nft> selectAllByNameLikeOrSymbolLike(@Param("likeName")String likeName,@Param("likeSymbol")String likeSymbol);


   int updateCurrentNumberByBlockchainLogId(@Param("updatedCurrentNumber")Integer updatedCurrentNumber,@Param("blockchainLogId")Integer blockchainLogId);










}