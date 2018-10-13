package com.seadun.helios.rest;

import javax.servlet.http.HttpServletRequest;

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
import com.seadun.helios.service.AlarmService;
import com.seadun.helios.service.LogService;

@Controller
@RequestMapping("/api/alarm")
public class AlarmController {
	@Autowired
	public AlarmService alarmService;
	@Autowired
	public LogService logService;
	
	@PostMapping(value = { "/page" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page(@RequestBody JSONObject jsb) {
		int pageNum = jsb.getIntValue("pageNum");
		int pageSize = jsb.getIntValue("pageSize");
		String assetCode = jsb.getString("assetCode");
		String detectId = jsb.getString("detectId");

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				alarmService.page(pageNum, pageSize, detectId, assetCode));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/fixed" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> add(HttpServletRequest request,@RequestBody JSONObject jsb) {
		String alarmId = jsb.getString("alarmId");
		String message = jsb.getString("message");
		
		alarmService.fixed(alarmId, message, request.getSession().getAttribute("userId").toString());
		
		
		logService.addLog(request, "修复异常："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
