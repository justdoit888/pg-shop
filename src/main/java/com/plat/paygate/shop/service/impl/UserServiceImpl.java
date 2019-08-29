package com.plat.paygate.shop.service.impl;

import com.plat.paygate.shop.common.BaseResponse;
import com.plat.paygate.shop.common.ResultEnum;
import com.plat.paygate.shop.dto.UserRegistDto;
import com.plat.paygate.shop.mapper.PgUserMapper;
import com.plat.paygate.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
}
