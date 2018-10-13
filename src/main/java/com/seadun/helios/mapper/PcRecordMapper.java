package com.seadun.helios.mapper;

import com.seadun.helios.entity.PcRecord;

public interface PcRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(PcRecord record);

    int insertSelective(PcRecord record);

    PcRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PcRecord record);

    int updateByPrimaryKeyWithBLOBs(PcRecord record);

    int updateByPrimaryKey(PcRecord record);
}