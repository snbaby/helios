package com.seadun.helios.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seadun.helios.entity.BaseMenuResource;
import com.seadun.helios.mapper.BaseMenuResourceMapper;

@Service
public class MenuResourceService {
	@Autowired
	private BaseMenuResourceMapper baseMenuResourceMapper;
	
	@Transactional
	public void add(String roleId,String menuId) {
		baseMenuResourceMapper.delete(roleId, menuId);
		BaseMenuResource baseMenuResource = new BaseMenuResource();
		baseMenuResource.setId(UUID.randomUUID().toString());
		baseMenuResource.setMenuId(menuId);
		baseMenuResource.setRoleId(roleId);
		baseMenuResourceMapper.insertSelective(baseMenuResource);
		
	}
	
	@Transactional
	public void del(String roleId,String menuId) {
		baseMenuResourceMapper.delete(roleId, menuId);
	}
}
