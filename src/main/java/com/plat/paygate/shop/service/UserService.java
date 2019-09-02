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


    /**
     * 加入我们
     * @param userName
     * @param tel
     * @param qqNumber
     * @param alipayNo
     * @param openId
     * @return
     */
    BaseResponse joinus(String userName,String tel,String qqNumber,String alipayNo,String openId);
}
