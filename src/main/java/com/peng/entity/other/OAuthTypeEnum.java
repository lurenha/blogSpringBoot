package com.peng.entity.other;

//状态码
public enum OAuthTypeEnum {
    /* 成功状态码 */
    GITHUB(1, "GITHUB"),
    GITEE(2, "GITEE"),
    ;

    private Integer code;
    private String name;

    OAuthTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    public Integer getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

}
