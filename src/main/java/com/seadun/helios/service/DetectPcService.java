package com.seadun.helios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.seadun.helios.constant.HeliosConstants;
import com.seadun.helios.constant.HeliosExceptionConstants;
import com.seadun.helios.entity.DetectPcRelation;
import com.seadun.helios.entity.DetectPort;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.entity.Pc;
import com.seadun.helios.entity.VDetectPc;
import com.seadun.helios.mapper.DetectPcRelationMapper;
import com.seadun.helios.mapper.DetectPortMapper;
import com.seadun.helios.mapper.PcMapper;
import com.seadun.helios.mapper.VDetectPcMapper;

@Service
public class DetectPcService {
	@Autowired
	private DetectPcRelationMapper detectPcRelationMapper;
	@Autowired
	private VDetectPcMapper vDetectPcMapper;
	@Autowired
	private DetectPortMapper detectPortMapper;
	@Autowired
	private PcMapper pcMapper; 

	@Transactional
	public PageInfo<VDetectPc> page(int pageNum, int pageSize, String assetCode, String detectCode, String portCode) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);

		List<VDetectPc> vDetectPcList = vDetectPcMapper.selectPage(rowBounds, assetCode, detectCode, portCode);
		PageInfo<VDetectPc> pageInfo = new PageInfo<VDetectPc>(vDetectPcList);// 封装分页信息，便于前端展示
		return pageInfo;
	}

	@Transactional
	public void addRelation(String portId, String pcCode, String crtUser) {
		DetectPcRelation oldRelationPortId = detectPcRelationMapper.selectByPortId(portId);
		if (oldRelationPortId != null) {
			throw new HeliosException(HeliosExceptionConstants.RELATION_PORT_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.RELATION_PORT_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.RELATION_PORT_EXIST_EXCEPTION_HTTP_STATUS);
		}

		DetectPcRelation oldRelationPcCode = detectPcRelationMapper.selectByPcCode(pcCode);
		if (oldRelationPcCode != null) {
			throw new HeliosException(HeliosExceptionConstants.RELATION_PC_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.RELATION_PC_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.RELATION_PC_EXIST_EXCEPTION_HTTP_STATUS);
		}

		DetectPcRelation detectPcRelation = new DetectPcRelation();
		detectPcRelation.setCrtTime(new Date());
		detectPcRelation.setCrtUser(crtUser);
		detectPcRelation.setId(UUID.randomUUID().toString());
		detectPcRelation.setStatus(HeliosConstants.RELATION_NORMAL);
		detectPcRelation.setPcCode(pcCode);
		detectPcRelation.setPortId(portId);
		
		DetectPort detectPort = detectPortMapper.selectByPrimaryKey(portId);
		detectPort.setStatus(HeliosConstants.RELATION_NORMAL);
		detectPort.setUptName(new Date());
		detectPort.setUptUser(crtUser);
		detectPortMapper.updateByPrimaryKeySelective(detectPort);
		
		Pc pc = pcMapper.selectByPrimaryKey(pcCode);
		pc.setStatus(HeliosConstants.RELATION_NORMAL);
		pc.setUptTime(new Date());
		pc.setUptUser(crtUser);
		pcMapper.updateByPrimaryKeySelective(pc);

		detectPcRelationMapper.insertSelective(detectPcRelation);
	}

	@Transactional
	public void deleteRelation(String id,String userId) {
		DetectPcRelation detectPcRelation = detectPcRelationMapper.selectByPrimaryKey(id);
		DetectPort detectPort = detectPortMapper.selectByPrimaryKey(detectPcRelation.getPortId());
		detectPort.setStatus(HeliosConstants.RELATION_DEFAULT);
		detectPort.setUptUser(userId);
		detectPort.setUptName(new Date());
		detectPortMapper.updateByPrimaryKeySelective(detectPort);
		
		Pc pc = pcMapper.selectByPrimaryKey(detectPcRelation.getPcCode());
		pc.setStatus(HeliosConstants.RELATION_DEFAULT);
		pc.setUptTime(new Date());
		pc.setUptUser(userId);
		pcMapper.updateByPrimaryKeySelective(pc);
		
		detectPcRelationMapper.deleteByPrimaryKey(id);
	}
}
