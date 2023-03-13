package com.common.enumeration;

/**
 * @Desc
 */
public enum UserTypeEnum {
    MASTER("管理员", "管理员"),
    ORDINARY("普通用户", "普通用户"),
    ;
    private String type;
    private String desc;

    UserTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
