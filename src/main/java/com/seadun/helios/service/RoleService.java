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
import com.seadun.helios.entity.BaseRole;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.mapper.BaseRoleMapper;

@Service
public class RoleService {
	@Autowired
	private BaseRoleMapper baseRoleMapper;

	@Transactional
	public void addRole(String name, String code, String crtUser) {
		BaseRole oldBaseRole = baseRoleMapper.selectByCode(code);
		if (oldBaseRole != null) {
			throw new HeliosException(HeliosExceptionConstants.ROLE_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.ROLE_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.ROLE_EXIST_EXCEPTION_HTTP_STATUS);
		}
		BaseRole baseRole = new BaseRole();
		baseRole.setId(UUID.randomUUID().toString());
		baseRole.setName(name);
		baseRole.setCode(code);
		baseRole.setCrtUser(crtUser);
		baseRole.setCrtTime(new Date());
		baseRoleMapper.insertSelective(baseRole);
	}
	
	@Transactional
	public void deleteRole(String id) {
		baseRoleMapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public PageInfo<BaseRole> page(int pageNum,int pageSize,String name,String code) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		
		List<BaseRole> roleList = baseRoleMapper.selectPage(rowBounds,name,code);
		PageInfo<BaseRole> pageInfo = new PageInfo<BaseRole>(roleList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
	
	@Transactional
	public List<BaseRole> listRole() {
		return baseRoleMapper.list();
	}
}
