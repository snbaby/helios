package com.seadun.helios.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.seadun.helios.entity.VAlarm;
import com.seadun.helios.mapper.VAlarmMapper;

@Service
public class AlarmService {
	@Autowired
	private VAlarmMapper vAlarmMapper;
	
	@Transactional
	public PageInfo<VAlarm> page(int pageNum,int pageSize,String detectId,String assetCode) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		
		List<VAlarm> vAlarmList = vAlarmMapper.selectPage(rowBounds,detectId,assetCode);
		PageInfo<VAlarm> pageInfo = new PageInfo<VAlarm>(vAlarmList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
}
