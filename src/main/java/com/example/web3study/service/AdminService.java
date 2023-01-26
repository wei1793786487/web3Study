package com.example.web3study.service;

import com.example.web3study.pojo.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {


    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin findOneByBuid(Integer buid);

}


