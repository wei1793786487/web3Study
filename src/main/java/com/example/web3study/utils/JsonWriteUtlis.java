package com.example.web3study.utils;

import com.example.web3study.pojo.common.ResultData;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.web3study.utils.ObjectTool.generateSuccessResultDataS;


/**
 * @author sun
 */
public class JsonWriteUtlis {


    public static void sendJsonSuccess( HttpServletResponse response, Object result) throws IOException {
        ResultData<Object> resultData = generateSuccessResultDataS(result);
        response.setContentType("text/json;charset=utf-8");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(resultData));
    }


    public static void sendJson( HttpServletResponse response, Object result) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(result));
    }
}