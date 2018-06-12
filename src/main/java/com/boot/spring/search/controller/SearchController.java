package com.boot.spring.search.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.search.service.SearchService;
import com.boot.spring.search.vo.ParameterVO;
import com.boot.spring.search.vo.RestResultVO;
import com.google.gson.Gson;

@Controller
public class SearchController {

	@Resource(name="searchService")
	SearchService searchService;
	
	@RequestMapping(value="/crawler", method=RequestMethod.GET)
	public String crawler() {
		System.out.println("Crawler Page -> Go");
		return "index";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public String search(@RequestBody String searchJSON) {
		System.out.println("[search] START");
		
		System.out.println("search : " + searchJSON);
		Gson gson = new Gson();
		RestResultVO resultVO = null;
		
		try {
			ParameterVO paramVO = searchService.setParameter(searchJSON);
			resultVO = searchService.getComments(paramVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gson.toJson(resultVO);
	}
	
	@RequestMapping(value="/getCrawlingData", method=RequestMethod.GET)
	@ResponseBody
	public String getCrawlingData() {
		
		System.out.println("[getCrawlingData] START");
		Gson gson = new Gson();
		RestResultVO resultVO = null;
		try {
			resultVO = searchService.getCommentGroupBy();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return gson.toJson(resultVO);
	}
	
}
