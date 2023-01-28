package com.example.web3study.handle;

import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.common.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof String){
            return objectMapper.writeValueAsString(ResultData.success(body));
        }
        if(body instanceof ResultData){
            return body;
        }
        return ResultData.success(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultData> exception(Exception e) throws Exception {
        //todo 异常处理有的不处理
        if (e instanceof XxException) {
             //普通异常
            return ResponseEntity.status(200).body(new ResultData((XxException) e));
        }else if (e instanceof MaxUploadSizeExceededException){
            return ResponseEntity.status(200).body(ResultData.fail(400,"超过最大的上传值"+((MaxUploadSizeExceededException) e).getMaxUploadSize()));
        }else if (e instanceof MissingServletRequestParameterException){
            /**
             * 参数缺少的处理
             */
            String parameter = ((MissingServletRequestParameterException) e).getParameterName();
            return ResponseEntity.status(200).body(ResultData.fail(400,"确少必要的参数"+parameter));
        }

        else {
            throw e;
        }
//        return  ResponseEntity.status(400).body(ResultData.fail(500,e.getMessage()));

    }

}

