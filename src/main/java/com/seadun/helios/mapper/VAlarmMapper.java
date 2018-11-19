package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.VAlarm;

public interface VAlarmMapper {
	List<VAlarm> selectPage(RowBounds rowBounds, @Param(value = "detectId") String detectId,
			@Param(value = "assetCode") String assetCode,@Param(value = "alarmStatus") String alarmStatus);
	
	VAlarm selectByAlarmId(@Param(value = "alarmId") String alarmId);
}