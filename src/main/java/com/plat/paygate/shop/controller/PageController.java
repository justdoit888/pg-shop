package com.plat.paygate.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sunny
 * @version 1.0
 * @date 2019-08-30 17:02
 * @package com.plat.paygate.shop.controller
 */
@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }
}
