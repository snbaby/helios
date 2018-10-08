package com.seadun.helios.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.seadun.helios.service.LogService;
import com.seadun.helios.service.PortService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/port")
@Slf4j
public class PortController {
	@Autowired
	private PortService portService;
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
		String detectId = jsb.getString("detectId");

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				portService.page(pageNum, pageSize, code, name, detectId));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}

	@PostMapping(value = { "/add" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> add(HttpServletRequest request, @RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		String name = jsb.getString("name");
		String code = jsb.getString("code");
		String detectId = jsb.getString("detectId");
		short port = jsb.getShortValue("port");

		portService.addPort(name, code, detectId, port, request.getSession().getAttribute("userId").toString());

		logService.addLog(request, "添加侦测器端口：" + jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@DeleteMapping(value = { "/del" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> del(HttpServletRequest request,@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		String id = jsb.getString("id");

		portService.deletePort(id);
		logService.addLog(request, "删除端口："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/list" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> list(String detectId) {
		if(StringUtils.isBlank(detectId)) {
			detectId = "";
		}
		
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				portService.list(detectId));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
