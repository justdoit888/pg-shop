package com.plat.paygate.shop.common.mybatisConfig;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;

/**
 * mybatis 字段转换驼峰
 * @version 1.0
 * @date 2018年6月30日下午3:09:17
 * @return
 */

public class MapWrapperFactory implements ObjectWrapperFactory {
	@Override
	public boolean hasWrapperFor(Object object) {
		return object != null && object instanceof Map;
	}
	
	@Override
	public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
		return new CustomWrapper(metaObject,(Map)object);
	}
}
