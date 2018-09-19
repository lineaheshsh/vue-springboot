package com.boot.spring.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.boot.spring.member.vo.MemberVO;

@Component
public class CommonUtils {

	
	public Map<String, String> parseJsonToMap(String jsonData) {
		
		Map<String, String> result = new HashMap<String, String>();
		JSONParser parser = new JSONParser();
		List<String> keys = new ArrayList<String>();

		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) parser.parse(jsonData);
			JSONObject dataObject = (JSONObject) jsonObject.get("data");
			Iterator<String> key = dataObject.keySet().iterator();
			
			 while(key.hasNext())
		    {
		        keys.add(key.next());
		    }
			
			if ( !keys.isEmpty() ) {
				for ( int i=0; i<keys.size(); i++) {
					String k = keys.get(i);
					
					if ( dataObject.get(k) instanceof Long ) {
						long temp = (Long) dataObject.get(k);
						result.put(k, Long.toString(temp));
					} else if ( dataObject.get(k) instanceof String ) {
						result.put(k, (String) dataObject.get(k));
					}
				}
			}
			
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
}
