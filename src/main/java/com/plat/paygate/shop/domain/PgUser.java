package com.plat.paygate.shop.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PgUser {
    /** 
     * 自增主键，起始值1000
     * @Author  zhjs
    **/
    private Long userId;

    /** 
     * 用户名（手机号）
     * @Author  zhjs
    **/
    private String userName;

    /** 
     * qq号，程序员结单的联系方式
     * @Author  zhjs
    **/
    private String qqNumber;

    /** 
     * 账户姓名
     * @Author  zhjs
    **/
    private String accountName;

    /** 
     * 支付宝结算账户号
     * @Author  zhjs
    **/
    private String accountNo;

    /** 
     * 地址
     * @Author  zhjs
    **/
    private String address;

    /** 
     * 密码
     * @Author  zhjs
    **/
    private String password;

    /** 
     * openId   小程序独有
     * @Author  zhjs
    **/
    private String openid;

    /** 
     * 角色：1-超级管理员、2-客服、程序员
     * @Author  zhjs
    **/
    private Integer role;

    /** 
     * 创建日期
     * @Author  zhjs
    **/
    private Date createTime;

    /** 
     * 更新日期
     * @Author  zhjs
    **/
    private Date updateTime;

    /**
     * 是否删除标志  0 未删  1 已删
     * @Author  zhjs
     **/
    private String isDel;
}