package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.VDetectPc;

public interface VDetectPcMapper {

	List<VDetectPc> selectPage(RowBounds rowBounds, @Param(value = "assetCode") String assetCode,
			@Param(value = "detectCode") String detectCode, @Param(value = "portCode") String portCode);
}