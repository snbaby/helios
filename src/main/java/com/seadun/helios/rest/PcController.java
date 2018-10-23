package com.seadun.helios.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.LogService;
import com.seadun.helios.service.PcService;

@Controller
@RequestMapping("/api/pc")
public class PcController {
	@Autowired
	private PcService pcService;
	@Autowired
	private LogService logService;

	@PostMapping(value = { "/page" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page(@RequestBody JSONObject jsb) {

		int pageNum = jsb.getIntValue("pageNum");
		int pageSize = jsb.getIntValue("pageSize");
		String assetCode = jsb.getString("assetCode");

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				pcService.page(pageNum, pageSize, assetCode));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/list" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> list() {
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				pcService.list());
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/list/useable" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> useableLit() {
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				pcService.useableLit());
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/reback-confirm" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> rebackConfirm(@RequestBody JSONObject jsb,HttpServletRequest request) {
		String assetCode = jsb.getString("assetCode");
		pcService.rebackConfirm(assetCode,request.getSession().getAttribute("userId").toString());
		
		logService.addLog(request, "确认返回："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
