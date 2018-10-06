package com.seadun.helios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.seadun.helios.entity.BaseLog;
import com.seadun.helios.entity.BaseUser;
import com.seadun.helios.mapper.BaseLogMapper;

@Service
public class LogService {
	@Autowired
	private BaseLogMapper baseLogMapper;
	
	@Transactional
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
	
	@Transactional
	public PageInfo<BaseLog> page(int pageNum,int pageSize,String name,String code) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		
		List<BaseLog> logList = baseLogMapper.selectPage(rowBounds,name,code);
		PageInfo<BaseLog> pageInfo = new PageInfo<BaseLog>(logList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
	
}
