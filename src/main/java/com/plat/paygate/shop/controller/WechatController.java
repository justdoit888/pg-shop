package com.plat.paygate.shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.plat.paygate.shop.common.BaseResponse;
import com.plat.paygate.shop.common.ResultEnum;
import com.plat.paygate.shop.common.utils.HttpUtil;
import com.plat.paygate.shop.domain.PgUser;
import com.plat.paygate.shop.service.OrderService;
import com.plat.paygate.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * @ClassName: WechatController
 * @author: zhjs
 * @createDate: 2019/8/31 上午9:44
 * @JDK: 1.8
 * @Desc: 微信小程序controller
 */
@RestController
@RequestMapping(value = "/wechat")
@Slf4j
public class WechatController {


    @Value("${wechat.appId}")
    private String APPID;

    @Value("${wechat.appSecret}")
    private String APPSECRET;

    @Value("${wechat.getOpenId_url}")
    private String OPENID_URL;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    /**
     * 根据code获取微信用户openId
     * @param code
     * @return
     */
    @RequestMapping("/getOpenId")
    public BaseResponse getOpenId(@RequestParam("code") String code){
        BaseResponse response = new BaseResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
        JSONObject object = HttpUtil.getJson(String.format(OPENID_URL, APPID, APPSECRET, code.trim()));
        if (object.get("openid") == null) {
            throw new IllegalArgumentException("code无效");
        }
        String openId = object.get("openid").toString();
        log.info("openId:{}",openId);
        PgUser user = userService.queryByOpenId(openId);
        if(null != user){
            response.setCode(ResultEnum.ALREADY_JOIN.getCode());
            response.setDesc(ResultEnum.ALREADY_JOIN.getDesc());
            response.setData(user);
        }else{
            response.setData(openId);
        }
        return response;
    }


    /**
     * 根据openId查询当前登录用户
     * @param openId
     * @return
     */
    @RequestMapping("/queryCurrUser")
    public BaseResponse queryCurrUser(String openId){
        BaseResponse response = new BaseResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
        PgUser user = userService.queryByOpenId(openId);
        response.setData(user);
        return response;
    }

    /**
     * 加入我们
     * @param userName
     * @param tel
     * @param qqNumber
     * @param alipayNo
     * @param openId
     * @return
     */
    @RequestMapping("/joinus")
    public BaseResponse joinus(String userName,String tel,String qqNumber,String alipayNo,String openId,Integer role){
        log.info("userName:{},tel:{},qqNumber:{},alipayNo:{},openId:{}",userName,tel,qqNumber,alipayNo,openId);
        BaseResponse response = new BaseResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
        if(StringUtils.isEmpty(userName)){
            response.setDesc("用户名不能为空");
            return response;
        }
        if(StringUtils.isEmpty(tel)){
            response.setDesc("手机号不能为空");
            return response;
        }
        if(StringUtils.isEmpty(qqNumber)){
            response.setDesc("接单QQ不能为空");
            return response;
        }
        if(StringUtils.isEmpty(alipayNo)){
            response.setDesc("支付宝账号不能为空");
            return response;
        }
        return userService.joinus(userName, tel, qqNumber, alipayNo, openId,role);
    }


    /**
     * 根据状态查询订单
     * @param openId
     * @param status
     * @return
     */
    @RequestMapping("/listOrderByStatus")
    public BaseResponse listOrderByStatus(String openId,Integer status){
        return orderService.listOrderByStatus(openId,status);
    }
}
