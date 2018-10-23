package com.seadun.helios.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.constant.HeliosConstants;
import com.seadun.helios.constant.HeliosExceptionConstants;
import com.seadun.helios.entity.DetectPcRelation;
import com.seadun.helios.entity.DetectPort;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.entity.Pc;
import com.seadun.helios.entity.PcRecord;
import com.seadun.helios.mapper.DetectPcRelationMapper;
import com.seadun.helios.mapper.DetectPortMapper;
import com.seadun.helios.mapper.PcMapper;
import com.seadun.helios.mapper.PcRecordMapper;

@Service
public class IemsService {
	@Autowired
	private DetectPcRelationMapper detectPcRelationMapper;
	@Autowired
	private PcRecordMapper pcRecordMapper;
	@Autowired
	private PcMapper pcMapper;
	@Autowired
	private DetectPortMapper detectPortMapper;

	@Transactional
	public void leave(JSONObject jsb) {
		DetectPcRelation detectPcRelation = detectPcRelationMapper.selectByPcCode(jsb.getString("ZCH"));
		if (detectPcRelation == null || !detectPcRelation.getStatus().equals("0")) {//正常
			throw new HeliosException(HeliosExceptionConstants.PC_STATUS_EXCEPTION_CODE,
					HeliosExceptionConstants.PC_STATUS_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.PC_STATUS_EXCEPTION_HTTP_STATUS);
		}
		
		detectPcRelation.setStatus(HeliosConstants.RELATION_LEAVEL);//2离开
		detectPcRelation.setUptTime(new Date());
		detectPcRelation.setUptUser("system");
		
		detectPcRelationMapper.updateByPrimaryKeySelective(detectPcRelation);
		
		Pc pc = pcMapper.selectByPrimaryKey(jsb.getString("ZCH"));
		pc.setAzwz(jsb.getString("AZWZ"));
		pc.setStatus(HeliosConstants.RELATION_LEAVEL);//2离开
		pc.setUptTime(new Date());
		pc.setUptUser("system");
		pcMapper.updateByPrimaryKeySelective(pc);
		
		DetectPort detectPort = detectPortMapper.selectByPrimaryKey(detectPcRelation.getPortId());
		detectPort.setStatus(HeliosConstants.RELATION_LEAVEL);
		detectPort.setUptName(new Date());
		detectPort.setUptUser("system");
		detectPortMapper.updateByPrimaryKey(detectPort);

		PcRecord pcRecord = new PcRecord();
		pcRecord.setId(jsb.getString("pId"));
		pcRecord.setApplyOrgCode(jsb.getString("SQRORGID"));
		pcRecord.setApplyOrgName(jsb.getString("SQRORGNAME"));
		pcRecord.setAssetApplyCode(jsb.getString("SQRID"));
		pcRecord.setAssetApplyName(jsb.getString("SQR"));
		pcRecord.setAssetCode(jsb.getString("ZCH"));
		pcRecord.setAssetDutyCode(jsb.getString("ZRRPERSONID"));
		pcRecord.setAssetDutyName(jsb.getString("EMPNAME"));
		pcRecord.setAssetName(jsb.getString("SBMC"));
		pcRecord.setAzwz(jsb.getString("AZWZ"));
		pcRecord.setCrtTime(new Date());
		pcRecord.setCrtUser("system");
		pcRecord.setDutyOrgCode(jsb.getString("ORGID"));
		pcRecord.setDutyOrgName(jsb.getString("ORGNAME"));
		pcRecord.setMessage(jsb.toJSONString());
		pcRecord.setReason(jsb.getString("Reason"));
		pcRecord.setStatus("leave");
		
		pcRecordMapper.insertSelective(pcRecord);

	}
	
	@Transactional
	public void reback(JSONObject jsb) {
		DetectPcRelation detectPcRelation = detectPcRelationMapper.selectByPcCode(jsb.getString("ZCH"));
		if (!detectPcRelation.getStatus().equals(HeliosConstants.RELATION_LEAVEL)) {//离开
			throw new HeliosException(HeliosExceptionConstants.PC_STATUS_EXCEPTION_CODE,
					HeliosExceptionConstants.PC_STATUS_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.PC_STATUS_EXCEPTION_HTTP_STATUS);
		}
		
		detectPcRelation.setStatus(HeliosConstants.RELATION_REBACK);//3返回
		detectPcRelation.setUptTime(new Date());
		detectPcRelation.setUptUser("system");
		
		detectPcRelationMapper.updateByPrimaryKeySelective(detectPcRelation);
		
		Pc pc = pcMapper.selectByPrimaryKey(jsb.getString("ZCH"));
		pc.setAzwz(jsb.getString("AZWZ"));
		pc.setStatus(HeliosConstants.RELATION_REBACK);//3返回
		pc.setUptTime(new Date());
		pc.setUptUser("system");
		pcMapper.updateByPrimaryKeySelective(pc);
		
		DetectPort detectPort = detectPortMapper.selectByPrimaryKey(detectPcRelation.getPortId());
		detectPort.setStatus(HeliosConstants.RELATION_REBACK);
		detectPort.setUptName(new Date());
		detectPort.setUptUser("system");
		detectPortMapper.updateByPrimaryKey(detectPort);

		PcRecord pcRecord = new PcRecord();
		pcRecord.setId(jsb.getString("pId"));
		pcRecord.setApplyOrgCode(jsb.getString("SQRORGID"));
		pcRecord.setApplyOrgName(jsb.getString("SQRORGNAME"));
		pcRecord.setAssetApplyCode(jsb.getString("SQRID"));
		pcRecord.setAssetApplyName(jsb.getString("SQR"));
		pcRecord.setAssetCode(jsb.getString("ZCH"));
		pcRecord.setAssetDutyCode(jsb.getString("ZRRPERSONID"));
		pcRecord.setAssetDutyName(jsb.getString("EMPNAME"));
		pcRecord.setAssetName(jsb.getString("SBMC"));
		pcRecord.setAzwz(jsb.getString("AZWZ"));
		pcRecord.setCrtTime(new Date());
		pcRecord.setCrtUser("system");
		pcRecord.setDutyOrgCode(jsb.getString("ORGID"));
		pcRecord.setDutyOrgName(jsb.getString("ORGNAME"));
		pcRecord.setMessage(jsb.toJSONString());
		pcRecord.setReason(jsb.getString("Reason"));
		pcRecord.setStatus("reback");
		
		pcRecordMapper.insertSelective(pcRecord);

	}
}
