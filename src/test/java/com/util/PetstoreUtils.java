package com.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PetstoreUtils {
	
	public static String processObject(Object request) {
		
		ObjectMapper mapper = new ObjectMapper();   
        mapper.setSerializationInclusion(Include.ALWAYS);  
        
        String payload = "";
        
		try {
			payload = mapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return payload;
	}

}
