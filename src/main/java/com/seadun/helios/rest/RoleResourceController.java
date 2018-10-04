package com.seadun.helios.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.RoleResourceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/role-resource")
@Slf4j
public class RoleResourceController {

	@Autowired
	private RoleResourceService roleResourceService;

	@PostMapping(value = { "/list" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> list(@RequestBody JSONObject jsb) {
		String roleId = jsb.getString("roleId");
		String userId = jsb.getString("userId");
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				roleResourceService.selectNode(userId, roleId));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}

	@PostMapping(value = { "/add" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> add(@RequestBody JSONObject jsb) {
		String roleId = jsb.getString("roleId");
		List<String> users = jsb.getJSONArray("users").toJavaList(String.class);
		roleResourceService.add(roleId, users);
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/del" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> del(@RequestBody JSONObject jsb) {
		String roleId = jsb.getString("roleId");
		List<String> users = jsb.getJSONArray("users").toJavaList(String.class);
		roleResourceService.del(roleId, users);
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
