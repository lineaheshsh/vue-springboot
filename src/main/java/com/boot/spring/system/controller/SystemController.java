package com.boot.spring.system.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.system.service.SystemService;
import com.google.gson.Gson;

@Controller
public class SystemController {
	
	@Resource(name = "systemService")
	SystemService systemService;

	@RequestMapping(value="/showDisk")
	@ResponseBody
	public String showDisk() {
		
		Map result = new HashMap<String, Object>();
		Gson gson = new Gson();
		
		return gson.toJson(systemService.showSystemInfo());
	}
}
