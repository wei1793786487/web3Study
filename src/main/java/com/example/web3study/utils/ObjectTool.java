package com.example.web3study.utils;

import com.example.web3study.pojo.MyPageInfo;
import com.example.web3study.pojo.Nft;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class ObjectTool {

    public static MyPageInfo<Nft> getPageObject(List object){
        PageInfo<Nft> nftPageInfo = new PageInfo<>(object);
        return  new MyPageInfo<Nft>(nftPageInfo.getTotal(),nftPageInfo.getList());
    }
}
