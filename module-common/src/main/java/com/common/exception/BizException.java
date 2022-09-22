package com.common.exception;

import com.common.enumeration.ApiCodeEnum;

/**
 * @author Administrator
 * @Desc
 */
public class BizException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    private int code;
    private String subcode;

    public BizException(String message) {
        super(message);
        this.code = ApiCodeEnum.BUSY.getCode();
    }

    public BizException(ApiCodeEnum apiCodeEnum) {
        super(apiCodeEnum.getDesc());
        this.code = ApiCodeEnum.BUSY.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }
}
