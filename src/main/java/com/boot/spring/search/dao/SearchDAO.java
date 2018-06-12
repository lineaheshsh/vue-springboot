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

	public RestResultVO getCommentGroupBy() throws Exception {
		SearchVO searchVO = new SearchVO();		
		// 쿼리 생성
		StringBuffer query = new StringBuffer();
		StringBuffer sbLog = new StringBuffer();
//		String strNmFd = paramVO.getFields().isEmpty()? "text_idx": paramVO.getFields();

		// 쿼리 부분
//		query = dcUtil.makeQuery( strNmFd , paramVO.getKwd(), "allword", query, "AND");	
		//결과내재검색
//		if( paramVO.getReSrchFlag() ){
//			int preCnt = paramVO.getPreviousQueries().length;
//			query.append(" AND  ")
//					.append(dcUtil.makePreQuery(strNmFd , paramVO.getKwd(), paramVO.getPreviousQueries(), preCnt ,"allword") );		        
//		}


		/**
		 * 날짜검색기간 조회
		 * 기간/일/월/년도, 구간검색으로 조회시 자바스크립트에서 시작날짜와 종료날짜 조회하여 전달함.
		 */
//		if (!paramVO.getStartDate().isEmpty() && !paramVO.getEndDate().isEmpty()){
//			query = dcUtil.makeRangeQuery("regdate", paramVO.getStartDate().replace(".", "")+"000000", paramVO.getEndDate().replace(".", "")+"999999", query) ;
//		}
		
		//정렬조건	
//		if ("d".equals(paramVO.getSort())){
//			query.append(" order by regdate desc");
//		}
		
		//로그기록 
		//SITE@인물+$|첫검색|1|정확도순^코난	
//		sbLog.append(  dcUtil.getLogInfo(commonUtil.null2Str(paramVO.getSiteNm(),"SITE"),
//				"게시판" ,
//				commonUtil.null2Str( paramVO.getUserId(),""), paramVO.getKwd(),
//				paramVO.getPageNum(),
//				false,
//				paramVO.getSiteNm(),
//				commonUtil.null2Str( paramVO.getRecKwd(),"" )) );
	
		searchVO.setUrl("http://localhost:7577/search");
		searchVO.setCharset("UTF8");
		searchVO.setFields("*");
		searchVO.setFrom("naverComment.naverComment");
//		searchVO.setHilightTxt(konanPropertiesService.getString("boardHilight"));
		searchVO.setQuery(URLEncoder.encode("group by writer order by count(*) desc", "UTF-8"));
		searchVO.setLogInfo(URLEncoder.encode(sbLog.toString()));
	
		// URL 생성
		String restUrl = dcUtil.getRestURL(searchVO);
		logger.debug("RESTURL board : " + restUrl);
		
		RestResultVO restVO = new RestResultVO();
		boolean success = restModule.restSearch(restUrl, restVO, searchVO.getFields());
		
		
		//초기화
//		query.charAt(0);
//		sbLog.charAt(0);
		
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
		if ( !paramVO.getKwd().isEmpty() || paramVO.getKwd() != null) {
			query = dcUtil.makeQuery( strNmFd , paramVO.getKwd(), "allword", query, "AND");	
		}
		//결과내재검색
//		if( paramVO.getReSrchFlag() ){
//			int preCnt = paramVO.getPreviousQueries().length;
//			query.append(" AND  ")
//					.append(dcUtil.makePreQuery(strNmFd , paramVO.getKwd(), paramVO.getPreviousQueries(), preCnt ,"allword") );		        
//		}


		/**
		 * 날짜검색기간 조회
		 * 기간/일/월/년도, 구간검색으로 조회시 자바스크립트에서 시작날짜와 종료날짜 조회하여 전달함.
		 */
//		if (!paramVO.getStartDate().isEmpty() && !paramVO.getEndDate().isEmpty()){
//			query = dcUtil.makeRangeQuery("regdate", paramVO.getStartDate().replace(".", "")+"000000", paramVO.getEndDate().replace(".", "")+"999999", query) ;
//		}
		
		//정렬조건	
//		if ("d".equals(paramVO.getSort())){
//			query.append(" order by regdate desc");
//		}
		
		//로그기록 
		//SITE@인물+$|첫검색|1|정확도순^코난	
//		sbLog.append(  dcUtil.getLogInfo(commonUtil.null2Str(paramVO.getSiteNm(),"SITE"),
//				"게시판" ,
//				commonUtil.null2Str( paramVO.getUserId(),""), paramVO.getKwd(),
//				paramVO.getPageNum(),
//				false,
//				paramVO.getSiteNm(),
//				commonUtil.null2Str( paramVO.getRecKwd(),"" )) );
	
		searchVO.setUrl("http://localhost:7577/search");
		searchVO.setCharset("UTF8");
		searchVO.setFields("*");
		searchVO.setFrom("naverComment.naverComment");
//		searchVO.setHilightTxt(konanPropertiesService.getString("boardHilight"));
		if ( !paramVO.getKwd().isEmpty() || paramVO.getKwd() != null) {
			System.out.println("kwd : " + paramVO.getKwd());
//			searchVO.setQuery(URLEncoder.encode("group by writer order by count(*) desc", "UTF-8"));
		}
		
//		searchVO.setLogInfo(URLEncoder.encode(sbLog.toString()));
	
		// URL 생성
		String restUrl = dcUtil.getRestURL(searchVO);
		logger.debug("RESTURL board : " + restUrl);
		
		RestResultVO restVO = new RestResultVO();
		boolean success = restModule.restSearch(restUrl, restVO, searchVO.getFields());
		
		
		//초기화
//		query.charAt(0);
//		sbLog.charAt(0);
		
		if(!success) 
			return null;
				
		return restVO;		
	}
}
