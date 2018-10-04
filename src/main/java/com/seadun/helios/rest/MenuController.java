package com.seadun.helios.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.MenuService;

@Controller
@RequestMapping("/api/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;

	@GetMapping(value = { "/tree" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> tree() {

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				menuService.getMenuTree());
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/auth-tree" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> authTree(@RequestParam String roleId) {
		
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				menuService.getAuthTree(roleId));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
