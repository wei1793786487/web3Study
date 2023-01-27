package com.example.web3study.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.web3study.pojo.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users findOneByPhone(@Param("phone")String phone);

	Users findOneByBuid(@Param("buid")Integer buid);


}