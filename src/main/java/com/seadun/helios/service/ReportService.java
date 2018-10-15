package com.seadun.helios.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.seadun.helios.entity.PcRecord;
import com.seadun.helios.entity.VPc;
import com.seadun.helios.mapper.PcRecordMapper;
import com.seadun.helios.mapper.VPcMapper;

@Service
public class ReportService {
	@Autowired
	private VPcMapper vPcMapper;
	@Autowired
	public PcRecordMapper pcRecordMapper;

	@Transactional
	public PageInfo<VPc> alarmPage(int pageNum, int pageSize, String detectId, String assetCode) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);

		List<VPc> vPcList = vPcMapper.selectPage(rowBounds, detectId, assetCode);
		PageInfo<VPc> pageInfo = new PageInfo<VPc>(vPcList);// 封装分页信息，便于前端展示
		return pageInfo;
	}

	@Transactional
	public PageInfo<PcRecord> recordPage(int pageNum, int pageSize, String id, String assetCode, String status) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);

		List<PcRecord> pcRecordList = pcRecordMapper.selectPage(rowBounds, id, assetCode, status);
		PageInfo<PcRecord> pageInfo = new PageInfo<PcRecord>(pcRecordList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
}
