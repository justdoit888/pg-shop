package com.plat.paygate.shop.controller;

import com.plat.paygate.shop.common.BaseResponse;
import com.plat.paygate.shop.dto.UserRegistDto;
import com.plat.paygate.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关服务
 * @author Sunny
 * @version 1.0
 * @date 2019-08-28 16:52
 * @package com.plat.paygate.shop.controller
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户注册
     *
     * @param userRegistDto
     * @return com.plat.paygate.shop.common.BaseResponse
     * @author Sunny
     * @date 2019/8/29 10:50
     * @version V1.0
     */
    public BaseResponse regist(@RequestBody @Validated UserRegistDto userRegistDto){
        userService.insert(userRegistDto);
        return null;
    }
}
