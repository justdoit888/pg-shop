package com.plat.paygate.shop.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PgOrder {
    private Long orderId;

    private String tbOrderId;

    private String buyerName;

    private BigDecimal amount;

    private Integer orderState;

    private String approvalUserId;

    private String approvalUserName;

    private String approveAccountName;

    private Long settleId;

    private Date orderTime;

    private Date createTime;

    private Date updateTime;

    private String remark;

}