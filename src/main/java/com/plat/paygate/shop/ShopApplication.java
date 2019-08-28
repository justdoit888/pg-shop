package com.plat.paygate.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author Sunny
 * @version 1.0
 * @date 2019-08-28 14:47
 * @package com.plat.paygate.shop
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:spring/applicationContext.xml"})
@Component("com.plat.paygate.shop.*")
@MapperScan("com.plat.paygate.shop.mapper")
public class ShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
