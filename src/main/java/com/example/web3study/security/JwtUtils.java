package com.example.web3study.security;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.asymmetric.RSA;
import com.example.web3study.pojo.SystemInfo;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author: 黑马程序员
 * 生成token以及校验token相关方法
 */
public class JwtUtils {

    private static final String JWT_PAYLOAD_USER_KEY = "user";


    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @param expire     过期时间，单位秒
     * @return JWT
     */
    public static String generateTokenExpireInSeconds(Object userInfo, PrivateKey privateKey, int expire) {
        Gson gson = new Gson();
        String userinfoString = gson.toJson(userInfo);
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, userinfoString)
                .setId(createJTI())
                .setExpiration(DateUtil.offsetSecond(new Date(), expire))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 获取token中的用户信息
     *
     *
     */
    public static <T> Payload<T> getInfoFromToken(String token, SystemInfo systemInfo, Class<T> userType) {
        RSA rsa = new RSA(systemInfo.getSystemPrivateKey(), systemInfo.getSystemPublicKey());
        Gson gson = new Gson();
        Jws<Claims> claimsJws = parserToken(token, rsa.getPublicKey());
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(gson.fromJson(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));
        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * 获取token中的载荷信息
     *

     */
    public static <T> Payload<T> getInfoFromToken(String token, SystemInfo systemInfo) {
        RSA rsa = new RSA(systemInfo.getSystemPrivateKey(), systemInfo.getSystemPublicKey());
        Jws<Claims> claimsJws = parserToken(token, rsa.getPublicKey());
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }
}