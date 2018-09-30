package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.seadun.helios.entity.BaseUser;

public interface BaseUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseUser record);

    int insertSelective(BaseUser record);

    BaseUser selectByPrimaryKey(String id);
    
    BaseUser selectByCode(String code);
    
    List<BaseUser> selectPage(RowBounds rowBounds);

    int updateByPrimaryKeySelective(BaseUser record);

    int updateByPrimaryKey(BaseUser record);
}