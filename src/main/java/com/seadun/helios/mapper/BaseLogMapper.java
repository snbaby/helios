package com.seadun.helios.mapper;

import com.seadun.helios.entity.BaseLog;

public interface BaseLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseLog record);

    int insertSelective(BaseLog record);

    BaseLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseLog record);

    int updateByPrimaryKey(BaseLog record);
}