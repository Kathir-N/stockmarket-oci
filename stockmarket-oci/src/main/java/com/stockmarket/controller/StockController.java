package com.stockmarket.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.model.Company;
import com.stockmarket.model.Stock;
import com.stockmarket.repostories.CompanyRepository;
import com.stockmarket.repostories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class StockController {
	@Autowired 
	private CompanyRepository companyRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	
	
	@PostMapping(value = "/api/v1.0/market/stock/add/{companyCode}")
	public String addStockPrice(@PathVariable long companyCode, @RequestBody Stock stock) {
   		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		    LocalDateTime now = LocalDateTime.now();
//		    stock.setDate((dtf.format(now)));
   		   stock.setDate(new Date());
			Company updatedcmpy = companyRepository.findById(companyCode).get();
			stock.setCompany(updatedcmpy);
		   stockRepository.save(stock);		   
		return "Updated with stock..";

	}


	@DeleteMapping(value = "/api/v1.0/market/company/deletestock/{companyCode}")
	public String deleteCompany(@PathVariable long companyCode) {
		Stock deletecompany = stockRepository.findbycompId(companyCode);
		stockRepository.delete(deletecompany);
		return "deleted stock";

	}


	
	
	
	@GetMapping(value = "/api/v1.0/market/company/{companyCode}/{fromdate}/{enddate}")
	
	public List<Stock> getStockBasedonDate(@PathVariable long companyCode, @PathVariable String fromdate,@PathVariable String enddate) throws ParseException {
		System.out.println(fromdate);
		System.out.println(enddate);

		LocalDate sdate = LocalDate.parse(fromdate);
	Date startdate = java.sql.Date.valueOf(sdate);

		LocalDate edate = LocalDate.parse(enddate);
	Date finaldate = java.sql.Date.valueOf(edate);

		List<Stock> stockdetials = stockRepository.findByDateandDate(companyCode,startdate,finaldate);
		System.out.println();
		
		return  stockdetials;

	}
	
	

	
}


