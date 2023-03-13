package com.common.handler;

import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;

/**
 * @Desc
 */
public class TargetHandler extends AbstractHandler<ProceedingJoinPoint> {
    public TargetHandler(int order) {
        super(order);
    }

    @Override
    public Object handler(HttpServletRequest request, ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        return handleNext(request, joinPoint, result);
    }
}
