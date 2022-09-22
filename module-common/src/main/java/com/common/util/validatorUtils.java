package com.common.util;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Desc
 */
public class validatorUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * bean整体校验，有不合规范，则抛出整体异常
     */
    public static void validate(Object o, Class<?>... groups) {
        Set<ConstraintViolation<Object>> resultSet = validator.validate(o, groups);
        if (!CollectionUtils.isEmpty(resultSet)) {
            throw new IllegalArgumentException(resultSet.toString());
        }
    }


    /**
     * bean整体校验，有不合规范，抛出第1个违规异常
     */
    public static void validate2(Object o, String msg, Class<?>... groups) {
        if (null == o) {
            if (null == msg) {
                return;
            }
            throw new IllegalArgumentException(msg);
        }
        Set<ConstraintViolation<Object>> resultSet = validator.validate(o, groups);
        for (ConstraintViolation<Object> constraintViolation : resultSet) {
            throw new IllegalArgumentException(constraintViolation.getMessage());
        }
    }

    /**
     * bean整体校验，把所有违规信息，输出String。
     */
    public static String validate3(Object o, Class<?>... groups) {
        Set<ConstraintViolation<Object>> resultSet = validator.validate(o, groups);
        if (resultSet == null || resultSet.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<Object> constraintViolation : resultSet) {
            sb.append(constraintViolation.getMessageTemplate()).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
