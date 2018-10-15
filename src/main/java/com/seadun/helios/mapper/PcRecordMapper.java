package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.PcRecord;

public interface PcRecordMapper {
	int deleteByPrimaryKey(String id);

	int insert(PcRecord record);

	int insertSelective(PcRecord record);

	PcRecord selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(PcRecord record);

	int updateByPrimaryKeyWithBLOBs(PcRecord record);

	int updateByPrimaryKey(PcRecord record);

	List<PcRecord> selectPage(RowBounds rowBounds, @Param(value = "id") String id,
			@Param(value = "assetCode") String assetCode, @Param(value = "status") String status);
}