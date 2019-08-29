package com.plat.paygate.shop.mapper;


import com.plat.paygate.shop.domain.PgUser;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface PgUserMapper extends Mapper<PgUser>, MySqlMapper<PgUser> {

}