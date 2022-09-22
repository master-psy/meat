package com.api.aspect;

import com.common.annotation.RateLimit;
import com.common.enumeration.ApiCodeEnum;
import com.common.exception.BizException;
import com.google.common.util.concurrent.RateLimiter;
import com.common.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流器
 *
 * @author Administrator
 * @url https://pdai.tech/md/spring/springboot/springboot-x-interface-xianliu.html
 * @Desc
 */
@Aspect
@Component
@Slf4j
public class RateLimitAspect {
    //现有的限流器
    private final ConcurrentHashMap<String, RateLimiter> EXISTED_RATE_LIMITERS = new ConcurrentHashMap();

    @Pointcut("@annotation(com.common.annotation.RateLimit)")
    public void rateLimit() {
    }

    @Around("rateLimit()")
    public Object aroundRate(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimit methodAnnotation = ClassUtil.getMethodAnnotation(joinPoint, RateLimit.class);
        RateLimiter rateLimiter = EXISTED_RATE_LIMITERS.computeIfAbsent(method.getName(), k -> RateLimiter.create(methodAnnotation.limit()));
        if (rateLimiter != null && rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        } else {
            throw new BizException(ApiCodeEnum.RATE_LIMITER);
        }
    }
}
