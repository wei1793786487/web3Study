package com.example.web3study.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.web3study.pojo.common.CurrencyInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author sun
 */
@Component
public class RedisUtils {


    private static  final String PHONE_CODE_KEY="phone_code";

    private static  final String ERC20_KEY="RRC20_info";

    private static  final Integer PHONE_EXPIRE_SECONDS= 60*10;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void hset(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public void setPhoneCodeReg(String phone, String value){
        redisTemplate.opsForValue().set(PHONE_CODE_KEY+"_"+phone, value,PHONE_EXPIRE_SECONDS, TimeUnit.SECONDS);
    }

    public String getPhoneCodeReg(String phone){
       return (String)redisTemplate.opsForValue().get(PHONE_CODE_KEY+"_"+phone);
    }
    public void removePhoneCodeReg(String phone){
        redisTemplate.delete(PHONE_CODE_KEY+"_"+phone);
    }


    public void initCurrencyInformation(CurrencyInformation currencyInformation){
        redisTemplate.opsForValue().set(ERC20_KEY,currencyInformation);
    }
    public CurrencyInformation getCurrencyInformation(){
        JSONObject jsonObject = (JSONObject) redisTemplate.opsForValue().get(ERC20_KEY);
        assert jsonObject != null;
        return jsonObject.toJavaObject(CurrencyInformation.class);
    }
}
