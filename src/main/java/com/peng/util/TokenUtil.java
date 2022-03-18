package com.peng.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.peng.entity.User;
import com.qiniu.util.StringUtils;

import java.util.Date;

public class TokenUtil {
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000; //12小时过期
    private static final String TOKEN_SECRET = "token1997WeiPeng";  //密钥盐
    public static final String TOKEN_HEADER = "Peng-Token";  //密钥盐

    /**
     * 签名生成
     *
     * @param user
     * @return
     */
    public static String sign(User user) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("peng")
                    .withClaim("userName", user.getUsername())//存放自己的信息
                    .withClaim("usId", user.getUsId())
                    .withClaim("permissionList", StringUtils.join(user.getPermissionList().toArray(), ","))
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }

    /**
     * 签名验证
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("peng").build();
            DecodedJWT jwt = verifier.verify(token);
//            System.out.println("认证通过：");
//            System.out.println("issuer: " + jwt.getIssuer());
//            System.out.println("name: " + jwt.getClaim("name").asString());
//            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取内容
     *
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("peng").build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("usId").asLong();
    }

    /**
     * 获取name
     *
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取权限
     * @param token
     * @return
     */
    public static String getPermissionList(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("permissionList").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
