package com.common.base;

import com.common.handler.AbstractHandler;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.Random;

/**
 * @Desc
 */
public class BizHelper {
    /**
     * 生成带目录的redis键
     *
     * @param redisCacheName
     * @param key
     * @return
     */
    public static String getRedisKey(String redisCacheName, String key) {
        return redisCacheName + "::" + key;
    }

    /**
     * 生成拦截器链
     *
     * @param handlers
     * @return
     */
    public static AbstractHandler createHandlerChain(AbstractHandler... handlers) {
        Arrays.sort(handlers);
        for (int i = 0; i < handlers.length - 1; i++) {
            AbstractHandler current = handlers[i];
            AbstractHandler next = handlers[i + 1];
            current.setNext(next);
        }
        return handlers[0];
    }

    /**
     * 获取方法名(class.method)
     *
     * @param joinPoint
     * @return
     */
    public static String getSimpleTargetMethodName(ProceedingJoinPoint joinPoint) {

        return joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName();
    }

    /**
     * 将joinPoint里的参数转换成目标类型
     *
     * @param joinPoint
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getParamByClass(ProceedingJoinPoint joinPoint, Class<T> clazz) {
        T t = null;
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (clazz.isInstance(arg)) {
                t = clazz.cast(arg);
                break;
            }
        }
        return t;
    }

    /**
     * 获取验证码
     *
     * @return
     */
    public static String getVerifyCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }
}
