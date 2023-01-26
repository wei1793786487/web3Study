package com.example.web3study.security.handler;

import com.example.web3study.pojo.common.ResultData;
import com.example.web3study.pojo.common.ReturnCode;
import com.example.web3study.utils.JsonWriteUtlis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Devil
 * @date 2020/1/5 20:38
 * 登录失败的逻辑处理器
 */
@Component
@Slf4j
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error(e.getMessage());
        ResultData<Object> resultData=null;
        if (e instanceof AccountExpiredException) {
            //账号过期
            resultData = ResultData.fail(ReturnCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            //如果抛出 UsernameNotFoundException 也会被其捕获 这里不做处理了 其实可以返回用户名或者密码错误
            //处理了
            //resultData= ResultData.fail(ReturnCode.USER_CREDENTIALS_ERROR);
            resultData = new ResultData<>();
            resultData.setStatus(400);
            resultData.setMessage(e.getMessage());
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            resultData = ResultData.fail(ReturnCode.USER_CREDENTIALS_ERROR);

        } else if (e instanceof DisabledException) {
            //账号不可用
            resultData= ResultData.fail(ReturnCode.USER_CREDENTIALS_ERROR);

        } else if (e instanceof LockedException) {
            //账号锁定
            resultData= ResultData.fail(ReturnCode.USER_CREDENTIALS_ERROR);

        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            resultData = ResultData.fail(ReturnCode.USER_CREDENTIALS_ERROR);

        } else if (e instanceof AuthenticationException){
            resultData = new ResultData<>();
            resultData.setStatus(400);
            resultData.setMessage(e.getMessage());
        }else {
            //其他错误
            ResultData.fail(ReturnCode.RC999);
        }
        JsonWriteUtlis.sendJson(response,resultData);
    }
}
