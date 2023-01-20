package com.example.web3study.service.impl;

import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.*;
import com.example.web3study.smartContract.NFT721;
import com.example.web3study.utils.Web3Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.web3study.mapper.NftMapper;
import com.example.web3study.service.NftService;
import org.springframework.util.StringUtils;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.List;
import java.util.function.BiConsumer;

import static com.example.web3study.utils.Web3Utils.web3jErrorToPojo;

@Service
@Slf4j
public class NftServiceImpl implements NftService {



    @Value("${ipfs.node_url}")
    private String nodeUrl;

    @Resource
    private NftMapper nftMapper;

    @Autowired
    private Web3j web3j;

    @Autowired
    private StaticGasProvider staticGasProvider;


    @Autowired
    TransactionManager transactionManager;





    @Override
    public int deleteByPrimaryKey(Integer id) {
        return nftMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Nft record) {
        return nftMapper.insert(record);
    }

    @Override
    public int insertSelective(Nft record) {
        return nftMapper.insertSelective(record);
    }

    @Override
    public Nft selectByPrimaryKey(Integer id) {
        return nftMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Nft record) {
        return nftMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Nft record) {
        return nftMapper.updateByPrimaryKey(record);
    }

    @Override
    public String createNft721(Nft nft) {

        nft.setType(1);

        //Todo 没做默认为0
        nft.setAuthorId(0);

        if (!StringUtils.hasText(nft.getImage())) {
            nft.setImage(nodeUrl + nft.getIpfsHash() + "/");
        }

        NFT721.deploy(web3j, transactionManager, staticGasProvider,
                nft.getName(), nft.getSymbol(), BigInteger.valueOf(nft.getMintNumber()),
                nft.getImage()).sendAsync().whenCompleteAsync(new BiConsumer<NFT721, Throwable>() {
            @Override
            public void accept(NFT721 nft721, Throwable throwable) {
                try {
                    if (throwable != null) {
                        String message = throwable.getMessage();
                        Web3TransactionError web3TransactionError = web3jErrorToPojo(message);
                        log.error(web3TransactionError.toString());
                        Integer logId = Web3Utils.generateContractInformation(nft721, 0,web3TransactionError.getError());
                        nft.setCurrentNumber(0);
                        nft.setBlockchainLogId(logId);
                        insertAndLogId(nft,logId);
                        throw new XxException(ReturnCode.NFT_MINT_ERROR);
                    } else {
                        Integer logId = Web3Utils.generateContractInformation(nft721, 1);
                        nft.setBlockchainLogId(logId);
                        nft.setCurrentNumber(nft.getMintNumber());
                        log.info("部署成功" + nft);
                        insertAndLogId(nft,logId);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return "操作成功";
    }

    @Override
    public int insertAndLogId(Nft record, Integer logId) {
        record.setBlockchainLogId(logId);
        return insert(record);
    }

    @Override
    public MyPageInfo<Nft> selectAll(String name, String symbol, PageParam page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
//        List<Nft> nfts = nftMapper.selectAllByNameLikeOrSymbolLike(name, symbol);
        List<Nft> nfts = nftMapper.selectAll();
        PageInfo<Nft> nftPageInfo = new PageInfo<>(nfts);
        return new MyPageInfo<Nft>(nftPageInfo.getTotal(),nfts);
    }

}








