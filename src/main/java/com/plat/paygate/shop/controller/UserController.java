package com.plat.paygate.shop.controller;

import com.plat.paygate.shop.common.BaseResponse;
import com.plat.paygate.shop.dto.UserRegistDto;
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

    public BaseResponse regist(@RequestBody UserRegistDto userRegistDto){

        return null;
    }
}
