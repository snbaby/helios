package com.seadun.helios.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seadun.helios.service.AgentService;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;

@Controller
@RequestMapping("/api")
public class AgentController {
	@Autowired
	private AgentService agentService;
	
	@Scheduled(initialDelay=10000,fixedDelay = 5000)
	public void detect() {
		System.out.println("------start");
		agentService.detect();
		System.out.println("------end");
	}
	
	public static void main(String[] args) throws ModbusInitException, ModbusTransportException, ErrorResponseException {
		IpParameters params = new IpParameters();
		params.setHost("192.168.2.249");
		params.setPort(7011);
		params.setEncapsulated(true);

		ModbusMaster master = new ModbusFactory().createTcpMaster(params, true);// TCP
		master.setTimeout(10000);
		master.init();
		BaseLocator<Number> loc = BaseLocator.holdingRegister(1, 0, DataType.FOUR_BYTE_BCD);
		Number num = master.getValue(loc);
		System.out.println(num);
	}
}
