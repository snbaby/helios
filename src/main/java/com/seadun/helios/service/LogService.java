package com.seadun.helios.service;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seadun.helios.entity.BaseLog;
import com.seadun.helios.mapper.BaseLogMapper;

@Service
public class LogService {
	@Autowired
	private BaseLogMapper baseLogMapper;
	
	public void addLog(HttpServletRequest request,String message) {
		BaseLog baseLog = new BaseLog();
		baseLog.setId(UUID.randomUUID().toString());
		baseLog.setCrtCode(request.getSession().getAttribute("code").toString());
		baseLog.setCrtName(request.getSession().getAttribute("name").toString());
		baseLog.setCrtTime(new Date());
		baseLog.setCrtUser(request.getSession().getAttribute("userId").toString());
		baseLog.setMessage(message);
		baseLogMapper.insertSelective(baseLog);
	}
	
}
