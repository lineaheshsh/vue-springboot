//package com.boot.spring.search.module;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.boot.spring.search.vo.ResultListVO;
//import com.boot.spring.search.vo.SearchVO;
//
//import konan.docruzer.DOCRUZER;
//
///** 
// * 검색엔진 API. 
// * 
// * @author Jinhoo.Jang
// * @since 2013.08.09
// * @version 1.0
// * Copyright ⓒ Konan Technology. All Right Reserved
// * ==================================================
// */
//@Component
//public class SearchModule {
//	private static final Logger logger = LoggerFactory.getLogger(SearchModule.class);
//	
////	@Value("#{kframework['engine.ip']}") private String engineIp;
////	@Value("#{kframework['engine.port']}") private int enginePort;
////	@Value("#{kframework['engine.language']}") private int language;
////	@Value("#{kframework['engine.charset']}") private int charset;
//	
//	private String engineIp = "127.0.0.1";
//	private int enginePort = 7577;
//	private int language = 1;
//	private int charset = 4;
//		
//    /**
//     * 일반 검색용 모듈
//     * 
//     * @param params
//     * @param search
//     * @return ResultVO
//     * @throws IOException
//     * @throws Exception
//     */
//    public ResultListVO dcSubmitQuery(String keyword, int pageNum, int pageSize, SearchVO search) 
//					throws IOException, Exception {
//        long hc;							// 검색 핸들
//        int rc;								// SubmitQuery 결과 값
//        int total;							// 검색 결과  건수
//        int rows;							// 페이지 사이즈에 해당하는 레코드 개수
//        int cols;							// 시나리오에 설정된 레코드
//        int startNum;						// 검색 결과 시작 오프셋
//        int i;								// for 문용 인스턴스 변수
//        int j;								// for 문용 인스턴스 변수
//        String msg = "";					// 에러 메시지
//        int[] rowIds = null;				// rowid 배열
//        int[] scores = null;				// score 배열
//        String[] tmpFdata = null;			// 임시 결과 배열
//        String[] colNames    = null;   		// 시나리오에 설정된 필드명
//
//		DOCRUZER crz = new DOCRUZER();		// DOCRUZER 객체 생성
//		hc = crz.CreateHandle();			// 핸들 생성
//		startNum = (pageNum -1) * pageSize;	// 검색 결과 시작 오프셋 계산		
//		ResultListVO rsb = new ResultListVO();
//		HashMap<String, String> map;
//		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//		
//		
//		
//		/*
//         * -----------------------------------------------------------------
//         * 쿼리 인코딩 설정 옵션
//         * rc = crz.SetCharacterEncoding(hc, DCConfig.getProperty("SEARCH_CHARACTER_ENCODING") );
//         * -----------------------------------------------------------------
//         * connection timeout 시간. 초단위(default : 30초)
//         * rc = crz.SetOption(hc, crz.OPTION_SOCKET_TIMEOUT_REQUEST, 30);
//         * -----------------------------------------------------------------
//         * 병렬 쿼리 수행 방식 (defaul : 0, 1일경우 병렬 쿼리 수행)
//         * rc = crz.SetOption(hc, DOCRUZER.OPTION_SOCKET_ASYNC_REQUEST, 1);
//         * -----------------------------------------------------------------
//         */
//		
//		if(charset == 4)
//			rc = crz.SetCharacterEncoding(hc, "UTF-8");
//		else if(charset == 1)
//			rc = crz.SetCharacterEncoding(hc, "EUC-KR");
//
//        // 검색 API - 사용자의 질의어를 서버로 보냄
//        rc = crz.SubmitQuery(
//        		hc,
//        		engineIp,
//        		enginePort,
//                "",
//                search.getLogInfo(),                
//                search.getScenario(),
//                search.getQuery(),
//                search.getOrderBy(),
//                search.getHilightTxt(),
//                startNum, 
//                pageSize, 
//                language, 
//                charset
//        );
//        //For Debug --> System.out.println("rc : " + rc);
//
//		if (rc != 0) {
//			msg = "rc : " + rc + " scn : " + search.getScenario() + "\n query : " + search.getScenario() + "\n orderBy : " + search.getOrderBy() ;
//			throw new Exception(crz.GetErrorMessage(hc) + msg);
//		} else {
//			// SubmitQuery 메소드로 실행한 검색 결과의 총 검색 결과 수
//			total = crz.GetResult_TotalCount(hc);
//			// SubmitQuery 메소드로 실행한 검색 결과 중 실제로 가져온 검색 결과의 개수
//			rows = crz.GetResult_RowSize(hc);
//			// SubmitQuery 메소드로 실행한 검색 결과의 필드 개수
//			cols = crz.GetResult_ColumnSize(hc);
//			
//			colNames  = new String[cols];
//			rc    = crz.GetResult_ColumnName(hc, colNames, cols);  // 시나리오 필드명 설정
//			
//			if (search.isFlag()) {
//				rowIds = new int[rows];
//				scores = new int[rows];
//			
//				crz.GetResult_ROWID(hc, rowIds, scores);
//			}
//
//			tmpFdata = new String[cols];
//			
//			for (i = 0; i < rows; i++) {
//				crz.GetResult_Row(hc, tmpFdata, i);
//				map = new HashMap<String, String>();
//				
//				for (j = 0; j < cols; j++) {
//					map.put(colNames[j], tmpFdata[j]);
//					//logger.debug(colNames[j] + " : " + tmpFdata[j]);
//				}
//				
//				list.add(map);
//				map = null;
//			}
//			
//			// ResultListVO에 결과를 담음
//			rsb.setTotal(total);
//			rsb.setRows(rows);
//			rsb.setCols(cols);
//			rsb.setRowIds(rowIds);
//			rsb.setScores(scores);
//			rsb.setList(list);
//			rsb.setColNames(colNames);
//		}
//		
//		// 핸들 삭제
//		crz.DestroyHandle(hc);
//		
//		return rsb;
//	}
//}