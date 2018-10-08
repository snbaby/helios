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
import com.seadun.helios.service.DetectPcService;
import com.seadun.helios.service.LogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/detect-pc")
@Slf4j
public class DetectPcController {
	@Autowired
	private DetectPcService detectPcService;
	@Autowired
	private LogService logService;
	
	@PostMapping(value = { "/add" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> add(HttpServletRequest request,@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		String portId = jsb.getString("portId");
		String pcCode = jsb.getString("assetCode");

		detectPcService.addRelation(portId, pcCode,request.getSession().getAttribute("userId").toString());
		
		logService.addLog(request, "添加pc与侦测器端口关联："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@DeleteMapping(value = { "/del" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> del(HttpServletRequest request,@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		String id = jsb.getString("id");

		detectPcService.deleteRelation(id);
		logService.addLog(request, "删除pc与侦测器端口关联："+jsb.toJSONString());
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/page" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page(@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		int pageNum = jsb.getIntValue("pageNum");
		int pageSize = jsb.getIntValue("pageSize");
		String assetCode = jsb.getString("assetCode");
		String detectCode = jsb.getString("detectCode");
		String portCode = jsb.getString("portCode");

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				detectPcService.page(pageNum, pageSize, assetCode, detectCode, portCode));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
}
