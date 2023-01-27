package com.example.web3study.pojo.common;

public enum ReturnCode {
    /**操作成功**/
    RC100(200,"success"),
    /**操作失败**/
    RC999(999,"操作失败"),



    USER_NOT_LOGIN(1001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(1002, "账号已过期"),
    USER_CREDENTIALS_ERROR(1003, "账号或者密码错误"),
    USER_CREDENTIALS_EXPIRED(1004, "密码过期"),
    USER_ACCOUNT_DISABLE(1005, "账号不可用"),
    USER_ACCOUNT_LOCKED(1006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(1007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(1008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(1009, "账号下线"),
    TOKEN_ERROR(1010,"TOKEN错误"),


    NFT_NAME_NULL(2001,"nft的名称不能为空"),
    IPFS_UPLOAD_ERROR(2002,"ipfs文件上传异常"),
    NFT_MINT_ERROR(2003,"nft铸造异常"),

    PARAMETER_ERROR(4004,"参数异常"),

    RECAPTCHA_ERROR(10086,"验证码异常");




    /**自定义状态码**/
    private final int code;
    /**自定义描述**/
    private final String message;

    ReturnCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
