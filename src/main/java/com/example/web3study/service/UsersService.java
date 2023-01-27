package com.example.web3study.service;

import com.example.web3study.pojo.Users;

public interface UsersService {


    Users selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users finOneByPhone(String phone);


    Users findOneByBuid(Integer buid);
}

