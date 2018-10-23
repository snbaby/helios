package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.DetectPort;

public interface DetectPortMapper {
	int deleteByPrimaryKey(String id);

	int insert(DetectPort record);

	int insertSelective(DetectPort record);

	DetectPort selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(DetectPort record);

	int updateByPrimaryKey(DetectPort record);

	List<DetectPort> selectPage(RowBounds rowBounds, @Param(value = "code") String code,
			@Param(value = "name") String name, @Param(value = "detectId") String detectId);
	DetectPort selectByCode(@Param(value = "code") String code);
	
	DetectPort selectByDetectIdAndPort(@Param(value = "detectId") String detectId,@Param(value = "port") short port);
	
	List<DetectPort> list(@Param(value = "detectId") String detectId);
	
	List<DetectPort> useableList(@Param(value = "detectId") String detectId);
	
	
}