package com.plat.paygate.shop.common.utils;

/**
 * @author Sunny
 * @version 1.0
 * @date 2019-08-29 11:00
 * @package com.plat.paygate.shop.common
 */
public class RegexUtils {

    //手机号检验规则
    public static final String MATCH_PTONE = "^[1](([3|5|8][\\\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\\\d]{8}$";
    //用户角色校验
    public static final String MATCH_ROLE = "1|2|3";
}
