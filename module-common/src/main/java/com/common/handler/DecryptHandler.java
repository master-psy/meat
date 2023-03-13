package com.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BizHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解密程序
 *
 * @author Administrator
 * @Desc
 */
@Slf4j
public class DecryptHandler extends AbstractHandler<ProceedingJoinPoint> {
    public DecryptHandler(int order) {
        super(order);
    }

    @Override
    public Object handler(HttpServletRequest request, ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String requestURL = request.getRequestURL().toString();
        String requestData = request.getParameter("data");
        //是否需要解密
        boolean needDecrypt = false;
        if (needDecrypt) {
            //是否有数据
            if (StringUtils.hasLength(requestData)) {
                for (Object arg : args) {
                    //参数对应的类型
                    Class targetClass = arg.getClass();
                    //// TODO: 2022/8/17 0017  解密
                    requestData = new String(Base64.decodeBase64(requestData.replace(" ", "+").getBytes("UTF-8")), "UTF-8");
                    //将json字符串转换成对应的对象
                    Object targetObject = JSONObject.parseObject(requestData, targetClass);
                    //将joinPoint参数类型转换成对应的对象类型(joinPoint.getArgs())
                    Object paramByClass = BizHelper.getParamByClass(joinPoint, targetClass);
                    if (paramByClass != null) {
                        //将joinPoint参数类型转换成对应的对象类型并赋值
                        BeanUtils.copyProperties(targetObject, paramByClass);
                    }
                    if (!(arg instanceof HttpServletResponse)) {
                        //log.info("接口入参==> requestURL={}, method={}, request parameter={}", requestURL, BizHelper.getSimpleTargetMethodName(joinPoint), JSONObject.toJSONString(arg));
                    }
                }
            }
        } else {
            //不需要解密
            for (Object arg : args) {
                //如果是HttpServletResponse 就不要json序列化 不然会报错java.lang.IllegalStateException: getOutputStream() has already been called for this response
                if (!(arg instanceof HttpServletResponse)) {
                    //log.info("接口入参==> requestURL={}, method={}, request parameter={}", requestURL, BizHelper.getSimpleTargetMethodName(joinPoint), JSONObject.toJSONString(arg));
                }

            }
        }
        return handleNext(request, joinPoint, null);
    }
}
