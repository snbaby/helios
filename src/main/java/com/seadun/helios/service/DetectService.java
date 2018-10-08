package com.seadun.helios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.seadun.helios.constant.HeliosExceptionConstants;
import com.seadun.helios.entity.BaseUser;
import com.seadun.helios.entity.Detect;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.mapper.DetectMapper;

@Service
public class DetectService {
	@Autowired
	private DetectMapper detectMapper;
	
	@Transactional
	public PageInfo<Detect> page(int pageNum,int pageSize,String code,String name,String ip) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		
		List<Detect> detectList = detectMapper.selectPage(rowBounds,code,name,ip);
		PageInfo<Detect> pageInfo = new PageInfo<Detect>(detectList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
	
	public void addDetect(String name, String code, String ip, String crtUser) {
		Detect oldDetect = detectMapper.selectByCode(code);
		if (oldDetect != null) {
			throw new HeliosException(HeliosExceptionConstants.DETECT_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.DETECT_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.DETECT_EXIST_EXCEPTION_HTTP_STATUS);
		}
		
		Detect oldIpDetect = detectMapper.selectByIp(ip);
		if (oldIpDetect != null) {
			throw new HeliosException(HeliosExceptionConstants.IP_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.IP_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.IP_EXIST_EXCEPTION_HTTP_STATUS);
		}
		
		Detect detect = new Detect();
		detect.setCode(code);
		detect.setCrtTime(new Date());
		detect.setCrtUser(crtUser);
		detect.setId(UUID.randomUUID().toString());
		detect.setIp(ip);
		detect.setName(name);
		detect.setStatus("0");
		
		detectMapper.insertSelective(detect);
	}
	
	@Transactional
	public void deleteDetect(String id) {
		detectMapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public List<Detect> list() {
		return detectMapper.list();
	}
}
