package com.davih.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

	@Select("select 'order'")
	String getById();
}
