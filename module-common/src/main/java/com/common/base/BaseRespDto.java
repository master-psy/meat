package com.common.base;

import com.common.enumeration.ApiCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

/**
 * 1.@JsonInclude(JsonInclude.Include.NON_NULL)注解: 类的属性为NULL则不参与序列化
 *
 * @author Administrator
 * @Desc 统一返回内容封装
 */
@ApiModel("通用返回")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRespDto {
    @ApiModelProperty("返回码: 200成功")
    private int code;
    @ApiModelProperty("签名")
    private String sign;
    @ApiModelProperty("返回信息")
    private String msg;
    @ApiModelProperty("返回内容,成功时才有值")
    private Object data;
    // 错误时的字段
    @ApiModelProperty(value = "在具体错误上，code无法覆盖，msg又不方便填写。用途如接口的逻辑错误号，PARAM_ERROR的错误字段")
    private String subcode;
    @ApiModelProperty(value = "明细，如堆栈")
    private String verbose;

    /**
     * 默认成功返回
     */
    public BaseRespDto() {
        this.initSuccess(null);
    }

    /**
     * 带结果成功返回
     *
     * @param data
     */
    public BaseRespDto(Object data) {
        this.initSuccess(data);
    }

    /**
     * 枚举系统异常返回
     *
     * @param apiCodeEnum
     */
    public BaseRespDto(ApiCodeEnum apiCodeEnum) {
        this.init(apiCodeEnum.getCode(), apiCodeEnum.getDesc(), null, null);
    }

    /**
     * 系统异常返回
     *
     * @param code
     * @param message
     */
    public BaseRespDto(int code, String message) {
        this.init(code, message, null, null);
    }

    /**
     * 自定义异常返回
     *
     * @param code    状态码
     * @param message 异常信息
     * @param subcode 自定义信息
     */
    public BaseRespDto(int code, String message, String subcode) {
        this.init(code, message, null, subcode);
    }

    private void init(int code, String message, String defaultMessage, String subcode) {
        this.init(code, message, defaultMessage, subcode, null);
    }

    private void init(int code, String message, String defaultMessage, String subcode, String verbose) {
        this.code = code;
        this.msg = StringUtils.isEmpty(message) ? defaultMessage : message;
        this.data = null;
        this.subcode = subcode;
        this.verbose = verbose;
    }

    private void initSuccess(Object data) {
        this.code = ApiCodeEnum.SUCCESS.getCode();
        this.msg = ApiCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return String.format("BaseRespDto [code=%s ,msg=%s ,sign=%s ,data=%s]", code, msg, sign, data);
    }
}
