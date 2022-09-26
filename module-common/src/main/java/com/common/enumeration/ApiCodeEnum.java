package com.common.enumeration;

/**
 * @author 接口状态枚举类
 */

public enum ApiCodeEnum {
    //系统
    SUCCESS(200, "success", "成功"),
    BUSY(500, "system busy", "系统繁忙"),
    RATE_LIMITER(501, "rate limiter", "请求太多啦"),
    //用户
    PLEASE_LOGIN(100, "please login", "请先登录"),
    USER_DOES_NOT_EXIST(100, "user does not exist", "用户不存在"),
    NO_PERMISSION(100, "No permission", "无权限"),
    PLEASE_REGISTER(100, "please register", "请先注册"),
    ILLEGAL_PHONE(100, "illegal phone", "非法手机号"),
    FAILED_TO_SEND_VERIFICATION_CODE(100, "Failed to send verification code", "非法手机号"),
    VERIFY_CODE_SEND_FREQUENTLY(451, "verify code send frequently", "验证码发送频繁"),
    CODE_HAS_EXPIRED(451, "code has expired", "验证码不正确或已过期"),
    USERNAME_OR_PASSWORD_ERROR(451, "username or password error", "用户名或密码错误"),
    //文件
    FILE_NOT_FOUND(301, "file not found", "文件未找到"),
    //excel
    FILE_EXPORT_FAILED(700, "file export failed", "文件导出失败"),
    ;
    private final int code;
    private final String msg;
    private final String desc;

    ApiCodeEnum(int code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getDesc() {
        return this.desc;
    }
}
