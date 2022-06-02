package com.stockmarket.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stockmarket.config.View;
import com.sun.istack.NotNull;

@Entity
@Table(name = "company")



public class Company implements Serializable {
	
	private static final long serialVersionUID = -353971991551040124L;

	@JsonView(value = { View.UserView.External.class })

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_code")
	private long companyCode;


	@JsonView(value = { View.UserView.External.class })

	@Column
	@NotEmpty
	private String companyName;

	@JsonView(value = { View.UserView.External.class })

	@Column
	@NotEmpty
	private String companyCeO;

	@JsonView(value = { View.UserView.External.class })

	@Column
	@NotEmpty
	private String companyWebsite;
	@JsonView(value = { View.UserView.External.class })

	@Column
	@Min(value = 10)
	private long turnOver;

	@JsonView(value = { View.UserView.External.class })

	@Column
	@NotEmpty
	private String stockExchange;

	@JsonView(value = { View.UserView.External.class })
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE, orphanRemoval = true)

	private Set<Stock> stocks ;

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCeO() {
		return companyCeO;
	}

	public void setCompanyCeO(String companyCeO) {
		this.companyCeO = companyCeO;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(long companyCode) {
		this.companyCode = companyCode;
	}

	public long getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(long turnOver) {
		this.turnOver = turnOver;
	}

	public Company() {

	}

	public Company(String companyName, String companyCeO, String companyWebsite, long turnOver, String stockExchange) {
		this.companyName = companyName;
		this.companyCeO = companyCeO;
		this.companyWebsite = companyWebsite;
		this.turnOver = turnOver;
		this.stockExchange = stockExchange;
	}

	public Set<Stock> getStock() {
		return stocks;
	}

	public void setBooks(Set<Stock> stocks) {
		this.stocks = stocks;
//
//        for(Stock s : stocks) {
//            s.setCompany(this);
//            
//        }
	}

}
