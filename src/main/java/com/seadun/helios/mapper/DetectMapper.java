package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.Detect;

public interface DetectMapper {
	int deleteByPrimaryKey(String id);

	int insert(Detect record);

	int insertSelective(Detect record);

	Detect selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Detect record);

	int updateByPrimaryKey(Detect record);

	List<Detect> selectPage(RowBounds rowBounds, @Param(value = "code") String code, @Param(value = "name") String name,
			@Param(value = "ip") String ip);
	
	Detect selectByCode(@Param(value = "code") String code);
	Detect selectByIp(@Param(value = "ip") String ip);
	
	List<Detect> list();
}