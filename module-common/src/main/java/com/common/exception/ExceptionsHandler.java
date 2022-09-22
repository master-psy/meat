package com.common.exception;

import com.common.base.BaseRespDto;
import com.common.enumeration.ApiCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author Administrator
 * @Desc
 */
@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {
    /**
     * 可以分开写 也可以写一起类型转换(如下)
     *
     * @param e
     * @return
     */
    //应用到所有@RequestMapping注解的方法，在其抛出Exception异常时执行
    //定义全局异常处理，value属性可以过滤拦截指定异常，此处拦截所有的Exception
    @ExceptionHandler(Exception.class)
    public BaseRespDto handleException(Exception e) {
        if (e instanceof BizException) {
            BizException ex = (BizException) e;
            return new BaseRespDto(ex.getCode(), e.getMessage(), ex.getSubcode());
        }
        log.info("系统异常", e);
        return new BaseRespDto(ApiCodeEnum.BUSY.getCode(), ApiCodeEnum.BUSY.getDesc());
    }

    /*// （3）自定义异常处理
    @ExceptionHandler(BizException.class)
    public BaseRespDto handleException(BizException e) {
        return new BaseRespDto(e.getCode(), e.getMessage(), e.getSubcode());
    }
    //应用到所有@RequestMapping注解的方法，在其抛出Exception异常时执行
    //全局异常处理，value属性可以过滤拦截指定异常，此处拦截所有的Exception
    @ExceptionHandler(Exception.class)
    public BaseRespDto handleException(Exception e) {
        if (e instanceof BizException) {
            BizException ex = (BizException) e;
            return new BaseRespDto(ex.getCode(), e.getMessage(), ex.getSubcode());
        }
        log.info("系统异常", e);
        return new BaseRespDto(ApiCodeEnum.BUSY.getCode(), ApiCodeEnum.BUSY.getDesc());
    }*/
}
