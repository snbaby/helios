package com.seadun.helios.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seadun.helios.service.AgentService;

@Controller
@RequestMapping("/api")
public class AgentController {
	@Autowired
	private AgentService agentService;
	
	@Scheduled(initialDelay=5000,fixedDelay = 5000)
	public void detect() {
		agentService.detect();
	}
}
