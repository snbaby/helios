package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.VPc;

public interface VPcMapper {
	List<VPc> selectPage(RowBounds rowBounds,@Param(value = "detectId") String detectId,@Param(value = "assetCode") String assetCode);
}