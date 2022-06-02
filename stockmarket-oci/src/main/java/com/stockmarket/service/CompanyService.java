//package com.stockmarket.service;
//
//import java.util.List;
//
//import org.springframework.boot.configurationprocessor.json.JSONArray;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.stockmarket.model.Company;
//
//public class CompanyService {
//
//	
//	public List<Company> sevice(List<Company> c)
//	{
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
//		String mp;
//		 mp = mapper.writeValueAsString(c);
//	        JSONObject json = new JSONObject(mp);   
//
//	        // get locations array from the JSON Object and store it into JSONArray  
//	        JSONArray jsonArray = json.getJSONArray("")
//	          
//	        // Iterate jsonArray using for loop   
//	        for (int i = 0; i < jsonArray.length(); i++) {  
//	              
//	            // store each object in JSONObject  
//	            JSONObject explrObject = jsonArray.getJSONObject(i);  
//	              
//	            // get field value from JSONObject using get() method  
//	            System.out.println(explrObject.get("address"));  
//	        }      
//		
//		return c;
//		
//	}
//	
//}
