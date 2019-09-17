package com.plat.paygate.shop.common.mybatisConfig;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 字段转换驼峰
 * @version 1.0
 * @date 2018年6月30日下午3:08:50
 * @return
 */
@Configuration
public class MybatisConfig {

	@Bean
	public ConfigurationCustomizer mybatisConfigurationCustomizer(){
		return new ConfigurationCustomizer() {
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
				configuration.setObjectWrapperFactory(new MapWrapperFactory());
			}
		};
	}
}
