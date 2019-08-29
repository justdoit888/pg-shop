package com.plat.paygate.shop.common;

import org.springframework.http.HttpStatus;

/**
 * @author Sunny
 * @version 1.0
 * @date 2019-07-22 10:20
 * @package com.plat.paygate.shop.common
 */
public enum ResultEnum {
    SUCCESS("200000", "成功"),
    USER_EXIST("200001", "此用户已经注册"),
    PASS_UNCONSISTEN("400001", "密码不一致"),
    SERVICE_EXCEPTION("500000","服务端系统异常"),

    ;
    private String code;
    private String desc;

    ResultEnum(){}
    ResultEnum( String code, String desc){
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ResultEnum getResultEnum(String code){
        for(ResultEnum resultEnum : values()){
            if(resultEnum.getCode().equals(code)){
                return resultEnum;
            }
        }
        return null;
    }

}
