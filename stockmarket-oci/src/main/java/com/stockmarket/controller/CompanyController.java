package com.stockmarket.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stockmarket.config.View;
import com.stockmarket.constant.Constants;
import com.stockmarket.exception.ResourceNotFoundException;
import com.stockmarket.model.Company;
import com.stockmarket.model.Request;
import com.stockmarket.model.ResponseHandler;
import com.stockmarket.model.Stock;
import com.stockmarket.repostories.CompanyRepository;
import com.stockmarket.repostories.StockRepository;

@RestController

public class CompanyController {
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private StockRepository stockRepository;

	@GetMapping(value = "/")
	public String getpage() {
		return "welcome";
	}

	@JsonView(View.UserView.External.class)
	@GetMapping("/api/v1.0/market/company/getall")

	public String fetchCompany() throws JsonProcessingException {
		List<Company> l= companyRepository.findAll();
		ObjectMapper mapper = new ObjectMapper();
		String newJsonData = mapper.writeValueAsString(l);
		System.out.println(newJsonData);		
		
		
		return newJsonData;


	}

	public static Map<String, String> parse(JSONObject json, Map<String, String> out) throws JSONException {
		Iterator<String> keys = json.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			String val = null;
			try {
				JSONObject value = json.getJSONObject(key);
				parse(value, out);
			} catch (Exception e) {
				val = json.getString(key);
			}

			if (val != null) {
				out.put(key, val);
			}
		}
		return out;
	}

	@PostMapping(value = "/api/v1.0/market/company/register", produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody ResponseEntity<String> initRegistration(@RequestBody Request reqCompanyobj,
			HttpServletResponse response) {
		Company registration = reqCompanyobj.getCompany();
		companyRepository.save(registration);
		return ResponseEntity.status(HttpStatus.CREATED).body(Constants.COMPANY_CREATED_SUCCESSFULLY);

	}

	public Company saveCompany(@RequestBody Company company) {
		return companyRepository.save(company);
	}

	@GetMapping("/api/v1.0/market/company/info/{companyCode}")
	public ResponseEntity<Object> fetchCompanybasedonID(@PathVariable long companyCode) throws JsonProcessingException, JSONException {
		float price = stockRepository.findstockprice(companyCode);
		System.out.println(price);
		Company updatedcmpy = companyRepository.findById(companyCode).get();
		return ResponseHandler.generateFetchResponse(HttpStatus.OK, "Success", updatedcmpy, price);

//		
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
//		json = mapper.writeValueAsString(updatedcmpy);
//		if (price != 0.0f) {
//			json = mapper.writeValueAsString(updatedcmpy);
//			return ResponseHandler.generateFetchResponse(HttpStatus.OK, "Success", json, price);
//			
//		} else {
//			json = mapper.writeValueAsString(updatedcmpy);
//
//			return ResponseEntity.status(HttpStatus.OK)
//					.body(String.format(Constants.COMPANY_DELETED_SUCCESSFULLY, companyCode));	
//			}

	}

	@PutMapping(value = "/api/v1.0/market/company/update/{companyCode}")
	public String updateCompany(@PathVariable long companyCode, @RequestBody Company company) {
		Company updatedcmpy = companyRepository.findById(companyCode).get();
		updatedcmpy.setCompanyCeO(company.getCompanyCeO());
		updatedcmpy.setCompanyName(company.getCompanyName());
		updatedcmpy.setCompanyWebsite(company.getCompanyWebsite());
		updatedcmpy.setStockExchange(company.getStockExchange());
		updatedcmpy.setTurnOver(company.getTurnOver());
		companyRepository.save(updatedcmpy);
		return "Updated..";

	}

	@DeleteMapping(value = "/api/v1.0/market/company/delete/{companyCode}")
	public ResponseEntity<String> deleteCompany(@PathVariable long companyCode) {
		Company updatedcmpy = companyRepository.findById(companyCode).get();
		stockRepository.deleteBycmpID(updatedcmpy.getCompanyCode());
		companyRepository.delete(updatedcmpy);
		return ResponseEntity.status(HttpStatus.OK)
				.body(String.format(Constants.COMPANY_DELETED_SUCCESSFULLY, companyCode));
	}
}
