package com.seadun.helios.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.DetectService;
import com.seadun.helios.service.LogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/detect")
@Slf4j
public class DetectController {
	@Autowired
	private DetectService detectService;
	@Autowired
	private LogService logService;

	@PostMapping(value = { "/page" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page(@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		int pageNum = jsb.getIntValue("pageNum");
		int pageSize = jsb.getIntValue("pageSize");
		String name = jsb.getString("name");
		String code = jsb.getString("code");
		String ip = jsb.getString("ip");

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				detectService.page(pageNum, pageSize, code, name, ip));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/add" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> add(HttpServletRequest request,@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		String name = jsb.getString("name");
		String code = jsb.getString("code");
		String ip = jsb.getString("ip");

		detectService.addDetect(name, code, ip,request.getSession().getAttribute("userId").toString());
		
		logService.addLog(request, "添加侦测器："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@DeleteMapping(value = { "/del" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> del(HttpServletRequest request,@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		String id = jsb.getString("id");

		detectService.deleteDetect(id);
		logService.addLog(request, "删除侦测器："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/list" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> list() {
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				detectService.list());
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
