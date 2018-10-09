package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.Pc;

public interface PcMapper {
    int deleteByPrimaryKey(String assetCode);

    int insert(Pc record);

    int insertSelective(Pc record);

    Pc selectByPrimaryKey(String assetCode);

    int updateByPrimaryKeySelective(Pc record);

    int updateByPrimaryKey(Pc record);
    
	List<Pc> selectPage(RowBounds rowBounds, @Param(value = "assetCode") String assetCode);
	
	List<Pc> list();
	
	int clear();
}