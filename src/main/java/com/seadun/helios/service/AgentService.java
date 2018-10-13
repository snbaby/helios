package com.seadun.helios.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seadun.helios.constant.HeliosConstants;
import com.seadun.helios.entity.Alarm;
import com.seadun.helios.entity.BaseLog;
import com.seadun.helios.entity.DetectPcRelation;
import com.seadun.helios.entity.Pc;
import com.seadun.helios.entity.VDetectPc;
import com.seadun.helios.mapper.AlarmMapper;
import com.seadun.helios.mapper.BaseLogMapper;
import com.seadun.helios.mapper.DetectPcRelationMapper;
import com.seadun.helios.mapper.PcMapper;
import com.seadun.helios.mapper.VDetectPcMapper;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;

@Service
public class AgentService {
	@Autowired
	private VDetectPcMapper vDetectPcMapper;
	@Autowired
	private BaseLogMapper baseLogMapper;
	@Autowired
	private DetectPcRelationMapper detectPcRelationMapper;
	@Autowired
	private PcMapper pcMapper;
	@Autowired
	private AlarmMapper alarmMapper;

	@Transactional
	public void detect() {
		List<VDetectPc> normal_vDetectPcs = vDetectPcMapper.selectByStatus(HeliosConstants.RELATION_NORMAL);// 检测正常的链接
		normal_vDetectPcs.forEach(normal_vDetectPc -> {
			IpParameters params = new IpParameters();
			params.setHost(normal_vDetectPc.getDetectIp());
			params.setPort(normal_vDetectPc.getPortPort());
			params.setEncapsulated(true);

			ModbusMaster master = new ModbusFactory().createTcpMaster(params, true);// TCP
			master.setTimeout(1000);
			Short num = 0;//探测标志1正常
			try {
				master.init();
				BaseLocator<Number> loc = BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_BCD);
				num = master.getValue(loc).shortValue();
				System.out.println("num:"+num+";id="+normal_vDetectPc.getAssetCode());
			} catch (ModbusInitException | ModbusTransportException | ErrorResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("num:"+num+";id="+normal_vDetectPc.getAssetCode());
				BaseLog baseLog = new BaseLog();
				baseLog.setId(UUID.randomUUID().toString());
				baseLog.setCrtCode("exception");
				baseLog.setCrtName("exception");
				baseLog.setCrtTime(new Date());
				baseLog.setCrtUser("exception");
				baseLog.setMessage(e.getMessage());
				baseLogMapper.insertSelective(baseLog);
			} finally {
				master.destroy();
			}
			DetectPcRelation detectPcRelation = detectPcRelationMapper.selectByPrimaryKey(normal_vDetectPc.getId());
			Pc pc = pcMapper.selectByPrimaryKey(normal_vDetectPc.getAssetCode());
			if(!num.equals(HeliosConstants.DETECT_NORM)) {
				detectPcRelation.setStatus(HeliosConstants.RELATION_ABNORMAL);
				detectPcRelation.setUptTime(new Date());
				detectPcRelation.setUptUser("system");
				detectPcRelationMapper.updateByPrimaryKeySelective(detectPcRelation);
				
				pc.setStatus("1");
				pc.setUptTime(new Date());
				pc.setUptUser("system");
				pcMapper.updateByPrimaryKeySelective(pc);
				
				Alarm alarm = new Alarm();
				alarm.setCrtTime(new Date());
				alarm.setCrtUser("system");
				alarm.setId(UUID.randomUUID().toString());
				alarm.setMessage("");
				alarm.setPcCode(normal_vDetectPc.getAssetCode());
				alarm.setPortId(normal_vDetectPc.getPortId());
				alarm.setStatus(HeliosConstants.RELATION_ABNORMAL);
				alarmMapper.insertSelective(alarm);
			}else {
				detectPcRelation.setStatus(HeliosConstants.RELATION_NORMAL);
				detectPcRelation.setUptTime(new Date());
				detectPcRelation.setUptUser("system");
				detectPcRelationMapper.updateByPrimaryKeySelective(detectPcRelation);
			}
		});
	}

}
