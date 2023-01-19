package com.example.web3study.controller;

import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.Nft;
import com.example.web3study.pojo.ReturnCode;
import com.example.web3study.pojo.Web3TransactionError;
import com.example.web3study.service.impl.NftServiceImpl;
import com.example.web3study.smartContract.NFT721;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.function.BiConsumer;

import static com.example.web3study.utils.Web3Utils.web3jErrorToPojo;

/**
 * (nft)表控制层
 *
 * @author xxxxx
 */
@RestController
@Slf4j
@RequestMapping("/nft")
public class NftController {



    /**
     * 服务对象
     */
    @Resource
    private NftServiceImpl nftServiceImpl;



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Nft selectOne(Integer id) {
        return nftServiceImpl.selectByPrimaryKey(id);
    }

    @PostMapping("/ERC721Nft")
    public String nft721(Nft nft) {
        return nftServiceImpl.createNft721(nft);
    }
    //TODO 模糊搜索



}
