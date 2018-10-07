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
