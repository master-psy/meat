package com.common.util;

import com.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate.validator检验工具,\
 * 可以将注解例如@NotBlank(message = "手机号不能为空")上的message返回给前端
 *
 * @author psy
 */
@Slf4j
public class ValidateUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void checkParams(Object object) {
        if (object == null) {
            return;
        }
        StringBuilder errorBuilder = new StringBuilder();
        String param = null;
        Set violations = validator.validate(object);
        StringBuilder logString = new StringBuilder();
        for (Object violation : violations) {
            ConstraintViolation constraintViolation = (ConstraintViolation) violation;
            //字段
            String property = constraintViolation.getPropertyPath().toString();
            //提示
            String message = constraintViolation.getMessage();
            logString.append(property);
            logString.append(message);
            logString.append(",");
        }
        if (logString.length() != 0) {
            log.info("参数异常={}", logString);
        }
        for (Object violation : violations) {
            ConstraintViolation constraintViolation = (ConstraintViolation) violation;
            if (!StringUtils.isEmpty(errorBuilder.toString())) {
                errorBuilder.append(" ");
            }
            errorBuilder.append(constraintViolation.getMessage());
            param = constraintViolation.getPropertyPath().toString();
            if (!StringUtils.isEmpty(errorBuilder.toString())) {
                throw new BizException(errorBuilder.toString());
            }
            break;
        }
    }
}
