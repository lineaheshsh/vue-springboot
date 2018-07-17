package com.boot.spring.search.dao;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boot.spring.search.common.CommonUtil;
import com.boot.spring.search.common.DCUtil;
import com.boot.spring.search.module.RestModule;
import com.boot.spring.search.vo.ParameterVO;
import com.boot.spring.search.vo.RestResultVO;
import com.boot.spring.search.vo.SearchVO;

@Component
public class SearchDAO {
	
	
private static final Logger logger = LoggerFactory.getLogger(SearchDAO.class);
	
	/** 엔진 공통 유틸 */
	@Resource(name = "dcUtil")
	private DCUtil dcUtil;
	
	/** common util Setting */
	@Resource(name = "commonUtil")
	private CommonUtil commonUtil;
	
	/** REST 모듈 */
	@Resource(name = "restModule")
	private RestModule restModule;
		
//	/** EgovPropertyService */
//	@Resource(name = "konanPropertiesService")
//	protected EgovPropertyService konanPropertiesService;	

	public RestResultVO getCommentGroupBy(int limit) throws Exception {
		SearchVO searchVO = new SearchVO();		
		// 쿼리 생성
		StringBuffer query = new StringBuffer();
		StringBuffer sbLog = new StringBuffer();
		
		searchVO.setUrl("http://localhost:7577/search");
		searchVO.setCharset("UTF8");
		searchVO.setFields("*");
		searchVO.setFrom("naverComment.naverComment");
//		searchVO.setHilightTxt(konanPropertiesService.getString("boardHilight"));
		searchVO.setQuery(URLEncoder.encode("group by writer order by count(*) desc", "UTF-8"));
		searchVO.setLogInfo(URLEncoder.encode(sbLog.toString()));
	
		// URL 생성
		String restUrl = dcUtil.getRestURL(searchVO, limit);
		logger.debug("RESTURL board : " + restUrl);
		
		RestResultVO restVO = new RestResultVO();
		boolean success = restModule.restGroupBy(restUrl, restVO, "writer,count(*)");
		
		
		//초기화
//		query.charAt(0);
//		sbLog.charAt(0);
		
		if(!success) 
			return null;
				
		return restVO;		
	}
	
	public RestResultVO getIssueWordGroupBy(int limit) throws Exception {
		SearchVO searchVO = new SearchVO();		
		// 쿼리 생성
		StringBuffer query = new StringBuffer();
		StringBuffer sbLog = new StringBuffer();
	
		searchVO.setUrl("http://localhost:7577/search");
		searchVO.setCharset("UTF8");
		searchVO.setFields("*");
		searchVO.setFrom("naverComment.naverComment");
//		searchVO.setHilightTxt(konanPropertiesService.getString("boardHilight"));
		searchVO.setQuery(URLEncoder.encode("group by issue_word order by count(*) desc", "UTF-8"));
		searchVO.setLogInfo(URLEncoder.encode(sbLog.toString()));
	
		// URL 생성
		String restUrl = dcUtil.getRestURL(searchVO, limit);
		logger.debug("RESTURL board : " + restUrl);
		
		RestResultVO restVO = new RestResultVO();
		boolean success = restModule.restGroupBy(restUrl, restVO, "issue_word,count(*)");
		
		if(!success) 
			return null;
				
		return restVO;		
	}
	
	public RestResultVO getComments(ParameterVO paramVO) throws Exception {
		SearchVO searchVO = new SearchVO();		
		// 쿼리 생성
		StringBuffer query = new StringBuffer();
		StringBuffer sbLog = new StringBuffer();
		String strNmFd = "text_idx";
		
		// 쿼리 부분
		if ( !paramVO.getKwd().isEmpty() || !paramVO.getKwd().equals("") ) {
			query = dcUtil.makeQuery( strNmFd , paramVO.getKwd(), "allword", query, "AND");	
		}
		
		searchVO.setUrl("http://localhost:7577/search");
		searchVO.setCharset("UTF8");
		searchVO.setFields("*");
		searchVO.setFrom("naverComment.naverComment");
		searchVO.setQuery(URLEncoder.encode(query.toString(), "UTF-8"));
		
			
		// URL 생성
		String restUrl = dcUtil.getRestURL(paramVO, searchVO);
		
		RestResultVO restVO = new RestResultVO();
		boolean success = restModule.restSearch(restUrl, restVO, searchVO.getFields());
		
		
		if(!success) 
			return null;
				
		return restVO;		
	}
}
