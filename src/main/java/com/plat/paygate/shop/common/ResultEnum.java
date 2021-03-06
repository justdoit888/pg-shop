package com.plat.paygate.shop.common;


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
    LACK_OF_PARAM("999999","参数缺失"),
    ALREADY_JOIN("999990","已经加入，无需重新加入"),
    NOT_JOIN("999991","还未加入"),
    INVALID_USER("000000","非法的用户"),
    NOT_EXIST_USER("000001"," 不存在的用户"),
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
