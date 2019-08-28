package com.plat.paygate.shop.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PgUser implements Serializable {
    private Long userId;

    private String userName;

    private String accountName;

    private String accountNo;

    private String address;

    private String password;

    private Byte role;

    private Date createTime;

    private Date updateTime;

}