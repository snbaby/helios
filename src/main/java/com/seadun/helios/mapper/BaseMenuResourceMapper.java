package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seadun.helios.entity.BaseMenuResource;

public interface BaseMenuResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseMenuResource record);

    int insertSelective(BaseMenuResource record);

    BaseMenuResource selectByPrimaryKey(String id);
    
    List<BaseMenuResource> list(@Param(value = "roleId") String roleId,@Param(value = "menuId") String menuId);
    
    int delete(@Param(value = "roleId") String roleId,@Param(value = "menuId") String menuId);
    
    int add(@Param(value = "roleId") String roleId,@Param(value = "menuId") String menuId);

    int updateByPrimaryKeySelective(BaseMenuResource record);

    int updateByPrimaryKey(BaseMenuResource record);
}