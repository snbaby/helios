package com.seadun.helios.mapper;

import org.apache.ibatis.annotations.Param;

import com.seadun.helios.entity.Alarm;

public interface AlarmMapper {
    int deleteByPrimaryKey(String id);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);
    
    int deleteByPortId(@Param(value = "portId") String portId);
}