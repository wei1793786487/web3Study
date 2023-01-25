package com.example.web3study.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.web3study.pojo.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

   Admin findOneByName(@Param("name")String name);



}