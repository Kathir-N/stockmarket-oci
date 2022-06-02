package com.stockmarket.repostories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stockmarket.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> 

{

	
}

