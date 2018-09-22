package com.seadun.helios.mapper;

import com.seadun.helios.entity.BaseRoleResource;

public interface BaseRoleResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseRoleResource record);

    int insertSelective(BaseRoleResource record);

    BaseRoleResource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseRoleResource record);

    int updateByPrimaryKey(BaseRoleResource record);
}