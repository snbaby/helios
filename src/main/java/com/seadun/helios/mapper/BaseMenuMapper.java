package com.seadun.helios.mapper;

import java.util.List;

import com.seadun.helios.entity.BaseMenu;
import com.seadun.helios.entity.BaseMenuTree;

public interface BaseMenuMapper {
    int deleteByPrimaryKey(String id);
    
    int clear();
    
    List<BaseMenuTree> selectTree();

    int insert(BaseMenu record);

    int insertSelective(BaseMenu record);

    BaseMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseMenu record);

    int updateByPrimaryKey(BaseMenu record);
}