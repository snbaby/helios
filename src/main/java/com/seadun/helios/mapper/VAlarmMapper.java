package com.seadun.helios.mapper;

import com.seadun.helios.entity.VAlarm;

public interface VAlarmMapper {
    int insert(VAlarm record);

    int insertSelective(VAlarm record);
}