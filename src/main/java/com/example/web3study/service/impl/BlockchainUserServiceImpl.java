package com.example.web3study.service.impl;

import com.example.web3study.pojo.Admin;
import com.example.web3study.pojo.Users;
import com.example.web3study.service.AdminService;
import com.example.web3study.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.web3study.pojo.BlockchainUser;
import com.example.web3study.mapper.BlockchainUserMapper;
import com.example.web3study.service.BlockchainUserService;
import org.springframework.util.ObjectUtils;

@Service
public class BlockchainUserServiceImpl implements BlockchainUserService{

    @Autowired
    private AdminService adminService;

    @Autowired
    private UsersService usersService;
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

    @Override
    public BlockchainUser findOneByPrivateKey(String privateKey) {
        return blockchainUserMapper.findOneByPrivateKey(privateKey);
    }

    @Override
    public Admin findAdminByPrivateKey(String privateKey) {
        BlockchainUser oneByPrivateKey = findOneByPrivateKey(privateKey);
        if (ObjectUtils.isEmpty(oneByPrivateKey)){
            return null;
        }
        return adminService.findOneByBuid(oneByPrivateKey.getId());
    }

    @Override
    public Users findUsersByPrivateKey(String privateKey) {
        BlockchainUser oneByPrivateKey = findOneByPrivateKey(privateKey);
        if (ObjectUtils.isEmpty(oneByPrivateKey)){
            return null;
        }
        return usersService.findOneByBuid(oneByPrivateKey.getId());
    }

}
