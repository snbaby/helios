package com.seadun.helios.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seadun.helios.entity.PageParam;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.RoleService;

@Controller
@RequestMapping("/api/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@PostMapping(value = { "/page" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> page(@RequestBody PageParam pageParam) {

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",
				roleService.page(pageParam.getPageNum(), pageParam.getPageSize()));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
}
