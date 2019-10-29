package com.plat.paygate.shop.common;

/**
 * @ClassName: ConstCode
 * @author: zhjs
 * @createDate: 2019/9/2 下午4:49
 * @JDK: 1.8
 * @Desc: TODO
 */
public class ConstCode {

    //已删除
    public final static String IS_DEL = "1";

    //未删除
    public final static String UN_DEL = "0";

    //订单状态-待核对
    public final static Integer ORDER_WAIT_AUDIT = 0;

    //订单状态-待结算
    public final static Integer ORDER_WAIT_SETTLED = 1;

    //订单状态-已结算
    public final static Integer ORDER_IS_SETTLED = 0;
}
