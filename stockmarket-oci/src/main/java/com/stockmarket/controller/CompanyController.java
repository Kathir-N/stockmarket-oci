package com.stockmarket.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.stockmarket.exception.ResourceNotFoundException;
import com.stockmarket.model.Company;
import com.stockmarket.model.Request;
import com.stockmarket.model.Stock;
import com.stockmarket.repostories.CompanyRepository;
import com.stockmarket.repostories.StockRepository;

@RestController

public class CompanyController {
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private StockRepository stockRepository;

//	@Autowired
//	private CompanyService comp;

	@GetMapping(value = "/")
	public String getpage() {
		return "welcome";
	}

//	   @JsonView(value = View.UserView.External.class) 

	@JsonView(View.UserView.External.class)
	@GetMapping("/api/v1.0/market/company/getall")

	public List<Company> fetchCompany() {
		// comp.sevice(companyRepository.findAll());
		return companyRepository.findAll();
	}

	@PostMapping(value = "/api/v1.0/market/company/register", produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody Company initRegistration(@RequestBody Request reqCompanyobj,
			HttpServletResponse response) {
		Company registration = reqCompanyobj.getCompany();

		return companyRepository.save(registration);

	}

	public Company saveCompany(@RequestBody Company company) {
		return companyRepository.save(company);
	}

	@GetMapping("/api/v1.0/market/company/info/{companyCode}")
	public String fetchCompanybasedonID(@PathVariable long companyCode) throws JsonProcessingException, JSONException {
		// return companyRepository.findById(companyCode);
		float price = stockRepository.findstockprice(companyCode);
		String json;

		System.out.println(price);
		Company updatedcmpy = companyRepository.findById(companyCode).get();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		json = mapper.writeValueAsString(updatedcmpy);

//	         JSONObject jsonObj = new JSONObject(json);
//	         JSONArray jsonArray = (JSONArray) jsonObj.get("stocks");
//	         
//	         for (int i = 0; i < jsonArray.length(); ++i) {
//					JSONObject obj = jsonArray.getJSONObject(i);
//					if (obj.getString("stocks") != null) {
//						jsonArray.remove(i);
//					}
//				}
//				System.out.println(obj);

		if (price != 0.0f) {
			json = mapper.writeValueAsString(updatedcmpy);
			return json.concat("Latest stock price:" + price);
		} else {
			json = mapper.writeValueAsString(updatedcmpy);
			return json;
		}

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
	public String deleteCompany(@PathVariable long companyCode) {
		Company updatedcmpy = companyRepository.findById(companyCode).get();
//		updatedcmpy.getStock().removeIf(c-> companyCode

		stockRepository.deleteBycmpID(updatedcmpy.getCompanyCode());
		companyRepository.delete(updatedcmpy);
		return "Deleted";
//		Company deletecompany = companyRepository.findById(companyCode).get();
//		//String stockp=stockRepository.deletebycompId(companyCode);
//		//System.out.println(stockp);
//	companyRepository.delete(deletecompany);

	}

//		return companyRepository.findById(companyCode).map(company ->{
//		companyRepository.delete(company);
//		return ResponseEntity.ok().build();
//	}).orElseThrow(() -> new ResourceNotFoundException("CompanyID "+ companyCode + "not found"));
//	}

//    Optional<Company> optionalcmp = companyRepository.findById(companyCode);
//
//    if (!optionalcmp.isPresent()) {
//        return ResponseEntity.unprocessableEntity().build();
//    }
//    	deleteRespEn(optionalcmp.get());
//
//
//    return ResponseEntity.noContent().build();
//}
//
//@Transactional
//public void deleteRespEn(Company cmp) {
//    stockRepository.deleteBycmpID(cmp.getCompanyCode());
//    companyRepository.delete(cmp);
//}

}
