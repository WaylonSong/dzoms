package com.dz.common.ocr;
// default package

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ElectricFromHtml entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "electric_from_html", catalog = "dzomsdb")
public class ElectricFromHtml implements java.io.Serializable {

	// Fields

	private Integer id;
	private String carframeNum;
	private Date fetchTime;
	private String fetchResult;
	private Integer fetchItems;

	// Constructors

	/** default constructor */
	public ElectricFromHtml() {
	}

	/** full constructor */
	public ElectricFromHtml(String carframeNum, Date fetchTime,
			String fetchResult, Integer fetchItems) {
		this.carframeNum = carframeNum;
		this.fetchTime = fetchTime;
		this.fetchResult = fetchResult;
		this.fetchItems = fetchItems;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "carframeNum", length = 30)
	public String getCarframeNum() {
		return this.carframeNum;
	}

	public void setCarframeNum(String carframeNum) {
		this.carframeNum = carframeNum;
	}

	@Column(name = "fetchTime", length = 0)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFetchTime() {
		return this.fetchTime;
	}

	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
	}

	@Column(name = "fetchResult")
	public String getFetchResult() {
		return this.fetchResult;
	}

	public void setFetchResult(String fetchResult) {
		this.fetchResult = fetchResult;
	}

	@Column(name = "fetchItems")
	public Integer getFetchItems() {
		return this.fetchItems;
	}

	public void setFetchItems(Integer fetchItems) {
		this.fetchItems = fetchItems;
	}

}