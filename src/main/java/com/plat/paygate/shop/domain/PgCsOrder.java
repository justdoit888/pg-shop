package com.plat.paygate.shop.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PgCsOrder {
    private Long orderId;

    private Long userId;

    private String userName;

    private Long settleId;

    private String accountName;

    private String accountNo;

    private BigDecimal settleAmount;

    private BigDecimal rate;

    /**
     * 结算金额  转为double类型   定时任务lambda表达式中计算总和使用
     */
    private Double amount;

    public Double getAmount() {
        return settleAmount.doubleValue();
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}