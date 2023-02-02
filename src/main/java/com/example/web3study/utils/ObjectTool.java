package com.example.web3study.utils;

import com.example.web3study.pojo.common.MyPageInfo;
import com.example.web3study.pojo.Nft;
import com.example.web3study.pojo.common.ResultData;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class ObjectTool extends Object{

    public static MyPageInfo<Nft> getPageObject(List object){
        PageInfo<Nft> nftPageInfo = new PageInfo<>(object);
        return  new MyPageInfo<Nft>(nftPageInfo.getTotal(),nftPageInfo.getList());
    }

    public static ResultData<Object> generateSuccessResultDataS(Object data){
        ResultData<Object> resultData=new ResultData<>();
        resultData.setMessage("success");
        resultData.setStatus(200);
        resultData.setData(data);
        return resultData;
    }


}
