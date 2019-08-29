package com.plat.paygate.shop.service;

import com.plat.paygate.shop.common.BaseResponse;
import com.plat.paygate.shop.dto.UserRegistDto;

/**
 * @author Sunny
 * @version 1.0
 * @date 2019-08-28 17:54
 * @package com.plat.paygate.shop.service
 */
public interface UserService {

    BaseResponse insert(UserRegistDto userRegistDto);
}
