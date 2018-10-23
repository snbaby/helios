package com.seadun.helios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.seadun.helios.constant.HeliosExceptionConstants;
import com.seadun.helios.entity.DetectPort;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.mapper.AlarmMapper;
import com.seadun.helios.mapper.DetectPcRelationMapper;
import com.seadun.helios.mapper.DetectPortMapper;

@Service
public class PortService {
	@Autowired
	private DetectPortMapper detectPortMapper;
	@Autowired
	private DetectPcRelationMapper detectPcRelationMapper;
	@Autowired
	private AlarmMapper alarmMapper;
	
	@Transactional
	public PageInfo<DetectPort> page(int pageNum,int pageSize,String code,String name,String detectId) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		
		List<DetectPort> detectPortList = detectPortMapper.selectPage(rowBounds,code,name,detectId);
		PageInfo<DetectPort> pageInfo = new PageInfo<DetectPort>(detectPortList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
	
	public void addPort(String name, String code, String detectId,short port, String crtUser) {
		DetectPort oldPort = detectPortMapper.selectByCode(code);
		if (oldPort != null) {
			throw new HeliosException(HeliosExceptionConstants.DETECT_PORT_CODE_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.DETECT_PORT_CODE_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.DETECT_PORT_CODE_EXIST_EXCEPTION_HTTP_STATUS);
		}
		
		DetectPort oldDetectIdPort = detectPortMapper.selectByDetectIdAndPort(detectId,port);
		if (oldDetectIdPort != null) {
			throw new HeliosException(HeliosExceptionConstants.DETECT_PORT_PORT_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.DETECT_PORT_PORT_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.DETECT_PORT_PORT_EXIST_EXCEPTION_HTTP_STATUS);
		}
		
		DetectPort detectPort = new DetectPort();
		detectPort.setCode(code);
		detectPort.setCrtTime(new Date());
		detectPort.setCrtUser(crtUser);
		detectPort.setDetectId(detectId);
		detectPort.setId(UUID.randomUUID().toString());
		detectPort.setName(name);
		detectPort.setPort(port);
		detectPort.setStatus("0");
		
		detectPortMapper.insertSelective(detectPort);
	}
	
	@Transactional
	public void deletePort(String id) {
		detectPortMapper.deleteByPrimaryKey(id);
		
		detectPcRelationMapper.deleteByPortId(id);
		alarmMapper.deleteByPortId(id);
		detectPortMapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public List<DetectPort> list(String detectId) {
		return detectPortMapper.list(detectId);
	}
	
	@Transactional
	public List<DetectPort> useableList(String detectId) {
		return detectPortMapper.useableList(detectId);
	}
}
