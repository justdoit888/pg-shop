package com.plat.paygate.shop.service.impl;

import com.plat.paygate.shop.common.BaseResponse;
import com.plat.paygate.shop.common.ResultEnum;
import com.plat.paygate.shop.domain.PgUser;
import com.plat.paygate.shop.dto.OrderListDto;
import com.plat.paygate.shop.mapper.PgProgrammerOrderMapper;
import com.plat.paygate.shop.mapper.PgUserMapper;
import com.plat.paygate.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName: OrderService
 * @author: zhjs
 * @createDate: 2019/9/6 上午9:44
 * @JDK: 1.8
 * @Desc: 订单service实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PgUserMapper pgUserMapper;

    @Autowired
    private PgProgrammerOrderMapper pgProgrammerOrderMapper;

    @Override
    public BaseResponse listOrderByStatus(String openId, Integer status) {
        //通过openId查询用户信息
        PgUser pgUser = pgUserMapper.queryByOpenId(openId);
        if(pgUser == null){
            return new BaseResponse(ResultEnum.INVALID_USER.getCode(),ResultEnum.INVALID_USER.getDesc());
        }
        List<OrderListDto> orderListDtos = pgProgrammerOrderMapper.listOrderByStatus(pgUser.getUserId(), status);
        return new BaseResponse(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getDesc(),orderListDtos);
    }
}
