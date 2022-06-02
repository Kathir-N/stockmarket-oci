package com.stockmarket.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

//public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean error,String message, Object responseObj) {
//    Map<String, Object> map = new HashMap<String, Object>();
//    try {
//        map.put("status", status.value());
//        map.put("isSuccess", error);
//        map.put("message", message);
//        map.put("data", responseObj);
//
//        return new ResponseEntity<Object>(map,status);
//    } catch (Exception e) {
//        map.clear();
//        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        map.put("isSuccess",false);
//        map.put("message", e.getMessage());
//        map.put("data", null);
//        return new ResponseEntity<Object>(map,status);
//    }
//}

public static ResponseEntity<Object> generateResponse(HttpStatus status,  String msg, List<Stock> stockdetials,
		double highestNumber, double lowestn, double avgstock) {

	 Map<String, Object> map = new HashMap<String, Object>();
	    try {
	        map.put("status", status.value());
	        map.put("message", msg);
	        map.put("Stock", stockdetials);
	        map.put("Highstock", highestNumber);
	        map.put("Loweststock", lowestn);
	        map.put("Avgstock", avgstock);

	        return new ResponseEntity<Object>(map,status);
	    } catch (Exception e) {
	        map.clear();
	        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        map.put("isSuccess",false);
	        map.put("message", e.getMessage());
	        map.put("data", null);
	        return new ResponseEntity<Object>(map,status);
	    }


}

public static ResponseEntity<Object> generateErrorResponse(HttpStatus badRequest, String string, Object object) {

	 Map<String, Object> map = new HashMap<String, Object>();
	    try {
	        map.put("status", badRequest.value());
	        map.put("message", string);
	        map.put("data", object);

	        return new ResponseEntity<Object>(map,badRequest);
	    } catch (Exception e) {
	        map.clear();
	        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        map.put("isSuccess",false);
	        map.put("message", e.getMessage());
	        map.put("data", null);
	        return new ResponseEntity<Object>(map,badRequest);
	    }


}

public static ResponseEntity<Object> generateFetchResponse( HttpStatus status, String msg, Company updatedcmpy,
		float price) {
	 Map<String, Object> map = new HashMap<String, Object>();
	    try {
	        map.put("status", status.value());
	        map.put("message", msg);
	        map.put("Companydetails", updatedcmpy);
	        map.put("Lateststockprice", price);
	        return new ResponseEntity<Object>(map,status);
	    } catch (Exception e) {
	        map.clear();
	        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        map.put("isSuccess",false);
	        map.put("message", e.getMessage());
	        map.put("data", null);
	        return new ResponseEntity<Object>(map,status);
	    }
}
}

