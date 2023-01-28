package com.example.web3study.pojo;

import java.util.Date;
import lombok.Data;

@Data

public class Users {
    private Integer id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 会员状态:0=冻结,1=正常
     */
    private Byte status;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 会员登录时间
     */
    private Date loginTime;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 删除状态:0=未删除,1=已删除
     */
    private Byte isDel;

    /**
     * 实名认证:0=未认证,1=已认证
     */
    private Byte isAuth;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String card;

    /**
     * 银行卡收款:0=未绑定,1=已绑定
     */
    private Byte isBank;

    /**
     * 银行卡名称
     */
    private String bankName;

    /**
     * 银行卡卡号
     */
    private String bankNumber;

    /**
     * 开户人
     */
    private String bankOwner;

    /**
     * 开户支行
     */
    private String bankBranch;

    /**
     * 区块链绑定的信息
     */
    private Integer buid;
}