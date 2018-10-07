package com.seadun.helios.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seadun.helios.entity.BaseMenu;
import com.seadun.helios.entity.BaseMenuResource;
import com.seadun.helios.entity.BaseRole;
import com.seadun.helios.entity.BaseRoleResource;
import com.seadun.helios.entity.BaseUser;
import com.seadun.helios.mapper.BaseMenuMapper;
import com.seadun.helios.mapper.BaseMenuResourceMapper;
import com.seadun.helios.mapper.BaseRoleMapper;
import com.seadun.helios.mapper.BaseRoleResourceMapper;
import com.seadun.helios.mapper.BaseUserMapper;

@Service
public class InitService {
	@Autowired
	private BaseMenuMapper baseMenuMapper;
	
	@Autowired
	private BaseRoleMapper baseRoleMapper;
	
	@Autowired
	private BaseUserMapper baseUserMapper;
	
	@Autowired
	private BaseRoleResourceMapper baseRoleResourceMapper;
	
	@Autowired
	private BaseMenuResourceMapper baseMenuResourceMapper;
	
	public void init() {
		baseMenuMapper.clear();
		
		BaseMenu homeMenu = new BaseMenu();
		homeMenu.setId(UUID.randomUUID().toString());
		homeMenu.setCode("home");
		homeMenu.setCrtTime(new Date());
		homeMenu.setCrtUser("system");
		homeMenu.setName("系统首页");
		homeMenu.setParentId("-1");
		homeMenu.setPath("/helios/home");
		baseMenuMapper.insertSelective(homeMenu);
		
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
		auditorManageMenu.setName("日志审计");
		auditorManageMenu.setParentId("-1");
		auditorManageMenu.setPath("/helios/auditor-manage");
		baseMenuMapper.insertSelective(auditorManageMenu);
		
		BaseMenu eqManageMenu = new BaseMenu();
		eqManageMenu.setId(UUID.randomUUID().toString());
		eqManageMenu.setCode("eqManager");
		eqManageMenu.setCrtTime(new Date());
		eqManageMenu.setCrtUser("system");
		eqManageMenu.setName("设备管控");
		eqManageMenu.setParentId("-1");
		eqManageMenu.setPath("/helios/eq-manage");
		baseMenuMapper.insertSelective(eqManageMenu);
		
		BaseMenu deviceManageMenu = new BaseMenu();
		deviceManageMenu.setId(UUID.randomUUID().toString());
		deviceManageMenu.setCode("deviceManager");
		deviceManageMenu.setCrtTime(new Date());
		deviceManageMenu.setCrtUser("system");
		deviceManageMenu.setName("设备管理");
		deviceManageMenu.setParentId(eqManageMenu.getId());
		deviceManageMenu.setPath("/helios/eq-manage/device-manage");
		baseMenuMapper.insertSelective(deviceManageMenu);
		
		BaseMenu portManageMenu = new BaseMenu();
		portManageMenu.setId(UUID.randomUUID().toString());
		portManageMenu.setCode("portManager");
		portManageMenu.setCrtTime(new Date());
		portManageMenu.setCrtUser("system");
		portManageMenu.setName("端口管理");
		portManageMenu.setParentId(eqManageMenu.getId());
		portManageMenu.setPath("/helios/eq-manage/port-manage");
		baseMenuMapper.insertSelective(portManageMenu);
		
		baseRoleMapper.clear();
		
		BaseRole administratorRole = new BaseRole();
		administratorRole.setCode("administrator");
		administratorRole.setCrtTime(new Date());
		administratorRole.setCrtUser("system");
		administratorRole.setId(UUID.randomUUID().toString());
		administratorRole.setName("管理员");
		baseRoleMapper.insertSelective(administratorRole);
		
		BaseRole securityRole = new BaseRole();
		securityRole.setCode("security");
		securityRole.setCrtTime(new Date());
		securityRole.setCrtUser("system");
		securityRole.setId(UUID.randomUUID().toString());
		securityRole.setName("安全员");
		baseRoleMapper.insertSelective(securityRole);
		
		BaseRole auditorRole = new BaseRole();
		auditorRole.setCode("auditor");
		auditorRole.setCrtTime(new Date());
		auditorRole.setCrtUser("system");
		auditorRole.setId(UUID.randomUUID().toString());
		auditorRole.setName("审计员");
		baseRoleMapper.insertSelective(auditorRole);
		
		baseUserMapper.clear();
		
		BaseUser administratorUser = new BaseUser();
		administratorUser.setCode("administrator");
		administratorUser.setName("管理员");
		administratorUser.setCrtTime(new Date());
		administratorUser.setCrtUser("system");
		administratorUser.setId(UUID.randomUUID().toString());
		administratorUser.setPassword(DigestUtils.md5Hex("administrator"));
		baseUserMapper.insertSelective(administratorUser);
		
		BaseUser securityUser = new BaseUser();
		securityUser.setCode("security");
		securityUser.setName("安全员");
		securityUser.setCrtTime(new Date());
		securityUser.setCrtUser("system");
		securityUser.setId(UUID.randomUUID().toString());
		securityUser.setPassword(DigestUtils.md5Hex("security"));
		baseUserMapper.insertSelective(securityUser);
		
		BaseUser auditorUser = new BaseUser();
		auditorUser.setCode("auditor");
		auditorUser.setName("审计员");
		auditorUser.setCrtTime(new Date());
		auditorUser.setCrtUser("system");
		auditorUser.setId(UUID.randomUUID().toString());
		auditorUser.setPassword(DigestUtils.md5Hex("auditor"));
		baseUserMapper.insertSelective(auditorUser);
		
		baseRoleResourceMapper.clear();
		
		BaseRoleResource administratorRoleResource = new BaseRoleResource();
		administratorRoleResource.setId(UUID.randomUUID().toString());
		administratorRoleResource.setRoleId(administratorRole.getId());
		administratorRoleResource.setUserId(administratorUser.getId());
		administratorRoleResource.setCrtTime(new Date());
		administratorRoleResource.setCrtUser("system");
		baseRoleResourceMapper.insertSelective(administratorRoleResource);
		
		BaseRoleResource securityRoleResource = new BaseRoleResource();
		securityRoleResource.setId(UUID.randomUUID().toString());
		securityRoleResource.setRoleId(securityRole.getId());
		securityRoleResource.setUserId(securityUser.getId());
		securityRoleResource.setCrtTime(new Date());
		securityRoleResource.setCrtUser("system");
		baseRoleResourceMapper.insertSelective(securityRoleResource);
		
		BaseRoleResource auditorRoleResource = new BaseRoleResource();
		auditorRoleResource.setId(UUID.randomUUID().toString());
		auditorRoleResource.setRoleId(auditorRole.getId());
		auditorRoleResource.setUserId(auditorUser.getId());
		auditorRoleResource.setCrtTime(new Date());
		auditorRoleResource.setCrtUser("system");
		baseRoleResourceMapper.insertSelective(auditorRoleResource);
		
		baseMenuResourceMapper.clear();
		
		BaseMenuResource administratorMenuResource0 = new BaseMenuResource();
		administratorMenuResource0.setCrtTime(new Date());
		administratorMenuResource0.setCrtUser("system");
		administratorMenuResource0.setId(UUID.randomUUID().toString());
		administratorMenuResource0.setMenuId(systemManageMenu.getId());
		administratorMenuResource0.setRoleId(administratorRole.getId());
		baseMenuResourceMapper.insertSelective(administratorMenuResource0);
		
		BaseMenuResource administratorMenuResource1 = new BaseMenuResource();
		administratorMenuResource1.setCrtTime(new Date());
		administratorMenuResource1.setCrtUser("system");
		administratorMenuResource1.setId(UUID.randomUUID().toString());
		administratorMenuResource1.setMenuId(userManageMenu.getId());
		administratorMenuResource1.setRoleId(administratorRole.getId());
		baseMenuResourceMapper.insertSelective(administratorMenuResource1);
		
		BaseMenuResource administratorMenuResource2 = new BaseMenuResource();
		administratorMenuResource2.setCrtTime(new Date());
		administratorMenuResource2.setCrtUser("system");
		administratorMenuResource2.setId(UUID.randomUUID().toString());
		administratorMenuResource2.setMenuId(roleManageMenu.getId());
		administratorMenuResource2.setRoleId(administratorRole.getId());
		baseMenuResourceMapper.insertSelective(administratorMenuResource2);
		
		BaseMenuResource securityMenuResource0 = new BaseMenuResource();
		securityMenuResource0.setCrtTime(new Date());
		securityMenuResource0.setCrtUser("system");
		securityMenuResource0.setId(UUID.randomUUID().toString());
		securityMenuResource0.setMenuId(securityManageMenu.getId());
		securityMenuResource0.setRoleId(securityRole.getId());
		baseMenuResourceMapper.insertSelective(securityMenuResource0);
		
		BaseMenuResource securityMenuResource1 = new BaseMenuResource();
		securityMenuResource1.setCrtTime(new Date());
		securityMenuResource1.setCrtUser("system");
		securityMenuResource1.setId(UUID.randomUUID().toString());
		securityMenuResource1.setMenuId(roleGrantMenu.getId());
		securityMenuResource1.setRoleId(securityRole.getId());
		baseMenuResourceMapper.insertSelective(securityMenuResource1);
		
		BaseMenuResource securityMenuResource2 = new BaseMenuResource();
		securityMenuResource2.setCrtTime(new Date());
		securityMenuResource2.setCrtUser("system");
		securityMenuResource2.setId(UUID.randomUUID().toString());
		securityMenuResource2.setMenuId(roleSetMenu.getId());
		securityMenuResource2.setRoleId(securityRole.getId());
		baseMenuResourceMapper.insertSelective(securityMenuResource2);
		
		BaseMenuResource auditorMenuResource = new BaseMenuResource();
		auditorMenuResource.setCrtTime(new Date());
		auditorMenuResource.setCrtUser("system");
		auditorMenuResource.setId(UUID.randomUUID().toString());
		auditorMenuResource.setMenuId(auditorManageMenu.getId());
		auditorMenuResource.setRoleId(auditorRole.getId());
		baseMenuResourceMapper.insertSelective(auditorMenuResource);
		
	}
}
