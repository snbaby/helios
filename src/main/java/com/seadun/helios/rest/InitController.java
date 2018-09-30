package com.seadun.helios.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.MenuService;

@Controller
@RequestMapping("/init")
public class InitController {
	@Autowired
	private MenuService menuService;
	
	@GetMapping(value = { "/menu" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page() {
		menuService.initMenus();
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
