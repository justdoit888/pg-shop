package com.plat.paygate.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: OrderListDto
 * @author: zhjs
 * @createDate: 2019/9/17 上午9:56
 * @JDK: 1.8
 * @Desc: 订单列表dto
 */
@Data
public class OrderListDto implements Serializable{

    //订单号
    private Long orderId;

    //用户id
    private Long userId;

     //用户名
    private String userName;

    //结算账户
    private String accountNo;

    //淘宝订单号
    private String tbOrderId;

    //订单金额
    private BigDecimal amount;

    //订单状态
    private Integer orderState;

    //订单时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    //备注
    private String remark;

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

    public void setOrderStateStr(String orderStateStr) {
        this.orderStateStr = orderStateStr;
    }
}
