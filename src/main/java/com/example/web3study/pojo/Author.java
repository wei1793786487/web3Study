package com.example.web3study.pojo;

import java.util.Date;
import lombok.Data;

@Data
public class Author {
    private Integer id;

    private String name;

    private String describe;

    private Date createTime;
}