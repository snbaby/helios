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
import com.seadun.helios.entity.Detect;
import com.seadun.helios.entity.DetectPort;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.mapper.AlarmMapper;
import com.seadun.helios.mapper.DetectMapper;
import com.seadun.helios.mapper.DetectPcRelationMapper;
import com.seadun.helios.mapper.DetectPortMapper;

@Service
public class DetectService {
	@Autowired
	private DetectMapper detectMapper;
	@Autowired
	private DetectPortMapper detectPortMapper;
	@Autowired
	private DetectPcRelationMapper detectPcRelationMapper;
	@Autowired
	private AlarmMapper alarmMapper;

	@Transactional
	public PageInfo<Detect> page(int pageNum, int pageSize, String code, String name, String ip) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);

		List<Detect> detectList = detectMapper.selectPage(rowBounds, code, name, ip);
		PageInfo<Detect> pageInfo = new PageInfo<Detect>(detectList);// 封装分页信息，便于前端展示
		return pageInfo;
	}

	public void addDetect(String name, String code, String ip, String crtUser) {
		Detect oldDetect = detectMapper.selectByCode(code);
		if (oldDetect != null) {
			throw new HeliosException(HeliosExceptionConstants.DETECT_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.DETECT_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.DETECT_EXIST_EXCEPTION_HTTP_STATUS);
		}

		Detect oldIpDetect = detectMapper.selectByIp(ip);
		if (oldIpDetect != null) {
			throw new HeliosException(HeliosExceptionConstants.IP_EXIST_EXCEPTION_CODE,
					HeliosExceptionConstants.IP_EXIST_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.IP_EXIST_EXCEPTION_HTTP_STATUS);
		}

		String detectId = UUID.randomUUID().toString();

		Detect detect = new Detect();
		detect.setCode(code);
		detect.setCrtTime(new Date());
		detect.setCrtUser(crtUser);
		detect.setId(detectId);
		detect.setIp(ip);
		detect.setName(name);
		detect.setStatus("0");

		detectMapper.insertSelective(detect);

		for (short i = 1; i <= 16; i++) {
			DetectPort detectPort = new DetectPort();
			detectPort.setCode(code + "_U" + i);
			detectPort.setCrtTime(new Date());
			detectPort.setCrtUser(crtUser);
			detectPort.setDetectId(detectId);
			detectPort.setId(UUID.randomUUID().toString());
			detectPort.setName("U" + i);
			detectPort.setPort((short) (HeliosConstants.BASE_PORT + i));
			detectPort.setStatus("-1");

			detectPortMapper.insertSelective(detectPort);
		}

	}

	public void editDetect(String name, String id, String ip, String crtUser) {
		Detect detect = detectMapper.selectByPrimaryKey(id);
		detect.setName(name);
		detect.setIp(ip);
		detect.setUptTime(new Date());
		detect.setUptUser(crtUser);
		detectMapper.updateByPrimaryKey(detect);
	}

	@Transactional
	public void deleteDetect(String id) {
		detectMapper.deleteByPrimaryKey(id);

		List<DetectPort> detectPortList = detectPortMapper.list(id);
		detectPortList.forEach(detectPort -> {
			detectPcRelationMapper.deleteByPortId(detectPort.getId());
			alarmMapper.deleteByPortId(detectPort.getId());
			detectPortMapper.deleteByPrimaryKey(detectPort.getId());
		});
	}

	@Transactional
	public List<Detect> list() {
		return detectMapper.list();
	}
}
