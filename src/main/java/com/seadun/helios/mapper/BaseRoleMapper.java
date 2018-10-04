package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.BaseRole;

public interface BaseRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseRole record);

    int insertSelective(BaseRole record);

    BaseRole selectByPrimaryKey(String id);
    
    BaseRole selectByCode(String code);
    
    List<BaseRole> list();
    
    List<BaseRole> selectPage(RowBounds rowBounds,@Param(value = "name") String name,@Param(value = "code") String code);

    int updateByPrimaryKeySelective(BaseRole record);

    int updateByPrimaryKey(BaseRole record);
}