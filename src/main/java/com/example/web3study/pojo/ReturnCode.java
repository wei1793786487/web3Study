package com.example.web3study.pojo;

public enum ReturnCode {
    /**操作成功**/
    RC100(100,"操作成功"),
    /**操作失败**/
    RC999(999,"操作失败"),


    NFT_NAME_NULL(1001,"nft的名称不能为空"),
    IPFS_UPLOAD_ERROR(1002,"ipfs文件上传异常"),
    INVALID_TOKEN(2001,"访问令牌不合法");


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
