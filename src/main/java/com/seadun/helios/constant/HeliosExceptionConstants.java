package com.seadun.helios.constant;

import org.springframework.http.HttpStatus;

public class HeliosExceptionConstants {
	public final static String USER_VALID_FAILD_EXCEPTION_CODE =  "USER_VALID_FAILD_EXCEPTION";
	public final static String USER_VALID_FAILD_EXCEPTION_MESSAGE =  "用户名或密码错误.";
	public static final HttpStatus USER_VALID_FAILD_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public final static String USER_VALID_PASSWORD_FAILD_CODE =  "USER_VALID_PASSWORD_FAILD";
	public final static String USER_VALID_PASSWORD_FAILD_MESSAGE =  "原密码错误.";
	public static final HttpStatus USER_VALID_PASSWORD_FAILD_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String USER_NOT_LOGIN_EXCEPTION_CODE = "USER_NOT_LOGIN_EXCEPTION";
	public static final String USER_NOT_LOGIN_EXCEPTION_MESSAGE = "用户未登录";
	public static final HttpStatus USER_NOT_LOGIN_EXCEPTION_HTTP_STATUS = HttpStatus.UNAUTHORIZED;
	
	public static final String USER_EXIST_EXCEPTION_CODE = "USER_EXIST_EXCEPTION";
	public static final String USER_EXIST_EXCEPTION_MESSAGE = "用户已存在";
	public static final HttpStatus USER_EXIST_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String DETECT_EXIST_EXCEPTION_CODE = "DETECT_EXIST_EXCEPTION";
	public static final String DETECT_EXIST_EXCEPTION_MESSAGE = "侦测器已存在";
	public static final HttpStatus DETECT_EXIST_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String RELATION_PORT_EXIST_EXCEPTION_CODE = "RELATION_PORT_EXIST_EXCEPTION";
	public static final String RELATION_PORT_EXIST_EXCEPTION_MESSAGE = "端口已被关联，不可重复关联";
	public static final HttpStatus RELATION_PORT_EXIST_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String RELATION_PC_EXIST_EXCEPTION_CODE = "RELATION_PC_EXIST_EXCEPTION";
	public static final String RELATION_PC_EXIST_EXCEPTION_MESSAGE = "主机已被关联，不可重复关联";
	public static final HttpStatus RELATION_PC_EXIST_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String DETECT_PORT_CODE_EXIST_EXCEPTION_CODE = "DETECT_PORT_CODE_EXIST_EXCEPTION";
	public static final String DETECT_PORT_CODE_EXIST_EXCEPTION_MESSAGE = "侦测器端口编码已存在";
	public static final HttpStatus DETECT_PORT_CODE_EXIST_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String DETECT_PORT_PORT_EXIST_EXCEPTION_CODE = "DETECT_PORT_PORT_EXIST_EXCEPTION";
	public static final String DETECT_PORT_PORT_EXIST_EXCEPTION_MESSAGE = "侦测器端口已存在";
	public static final HttpStatus DETECT_PORT_PORT_EXIST_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String IP_EXIST_EXCEPTION_CODE = "IP_EXIST_EXCEPTION";
	public static final String IP_EXIST_EXCEPTION_MESSAGE = "IP已存在";
	public static final HttpStatus IP_EXIST_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	public static final String ROLE_EXIST_EXCEPTION_CODE = "ROLE_EXIST_EXCEPTION";
	public static final String ROLE_EXIST_EXCEPTION_MESSAGE = "角色已存在";
	public static final HttpStatus ROLE_EXIST_EXCEPTION_HTTP_STATUS = HttpStatus.FORBIDDEN;
	
	// 系统内部错误
	public static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL_SERVER_ERROR";
	public static final String INTERNAL_SERVER_ERROR_MESSAGE = "系统内部异常";
	public static final HttpStatus INTERNAL_SERVER_ERROR_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
	
	// 参数不合法
	public static final String PARAMETER_EXCEPTION_CODE = "PARAMETER_EXCEPTION";
	public static final String PARAMETER_EXCEPTION_MESSAGE = "参数校验失败";
	public static final HttpStatus PARAMETER_EXCEPTION_HTTP_STATUS = HttpStatus.BAD_REQUEST;
	
	
}
