package com.seadun.helios.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.OverviewService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/overview")
@Slf4j
public class OverviewController {
	@Autowired
	private OverviewService overviewService;

	@GetMapping(value = { "/info" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> info() {
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",overviewService.info());
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
