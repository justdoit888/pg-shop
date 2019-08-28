package com.plat.paygate.shop.common;
/**
 * @author Sunny
 * @version 1.0
 * @date 2019-07-22 9:42
 * @package cn.homecredit.spgw.core.common
 */
public class BaseResponse<T> {
    private String code;
    private String desc;
    private T data;

    public BaseResponse(){

    }
    public BaseResponse(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
