package com.common.handler;

import com.common.util.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;

/**
 * 参数校验
 *
 * @author Administrator
 * @Desc
 */
@Slf4j
public class ParamsValidateHandler extends AbstractHandler<ProceedingJoinPoint> {


    public ParamsValidateHandler(int order) {
        super(order);
    }

    @Override
    public Object handler(HttpServletRequest request, ProceedingJoinPoint joinPoint) throws Throwable {
        //hibernate.validator参数校验
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            ValidateUtil.checkParams(arg);
        }
        return handleNext(request, joinPoint, null);
    }
}
