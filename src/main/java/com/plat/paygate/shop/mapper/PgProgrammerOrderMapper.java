package com.plat.paygate.shop.mapper;


import com.plat.paygate.shop.domain.PgProgrammerOrder;
import com.plat.paygate.shop.dto.OrderListDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgProgrammerOrderMapper {

    List<OrderListDto> listOrderByStatus(@Param("userId") Long userId, @Param("orderStatus") Integer orderStatus);

    PgProgrammerOrder loadById(Long orderId);

    int insertSelective(PgProgrammerOrder record);

    List<PgProgrammerOrder> queryOrderByOrderIds(@Param("orderIds") List<Long> orderIds);

    void updateSettleIdByUserId(@Param("settleId") Long settleId,@Param("orderIds") List<Long> orderIds,@Param("userId") Long userId);
}