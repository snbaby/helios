package com.seadun.helios.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.entity.BaseMenu;
import com.seadun.helios.entity.BaseMenuResource;
import com.seadun.helios.entity.BaseMenuTree;
import com.seadun.helios.mapper.BaseMenuMapper;
import com.seadun.helios.mapper.BaseMenuResourceMapper;

@Service
public class MenuService {
	@Autowired
	private BaseMenuMapper baseMenuMapper;
	
	@Autowired
	private BaseMenuResourceMapper  baseMenuResourceMapper;
	
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
		auditorManageMenu.setName("日志审计");
		auditorManageMenu.setParentId("-1");
		auditorManageMenu.setPath("/helios/auditor-manage");
		baseMenuMapper.insertSelective(auditorManageMenu);
	}
	
	@Transactional
	public List<BaseMenuTree> getMenuTree() {
		return baseMenuMapper.selectTree();
	}
	
	@Transactional
	public JSONArray getAuthTree(String roleId) {
		List<BaseMenuResource> baseMenuResourceList = baseMenuResourceMapper.list(roleId, "");
		List<String> menuIdList = new ArrayList<>();
		baseMenuResourceList.forEach(baseMenuResource->{
			menuIdList.add(baseMenuResource.getMenuId());
		});
		
		List<BaseMenuTree> baseMenuTreeList = baseMenuMapper.selectTree();
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(baseMenuTreeList));		
		
		convertJsonArray(jsonArray, menuIdList);
		return jsonArray;
	}
	
	private void convertJsonArray(JSONArray jsonArray,List<String> menuIdList) {
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jsb = jsonArray.getJSONObject(i);
			String menu = jsb.getString("menuId");
			System.out.println(menuIdList.contains(jsb.getString("id")));
			if(menuIdList.contains(jsb.getString("id"))) {
				jsb.put("auth", true);
				convertJsonArray(jsb.getJSONArray("children"), menuIdList);
			}else {
				jsb.put("auth", false);
				convertJsonArray(jsb.getJSONArray("children"), menuIdList);
			}
		}
	}
}
