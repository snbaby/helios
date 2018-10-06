package com.seadun.helios.rest;

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
import com.seadun.helios.service.LogService;

@Controller
@RequestMapping("/api/log")
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@PostMapping(value = { "/page" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page(@RequestBody JSONObject jsb) {
		int pageNum = jsb.getIntValue("pageNum");
		int pageSize = jsb.getIntValue("pageSize");
		String name = jsb.getString("name");
		String code = jsb.getString("code");

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				logService.page(pageNum, pageSize, name, code));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
