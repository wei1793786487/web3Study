package com.example.web3study.pojo;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.IPage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageParam  implements IPage {

    private Integer pageNum = 1;

    private Integer pageSize = 20;

    private String orderBy;

    private String orderKey="id";

    public String getOrderBy() {
        if (!"asc".equals(orderBy)&&!"desc".equals(orderBy)){
            orderBy="asc";
        }
        return StrUtil.toUnderlineCase(orderKey)+" "+ orderBy;
    }

}
