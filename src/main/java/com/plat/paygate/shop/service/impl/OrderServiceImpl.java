package com.plat.paygate.shop.service.impl;

import com.plat.paygate.shop.common.BaseResponse;
import com.plat.paygate.shop.common.ConstCode;
import com.plat.paygate.shop.common.ResultEnum;
import com.plat.paygate.shop.common.RoleEnum;
import com.plat.paygate.shop.common.exception.ServiceException;
import com.plat.paygate.shop.common.utils.SnowFlake;
import com.plat.paygate.shop.domain.*;
import com.plat.paygate.shop.dto.OrderDetailDto;
import com.plat.paygate.shop.dto.OrderListDto;
import com.plat.paygate.shop.mapper.PgCsOrderMapper;
import com.plat.paygate.shop.mapper.PgOrderMapper;
import com.plat.paygate.shop.mapper.PgProgrammerOrderMapper;
import com.plat.paygate.shop.mapper.PgUserMapper;
import com.plat.paygate.shop.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.rmi.server.ServerCloneException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @ClassName: OrderService
 * @author: zhjs
 * @createDate: 2019/9/6 上午9:44
 * @JDK: 1.8
 * @Desc: 订单service实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PgUserMapper pgUserMapper;

    @Autowired
    private PgProgrammerOrderMapper pgProgrammerOrderMapper;

    @Autowired
    private PgCsOrderMapper pgCsOrderMapper;

    @Autowired
    private PgOrderMapper pgOrderMapper;

    @Override
    public BaseResponse listOrderByStatus(Long userId,Integer status,Integer role) {
//        //通过openId查询用户信息
//        PgUser pgUser = pgUserMapper.queryByOpenId(openId);
//        if(pgUser == null){
//            return new BaseResponse(ResultEnum.INVALID_USER.getCode(),ResultEnum.INVALID_USER.getDesc());
//        }
        List<OrderListDto> orderListDtos = new ArrayList<>();
        if(RoleEnum.PROGRAMMER.getCode().equals(role)){
            //程序员
            orderListDtos = pgProgrammerOrderMapper.listOrderByStatus(userId, status);
        }else if(RoleEnum.CUSTOMER_SERVICE.getCode().equals(role)){
            //客服
            orderListDtos = pgCsOrderMapper.listOrderByStatus(userId,status);
        }
        return new BaseResponse(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getDesc(),orderListDtos);
    }

    @Override
    public BaseResponse queryOrderDetailByOrderId(Long orderId,Integer role) {
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        PgOrder pgOrder = pgOrderMapper.loadById(orderId);
        BeanUtils.copyProperties(pgOrder,orderDetailDto);
        if(RoleEnum.PROGRAMMER.getCode().equals(role)){
            //程序员
            PgProgrammerOrder pgProgrammerOrder = pgProgrammerOrderMapper.loadById(orderId);
            orderDetailDto.setSettleAmount(pgProgrammerOrder.getSettleAmount());
        }else if(RoleEnum.CUSTOMER_SERVICE.getCode().equals(role)){
            //客服
            PgCsOrder pgCsOrder = pgCsOrderMapper.loadById(orderId);
            orderDetailDto.setSettleAmount(pgCsOrder.getSettleAmount());
        }
        return new BaseResponse(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getDesc(),orderDetailDto);
    }

    @Override
    public BaseResponse enterOrder(String tbOrderId, String buyerName, String amount,Long csUserId,String orderDate, String tel, String remark) {
        //根据电话号查询程序员的信息
        try{
            Long orderId = SnowFlake.getSnowFlakeId();
            PgUser programmerUser = pgUserMapper.queryByTel(tel);
            if(null == programmerUser){
                return new BaseResponse(ResultEnum.NOT_EXIST_USER.getCode(),ResultEnum.NOT_EXIST_USER.getDesc());
            }

            //根据客服id查询客服信息
            PgUser csUser = pgUserMapper.selectByPrimaryKey(csUserId);
            if(null == csUser){
                return new BaseResponse(ResultEnum.NOT_EXIST_USER.getCode(),ResultEnum.NOT_EXIST_USER.getDesc());
            }

            //程序员订单
            PgProgrammerOrder pgProgrammerOrder = new PgProgrammerOrder();
            pgProgrammerOrder.setOrderId(orderId);
            pgProgrammerOrder.setUserId(programmerUser.getUserId());
            pgProgrammerOrder.setUserName(programmerUser.getAccountName());
            pgProgrammerOrder.setAccountName(programmerUser.getAccountName());
            pgProgrammerOrder.setAccountNo(programmerUser.getAccountNo());
            pgProgrammerOrder.setSettleAmount(new BigDecimal(amount).multiply(new BigDecimal("0.8")));
            pgProgrammerOrder.setRate(new BigDecimal("0.8"));
            pgProgrammerOrderMapper.insertSelective(pgProgrammerOrder);

            //客服
            PgCsOrder pgCsOrder = new PgCsOrder();
            pgCsOrder.setOrderId(orderId);
            pgCsOrder.setUserId(csUser.getUserId());
            pgCsOrder.setUserName(csUser.getAccountName());
            pgCsOrder.setAccountName(csUser.getAccountName());
            pgCsOrder.setAccountNo(csUser.getAccountNo());
            pgCsOrder.setSettleAmount(new BigDecimal(amount).multiply(new BigDecimal("0.1")));
            pgCsOrder.setRate(new BigDecimal("0.1"));
            pgCsOrderMapper.insertSelective(pgCsOrder);


            //主订单
            PgOrder pgOrder = new PgOrder();
            pgOrder.setOrderId(orderId);
            pgOrder.setTbOrderId(tbOrderId);
            pgOrder.setBuyerName(buyerName);
            pgOrder.setAmount(new BigDecimal(amount));
            pgOrder.setOrderState(ConstCode.ORDER_WAIT_AUDIT);
            pgOrder.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderDate));
            pgOrder.setCreateTime(new Date());
            pgOrder.setUpdateTime(new Date());
            pgOrder.setRemark(remark);
            pgOrderMapper.insertSelective(pgOrder);
        }catch(Exception e){
            throw new ServiceException(ResultEnum.SERVICE_EXCEPTION.getCode(),ResultEnum.SERVICE_EXCEPTION.getDesc(),"");
        }

        return new BaseResponse(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getDesc(),"");
    }
}
