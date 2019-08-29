package com.plat.paygate.shop.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PgProgrammerOrder {
    private Long orderId;

    private Long userId;

    private String userName;

    private String accountName;

    private String accountNo;

    private BigDecimal settleAmount;

    private BigDecimal rate;


}