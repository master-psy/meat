package com.common.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Class工具类
 * @author Administrator
 */
@Slf4j
public class ClassUtil {
    /**
     * 获取方法上的注释类
     *
     * @param joinPoint
     * @return
     */
    public static Annotation[] getMethodAnnotations(JoinPoint joinPoint) {
        return getMethod(joinPoint).getAnnotations();
    }

    /**
     * 获取类上的注释类
     *
     * @param joinPoint
     * @return
     */
    public static Annotation[] getClassAnnotations(JoinPoint joinPoint) {
        Class clazz = joinPoint.getTarget().getClass();
        return clazz.getAnnotations();
    }

    /**
     * 方法是否有目标注释类
     *
     * @param joinPoint
     * @param clazz
     * @return
     */
    public static boolean containMethodAnnotation(JoinPoint joinPoint, Class<?> clazz) {
        Annotation[] annotationArr = ClassUtil.getMethodAnnotations(joinPoint);
        return containAnnotation(annotationArr, clazz);
    }

    /**
     * 类是否有目标注释类
     *
     * @param joinPoint
     * @param clazz
     * @return
     */
    public static boolean containClassAnnotation(JoinPoint joinPoint, Class<?> clazz) {
        Annotation[] annotationArr = ClassUtil.getClassAnnotations(joinPoint);
        return containAnnotation(annotationArr, clazz);
    }

    /**
     * 是否有目标注释类
     *
     * @param annotationArr
     * @param clazz
     * @return
     */
    public static boolean containAnnotation(Annotation[] annotationArr, Class<?> clazz) {
        for (int i = 0; i < annotationArr.length; i++) {
            if (annotationArr[i].annotationType() == clazz) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取方法上的注解
     *
     * @param joinPoint
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getMethodAnnotation(JoinPoint joinPoint, Class<T> clazz) {
        return getMethod(joinPoint).getDeclaredAnnotation(clazz);
    }

    /**
     * 获取方法
     *
     * @param joinPoint
     * @return
     */
    public static Method getMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Class clazz = joinPoint.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Class[] parameterTypes = methodSignature.getParameterTypes();
        Method method = null;
        try {
            method = clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            log.info("method: " + methodName + ", may not be exist");
        }
        return method;
    }
}
