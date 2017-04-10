package com.dz.module.vehicle.service;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Servicespace entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "servicespace", catalog = "dzomsdb")
public class ServiceSpace implements java.io.Serializable {

	// Fields

	private Integer id;
	private String carframeNum;
	private Date date;
	private String dept;

	// Constructors

	/** default constructor */
	public ServiceSpace() {
	}

	/** full constructor */
	public ServiceSpace(String carframeNum, Date date) {
		this.carframeNum = carframeNum;
		this.date = date;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 0)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name = "dept", length = 30)
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}