package com.seadun.helios.mapper;

import com.seadun.helios.entity.BaseMenuResource;

public interface BaseMenuResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseMenuResource record);

    int insertSelective(BaseMenuResource record);

    BaseMenuResource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseMenuResource record);

    int updateByPrimaryKey(BaseMenuResource record);
}