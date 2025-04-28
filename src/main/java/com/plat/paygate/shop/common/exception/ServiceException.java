package com.plat.paygate.shop.common.exception;

import lombok.Data;

/**
 * @ClassName: ServiceException11111
 * @author: zhjs
 * @createDate: 2019/10/29 下午2:04
 * @JDK: 1.8
 * @Desc: 自定义异常
 */
@Data
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private Object data;

    public ServiceException(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
