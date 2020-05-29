package com.howie.shirojwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT工具
 *
 * @Author zekun.zheng@unionman.com.cn
 * @Description JWT 工具类
 * @Date 2020-05-29
 * @Time 22:48
 */
public class JWTUtil {
    // 过期时间
    private static final long EXPIRE_TIME = 01 * 01 * 10 * 1000;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";

    /**
     * 根据传入的数据生成 token，设置过期时间
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username, int id) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            System.out.println(username+ "的过期时间为：" + date);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("id", id)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验附带数据的 token 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username, int id) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .withClaim("id", id)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根据token获取其中包含的用户ID
     * @param token
     * @return
     */
    public static int getId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            return Integer.parseInt(null);
        }
    }
}
