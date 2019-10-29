package com.plat.paygate.shop.service;

import com.plat.paygate.shop.common.BaseResponse;

import java.text.ParseException;

/**
 * @ClassName: OrderService
 * @author: zhjs
 * @createDate: 2019/9/6 上午9:44
 * @JDK: 1.8
 * @Desc: 订单service
 */
public interface OrderService {

    /**
     * 根据状态查询订单
     * @param userId
     * @param status
     * @return
     */
    BaseResponse listOrderByStatus(Long userId,Integer status,Integer role);

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    BaseResponse queryOrderDetailByOrderId(Long orderId,Integer role);

    /**
     * 订单录入
     * @param tbOrderId
     * @param buyerName
     * @param amount
     * @param tel
     * @param remark
     * @return
     */
    BaseResponse enterOrder(String tbOrderId,String buyerName,String amount,Long csUserId,String orderDate,String tel,String remark);

}
