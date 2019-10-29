package com.plat.paygate.shop.schedule;

import com.plat.paygate.shop.common.utils.DateUtils;
import com.plat.paygate.shop.common.utils.SnowFlake;
import com.plat.paygate.shop.domain.*;
import com.plat.paygate.shop.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SettleSchedule
 * @author: zhjs
 * @createDate: 2019/10/29 下午2:23
 * @JDK: 1.8
 * @Desc: 每周日晚上8点跑一个定时任务
 *        结算上周日晚8点到到本周日晚8点的订单
 *        算上上周日8点整的订单  本周日8点整的订单计入下一个结算周期
 */

@Component
public class SettleSchedule {

    @Autowired
    private PgCsOrderMapper pgCsOrderMapper;

    @Autowired
    private PgProgrammerOrderMapper pgProgrammerOrderMapper;

    @Autowired
    private PgUserMapper pgUserMapper;

    @Autowired
    private PgOrderMapper pgOrderMapper;

    @Autowired
    private PgSettleMapper pgSettleMapper;

    @Scheduled(cron = "0 00 20 ? * 0")
    public void settleOrder(){
        try{
            //查询当前时间到上周日晚八点的所有订单
            String startTime = DateUtils.getSpecialTime(-7,"20:00:00");
            String endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + " 20:00:00";
            List<Long> orderIds = pgOrderMapper.queryOrderIdByDate(startTime, endTime);
            if(!CollectionUtils.isEmpty(orderIds)){
                this.settleProgrammerOrder(orderIds);
                this.settleCsOrder(orderIds);
            }
        }catch(Exception e){

        }
    }


    /**
     * 程序员订单清算
     * @param orderIds
     */
    private void settleProgrammerOrder(List<Long> orderIds){
        List<PgProgrammerOrder> pgProgrammerOrders = pgProgrammerOrderMapper.queryOrderByOrderIds(orderIds);
        if(!CollectionUtils.isEmpty(pgProgrammerOrders)){
            Map<Long, Double> programmerSettleAmountMap = pgProgrammerOrders.stream().collect(Collectors.groupingBy(PgProgrammerOrder::getUserId, Collectors.summingDouble(PgProgrammerOrder::getAmount)));
            if(!CollectionUtils.isEmpty(programmerSettleAmountMap)){

                Iterator<Map.Entry<Long, Double>> iterator = programmerSettleAmountMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<Long, Double> curr = iterator.next();
                    Long userId = curr.getKey();
                    BigDecimal settleAmount = new BigDecimal(curr.getValue());
                    Long settleId = SnowFlake.getSnowFlakeId();
                    this.insertSettleInfo(settleId,userId,settleAmount);
                    //更新程序员表结算
                    pgProgrammerOrderMapper.updateSettleIdByUserId(settleId,orderIds,userId);
                }
            }

        }
    }


    /**
     * 客服订单清算
     * @param orderIds
     */
    private void settleCsOrder(List<Long> orderIds){
        List<PgCsOrder> pgCsOrders = pgCsOrderMapper.queryOrderByOrderIds(orderIds);
        if(!CollectionUtils.isEmpty(pgCsOrders)){
            Map<Long, Double> csOrderSettleAmountMap = pgCsOrders.stream().collect(Collectors.groupingBy(PgCsOrder::getUserId, Collectors.summingDouble(PgCsOrder::getAmount)));
            if(!CollectionUtils.isEmpty(csOrderSettleAmountMap)){

                Iterator<Map.Entry<Long, Double>> iterator = csOrderSettleAmountMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<Long, Double> curr = iterator.next();
                    Long userId = curr.getKey();
                    BigDecimal settleAmount = new BigDecimal(curr.getValue());
                    Long settleId = SnowFlake.getSnowFlakeId();
                    this.insertSettleInfo(settleId,userId,settleAmount);

                    //更新客服表结算id
                    pgCsOrderMapper.updateSettleIdByUserId(settleId,orderIds,userId);
                }
            }

        }
    }


    /**
     * 插入结算信息
     * @param userId
     * @param settleAmount
     */
    private void insertSettleInfo(Long settleId,Long userId,BigDecimal settleAmount){
        PgSettle settle = new PgSettle();
        settle.setSettleId(settleId);
        settle.setUserId(userId);
        PgUser pgUser = pgUserMapper.selectByPrimaryKey(userId);
        if(null != pgUser){
            settle.setUserName(pgUser.getAccountName());
            settle.setAccountName(pgUser.getAccountName());
            settle.setAccountNo(pgUser.getAccountNo());

        }
        settle.setSettleAmount(settleAmount);
        settle.setSettleDate(new Date());
        settle.setCreateTime(new Date());
        settle.setUpdateTime(new Date());
        pgSettleMapper.insertSelective(settle);
    }
}
