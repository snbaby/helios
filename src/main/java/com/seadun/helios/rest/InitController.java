package com.seadun.helios.rest;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seadun.helios.entity.BaseLog;
import com.seadun.helios.mapper.BaseLogMapper;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.MenuService;

@Controller
@RequestMapping("/init")
public class InitController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private BaseLogMapper baseLogMapper;
	
	@GetMapping(value = { "/menu" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page() {
		menuService.initMenus();
		
		BaseLog baseLog = new BaseLog();
		baseLog.setId(UUID.randomUUID().toString());
		baseLog.setCrtCode("system");
		baseLog.setCrtName("system");
		baseLog.setCrtTime(new Date());
		baseLog.setCrtUser("system");
		baseLog.setMessage("初始化菜单");
		baseLogMapper.insertSelective(baseLog);
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
