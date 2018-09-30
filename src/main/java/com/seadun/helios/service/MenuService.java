package com.seadun.helios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seadun.helios.entity.BaseMenu;
import com.seadun.helios.entity.BaseMenuTree;
import com.seadun.helios.mapper.BaseMenuMapper;

@Service
public class MenuService {
	@Autowired
	private BaseMenuMapper baseMenuMapper;
	
	@Transactional
	public void initMenus() {
		baseMenuMapper.clear();
		BaseMenu systemManageMenu = new BaseMenu();
		systemManageMenu.setId(UUID.randomUUID().toString());
		systemManageMenu.setCode("systemManager");
		systemManageMenu.setCrtTime(new Date());
		systemManageMenu.setCrtUser("system");
		systemManageMenu.setName("系统设置");
		systemManageMenu.setParentId("-1");
		systemManageMenu.setPath("/helios/system-manage");
		baseMenuMapper.insertSelective(systemManageMenu);
		
		BaseMenu userManageMenu = new BaseMenu();
		userManageMenu.setId(UUID.randomUUID().toString());
		userManageMenu.setCode("systemManager-userManager");
		userManageMenu.setCrtTime(new Date());
		userManageMenu.setCrtUser("system");
		userManageMenu.setName("用户设置");
		userManageMenu.setParentId(systemManageMenu.getId());
		userManageMenu.setPath("/helios/system-manage/user");
		baseMenuMapper.insertSelective(userManageMenu);
		
		BaseMenu roleManageMenu = new BaseMenu();
		roleManageMenu.setId(UUID.randomUUID().toString());
		roleManageMenu.setCode("systemManager-roleManager");
		roleManageMenu.setCrtTime(new Date());
		roleManageMenu.setCrtUser("system");
		roleManageMenu.setName("角色设置");
		roleManageMenu.setParentId(systemManageMenu.getId());
		roleManageMenu.setPath("/helios/system-manage/role");
		baseMenuMapper.insertSelective(roleManageMenu);
		
		BaseMenu securityManageMenu = new BaseMenu();
		securityManageMenu.setId(UUID.randomUUID().toString());
		securityManageMenu.setCode("securityManager");
		securityManageMenu.setCrtTime(new Date());
		securityManageMenu.setCrtUser("system");
		securityManageMenu.setName("安全设置");
		securityManageMenu.setParentId("-1");
		securityManageMenu.setPath("/helios/security-manage");
		baseMenuMapper.insertSelective(securityManageMenu);
		
		BaseMenu roleGrantMenu = new BaseMenu();
		roleGrantMenu.setId(UUID.randomUUID().toString());
		roleGrantMenu.setCode("securityManager-roleGrant");
		roleGrantMenu.setCrtTime(new Date());
		roleGrantMenu.setCrtUser("system");
		roleGrantMenu.setName("角色授权");
		roleGrantMenu.setParentId(securityManageMenu.getId());
		roleGrantMenu.setPath("/helios/security-manage/role-grant");
		baseMenuMapper.insertSelective(roleGrantMenu);
		
		BaseMenu roleSetMenu = new BaseMenu();
		roleSetMenu.setId(UUID.randomUUID().toString());
		roleSetMenu.setCode("securityManager-roleSet");
		roleSetMenu.setCrtTime(new Date());
		roleSetMenu.setCrtUser("system");
		roleSetMenu.setName("角色分配");
		roleSetMenu.setParentId(securityManageMenu.getId());
		roleSetMenu.setPath("/helios/security-manage/role-set");
		baseMenuMapper.insertSelective(roleSetMenu);
		
		BaseMenu auditorManageMenu = new BaseMenu();
		auditorManageMenu.setId(UUID.randomUUID().toString());
		auditorManageMenu.setCode("auditorManager");
		auditorManageMenu.setCrtTime(new Date());
		auditorManageMenu.setCrtUser("system");
		auditorManageMenu.setName("安全设置");
		auditorManageMenu.setParentId("-1");
		auditorManageMenu.setPath("/helios/auditor-manage");
		baseMenuMapper.insertSelective(auditorManageMenu);
	}
	
	@Transactional
	public List<BaseMenuTree> getMenuTree() {
		return baseMenuMapper.selectTree();
	}
}
