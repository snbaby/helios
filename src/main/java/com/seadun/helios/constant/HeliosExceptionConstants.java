package com.seadun.helios.constant;

import org.springframework.http.HttpStatus;

public class HeliosExceptionConstants {
	public final static String USER_VALID_FAILD_CODE =  "USER_VALID_FAILD";
	public final static String USER_VALID_FAILD_MESSAGE =  "用户名或密码错误.";
	public static final HttpStatus USER_VALID_FAILD_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public final static String USER_VALID_PASSWORD_FAILD_CODE =  "USER_VALID_PASSWORD_FAILD";
	public final static String USER_VALID_PASSWORD_FAILD_MESSAGE =  "原密码错误.";
	public static final HttpStatus USER_VALID_PASSWORD_FAILD_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String USER_NOT_LOGIN_EXCEPTION_CODE = "USER_NOT_LOGIN_EXCEPTION";
	public static final String USER_NOT_LOGIN_EXCEPTION_MESSAGE = "用户未登录";
	public static final HttpStatus USER_NOT_LOGIN_EXCEPTION_HTTP_STATUS = HttpStatus.UNAUTHORIZED;
	
	// 系统内部错误
	public static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL_SERVER_ERROR";
	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "系统内部异常";
	public static final HttpStatus INTERNAL_SERVER_ERROR_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
	
	// 参数不合法
		public static final String PARAMETER_EXCEPTION_CODE = "PARAMETER_EXCEPTION";
		public static final String PARAMETER_EXCEPTION_MESSAGE = "参数校验失败";
		public static final HttpStatus PARAMETER_EXCEPTION_HTTP_STATUS = HttpStatus.BAD_REQUEST;
	
	
}
