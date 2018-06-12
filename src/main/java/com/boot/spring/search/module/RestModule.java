package com.boot.spring.search.module;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boot.spring.search.vo.RestResultVO;

@Component("restModule")
public class RestModule {
	
	private static final Logger logger = LoggerFactory.getLogger(RestModule.class);
	
//	/** EgovPropertyService */
//	@Resource(name = "konanPropertiesService")
//	protected EgovPropertyService konanPropertiesService;
	
	
	/**
	 * REST 방식으로 엔진 검색
	 * 20160708 수정
	 * @return
	 * @throws Exception 
	 */
	public boolean restSearch(String restUrl, RestResultVO restVO, String selectField) {
		URL url;
		
		try {
			url = new URL(restUrl);	
			URLConnection conn = url.openConnection();	
			logger.debug(restUrl);
			System.out.println("url : " + restUrl);

			BufferedReader in = new BufferedReader
					(new InputStreamReader(conn.getInputStream()));

			//BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")) ;
			String line = "";
			StringBuffer sb = new StringBuffer();
			
			while((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			System.out.println("sb : " + sb.toString());

			// 파싱하는 부분
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(sb.toString());
			
			JSONObject jsonObj = (JSONObject) obj;
			restVO.setStatus((String)jsonObj.get("status"));
			
			JSONObject resultObj = (JSONObject) jsonObj.get("result");
			restVO.setTotal((long)resultObj.get("total_count"));
			
			JSONArray arr = (JSONArray) resultObj.get("rows");
			int arrCnt = arr.size();
			int fieldCnt = 0;
			
			if(arr != null && arrCnt > 0) {
//				String[] fields = selectField.split(",");
				String[] fields = "writer,count(*)".split(",");
				HashMap<String, Object> map;
				List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>> (); 
						
				JSONObject result;
				JSONObject record;
				fieldCnt = fields.length;
				for(int i=0; i<arrCnt; i++) {
					map = new HashMap<String, Object> ();
					
					result = (JSONObject) arr.get(i);
					record = (JSONObject) result.get("fields");
					
					for(int j=0; j<fieldCnt; j++) {
//						if(!konanPropertiesService.getBoolean("useWarning")) {
//							map.put(fields[j], record.get(fields[j]).toString()
//									.replaceAll("\\(WARNING: EVALUATION COPY\\)", ""));
//						} else {
						if ( fields[j].equals("count(*)") ) {
							map.put("count", (long)record.get(fields[j]));
//							System.out.println("record : " + (long)record.get(fields[j]) );
						} else {
							map.put(fields[j], (String)record.get(fields[j]));
//							System.out.println("record : " + (String)record.get(fields[j]) );
						}
//						}
						
						
					}
					
					list.add(map);
					map = null;
				}
				
				restVO.setResult(list);
			}
			// 파싱 끝			
			in.close();
		} catch (Exception e) {
			logger.error("ERROR:" + e.getMessage());
			return false;
		}		
		
		return true;
	}
	
	
	
	
}
