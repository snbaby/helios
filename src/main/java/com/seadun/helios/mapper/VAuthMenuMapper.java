package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seadun.helios.entity.VAuthMenu;
import com.seadun.helios.entity.VAuthMenuTree;

public interface VAuthMenuMapper {
    int insert(VAuthMenu record);

    int insertSelective(VAuthMenu record);
    
    List<VAuthMenuTree> selectTree(@Param(value = "userId") String userId);
    
    List<VAuthMenu> select(@Param(value = "userId") String userId);
}