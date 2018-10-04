package com.seadun.helios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seadun.helios.entity.BaseRoleResource;
import com.seadun.helios.entity.BaseRoleResourceNode;
import com.seadun.helios.mapper.BaseRoleResourceMapper;

@Service
public class RoleResourceService {
	@Autowired
	private BaseRoleResourceMapper baseRoleResourceMapper;
	
	@Transactional
	public void add(String roleId,List<String> users) {
		users.forEach(userId->{
			baseRoleResourceMapper.delete(userId, roleId);
			BaseRoleResource baseRoleResource = new BaseRoleResource();
			baseRoleResource.setId(UUID.randomUUID().toString());
			baseRoleResource.setRoleId(roleId);
			baseRoleResource.setUserId(userId);
			baseRoleResource.setCrtTime(new Date());
			baseRoleResource.setCrtUser("system");
			baseRoleResourceMapper.insertSelective(baseRoleResource);
		});
	}
	
	@Transactional
	public void del(String roleId,List<String> users) {
		users.forEach(userId->{
			baseRoleResourceMapper.delete(userId, roleId);
		});
	}
	
	@Transactional
	public List<BaseRoleResourceNode> selectNode(String userId,String roleId){
		return baseRoleResourceMapper.selectNode(userId, roleId);
	}
}
