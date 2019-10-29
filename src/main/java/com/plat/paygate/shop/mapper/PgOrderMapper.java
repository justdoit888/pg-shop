package com.plat.paygate.shop.mapper;


import com.plat.paygate.shop.domain.PgOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgOrderMapper {

    PgOrder loadById(Long orderId);

    int insertSelective(PgOrder record);

    List<Long> queryOrderIdByDate(@Param("startTime") String startTime, @Param("endTime") String endTime);

}