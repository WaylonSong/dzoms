package com.dz.module.vehicle;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * BusinessLicense entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_license", catalog = "dzomsdb", uniqueConstraints = @UniqueConstraint(columnNames = "license_num"))
public class BusinessLicense implements java.io.Serializable {

	// Fields
	private String licenseNum;
	private Date beginDate;
	private Date endDate;
	private Integer register;
	private Date registDate;

	// Constructors

	/** default constructor */
	public BusinessLicense() {
	}

	/** full constructor */
	public BusinessLicense(String licenseNum, Date beginDate, Date endDate,
			Integer register, Date registDate) {
		this.licenseNum = licenseNum;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.register = register;
		this.registDate = registDate;
	}

	@Id
	@Column(name = "license_num", unique = true, length = 30,nullable=false)
	public String getLicenseNum() {
		return this.licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "begin_date", length = 0)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 0)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "register")
	public Integer getRegister() {
		return this.register;
	}

	public void setRegister(Integer register) {
		this.register = register;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "regist_date", length = 0)
	public Date getRegistDate() {
		return this.registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

}