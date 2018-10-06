package com.seadun.helios.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.LogService;
import com.seadun.helios.service.MenuResourceService;

@Controller
@RequestMapping("/api/menu-resource")
public class MenuResourceController {

	@Autowired
	private MenuResourceService menuResourceService;
	@Autowired
	private LogService logService;
	
	@PostMapping(value = { "/add" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> add(HttpServletRequest request,@RequestBody JSONObject jsb) {
		String roleId = jsb.getString("roleId");
		String menuId = jsb.getString("menuId");
		
		menuResourceService.add(roleId, menuId,request.getSession().getAttribute("userId").toString());
		
		logService.addLog(request, "角色赋菜单权限："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@DeleteMapping(value = { "/del" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> del(HttpServletRequest request,@RequestBody JSONObject jsb) {
		String roleId = jsb.getString("roleId");
		String menuId = jsb.getString("menuId");
		menuResourceService.del(roleId, menuId);
		
		logService.addLog(request, "角色删除菜单权限："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
