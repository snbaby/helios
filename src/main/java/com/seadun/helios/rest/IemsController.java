package com.seadun.helios.rest;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seadun.helios.entity.BaseLog;
import com.seadun.helios.mapper.BaseLogMapper;
import com.seadun.helios.response.ResponseSuccessResult;
import com.seadun.helios.service.IemsService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/iems/asset-pc")
@Slf4j
public class IemsController {

	@Autowired
	private BaseLogMapper baseLogMapper;
	@Autowired
	private IemsService iemsService;

	@PostMapping(value = { "init" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> assetPc(@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		BaseLog baseLog = new BaseLog();
		baseLog.setId(UUID.randomUUID().toString());
		baseLog.setCrtCode("system");
		baseLog.setCrtName("system");
		baseLog.setCrtTime(new Date());
		baseLog.setCrtUser("system");
		baseLog.setMessage("init:"+jsb.toJSONString());
		baseLogMapper.insertSelective(baseLog);

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/leave" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> leave(@RequestBody JSONObject jsb) {
		String jsonString = "{\"ORGID\":\"010005\",\"ZRRPERSONID\":\"179001\",\"AZWZ\":\"5645\",\"SBMC\":\"wer45\",\"SQRORGNAME\":\"研发部\",\"ORGNAME\":\"研发部\",\"pId\":\"10564\",\"SQRORGID\":\"010001\",\"EMPNAME\":\"南卫兵\",\"Reason\":\"sdf\",\"SQR\":\"胡税涛\",\"SQRID\":\"197051\",\"items\":[{\"actDefID\":\"manualActivity\",\"actInstID\":\"73608\",\"content\":\"ok\",\"extendAttrs\":{\"pId\":\"10564\",\"isOK\":\"true\",\"SPJL\":\"ok\",\"ENDTIME\":\"2018-10-10 09:38:57\",\"STARTTIME\":\"2018-10-10 09:38:37\",\"CREATETIME\":\"2018-10-10 09:38:37\",\"WorkItemName\":\"服务器管理员审批\",\"loginUserName\":\"胡税涛\",\"SQRID\":\"197051\",\"TLSC\":\"用时0天0小时0分20秒\",\"loginUserID\":\"197051\"},\"from\":\"197051\",\"index\":\"0\",\"operateType\":\"true\",\"time\":\"20181010093857\",\"workItemID\":\"27544\"},{\"actDefID\":\"manualActivity\",\"actInstID\":\"73608\",\"content\":\"ok\",\"extendAttrs\":{\"pId\":\"10564\",\"isOK\":\"true\",\"SPJL\":\"ok\",\"ENDTIME\":\"2018-10-10 09:38:57\",\"STARTTIME\":\"2018-10-10 09:38:37\",\"CREATETIME\":\"2018-10-10 09:38:37\",\"WorkItemName\":\"服务器管理员审批\",\"loginUserName\":\"胡税涛\",\"SQRID\":\"197051\",\"TLSC\":\"用时0天0小时0分20秒\",\"loginUserID\":\"197051\"},\"from\":\"197051\",\"index\":\"1\",\"operateType\":\"true\",\"time\":\"20181010094039\",\"workItemID\":\"27544\"},{\"actDefID\":\"manualActivity1\",\"actInstID\":\"73609\",\"content\":\"\",\"extendAttrs\":{\"pId\":\"10564\",\"isOK\":\"true\",\"SPJL\":\"\",\"ENDTIME\":\"2018-10-13 12:10:38\",\"STARTTIME\":\"2018-10-10 09:38:57\",\"CREATETIME\":\"2018-10-10 09:38:57\",\"WorkItemName\":\"申请部门领导审批\",\"loginUserName\":\"李怡亮\",\"SQRID\":\"197051\",\"TLSC\":\"用时3天2小时31分41秒\",\"loginUserID\":\"199433\"},\"from\":\"199433\",\"index\":\"0\",\"operateType\":\"true\",\"time\":\"20181013121038\",\"workItemID\":\"27545\"},{\"actDefID\":\"manualActivity2\",\"actInstID\":\"73670\",\"content\":\"\",\"extendAttrs\":{\"pId\":\"10564\",\"isOK\":\"true\",\"SPJL\":\"\",\"WorkItemName\":\"保密办审批\",\"loginUserName\":\"李怡亮\",\"SQRID\":\"197051\",\"loginUserID\":\"199433\"},\"from\":\"199433\",\"index\":\"0\",\"operateType\":\"true\",\"time\":\"20181013121111\",\"workItemID\":\"27585\"}],\"ZCH\":\"W00035\"}";
		jsb = JSON.parseObject(jsonString);
		
		iemsService.leave(jsb);
		log.debug("jsb:{}", jsb);
		BaseLog baseLog = new BaseLog();
		baseLog.setId(UUID.randomUUID().toString());
		baseLog.setCrtCode("system");
		baseLog.setCrtName("system");
		baseLog.setCrtTime(new Date());
		baseLog.setCrtUser("system");
		baseLog.setMessage("leave:"+jsb.toJSONString());
		baseLogMapper.insertSelective(baseLog);

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	@PostMapping(value = { "/reback" })
	@ResponseBody
	public ResponseEntity<ResponseSuccessResult> reback(@RequestBody JSONObject jsb) {
		log.debug("jsb:{}", jsb);
		String jsonString = "{\"ORGID\":\"010005\",\"ZRRPERSONID\":\"179001\",\"AZWZ\":\"5645中心\",\"SBMC\":\"wer45\",\"SQRORGNAME\":\"研发部\",\"ORGNAME\":\"研发部\",\"pId\":\"10563\",\"SQRORGID\":\"010001\",\"EMPNAME\":\"南卫兵\",\"Reason\":\"3\",\"SQR\":\"胡税涛\",\"SQRID\":\"197051\",\"items\":[{\"actDefID\":\"manualActivity\",\"actInstID\":\"73606\",\"content\":\"\",\"extendAttrs\":{\"pId\":\"10563\",\"isOK\":\"true\",\"SPJL\":\"\",\"ENDTIME\":\"2018-10-13 09:53:04\",\"STARTTIME\":\"2018-10-10 09:32:21\",\"CREATETIME\":\"2018-10-10 09:32:21\",\"WorkItemName\":\"服务器管理员审批\",\"loginUserName\":\"胡税涛\",\"SQRID\":\"197051\",\"TLSC\":\"用时3天0小时20分43秒\",\"loginUserID\":\"197051\"},\"from\":\"197051\",\"index\":\"0\",\"operateType\":\"true\",\"time\":\"20181013095304\",\"workItemID\":\"27543\"},{\"actDefID\":\"manualActivity1\",\"actInstID\":\"73624\",\"content\":\"同意\",\"extendAttrs\":{\"pId\":\"10563\",\"isOK\":\"true\",\"SPJL\":\"同意\",\"ENDTIME\":\"2018-10-13 09:54:00\",\"STARTTIME\":\"2018-10-13 09:53:04\",\"CREATETIME\":\"2018-10-13 09:53:04\",\"WorkItemName\":\"申请部门领导审批\",\"loginUserName\":\"李怡亮\",\"SQRID\":\"197051\",\"TLSC\":\"用时0天0小时0分56秒\",\"loginUserID\":\"199433\"},\"from\":\"199433\",\"index\":\"0\",\"operateType\":\"true\",\"time\":\"20181013095359\",\"workItemID\":\"27563\"},{\"actDefID\":\"manualActivity1\",\"actInstID\":\"73624\",\"content\":\"同意\",\"extendAttrs\":{\"pId\":\"10563\",\"isOK\":\"true\",\"SPJL\":\"同意\",\"ENDTIME\":\"2018-10-13 09:54:00\",\"STARTTIME\":\"2018-10-13 09:53:04\",\"CREATETIME\":\"2018-10-13 09:53:04\",\"WorkItemName\":\"申请部门领导审批\",\"loginUserName\":\"李怡亮\",\"SQRID\":\"197051\",\"TLSC\":\"用时0天0小时0分56秒\",\"loginUserID\":\"199433\"},\"from\":\"199433\",\"index\":\"1\",\"operateType\":\"true\",\"time\":\"20181013095531\",\"workItemID\":\"27563\"},{\"actDefID\":\"manualActivity2\",\"actInstID\":\"73625\",\"content\":\"\",\"extendAttrs\":{\"pId\":\"10563\",\"isOK\":\"true\",\"SPJL\":\"\",\"WorkItemName\":\"保密办审批\",\"loginUserName\":\"胡税涛\",\"SQRID\":\"197051\",\"loginUserID\":\"197051\"},\"from\":\"197051\",\"index\":\"0\",\"operateType\":\"true\",\"time\":\"20181013120848\",\"workItemID\":\"27564\"}],\"ZCH\":\"W00035\"}";
		jsb = JSON.parseObject(jsonString);
		iemsService.reback(jsb);
		BaseLog baseLog = new BaseLog();
		baseLog.setId(UUID.randomUUID().toString());
		baseLog.setCrtCode("system");
		baseLog.setCrtName("system");
		baseLog.setCrtTime(new Date());
		baseLog.setCrtUser("system");
		baseLog.setMessage("reback:"+jsb.toJSONString());
		baseLogMapper.insertSelective(baseLog);

		ResponseSuccessResult responseResult = new ResponseSuccessResult(HttpStatus.OK.value(), "success");
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}
	
	

}
