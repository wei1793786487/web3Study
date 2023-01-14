package com.example.web3study.pojo;

import com.example.web3study.exception.XxException;
import lombok.Data;

@Data
public class ResultData<t> {
  /** 结果状态 ,具体状态码参见ResultData.java*/
  private int status;
  private String message;
  private t data;
  private long timestamp ;

  public ResultData (){
    this.timestamp = System.currentTimeMillis();
  }
  public ResultData(XxException e) {
    this.status = e.getExceptionEnums().getCode();
    this.message =  e.getExceptionEnums().getMessage();
    this.timestamp = System.currentTimeMillis();
  }
  public static <t> ResultData<t> success(t data) {
    ResultData<t> resultData = new ResultData<>();
    resultData.setStatus(ReturnCode.RC100.getCode());
    resultData.setMessage(ReturnCode.RC100.getMessage());
    resultData.setData(data);
    return resultData;
  }

  public static <t> ResultData<t> fail(int code, String message) {
    ResultData<t> resultData = new ResultData<>();
    resultData.setStatus(code);
    resultData.setMessage(message);
    return resultData;
  }




}

