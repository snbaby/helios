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
import com.seadun.helios.entity.Alarm;
import com.seadun.helios.entity.BaseLog;
import com.seadun.helios.entity.DetectPcRelation;
import com.seadun.helios.entity.DetectPort;
import com.seadun.helios.entity.HeliosException;
import com.seadun.helios.entity.Pc;
import com.seadun.helios.entity.VAlarm;
import com.seadun.helios.mapper.AlarmMapper;
import com.seadun.helios.mapper.BaseLogMapper;
import com.seadun.helios.mapper.DetectPcRelationMapper;
import com.seadun.helios.mapper.DetectPortMapper;
import com.seadun.helios.mapper.PcMapper;
import com.seadun.helios.mapper.VAlarmMapper;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;

@Service
public class AlarmService {
	@Autowired
	private VAlarmMapper vAlarmMapper;
	@Autowired
	private BaseLogMapper baseLogMapper;
	@Autowired
	private DetectPcRelationMapper detectPcRelationMapper;
	@Autowired
	private PcMapper pcMapper;
	@Autowired
	private AlarmMapper alarmMapper;
	@Autowired
	private DetectPortMapper detectPortMapper;

	@Transactional
	public PageInfo<VAlarm> page(int pageNum, int pageSize, String detectId, String assetCode) {
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);

		List<VAlarm> vAlarmList = vAlarmMapper.selectPage(rowBounds, detectId, assetCode);
		PageInfo<VAlarm> pageInfo = new PageInfo<VAlarm>(vAlarmList);// 封装分页信息，便于前端展示
		return pageInfo;
	}

	@Transactional
	public void fixed(String alarmId, String message, String userId) {
		VAlarm vAlarm = vAlarmMapper.selectByAlarmId(alarmId);

		IpParameters params = new IpParameters();
		params.setHost(vAlarm.getDetectIp());
		params.setPort(vAlarm.getPortPort());
		params.setEncapsulated(true);

		ModbusMaster master = new ModbusFactory().createTcpMaster(params, true);// TCP
		master.setTimeout(1000);
		Short num = 0;// 探测标志1正常
		try {
			master.init();
			BaseLocator<Number> loc = BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_BCD);
			num = master.getValue(loc).shortValue();
		} catch (ModbusInitException | ModbusTransportException | ErrorResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BaseLog baseLog = new BaseLog();
			baseLog.setId(UUID.randomUUID().toString());
			baseLog.setCrtCode("exception");
			baseLog.setCrtName("exception");
			baseLog.setCrtTime(new Date());
			baseLog.setCrtUser("exception");
			baseLog.setMessage("人工修復异常:" + e.getMessage());
			baseLogMapper.insertSelective(baseLog);
		} finally {
			master.destroy();
		}

		if (!num.equals(HeliosConstants.DETECT_NORM)) {// 异常
			throw new HeliosException(HeliosExceptionConstants.FIXED_EXCEPTION_CODE,
					HeliosExceptionConstants.FIXED_EXCEPTION_MESSAGE,
					HeliosExceptionConstants.FIXED_EXCEPTION_HTTP_STATUS);
		} else {
			DetectPcRelation detectPcRelation = detectPcRelationMapper.selectByPcCode(vAlarm.getAssetCode());
			detectPcRelation.setStatus(HeliosConstants.RELATION_NORMAL);
			detectPcRelation.setUptTime(new Date());
			detectPcRelation.setUptUser(userId);
			detectPcRelationMapper.updateByPrimaryKeySelective(detectPcRelation);

			Pc pc = pcMapper.selectByPrimaryKey(vAlarm.getAssetCode());
			pc.setStatus(HeliosConstants.RELATION_NORMAL);
			pc.setUptTime(new Date());
			pc.setUptUser(userId);
			pcMapper.updateByPrimaryKeySelective(pc);
			
			DetectPort detectPort = detectPortMapper.selectByCode(vAlarm.getPortCode());
			detectPort.setStatus(HeliosConstants.RELATION_NORMAL);
			detectPort.setUptName(new Date());
			detectPort.setUptUser(userId);
			detectPortMapper.updateByPrimaryKeySelective(detectPort);

			Alarm alarm = alarmMapper.selectByPrimaryKey(alarmId);
			alarm.setMessage(message);
			alarm.setStatus(HeliosConstants.RELATION_NORMAL);
			alarm.setUptTime(new Date());
			alarm.setUptUser(userId);

			alarmMapper.updateByPrimaryKeySelective(alarm);
		}
	}
}
