package com.seadun.helios.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seadun.helios.constant.HeliosExceptionConstants;
import com.seadun.helios.entity.BaseUser;
import com.seadun.helios.exception.HeliosException;
import com.seadun.helios.response.ResponseSuccessResult;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@PostMapping(value = { "/login" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> clientData(@RequestBody BaseUser baseUser)
			throws ModbusTransportException, ErrorResponseException, ModbusInitException {
		
		if(StringUtils.isBlank(baseUser.getName())||StringUtils.isBlank(baseUser.getPassword())) {
			throw new HeliosException(HeliosExceptionConstants.PARAMETER_EXCEPTION_CODE,
					HeliosExceptionConstants.PARAMETER_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.PARAMETER_EXCEPTION_HTTP_STATUS);
		}
		
		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);

	}
}
