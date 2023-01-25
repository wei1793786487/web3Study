package com.example.web3study.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.web3study.mapper.SystemInfoMapper;
import com.example.web3study.pojo.SystemInfo;
import com.example.web3study.service.SystemInfoService;
@Service
public class SystemInfoServiceImpl implements SystemInfoService{

    @Resource
    private SystemInfoMapper systemInfoMapper;


    @Override
    public SystemInfo getSystemInfo() {
        return systemInfoMapper.selectByPrimaryKey(0);
    }
}
