package com.plat.paygate.shop.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: OrderDetailDto
 * @author: zhjs
 * @createDate: 2019/10/11 下午2:42
 * @JDK: 1.8
 * @Desc: 订单明细dto
 */
@Data
public class OrderDetailDto implements Serializable{

    private Long orderId;

    private String tbOrderId;

    private BigDecimal amount;

    private Integer orderState;

    private Date orderTime;

    private Date enterTime;

    private BigDecimal settleAmount;


    private String orderStateStr;

    public String getOrderStateStr() {
        if(this.orderState == 0){
            return "待核对";
        }else if (this.orderState == 1){
            return "待结算";
        }else{
            return "已结算";
        }
    }
}
