package com.seadun.helios.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.entity.BaseMenu;
import com.seadun.helios.entity.BaseMenuResource;
import com.seadun.helios.entity.BaseRole;
import com.seadun.helios.entity.BaseRoleResource;
import com.seadun.helios.entity.BaseUser;
import com.seadun.helios.entity.Pc;
import com.seadun.helios.mapper.BaseMenuMapper;
import com.seadun.helios.mapper.BaseMenuResourceMapper;
import com.seadun.helios.mapper.BaseRoleMapper;
import com.seadun.helios.mapper.BaseRoleResourceMapper;
import com.seadun.helios.mapper.BaseUserMapper;
import com.seadun.helios.mapper.PcMapper;

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
	
	@Autowired
	private PcMapper pcMapper;

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
		 deviceManageMenu.setCode("pcManager");
		 deviceManageMenu.setCrtTime(new Date());
		 deviceManageMenu.setCrtUser("system");
		 deviceManageMenu.setName("设备管理");
		 deviceManageMenu.setParentId(eqManageMenu.getId());
		 deviceManageMenu.setPath("/helios/eq-manage/pc-manage");
		 baseMenuMapper.insertSelective(deviceManageMenu);
		
		 BaseMenu detectMenu = new BaseMenu();
		 detectMenu.setId(UUID.randomUUID().toString());
		 detectMenu.setCode("detect");
		 detectMenu.setCrtTime(new Date());
		 detectMenu.setCrtUser("system");
		 detectMenu.setName("侦测器");
		 detectMenu.setParentId(eqManageMenu.getId());
		 detectMenu.setPath("/helios/eq-manage/detect");
		 baseMenuMapper.insertSelective(detectMenu);
		
		 BaseMenu portManageMenu = new BaseMenu();
		 portManageMenu.setId(UUID.randomUUID().toString());
		 portManageMenu.setCode("portManager");
		 portManageMenu.setCrtTime(new Date());
		 portManageMenu.setCrtUser("system");
		 portManageMenu.setName("端口管理");
		 portManageMenu.setParentId(detectMenu.getId());
		 portManageMenu.setPath("/helios/eq-manage/detect/port-manage");
		 baseMenuMapper.insertSelective(portManageMenu);
		
		 BaseMenu detectManageMenu = new BaseMenu();
		 detectManageMenu.setId(UUID.randomUUID().toString());
		 detectManageMenu.setCode("detectManager");
		 detectManageMenu.setCrtTime(new Date());
		 detectManageMenu.setCrtUser("system");
		 detectManageMenu.setName("侦测器管理");
		 detectManageMenu.setParentId(detectMenu.getId());
		 detectManageMenu.setPath("/helios/eq-manage/detect/detect-manage");
		 baseMenuMapper.insertSelective(detectManageMenu);
		
		 BaseMenu eqRelationMenu = new BaseMenu();
		 eqRelationMenu.setId(UUID.randomUUID().toString());
		 eqRelationMenu.setCode("eqRelation");
		 eqRelationMenu.setCrtTime(new Date());
		 eqRelationMenu.setCrtUser("system");
		 eqRelationMenu.setName("设备关联");
		 eqRelationMenu.setParentId(eqManageMenu.getId());
		 eqRelationMenu.setPath("/helios/eq-manage/eq-relation");
		 baseMenuMapper.insertSelective(eqRelationMenu);
		
		 BaseMenu alarmMenu = new BaseMenu();
		 alarmMenu.setId(UUID.randomUUID().toString());
		 alarmMenu.setCode("alarm");
		 alarmMenu.setCrtTime(new Date());
		 alarmMenu.setCrtUser("system");
		 alarmMenu.setName("报警管理");
		 alarmMenu.setParentId("-1");
		 alarmMenu.setPath("/helios/alarm");
		 baseMenuMapper.insertSelective(alarmMenu);
		
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
		
		pcMapper.clear();
		String jsonString = "{\"SBTZXX\":[{\"ZCID\":\"2931b1b2-5ccb-4084-93c2-723a7d6c70c8\",\"SBMC\":null,\"ZCH\":\"W00195\",\"CCBH\":null,\"AZWZ\":null,\"HTH\":null,\"XLH\":null,\"SBXH\":\"22\",\"ZRRPERSONID\":null,\"SBSYRPERSONID\":null,\"GMRQ\":\"2018-09-05\",\"EMPNAME\":null,\"ORGNAME\":\"研发部\",\"CFNAME\":null,\"SBMJNAME\":null,\"SBLXNAME\":\"服务器\",\"SBZTNAME\":\"入库中\",\"SBYTNAME\":null,\"NETCONFIG\":[],\"YP\":[],\"WK\":[],\"NC\":[],\"CPU\":[],\"XK\":[],\"SB\":[],\"WS\":[],\"XSQ\":[]},{\"ZCID\":\"28aa27a6-866b-4f2f-9e6a-4abb1f835c92\",\"SBMC\":null,\"ZCH\":\"W00049\",\"CCBH\":null,\"AZWZ\":\"办公室\",\"HTH\":null,\"XLH\":null,\"SBXH\":\"IBM\",\"ZRRPERSONID\":\"199433\",\"SBSYRPERSONID\":\"199433;197365\",\"GMRQ\":\"2017-12-12\",\"EMPNAME\":\"李怡亮\",\"ORGNAME\":\"研发部\",\"CFNAME\":null,\"SBMJNAME\":\"内部\",\"SBLXNAME\":\"服务器\",\"SBZTNAME\":\"变更中\",\"SBYTNAME\":\"部门级服务器\",\"NETCONFIG\":[],\"YP\":[{\"YPSN\":\"TEST111111\",\"YPRL\":\"100G\",\"TIME\":\"2017-12-12\",\"YPJKLXNAME\":\"SAS\",\"SBWXNAME\":\"移除\"}],\"WK\":[{\"WKMAC\":\"ff-ff-ff-ff-ff-ff\",\"SBWXNAME\":\"移除\",\"TIME\":\"2017-12-12\"}],\"NC\":[{\"NCRL\":\"8G\",\"SBWXNAME\":\"移除\",\"NCLXNAME\":\"DDR4\",\"TIME\":\"2017-12-12\"}],\"CPU\":[{\"CPUXH\":\"E51236\",\"SBWXNAME\":\"移除\",\"TIME\":\"2017-12-12\"}],\"XK\":[{\"XKXH\":\"H6510\",\"XCRL\":null,\"SBWXNAME\":\"移除\",\"TIME\":\"2017-12-12\"}],\"SB\":[{\"ZJSN\":null,\"CZXTTIME\":\"2017-12-12\",\"CZXTBB\":null,\"CZXTNAME\":\"windows server2003\",\"GQLXNAME\":null}],\"WS\":[{\"EXTSBNAME\":\"无\",\"ASZCID\":null,\"SBWXNAME\":\"新安装\",\"TIME\":\"2017-12-12\"}],\"XSQ\":[]},{\"ZCID\":\"bccf9edd-e8de-4745-9903-64acfbb60567\",\"SBMC\":null,\"ZCH\":\"W00035\",\"CCBH\":null,\"AZWZ\":\"研发中心\",\"HTH\":null,\"XLH\":null,\"SBXH\":\"FWQ112\",\"ZRRPERSONID\":\"199433\",\"SBSYRPERSONID\":\"198554\",\"GMRQ\":\"2017-05-19\",\"EMPNAME\":\"李怡亮\",\"ORGNAME\":\"研发部\",\"CFNAME\":null,\"SBMJNAME\":null,\"SBLXNAME\":\"服务器\",\"SBZTNAME\":\"回归审批中\",\"SBYTNAME\":\"企业级服务器\",\"NETCONFIG\":[{\"IP\":\"192.168.0.0\",\"PORT\":null,\"JRWLNAME\":\"待接入\",\"WKMAC\":\"FW-QW-KM-AC-00-01\"}],\"YP\":[{\"YPSN\":\"YPSNFWQ001\",\"YPRL\":\"1T\",\"TIME\":\"2017-05-19\",\"YPJKLXNAME\":\"IDE\",\"SBWXNAME\":\"原配\"}],\"WK\":[{\"WKMAC\":\"FW-QW-KM-AC-00-01\",\"SBWXNAME\":\"原配\",\"TIME\":\"2017-05-19\"}],\"NC\":[{\"NCRL\":null,\"SBWXNAME\":\"原配\",\"NCLXNAME\":null,\"TIME\":\"2017-05-19\"}],\"CPU\":[{\"CPUXH\":null,\"SBWXNAME\":\"原配\",\"TIME\":\"2017-05-19\"}],\"XK\":[{\"XKXH\":null,\"XCRL\":null,\"SBWXNAME\":\"原配\",\"TIME\":\"2017-05-19\"}],\"SB\":[{\"ZJSN\":null,\"CZXTTIME\":\"2017-05-19\",\"CZXTBB\":null,\"CZXTNAME\":\"winxp12\",\"GQLXNAME\":\"只读\"}],\"WS\":[{\"EXTSBNAME\":null,\"ASZCID\":null,\"SBWXNAME\":\"新安装\",\"TIME\":\"2017-05-19\"}],\"XSQ\":[{\"XSQLBCODE\":null,\"XSQCCNAME\":null,\"SBWXNAME\":\"新安装\",\"TIME\":\"2017-05-19\"}]},{\"ZCID\":\"98179b27-9735-4e11-ba53-051a2b62a1ed\",\"SBMC\":null,\"ZCH\":\"W00034\",\"CCBH\":null,\"AZWZ\":null,\"HTH\":\"6666\",\"XLH\":null,\"SBXH\":\"FWQ112\",\"ZRRPERSONID\":null,\"SBSYRPERSONID\":null,\"GMRQ\":\"2017-05-19\",\"EMPNAME\":null,\"ORGNAME\":\"研发部\",\"CFNAME\":null,\"SBMJNAME\":null,\"SBLXNAME\":\"服务器\",\"SBZTNAME\":\"验机中\",\"SBYTNAME\":null,\"NETCONFIG\":[],\"YP\":[],\"WK\":[],\"NC\":[],\"CPU\":[],\"XK\":[],\"SB\":[],\"WS\":[],\"XSQ\":[]},{\"ZCID\":\"e2b4cd4b-bb66-492d-a83b-dc6daad8f190\",\"SBMC\":null,\"ZCH\":\"W00033\",\"CCBH\":null,\"AZWZ\":null,\"HTH\":\"6666\",\"XLH\":null,\"SBXH\":\"FWQ112\",\"ZRRPERSONID\":null,\"SBSYRPERSONID\":null,\"GMRQ\":\"2017-05-19\",\"EMPNAME\":null,\"ORGNAME\":\"研发部\",\"CFNAME\":null,\"SBMJNAME\":null,\"SBLXNAME\":\"服务器\",\"SBZTNAME\":\"验机中\",\"SBYTNAME\":null,\"NETCONFIG\":[],\"YP\":[],\"WK\":[],\"NC\":[],\"CPU\":[],\"XK\":[],\"SB\":[],\"WS\":[],\"XSQ\":[]},{\"ZCID\":\"8318bdc7-2c60-4439-a8ba-4b7cc6c6b5ff\",\"SBMC\":null,\"ZCH\":\"W00032\",\"CCBH\":null,\"AZWZ\":null,\"HTH\":null,\"XLH\":null,\"SBXH\":\"FWQ112\",\"ZRRPERSONID\":null,\"SBSYRPERSONID\":null,\"GMRQ\":\"2017-05-19\",\"EMPNAME\":null,\"ORGNAME\":\"研发部\",\"CFNAME\":null,\"SBMJNAME\":null,\"SBLXNAME\":\"服务器\",\"SBZTNAME\":\"已验机\",\"SBYTNAME\":null,\"NETCONFIG\":[],\"YP\":[{\"YPSN\":\"YPSNWFQ002\",\"YPRL\":\"2000\",\"TIME\":\"2017-05-19\",\"YPJKLXNAME\":\"SATA\",\"SBWXNAME\":\"原配\"}],\"WK\":[{\"WKMAC\":\"WF-QW-KM-AC-00-02\",\"SBWXNAME\":\"原配\",\"TIME\":\"2017-05-19\"}],\"NC\":[{\"NCRL\":null,\"SBWXNAME\":\"原配\",\"NCLXNAME\":null,\"TIME\":\"2017-05-19\"}],\"CPU\":[{\"CPUXH\":null,\"SBWXNAME\":\"原配\",\"TIME\":\"2017-05-19\"}],\"XK\":[{\"XKXH\":null,\"XCRL\":null,\"SBWXNAME\":\"原配\",\"TIME\":\"2017-05-19\"}],\"SB\":[{\"ZJSN\":null,\"CZXTTIME\":\"2017-05-19\",\"CZXTBB\":null,\"CZXTNAME\":\"windows10\",\"GQLXNAME\":\"无光驱\"}],\"WS\":[{\"EXTSBNAME\":null,\"ASZCID\":null,\"SBWXNAME\":\"新安装\",\"TIME\":\"2017-05-19\"}],\"XSQ\":[]},{\"ZCID\":\"196944d3-cf76-4b5a-9cbf-9a9ed3f7391f\",\"SBMC\":null,\"ZCH\":\"W00001\",\"CCBH\":null,\"AZWZ\":null,\"HTH\":null,\"XLH\":null,\"SBXH\":\"FWQ112\",\"ZRRPERSONID\":null,\"SBSYRPERSONID\":null,\"GMRQ\":\"2017-05-19\",\"EMPNAME\":null,\"ORGNAME\":\"研发部\",\"CFNAME\":null,\"SBMJNAME\":null,\"SBLXNAME\":\"服务器\",\"SBZTNAME\":\"入库中\",\"SBYTNAME\":null,\"NETCONFIG\":[],\"YP\":[],\"WK\":[],\"NC\":[],\"CPU\":[],\"XK\":[],\"SB\":[],\"WS\":[],\"XSQ\":[]},{\"ZCID\":\"55dd8b93-4f8e-4ffa-9fc4-8e1cbbfeab33\",\"SBMC\":null,\"ZCH\":\"730103-0004\",\"CCBH\":\"4556\",\"AZWZ\":\"5645\",\"HTH\":\"456\",\"XLH\":null,\"SBXH\":\"wer45\",\"ZRRPERSONID\":\"179001\",\"SBSYRPERSONID\":null,\"GMRQ\":\"2018-05-19\",\"EMPNAME\":\"南卫兵\",\"ORGNAME\":\"研发部\",\"CFNAME\":null,\"SBMJNAME\":\"秘密\",\"SBLXNAME\":\"服务器\",\"SBZTNAME\":\"回归审批中\",\"SBYTNAME\":\"应用服务器\",\"NETCONFIG\":[{\"IP\":\"452sdfsdf\",\"PORT\":\"465\",\"JRWLNAME\":\"金航网\",\"WKMAC\":\"af-dd-dd-dd-dd-dd\"},{\"IP\":\"46\",\"PORT\":\"465\",\"JRWLNAME\":\"金航网\",\"WKMAC\":\"fs-fg-gg-gg-gg-gg\"}],\"YP\":[{\"YPSN\":\"adfg\",\"YPRL\":null,\"TIME\":\"2018-05-29\",\"YPJKLXNAME\":null,\"SBWXNAME\":\"原配\"},{\"YPSN\":\"adfgsfg\",\"YPRL\":null,\"TIME\":\"2018-05-29\",\"YPJKLXNAME\":null,\"SBWXNAME\":\"原配\"}],\"WK\":[{\"WKMAC\":\"af-dd-dd-dd-dd-dd\",\"SBWXNAME\":\"原配\",\"TIME\":\"2018-05-29\"},{\"WKMAC\":\"fs-fg-gg-gg-gg-gg\",\"SBWXNAME\":\"原配\",\"TIME\":\"2018-05-29\"}],\"NC\":[{\"NCRL\":null,\"SBWXNAME\":\"原配\",\"NCLXNAME\":null,\"TIME\":\"2018-05-29\"}],\"CPU\":[{\"CPUXH\":null,\"SBWXNAME\":\"原配\",\"TIME\":\"2018-05-29\"}],\"XK\":[{\"XKXH\":null,\"XCRL\":null,\"SBWXNAME\":\"原配\",\"TIME\":\"2018-05-29\"}],\"SB\":[{\"ZJSN\":\"dsaf\",\"CZXTTIME\":\"2018-05-01\",\"CZXTBB\":null,\"CZXTNAME\":\"windows7（64位）\",\"GQLXNAME\":\"刻录\"}],\"WS\":[{\"EXTSBNAME\":null,\"ASZCID\":null,\"SBWXNAME\":\"新安装\",\"TIME\":\"2018-05-29\"}],\"XSQ\":[{\"XSQLBCODE\":null,\"XSQCCNAME\":null,\"SBWXNAME\":\"原配\",\"TIME\":\"2018-05-29\"}]}]}";
		JSONObject jsb = JSON.parseObject(jsonString);
		JSONArray jsonArray = jsb.getJSONArray("SBTZXX");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject assetPcJsb = jsonArray.getJSONObject(i);
			Pc pc = new Pc();
			pc.setAssetCode(assetPcJsb.getString("ZCH"));
			pc.setAssetName(assetPcJsb.getString("SBMC"));
			pc.setAssetType(assetPcJsb.getString("SBXH"));
			pc.setAssetDutyCode(assetPcJsb.getString("ZRRPERSONID"));
			pc.setAssetDutyName(assetPcJsb.getString("EMPNAME"));
			pc.setOrgCode(assetPcJsb.getString("ORGNAME"));
			pc.setOrgName(assetPcJsb.getString("ORGNAME"));
			pc.setStatus("0");
			pc.setCrtTime(new Date());
			pc.setCrtUser("system");
			pcMapper.insertSelective(pc);
		}

	}
}
