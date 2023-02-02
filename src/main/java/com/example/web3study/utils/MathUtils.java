package com.example.web3study.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
@Slf4j
public class MathUtils {
    public static BigInteger decimalsToDivideNumber(BigInteger all , BigInteger decimals){
        BigInteger pow = new BigInteger("10").pow(decimals.intValue());
        return all.divide(pow);
    }

    public static BigDecimal decimalsToMultiplyNumber(double number , BigInteger decimals){
        BigInteger pow = new BigInteger("10").pow(decimals.intValue());
        BigDecimal bigDecimal = new BigDecimal(pow);
        BigDecimal multiply1 = new BigDecimal(number, MathContext.DECIMAL64).multiply(bigDecimal);
        log.info(multiply1.toString());
        return multiply1;
    }
}
