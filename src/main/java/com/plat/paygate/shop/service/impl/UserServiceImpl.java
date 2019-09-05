package com.plat.paygate.shop.service.impl;

import com.plat.paygate.shop.common.BaseResponse;
import com.plat.paygate.shop.common.ConstCode;
import com.plat.paygate.shop.common.ResultEnum;
import com.plat.paygate.shop.common.RoleEnum;
import com.plat.paygate.shop.common.utils.MD5Util;
import com.plat.paygate.shop.domain.PgUser;
import com.plat.paygate.shop.dto.UserRegistDto;
import com.plat.paygate.shop.mapper.PgUserMapper;
import com.plat.paygate.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Sunny
 * @version 1.0
 * @date 2019-08-28 17:54
 * @package com.plat.paygate.shop.service.impl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PgUserMapper userMapper;

    @Override
    public BaseResponse insert(UserRegistDto userRegistDto) {
        BaseResponse response = new BaseResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());

        //验证密码与确认密码是否一致
        if(!userRegistDto.getPassword().equals(userRegistDto.getConfirmPassword())){
            response.setCode(ResultEnum.PASS_UNCONSISTEN.getCode());
            response.setDesc(ResultEnum.PASS_UNCONSISTEN.getDesc());
        }
        //验证此用户是否已经注册
        try {
            userMapper.insert(null);
        }catch (DuplicateKeyException e){
            response.setCode(ResultEnum.USER_EXIST.getCode());
            response.setDesc(ResultEnum.USER_EXIST.getDesc());
        }

        return response;
    }

    @Override
    public BaseResponse joinus(String userName, String tel, String qqNumber, String alipayNo, String openId,Integer role) {
        PgUser user = userMapper.queryByOpenId(openId);
        if(user != null){
            return new BaseResponse(ResultEnum.ALREADY_JOIN.getCode(), ResultEnum.ALREADY_JOIN.getDesc());
        }
        user = new PgUser();
        user.setUserName(tel);
        user.setAccountName(userName);
        user.setAccountNo(alipayNo);
        user.setOpenid(openId);
        user.setQqNumber(qqNumber);
        user.setRole(role);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDel(ConstCode.UN_DEL);
        //默认密码为123456
        user.setPassword(MD5Util.getMD5("123456"));
        userMapper.insert(user);
        return new BaseResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
    }

    @Override
    public PgUser queryByOpenId(String openId) {
        return userMapper.queryByOpenId(openId);
    }
}
