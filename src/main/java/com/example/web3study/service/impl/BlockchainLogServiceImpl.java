package com.example.web3study.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.web3study.pojo.BlockchainLog;
import com.example.web3study.mapper.BlockchainLogMapper;
import com.example.web3study.service.BlockchainLogService;

@Service
public class BlockchainLogServiceImpl implements BlockchainLogService {

    @Resource
    private BlockchainLogMapper blockchainLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blockchainLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlockchainLog record) {
        return blockchainLogMapper.insert(record);
    }

    @Override
    public int insertSelective(BlockchainLog record) {
        return blockchainLogMapper.insertSelective(record);
    }

    @Override
    public BlockchainLog selectByPrimaryKey(Integer id) {
        return blockchainLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlockchainLog record) {
        return blockchainLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlockchainLog record) {
        return blockchainLogMapper.updateByPrimaryKey(record);
    }

}


