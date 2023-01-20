package com.example.web3study.pojo;

import lombok.Data;

import java.util.List;

@Data
public class MyPageInfo<T> {


    /**
     * 总条数
     */
    private Long count;

    /**
     * 信息
     */
    private List<T> data;


    public MyPageInfo(Long count, List<T> data) {
        this.count = count;
        this.data = data;
    }

    public MyPageInfo(Integer code, Long count, List<T> data) {
        this.count = count;

    }

    public MyPageInfo() {
    }
}