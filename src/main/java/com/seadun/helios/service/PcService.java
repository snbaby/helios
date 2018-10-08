package com.seadun.helios.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.seadun.helios.entity.Pc;
import com.seadun.helios.mapper.PcMapper;

@Service
public class PcService {
	@Autowired
	private PcMapper pcMapper;
	
	@Transactional
	public PageInfo<Pc> page(int pageNum,int pageSize,String assetCode,String assetType) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		
		List<Pc> pcList = pcMapper.selectPage(rowBounds,assetCode,assetCode);
		PageInfo<Pc> pageInfo = new PageInfo<Pc>(pcList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
	
	@Transactional
	public List<Pc> list() {
		return pcMapper.list();
	}
	
}
