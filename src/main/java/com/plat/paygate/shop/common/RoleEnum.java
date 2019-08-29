package com.plat.paygate.shop.common;

/**
 * @author Sunny
 * @version 1.0
 * @date 2019-08-29 11:24
 * @package com.plat.paygate.shop.common
 */
public enum RoleEnum {

    ADMIN(1, "超级管理员"),
    CUSTOMER_SERVICE(2, "客服"),
    PROGRAMMER(3, "程序员"),
    USET(4, "会员"),
    ;

    private Integer code;
    private String desc;

    RoleEnum(){}
    RoleEnum( Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
