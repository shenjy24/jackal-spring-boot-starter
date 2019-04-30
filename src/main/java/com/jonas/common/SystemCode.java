package com.jonas.common;

/**
 * 【 系统状态码 】
 *
 * @author shenjy 2018/08/13
 */
public enum SystemCode implements CodeStatus {

    SUCCESS("2000", "成功"),
    NEED_LOGIN("2001", "未登录"),
    NEED_AUTH("2002", "权限不足"),
    PARAM_ERROR("2003", "参数异常"),
    SERVER_ERROR("2004", "服务器异常"),
    BIZ_ERROR("2005", "自定义异常")
    ;

    private String code;

    private String message;

    SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
