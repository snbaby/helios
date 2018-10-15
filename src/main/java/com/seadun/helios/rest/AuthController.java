package com.seadun.helios.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.seadun.helios.mapper.BaseUserMapper;
import com.seadun.helios.mapper.VAuthMenuMapper;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.LogService;

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
	public ResponseEntity<ResponseSuccessResult> login(HttpServletRequest request,@RequestBody BaseUser baseUserParam) {

		if (StringUtils.isBlank(baseUserParam.getCode()) || StringUtils.isBlank(baseUserParam.getPassword())) {
			throw new HeliosException(HeliosExceptionConstants.PARAMETER_EXCEPTION_CODE,
					HeliosExceptionConstants.PARAMETER_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.PARAMETER_EXCEPTION_HTTP_STATUS);
		}

		BaseUser baseUser = baseUserMapper.selectUser(baseUserParam.getCode(), DigestUtils.md5Hex(baseUserParam.getPassword()));

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
	
	@PostMapping(value = { "/logout" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> logout(HttpServletRequest request) {
		logService.addLog(request, "登出成功");
		request.getSession().invalidate();
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);

	}
	
	@PostMapping(value = { "/update-password" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> login(HttpServletRequest request,@RequestBody JSONObject jsb) {
		String oldPassword = jsb.getString("oldPassword");
		String newPassword = jsb.getString("newPassword");
		
		BaseUser baseUser = baseUserMapper.selectUser(request.getSession().getAttribute("code").toString(), DigestUtils.md5Hex(oldPassword));
		if (baseUser == null) {
			throw new HeliosException(HeliosExceptionConstants.USER_VALID_FAILD_EXCEPTION_CODE,
					HeliosExceptionConstants.USER_VALID_FAILD_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.USER_VALID_FAILD_EXCEPTION_HTTP_STATUS);
		}

		baseUser.setPassword(DigestUtils.md5Hex(newPassword));
		baseUserMapper.updateByPrimaryKeySelective(baseUser);
		
		logService.addLog(request, "修改密码成功："+jsb.toJSONString());
		request.getSession().invalidate();
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);

	}
}
