package com.seadun.helios.mapper;

import org.apache.ibatis.annotations.Param;

import com.seadun.helios.entity.DetectPcRelation;

public interface DetectPcRelationMapper {
    int deleteByPrimaryKey(String id);

    int insert(DetectPcRelation record);

    int insertSelective(DetectPcRelation record);

    DetectPcRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DetectPcRelation record);

    int updateByPrimaryKey(DetectPcRelation record);
    
    DetectPcRelation selectByPortId(@Param(value = "portId") String portId);
    
    DetectPcRelation selectByPcCode(@Param(value = "pcCode") String pcCode);
}