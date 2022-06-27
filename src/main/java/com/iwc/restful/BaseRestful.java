package com.iwc.restful;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseRestful {
	
	@Value("${resources.static.path}")
	protected String resourcesStaticPath;

	protected Map<String,String>returnMap(Map<String, String> map){
		
		HashMap<Integer, String> map1 = new HashMap<>();
		map1.put(1, "");
		
		return map;
		
	}
}
