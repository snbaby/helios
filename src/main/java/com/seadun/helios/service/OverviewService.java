package com.seadun.helios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.entity.Detect;
import com.seadun.helios.entity.DetectPort;
import com.seadun.helios.entity.VDetectPc;
import com.seadun.helios.mapper.DetectMapper;
import com.seadun.helios.mapper.DetectPortMapper;
import com.seadun.helios.mapper.VDetectPcMapper;

@Service
public class OverviewService {
	@Autowired
	private DetectMapper detectMapper;
	@Autowired
	private VDetectPcMapper vDetectPcMapper;
	@Autowired
	private DetectPortMapper detectPortMapper;

	@Transactional
	public JSONObject info() {
		JSONObject result = new JSONObject();
		List<Detect> detectList = detectMapper.list();
		List<VDetectPc> vDetectPcList = vDetectPcMapper.list();
		List<DetectPort> detectPortList = detectPortMapper.list("");

		int normal = 0;
		int abNormal = 0;
		int leave = 0;
		int reback = 0;
		JSONArray resultJsonArray = new JSONArray();
		for (int i = 0; i < vDetectPcList.size(); i++) {
			if (vDetectPcList.get(i).getStatus().equals("0")) {
				normal++;
			} else if (vDetectPcList.get(i).getStatus().equals("1")) {
				abNormal++;
			} else if (vDetectPcList.get(i).getStatus().equals("2")) {
				leave++;
			} else {
				reback++;
			}
		}
		detectList.forEach(detect -> {
			JSONObject detectJsb = JSON.parseObject(JSON.toJSONString(detect));
			JSONArray detectPcJsa = new JSONArray();
			vDetectPcList.forEach(vDetectPc -> {
				if (vDetectPc.getDetectId().equals(detect.getId())) {
					detectPcJsa.add(JSON.parseObject(JSON.toJSONString(vDetectPc)));
				}
			});
			
			JSONArray detectPortJsa = new JSONArray();
			detectPortList.forEach(detectPort->{
				if(detectPort.getDetectId().equals(detect.getId())) {
					detectPortJsa.add(JSON.parseObject(JSON.toJSONString(detectPort)));
				}
			});
			
			detectJsb.put("detectPcList", detectPcJsa);
			detectJsb.put("detectPortList", detectPortJsa);
			resultJsonArray.add(detectJsb);
		});
		result.put("normal", normal);
		result.put("abNormal", abNormal);
		result.put("leave", leave);
		result.put("reback", reback);
		result.put("list", resultJsonArray);
		return result;
	}
}
