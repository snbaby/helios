package com.seadun.helios.rest;

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
import com.seadun.helios.service.MenuResourceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/menu-resource")
@Slf4j
public class MenuResourceController {

	@Autowired
	private MenuResourceService menuResourceService;
	
	@PostMapping(value = { "/add" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> add(@RequestBody JSONObject jsb) {
		String roleId = jsb.getString("roleId");
		String menuId = jsb.getString("menuId");
		
		menuResourceService.add(roleId, menuId);
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@DeleteMapping(value = { "/del" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> del(@RequestBody JSONObject jsb) {
		String roleId = jsb.getString("roleId");
		String menuId = jsb.getString("menuId");
		menuResourceService.del(roleId, menuId);
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
