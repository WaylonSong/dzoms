package com.dz.module.search;

import java.util.Date;

/**
 * DvId entity. @author MyEclipse Persistence Tools
 */

public class Dv implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8544375893873604390L;
	private String idNum;
	private String name;
	private String phoneNum1;
	private Boolean isInCar;
	private String carframeNum;
	private String engineNum;
	private String carMode;
	private String dept;
	private String firstDriver;
	private String secondDriver;
	private String thirdDriver;
	private String forthDriver;
	private String tempDriver;
	private Date tempLastDate;
	private String team;
	private Short state;
	private String licenseNum;

	// Constructors

	/** default constructor */
	public Dv() {
	}

	/** minimal constructor */
	public Dv(String idNum, String name, String phoneNum1, Boolean isInCar,
			String carframeNum, String engineNum, String carMode) {
		this.idNum = idNum;
		this.name = name;
		this.phoneNum1 = phoneNum1;
		this.isInCar = isInCar;
		this.carframeNum = carframeNum;
		this.engineNum = engineNum;
		this.carMode = carMode;
	}

	/** full constructor */
	public Dv(String idNum, String name, String phoneNum1, Boolean isInCar,
			String carframeNum, String engineNum, String carMode, String dept,
			String firstDriver, String secondDriver, String thirdDriver,
			String forthDriver, String tempDriver, Date tempLastDate,
			String team, Short state, String licenseNum) {
		this.idNum = idNum;
		this.name = name;
		this.phoneNum1 = phoneNum1;
		this.isInCar = isInCar;
		this.carframeNum = carframeNum;
		this.engineNum = engineNum;
		this.carMode = carMode;
		this.dept = dept;
		this.firstDriver = firstDriver;
		this.secondDriver = secondDriver;
		this.thirdDriver = thirdDriver;
		this.forthDriver = forthDriver;
		this.tempDriver = tempDriver;
		this.tempLastDate = tempLastDate;
		this.team = team;
		this.state = state;
		this.licenseNum = licenseNum;
	}

	// Property accessors

	public String getIdNum() {
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum1() {
		return this.phoneNum1;
	}

	public void setPhoneNum1(String phoneNum1) {
		this.phoneNum1 = phoneNum1;
	}

	public Boolean getIsInCar() {
		return this.isInCar;
	}

	public void setIsInCar(Boolean isInCar) {
		this.isInCar = isInCar;
	}

	public String getCarframeNum() {
		return this.carframeNum;
	}

	public void setCarframeNum(String carframeNum) {
		this.carframeNum = carframeNum;
	}

	public String getEngineNum() {
		return this.engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getCarMode() {
		return this.carMode;
	}

	public void setCarMode(String carMode) {
		this.carMode = carMode;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getFirstDriver() {
		return this.firstDriver;
	}

	public void setFirstDriver(String firstDriver) {
		this.firstDriver = firstDriver;
	}

	public String getSecondDriver() {
		return this.secondDriver;
	}

	public void setSecondDriver(String secondDriver) {
		this.secondDriver = secondDriver;
	}

	public String getThirdDriver() {
		return this.thirdDriver;
	}

	public void setThirdDriver(String thirdDriver) {
		this.thirdDriver = thirdDriver;
	}

	public String getForthDriver() {
		return this.forthDriver;
	}

	public void setForthDriver(String forthDriver) {
		this.forthDriver = forthDriver;
	}

	public String getTempDriver() {
		return this.tempDriver;
	}

	public void setTempDriver(String tempDriver) {
		this.tempDriver = tempDriver;
	}

	public Date getTempLastDate() {
		return this.tempLastDate;
	}

	public void setTempLastDate(Date tempLastDate) {
		this.tempLastDate = tempLastDate;
	}

	public String getTeam() {
		return this.team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getLicenseNum() {
		return this.licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Dv))
			return false;
		Dv castOther = (Dv) other;

		return ((this.getIdNum() == castOther.getIdNum()) || (this.getIdNum() != null
				&& castOther.getIdNum() != null && this.getIdNum().equals(
				castOther.getIdNum())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getPhoneNum1() == castOther.getPhoneNum1()) || (this
						.getPhoneNum1() != null
						&& castOther.getPhoneNum1() != null && this
						.getPhoneNum1().equals(castOther.getPhoneNum1())))
				&& ((this.getIsInCar() == castOther.getIsInCar()) || (this
						.getIsInCar() != null && castOther.getIsInCar() != null && this
						.getIsInCar().equals(castOther.getIsInCar())))
				&& ((this.getCarframeNum() == castOther.getCarframeNum()) || (this
						.getCarframeNum() != null
						&& castOther.getCarframeNum() != null && this
						.getCarframeNum().equals(castOther.getCarframeNum())))
				&& ((this.getEngineNum() == castOther.getEngineNum()) || (this
						.getEngineNum() != null
						&& castOther.getEngineNum() != null && this
						.getEngineNum().equals(castOther.getEngineNum())))
				&& ((this.getCarMode() == castOther.getCarMode()) || (this
						.getCarMode() != null && castOther.getCarMode() != null && this
						.getCarMode().equals(castOther.getCarMode())))
				&& ((this.getDept() == castOther.getDept()) || (this.getDept() != null
						&& castOther.getDept() != null && this.getDept()
						.equals(castOther.getDept())))
				&& ((this.getFirstDriver() == castOther.getFirstDriver()) || (this
						.getFirstDriver() != null
						&& castOther.getFirstDriver() != null && this
						.getFirstDriver().equals(castOther.getFirstDriver())))
				&& ((this.getSecondDriver() == castOther.getSecondDriver()) || (this
						.getSecondDriver() != null
						&& castOther.getSecondDriver() != null && this
						.getSecondDriver().equals(castOther.getSecondDriver())))
				&& ((this.getThirdDriver() == castOther.getThirdDriver()) || (this
						.getThirdDriver() != null
						&& castOther.getThirdDriver() != null && this
						.getThirdDriver().equals(castOther.getThirdDriver())))
				&& ((this.getForthDriver() == castOther.getForthDriver()) || (this
						.getForthDriver() != null
						&& castOther.getForthDriver() != null && this
						.getForthDriver().equals(castOther.getForthDriver())))
				&& ((this.getTempDriver() == castOther.getTempDriver()) || (this
						.getTempDriver() != null
						&& castOther.getTempDriver() != null && this
						.getTempDriver().equals(castOther.getTempDriver())))
				&& ((this.getTempLastDate() == castOther.getTempLastDate()) || (this
						.getTempLastDate() != null
						&& castOther.getTempLastDate() != null && this
						.getTempLastDate().equals(castOther.getTempLastDate())))
				&& ((this.getTeam() == castOther.getTeam()) || (this.getTeam() != null
						&& castOther.getTeam() != null && this.getTeam()
						.equals(castOther.getTeam())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null && castOther.getState() != null && this
						.getState().equals(castOther.getState())))
				&& ((this.getLicenseNum() == castOther.getLicenseNum()) || (this
						.getLicenseNum() != null
						&& castOther.getLicenseNum() != null && this
						.getLicenseNum().equals(castOther.getLicenseNum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdNum() == null ? 0 : this.getIdNum().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getPhoneNum1() == null ? 0 : this.getPhoneNum1().hashCode());
		result = 37 * result
				+ (getIsInCar() == null ? 0 : this.getIsInCar().hashCode());
		result = 37
				* result
				+ (getCarframeNum() == null ? 0 : this.getCarframeNum()
						.hashCode());
		result = 37 * result
				+ (getEngineNum() == null ? 0 : this.getEngineNum().hashCode());
		result = 37 * result
				+ (getCarMode() == null ? 0 : this.getCarMode().hashCode());
		result = 37 * result
				+ (getDept() == null ? 0 : this.getDept().hashCode());
		result = 37
				* result
				+ (getFirstDriver() == null ? 0 : this.getFirstDriver()
						.hashCode());
		result = 37
				* result
				+ (getSecondDriver() == null ? 0 : this.getSecondDriver()
						.hashCode());
		result = 37
				* result
				+ (getThirdDriver() == null ? 0 : this.getThirdDriver()
						.hashCode());
		result = 37
				* result
				+ (getForthDriver() == null ? 0 : this.getForthDriver()
						.hashCode());
		result = 37
				* result
				+ (getTempDriver() == null ? 0 : this.getTempDriver()
						.hashCode());
		result = 37
				* result
				+ (getTempLastDate() == null ? 0 : this.getTempLastDate()
						.hashCode());
		result = 37 * result
				+ (getTeam() == null ? 0 : this.getTeam().hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37
				* result
				+ (getLicenseNum() == null ? 0 : this.getLicenseNum()
						.hashCode());
		return result;
	}

}