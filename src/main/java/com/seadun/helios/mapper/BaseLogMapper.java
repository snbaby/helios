package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.BaseLog;

public interface BaseLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseLog record);

    int insertSelective(BaseLog record);

    BaseLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseLog record);

    int updateByPrimaryKeyWithBLOBs(BaseLog record);

    int updateByPrimaryKey(BaseLog record);
    
    List<BaseLog> selectPage(RowBounds rowBounds,@Param(value = "name") String name,@Param(value = "code") String code);
}