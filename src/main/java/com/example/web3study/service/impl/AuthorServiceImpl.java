package com.example.web3study.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.web3study.pojo.Author;
import com.example.web3study.mapper.AuthorMapper;
import com.example.web3study.service.AuthorService;
@Service
public class AuthorServiceImpl implements AuthorService{

    @Resource
    private AuthorMapper authorMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return authorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Author record) {
        return authorMapper.insert(record);
    }

    @Override
    public int insertSelective(Author record) {
        return authorMapper.insertSelective(record);
    }

    @Override
    public Author selectByPrimaryKey(Integer id) {
        return authorMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Author record) {
        return authorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Author record) {
        return authorMapper.updateByPrimaryKey(record);
    }

}
