package com.seadun.helios.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.constant.HeliosExceptionConstants;
import com.seadun.helios.entity.BaseUser;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.entity.VAuthMenuTree;
import com.seadun.helios.mapper.BaseUserMapper;
import com.seadun.helios.mapper.VAuthMenuMapper;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.LogService;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private BaseUserMapper baseUserMapper;
	@Autowired
	private VAuthMenuMapper vAuthMenuMapper;
	@Autowired
	private LogService logService;

	@PostMapping(value = { "/login" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> clientData(HttpServletRequest request,@RequestBody BaseUser baseUserParam)
			throws ModbusTransportException, ErrorResponseException, ModbusInitException {

		if (StringUtils.isBlank(baseUserParam.getCode()) || StringUtils.isBlank(baseUserParam.getPassword())) {
			throw new HeliosException(HeliosExceptionConstants.PARAMETER_EXCEPTION_CODE,
					HeliosExceptionConstants.PARAMETER_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.PARAMETER_EXCEPTION_HTTP_STATUS);
		}

		BaseUser baseUser = baseUserMapper.selectUser(baseUserParam.getCode(), baseUserParam.getPassword());

		if (baseUser == null) {
			throw new HeliosException(HeliosExceptionConstants.USER_VALID_FAILD_EXCEPTION_CODE,
					HeliosExceptionConstants.USER_VALID_FAILD_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.USER_VALID_FAILD_EXCEPTION_HTTP_STATUS);
		}
		JSONObject jsb = new JSONObject();
		jsb.put("name", baseUser.getName());
		jsb.put("code", baseUser.getCode());
		jsb.put("userId", baseUser.getId());
		jsb.put("authMenuTree", vAuthMenuMapper.selectTree(baseUser.getId()));
		jsb.put("authMenu", vAuthMenuMapper.select(baseUser.getId()));
		
		request.getSession().setAttribute("isLogin", "yes");// 登录成功
		request.getSession().setAttribute("name", baseUser.getName());// 登录成功
		request.getSession().setAttribute("code", baseUser.getCode());// 登录成功
		request.getSession().setAttribute("userId", baseUser.getId());// 登录成功
		request.getSession().setMaxInactiveInterval(30*60);
		
		logService.addLog(request, "登录成功");
		
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success",jsb);
		return new ResponseEntity<>(responseResult, HttpStatus.OK);

	}
}
