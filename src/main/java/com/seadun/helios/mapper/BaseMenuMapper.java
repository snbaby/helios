package com.seadun.helios.mapper;

import com.seadun.helios.entity.BaseMenu;

public interface BaseMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseMenu record);

    int insertSelective(BaseMenu record);

    BaseMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseMenu record);

    int updateByPrimaryKey(BaseMenu record);
}