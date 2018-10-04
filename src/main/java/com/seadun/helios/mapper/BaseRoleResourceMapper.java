package com.seadun.helios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seadun.helios.entity.BaseRoleResource;
import com.seadun.helios.entity.BaseRoleResourceNode;

public interface BaseRoleResourceMapper {
    int deleteByPrimaryKey(String id);
    
    int delete(@Param(value = "userId") String userId,@Param(value = "roleId") String roleId);

    int insert(BaseRoleResource record);

    int insertSelective(BaseRoleResource record);

    BaseRoleResource selectByPrimaryKey(String id);
    
    List<BaseRoleResourceNode> selectNode(@Param(value = "userId") String userId,@Param(value = "roleId") String roleId);

    int updateByPrimaryKeySelective(BaseRoleResource record);

    int updateByPrimaryKey(BaseRoleResource record);
}