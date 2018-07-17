package com.boot.spring.search.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.spring.login.vo.LoginVO;
import com.boot.spring.search.dao.SearchDAO;
import com.boot.spring.search.vo.ParameterVO;
import com.boot.spring.search.vo.RestResultVO;

@Service("searchService")
public class SearchService {

	@Autowired
	SearchDAO searchDao;
//	
//	public List<Member> selectMembers() {
//		return memberDao.selectMembers();		
//	}
//	
//	public ResultListVO selectMembers(String keyword) throws IOException, Exception {
//		return memberDao.selectMembers(keyword);		
//	}
	
	public RestResultVO getCommentGroupBy(int limit) throws Exception {
		
		return searchDao.getCommentGroupBy(limit);
	}
	
	public RestResultVO getIssueWordGroupBy(int limit) throws Exception {
		
		return searchDao.getIssueWordGroupBy(limit);
	}
	
	public RestResultVO getComments(ParameterVO paramVO) throws Exception {
		System.out.println("[service] getPageNum : " + paramVO.getPageNum());
		return searchDao.getComments(paramVO);
	}
	
	public ParameterVO setParameter(String searchJSON) throws Exception {
		JSONParser parser = new JSONParser();

		JSONObject jsonObject = (JSONObject) parser.parse(searchJSON);
		JSONObject dataObject = (JSONObject) jsonObject.get("data");
		
		String kwd = (String) dataObject.get("kwd");
		long currentPage = (long) dataObject.get("currentPage");
		String pageName = (String) dataObject.get("pageName");
		
		ParameterVO paramVO = new ParameterVO();
		
		paramVO.setKwd(kwd);
		
		paramVO.setPageNum((int) currentPage);
		paramVO.setCategory(pageName);
		paramVO.setPageSize(10);
		
		return paramVO;
		
	}
}
