package com.plat.paygate.shop.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PgSettle {
    private Long settleId;

    private Long userId;

    private String userName;

    private String accountName;

    private String accountNo;

    private BigDecimal settleAmount;

    private Long settleUserId;

    private String settleUserName;

    private String settleAccountName;

    private String settleAccountNo;

    private Date settleDate;

    private Byte settleState;

    private Date createTime;

    private Date updateTime;

}