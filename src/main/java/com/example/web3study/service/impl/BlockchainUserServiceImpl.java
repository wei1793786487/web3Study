package com.example.web3study.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.web3study.pojo.BlockchainUser;
import com.example.web3study.mapper.BlockchainUserMapper;
import com.example.web3study.service.BlockchainUserService;
@Service
public class BlockchainUserServiceImpl implements BlockchainUserService{

    @Resource
    private BlockchainUserMapper blockchainUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blockchainUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BlockchainUser record) {
        return blockchainUserMapper.insert(record);
    }

    @Override
    public int insertSelective(BlockchainUser record) {
        return blockchainUserMapper.insertSelective(record);
    }

    @Override
    public BlockchainUser selectByPrimaryKey(Integer id) {
        return blockchainUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BlockchainUser record) {
        return blockchainUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BlockchainUser record) {
        return blockchainUserMapper.updateByPrimaryKey(record);
    }

}
