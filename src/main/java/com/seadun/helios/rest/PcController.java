package com.seadun.helios.rest;

import org.apache.commons.lang3.StringUtils;
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
import com.seadun.helios.service.PcService;

@Controller
@RequestMapping("/api/pc")
public class PcController {
	@Autowired
	private PcService pcService;

	@PostMapping(value = { "/page" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page(@RequestBody JSONObject jsb) {

		int pageNum = jsb.getIntValue("pageNum");
		int pageSize = jsb.getIntValue("pageSize");
		String assetCode = jsb.getString("assetCode");
		String assetType = jsb.getString("assetType");

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				pcService.page(pageNum, pageSize, assetCode, assetType));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/list" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> list() {
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				pcService.list());
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
