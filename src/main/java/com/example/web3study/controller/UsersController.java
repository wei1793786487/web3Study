package com.example.web3study.controller;

import com.example.web3study.pojo.Users;
import com.example.web3study.security.conf.AdminRestController;
import com.example.web3study.service.impl.UsersServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (users)表控制层
 *
 * @author xxxxx
 */
@AdminRestController
@RequestMapping("/users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersServiceImpl usersServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Users selectOne(Integer id) {
        return usersServiceImpl.selectByPrimaryKey(id);
    }










}
