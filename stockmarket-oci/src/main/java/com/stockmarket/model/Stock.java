package com.stockmarket.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.stockmarket.config.View;

@Entity
@Table(name = "stock")

@JsonIgnoreProperties({ "id", "stock_date" })
public class Stock implements Serializable {

	private static final long serialVersionUID = -353971991551040124L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	@Column(name="stock_date")
	private Date date;

	@JsonView(value = { View.UserView.External.class })
	@Column
	private float stockPrice;

	@ManyToOne( cascade = CascadeType.REMOVE )
	@JoinColumn(name = "company_code", nullable = false)
	@JsonIgnore
	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(float stockPrice) {
		this.stockPrice = stockPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
