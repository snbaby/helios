package com.seadun.helios.rest;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.entity.BaseLog;
import com.seadun.helios.mapper.BaseLogMapper;
import com.seadun.helios.response.ResponseSuccessResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/iems/asset-pc")
@Slf4j
public class IemsController {

	@Autowired
	private BaseLogMapper baseLogMapper;

	@PostMapping(value = { "init" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> assetPc(@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		BaseLog baseLog = new BaseLog();
		baseLog.setId(UUID.randomUUID().toString());
		baseLog.setCrtCode("system");
		baseLog.setCrtName("system");
		baseLog.setCrtTime(new Date());
		baseLog.setCrtUser("system");
		baseLog.setMessage("init:"+jsb.toJSONString());
		baseLogMapper.insertSelective(baseLog);

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/leave" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> leave(@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		BaseLog baseLog = new BaseLog();
		baseLog.setId(UUID.randomUUID().toString());
		baseLog.setCrtCode("system");
		baseLog.setCrtName("system");
		baseLog.setCrtTime(new Date());
		baseLog.setCrtUser("system");
		baseLog.setMessage("leave:"+jsb.toJSONString());
		baseLogMapper.insertSelective(baseLog);

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/reback" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> reback(@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		BaseLog baseLog = new BaseLog();
		baseLog.setId(UUID.randomUUID().toString());
		baseLog.setCrtCode("system");
		baseLog.setCrtName("system");
		baseLog.setCrtTime(new Date());
		baseLog.setCrtUser("system");
		baseLog.setMessage("reback:"+jsb.toJSONString());
		baseLogMapper.insertSelective(baseLog);

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	

}
