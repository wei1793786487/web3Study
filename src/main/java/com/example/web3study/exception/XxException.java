package com.example.web3study.exception;

import com.example.web3study.pojo.common.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class XxException extends RuntimeException {
    private ReturnCode exceptionEnums;
}