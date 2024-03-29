package com.example.web3study.controller;

import com.example.web3study.pojo.common.MyPageInfo;
import com.example.web3study.pojo.Nft;
import com.example.web3study.pojo.common.PageParam;
import com.example.web3study.security.conf.AdminRestController;
import com.example.web3study.service.impl.NftServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * (nft)表控制层
 *
 * @author xxxxx
 */
@AdminRestController
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
    @GetMapping("/ERC721Nft/{id}")
    public Nft selectOne(@PathVariable("id") Integer id) {
        //缺少参数异常捕获不到     忘记加注解。。。
        return nftServiceImpl.selectByPrimaryKey(id);
    }

    @PostMapping("/ERC721Nft")
    public String nft721(Nft nft) {
        return nftServiceImpl.createNft721(nft);
    }
    @PostMapping("/ERC721NftList")
    public MyPageInfo<Nft> nft721All(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "symbol", required = false ) String symbol,
            PageParam page
    ) {
        return nftServiceImpl.selectAll(name,symbol,page);
    }




}
