package com.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.common.constant.Constants;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Json web token
 * jwt 工具类
 * 加密和解密(sign & verify)
 *
 * @author Administrator
 * @Desc
 */
public class JwtUtils {

    //声明字段
    public static final String USER_PHONE = "userPhone";
    //jwt过期时间,默认7天
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7L;

    //private static final long EXPIRE_TIME = 1000 * 2L;

    /**
     * 加密
     *
     * @return
     */
    public static String sign(String phone) {
        //设置过期时间
        Date expireTime = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //secret私钥加密
        Algorithm algorithm = Algorithm.HMAC256(Constants.PRIVATE_KEY);
        //生成token
        return JWT.create()
                .withClaim(USER_PHONE, phone)
                .withExpiresAt(expireTime)
                .sign(algorithm);
    }

    /**
     * 验证jwt是否有效
     *
     * @return
     */
    public static boolean verify(String token, String phone) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(Constants.PRIVATE_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(USER_PHONE, phone)
                    .build();
            /**
             * 抛出：
             * AlgorithmMismatchException – 如果令牌标头中声明的算法不等于JWTVerifier中定义的算法。
             * SignatureVerificationException – 如果签名无效。
             * TokenExpiredException – 如果令牌已过期。
             * InvalidClaimException – 如果声明包含与预期不同的值
             */
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取声明字段
     *
     * @return
     */
    public static String getClaimField(String token, String claim) {
        try {
            //解密
            DecodedJWT jwt = JWT.decode(token);
            //获取指定字段
            return jwt.getClaim(claim).asString();
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        //测试jwt
        String userPhone = "15216134134";
        String token = sign(userPhone);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean verify = verify(token, userPhone);
        System.out.println(verify);
        System.out.println(getClaimField(token, USER_PHONE));
    }

}
