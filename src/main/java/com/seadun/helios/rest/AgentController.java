package com.seadun.helios.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seadun.helios.entity.BaseUser;
import com.seadun.helios.mapper.BaseUserMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api")
@Slf4j
public class AgentController {
	
	@Autowired
	private BaseUserMapper baseUserMapper;
	
	@GetMapping(value = { "/test" })
	@ResponseBody
	public void clientData(HttpServletRequest req) {
		BaseUser baseUser = baseUserMapper.selectByPrimaryKey("1");
		log.debug(">>>>>测试数据,baseUserMapper:{}", baseUserMapper.selectByPrimaryKey("1"));
	}
}
