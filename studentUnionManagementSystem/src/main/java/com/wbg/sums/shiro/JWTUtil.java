package com.wbg.sums.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 1、在这个工具类中可以实现对用户名和密码的加密处理，校验 token 是否正确，获取用户名等操作
 * 2、Algorithm algorithm = Algorithm.HMAC256(password) 是对密码进行加密后再与用户名混淆在一起
 * 3、在签名时可以通过 .withExpiresAt(date) 指定 token 的过期时间
 *  @author WBG
 *   @date 2019/02/20
 */

public class JWTUtil {

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5*60*1000;

    /**
     * 校验token是否正确
     *
     * @param token    密钥
     * @param username 登录名
     * @param password 密码
     * @return
     */
    public static boolean verify(String token, String username, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);

            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();

            DecodedJWT jwt = verifier.verify(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
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
     * 生成签名,5min后过期
     * @param username 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
