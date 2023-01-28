package com.example.web3study.service.impl;

import cn.hutool.http.HttpUtil;
import com.example.web3study.check.google.CaptchaSettings;
import com.example.web3study.check.google.GoogleResponse;
import com.example.web3study.exception.XxException;
import com.example.web3study.service.CheckService;
import com.example.web3study.pojo.common.ReturnCode;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * @author sun
 */
@Service
@Slf4j
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CaptchaSettings captchaSettings;
    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Override
    public Boolean checkByGoogleRecaptcha(String response,String ip) {
        if(!responseSanityCheck(response)) {
            throw new XxException(ReturnCode.RECAPTCHA_ERROR);
        }
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("secret", captchaSettings.getSecret());
        paramMap.put("response",response);
        paramMap.put("remoteip",ip);

        String result3= HttpUtil.get("https://www.recaptcha.net/recaptcha/api/siteverify", paramMap);
        Gson gson = new Gson();
        GoogleResponse googleResponse = gson.fromJson(result3, GoogleResponse.class);
        log.info(googleResponse.toString());
        if(!googleResponse.isSuccess()) {
            throw new XxException(ReturnCode.RECAPTCHA_ERROR);
        }
        return googleResponse.getScore() > 0.7;
    }
    private boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }
}
