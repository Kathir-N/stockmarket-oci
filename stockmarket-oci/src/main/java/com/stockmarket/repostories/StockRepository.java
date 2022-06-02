package com.stockmarket.repostories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stockmarket.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query(nativeQuery = true, value = "select stock_price from stock s where s.company_code=:company_code ORDER BY date DESC LIMIT 1")
	public float findstockprice(@Param("company_code") long id);

	@Query(nativeQuery = true, value = "select * FROM stock s  WHERE s.company_code=:company_code")

	public Stock findbycompId(@Param("company_code") long id);
	


	@Query(nativeQuery = true, value = "select * FROM stock s where s.company_code=:company_code and (cast( stock_date as date) BETWEEN :stock_date AND  :stock_date)")

	public List<Stock> findByDateandDate(@Param("company_code") long id ,@Param(value = "stock_date") Date startDate,@Param(value = "stock_date")  Date endDate);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM stock s WHERE s.company_code = :company_code")
	void deleteBycmpID(@Param("company_code") long id);

}
