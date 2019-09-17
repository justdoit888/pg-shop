package com.plat.paygate.shop.service;

import com.plat.paygate.shop.common.BaseResponse;

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
     * @param openId
     * @param status
     * @return
     */
    BaseResponse listOrderByStatus(String openId, Integer status);
}
