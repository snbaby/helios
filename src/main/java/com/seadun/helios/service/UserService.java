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
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.mapper.BaseUserMapper;

@Service
public class UserService {
	@Autowired
	private BaseUserMapper baseUserMapper;

	@Transactional
	public void addUser(String name, String code, String password, String crtUser) {
		BaseUser oldBaseUser = baseUserMapper.selectByCode(code);
		if (oldBaseUser != null) {
			throw new HeliosException(HeliosExceptionConstants.USER_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.USER_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.USER_EXIST_EXCEPTION_HTTP_STATUS);
		}
		BaseUser baseUser = new BaseUser();
		baseUser.setId(UUID.randomUUID().toString());
		baseUser.setName(name);
		baseUser.setCode(code);
		baseUser.setPassword(DigestUtils.md5Hex(password));
		baseUser.setCrtUser(crtUser);
		baseUser.setCrtTime(new Date());
		baseUserMapper.insertSelective(baseUser);
	}
	
	@Transactional
	public PageInfo<BaseUser> page(int pageNum,int pageSize) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		
		List<BaseUser> userList = baseUserMapper.selectPage(rowBounds);
		PageInfo<BaseUser> pageInfo = new PageInfo<BaseUser>(userList);// 封装分页信息，便于前端展示
		return pageInfo;
	}
}
