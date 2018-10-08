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
import com.seadun.helios.entity.DetectPcRelation;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.entity.VDetectPc;
import com.seadun.helios.mapper.DetectPcRelationMapper;
import com.seadun.helios.mapper.VDetectPcMapper;

@Service
public class DetectPcService {
	@Autowired
	private DetectPcRelationMapper detectPcRelationMapper;
	@Autowired
	private VDetectPcMapper vDetectPcMapper;

	@Transactional
	public PageInfo<VDetectPc> page(int pageNum, int pageSize, String assetCode, String detectCode, String portCode) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);

		List<VDetectPc> vDetectPcList = vDetectPcMapper.selectPage(rowBounds, assetCode, detectCode, portCode);
		PageInfo<VDetectPc> pageInfo = new PageInfo<VDetectPc>(vDetectPcList);// 封装分页信息，便于前端展示
		return pageInfo;
	}

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
		detectPcRelation.setStatus("0");
		detectPcRelation.setPcCode(pcCode);
		detectPcRelation.setPortId(portId);

		detectPcRelationMapper.insertSelective(detectPcRelation);
	}

	@Transactional
	public void deleteRelation(String id) {
		detectPcRelationMapper.deleteByPrimaryKey(id);
	}
}
