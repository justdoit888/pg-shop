package com.plat.paygate.shop.mapper;

import com.plat.paygate.shop.domain.PgUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(PgUser record);

    int insertSelective(PgUser record);

    PgUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(PgUser record);

    int updateByPrimaryKey(PgUser record);

    PgUser queryByOpenId(String openId);

    PgUser queryByTel(String tel);

    List<Long> queryAllUserIdByRole(Integer role);
}