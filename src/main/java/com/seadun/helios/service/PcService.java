package com.seadun.helios.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.seadun.helios.constant.HeliosConstants;
import com.seadun.helios.entity.DetectPcRelation;
import com.seadun.helios.entity.DetectPort;
import com.seadun.helios.entity.Pc;
import com.seadun.helios.mapper.DetectPcRelationMapper;
import com.seadun.helios.mapper.DetectPortMapper;
import com.seadun.helios.mapper.PcMapper;

@Service
public class PcService {
	@Autowired
	private PcMapper pcMapper;
	@Autowired
	private DetectPcRelationMapper detectPcRelationMapper;
	@Autowired
	private DetectPortMapper detectPortMapper;
	
	
	@Transactional
	public PageInfo<Pc> page(int pageNum,int pageSize,String assetCode) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		
		List<Pc> pcList = pcMapper.selectPage(rowBounds,assetCode);
		PageInfo<Pc> pageInfo = new PageInfo<Pc>(pcList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
	
	@Transactional
	public List<Pc> list() {
		return pcMapper.list();
	}
	
	@Transactional
	public List<Pc> useableLit() {
		return pcMapper.useableLit();
	}
	
	@Transactional
	public void rebackConfirm(String assetCode,String userId) {
		Pc pc = pcMapper.selectByPrimaryKey(assetCode);
		pc.setStatus(HeliosConstants.RELATION_NORMAL);//
		pc.setUptTime(new Date());
		pc.setUptUser(userId);
		pcMapper.updateByPrimaryKeySelective(pc);
		
		DetectPcRelation detectPcRelation = detectPcRelationMapper.selectByPcCode(assetCode);
		if(detectPcRelation == null) {
			return;
		}
		detectPcRelation.setStatus(HeliosConstants.RELATION_NORMAL);//正常
		detectPcRelation.setUptTime(new Date());
		detectPcRelation.setUptUser(userId);
		detectPcRelationMapper.updateByPrimaryKeySelective(detectPcRelation);
		
		DetectPort detectPort = detectPortMapper.selectByPrimaryKey(detectPcRelation.getPortId());
		detectPort.setStatus(HeliosConstants.RELATION_NORMAL);
		detectPort.setUptName(new Date());
		detectPort.setUptUser(userId);
		detectPortMapper.updateByPrimaryKey(detectPort);
	}
}
