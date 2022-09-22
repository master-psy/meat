package com.api.aspect;

import com.alibaba.fastjson.JSONObject;
import com.common.base.BaseRespDto;
import com.common.base.BizHelper;
import com.common.enumeration.ApiCodeEnum;
import com.common.exception.BizException;
import com.common.factory.WebHandlerFactory;
import com.common.handler.AbstractHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author AOP切入
 * @Desc
 */
@Order(1)
@Slf4j
@Aspect
@Component
public class WebAspect {
    /**
     * 切入什么方法里,这里是拦截所有request请求(post,get,put,delete)
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.PostMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.PutMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)"
            + "||@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void requestService() {
    }

    /**
     * 切点增强操作
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("requestService()")
    public Object handler(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestAttributes().resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String requestURL = request.getRequestURL().toString();
        try {
            AbstractHandler handler = WebHandlerFactory.getInstance().getObject();
            //方法执行前
            result = handler.handler(request, joinPoint);
            //输出日志
            this.printLog(request, joinPoint, result, beginTime);
            //方法执行后&返回前,可以进行返回结果加密|json处理. 如果不需要加密即可直接返回
            boolean needEncryption = false;
            if (needEncryption) {
                //加密
                encryptResult(result);
            } else {
                //不需要加密直接返回
                return result;
            }
        } catch (BizException e) {
            log.info("自定义异常==> requestURL={}, method={}, request parameter={}, biz exception={}", requestURL, BizHelper.getSimpleTargetMethodName(joinPoint), getRequestParams(request), e.toString());
            result = new BaseRespDto(e.getCode(), e.getMessage());
        } catch (Throwable e) {
            String message = e.getMessage();
            log.info("系统异常==> requestURL={}, method={}, request parameter={}, unExpected error", requestURL, BizHelper.getSimpleTargetMethodName(joinPoint), getRequestParams(request), e);
            //调试阶段提示具体错误,生产应该规范一点(统一:系统开小差之类的)
            result = new BaseRespDto(ApiCodeEnum.BUSY.getCode(), StringUtils.isNotBlank(message) ? message : ApiCodeEnum.BUSY.getDesc());
        }
        return result;
    }

    /**
     * 获取HttpServletRequest中的参数
     *
     * @param request
     * @return
     */
    private String getRequestParams(HttpServletRequest request) {
        HashMap<String, String> params = new HashMap<>();
        request.getParameterMap().forEach((key, value) -> {
            params.put(key, value[0]);
        });
        return params.toString();
    }

    /**
     * 出参内容加密
     *
     * @param result
     */
    private void encryptResult(Object result) {
        if (result instanceof BaseRespDto) {
            BaseRespDto respDto = (BaseRespDto) result;
            Object data = respDto.getData();
            // TODO: 2022/8/17 0017  出参加密


        }
    }

    /**
     * 请求出参日志打印
     *
     * @param request
     * @param joinPoint
     * @param result
     * @param beginTime
     */
    private void printLog(HttpServletRequest request, ProceedingJoinPoint joinPoint, Object result, long beginTime) {
        long endTime = System.currentTimeMillis();
        String requestURL = request.getRequestURL().toString();
        String method = BizHelper.getSimpleTargetMethodName(joinPoint);
        if (printParameters(joinPoint)) {
            log.info("requestURL={}, method={}, request parameter={}, resopnse parameter={}, time={}ms", requestURL, method, JSONObject.toJSONString(joinPoint.getArgs()), JSONObject.toJSONString(result), (endTime - beginTime));
        } else {
            log.info("requestURL={}, method={}, resopnse parameter={}, time={}ms", requestURL, method, JSONObject.toJSONString(result), (endTime - beginTime));
        }
    }

    /**
     * 是否打印入参
     *
     * @param joinPoint
     * @return
     */
    private boolean printParameters(ProceedingJoinPoint joinPoint) {
        boolean flag = true;
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            if (joinPoint.getArgs()[i] instanceof HttpServletRequest
                    || joinPoint.getArgs()[i] instanceof HttpServletResponse
                    || joinPoint.getArgs()[i] instanceof HttpSession
                    || joinPoint.getArgs()[i] instanceof MultipartFile[]
                    || joinPoint.getArgs()[i] instanceof MultipartFile) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * //获取目标方法的参数信息
     *         Object[] obj = joinPoint.getArgs();
     *         //AOP代理类的信息
     *         Object aThis = joinPoint.getThis();
     *         //代理的目标对象
     *         Object target = joinPoint.getTarget();
     *         //用的最多 通知的签名
     *         Signature signature = joinPoint.getSignature();
     *         //代理的是哪一个方法
     *         log.info("==> 代理的是哪一个方法 :" + signature.getName());
     *         //AOP代理类的名字
     *         log.info("==> AOP代理类的名字:" + signature.getDeclaringTypeName());
     *         //AOP代理类的类（class）信息
     *         Class declaringType = signature.getDeclaringType();
     *         //获取RequestAttributes
     *         RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
     *         //从获取RequestAttributes中获取HttpServletRequest的信息
     *         HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
     *         log.info("==> 请求者的IP：" + request.getRemoteAddr());
     *         //如果要获取Session信息的话，可以这样写：
     *         //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
     *         Enumeration<String> enumeration = request.getParameterNames();
     *         Map<String, String> parameterMap = new HashMap<String, String>();
     *         while (enumeration.hasMoreElements()) {
     *             String parameter = enumeration.nextElement();
     *             parameterMap.put(parameter, request.getParameter(parameter));
     *         }
     *         String str = JSON.toJSONString(parameterMap);
     *         if (obj.length > 0) {
     *             log.info("==> 请求的参数信息为：" + str);
     *         }
     */
/* @Around("requestService()")
    public Object handler(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        Object aThis = joinPoint.getThis();
        //代理的目标对象
        Object target = joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        log.info("==> 代理的是哪一个方法 :" + signature.getName());
        //AOP代理类的名字
        log.info("==> AOP代理类的名字:" + signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        Class declaringType = signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        log.info("==> 请求者的IP：" + request.getRemoteAddr());
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String, String> parameterMap = new HashMap<String, String>();
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter, request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if (obj.length > 0) {
            log.info("==> 请求的参数信息为：" + str);
        }
        return null;
       }*/
}
