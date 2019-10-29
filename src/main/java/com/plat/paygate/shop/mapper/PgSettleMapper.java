package com.plat.paygate.shop.mapper;


import com.plat.paygate.shop.domain.PgSettle;
import org.springframework.stereotype.Repository;

@Repository
public interface PgSettleMapper {


    int insert(PgSettle record);

    int insertSelective(PgSettle record);

}