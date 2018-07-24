package com.boot.spring.common.util;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	
	public Map<String, String> parseJsonToMap(String fields, String jsonData) {
		
		Map<String, String> result = new HashMap<String, String>();
		JSONParser parser = new JSONParser();

		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) parser.parse(jsonData);
			JSONObject dataObject = (JSONObject) jsonObject.get("data");
			
			if ( fields.indexOf(",") > -1 ) {
				String[] fieldsArr = fields.split(",");
				
				for ( int i=0; i<fieldsArr.length; i++) {
					result.put(fieldsArr[i], (String) dataObject.get(fieldsArr[i]));
				}
			} else {
				if ( dataObject.get(fields) instanceof Long ) {
					long temp = (Long) dataObject.get(fields);
					result.put(fields, Long.toString(temp));
				} else if ( dataObject.get(fields) instanceof String ) {
					result.put(fields, (String) dataObject.get(fields));
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
