package com.dz.common.dataimport;

public class RawVehicle {
	private String licenseNum;
	private String driverName;
	private String driverId;
	private String carframeNum;
	private String engineNum;
	private String moneyCountor;
	private String carMode;
	private String certifyNum;
	private String dept;	
	private String pd;
	private String inDate;
	private String licensePurseNum;
	private String isNewLicense;
	private String operateCard;
	private String operateCardTime;
	private String businessLicenseNum;
	private String businessLicenseBeginDate;
	private String businessLicenseEndDate;
	
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getCarframeNum() {
		return carframeNum;
	}
	public void setCarframeNum(String carframeNum) {
		this.carframeNum = carframeNum;
	}
	public String getEngineNum() {
		return engineNum;
	}
	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}
	public String getMoneyCountor() {
		return moneyCountor;
	}
	public void setMoneyCountor(String moneyCountor) {
		this.moneyCountor = moneyCountor;
	}
	public String getCarMode() {
		return carMode;
	}
	public void setCarMode(String carMode) {
		this.carMode = carMode;
	}
	public String getCertifyNum() {
		return certifyNum;
	}
	public void setCertifyNum(String certifyNum) {
		this.certifyNum = certifyNum;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getLicensePurseNum() {
		return licensePurseNum;
	}
	public void setLicensePurseNum(String licensePurseNum) {
		this.licensePurseNum = licensePurseNum;
	}
	public String getIsNewLicense() {
		return isNewLicense;
	}
	public void setIsNewLicense(String isNewLicense) {
		this.isNewLicense = isNewLicense;
	}
	public String getOperateCard() {
		return operateCard;
	}
	public void setOperateCard(String operateCard) {
		this.operateCard = operateCard;
	}
	public String getOperateCardTime() {
		return operateCardTime;
	}
	public void setOperateCardTime(String operateCardTime) {
		this.operateCardTime = operateCardTime;
	}
	public String getBusinessLicenseNum() {
		return businessLicenseNum;
	}
	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}
	public String getBusinessLicenseBeginDate() {
		return businessLicenseBeginDate;
	}
	public void setBusinessLicenseBeginDate(String businessLicenseBeginDate) {
		this.businessLicenseBeginDate = businessLicenseBeginDate;
	}
	public String getBusinessLicenseEndDate() {
		return businessLicenseEndDate;
	}
	public void setBusinessLicenseEndDate(String businessLicenseEndDate) {
		this.businessLicenseEndDate = businessLicenseEndDate;
	}
	@Override
	public String toString() {
		return "RawVehicle [licenseNum=" + licenseNum + ", driverName="
				+ driverName + ", driverId=" + driverId + ", carframeNum="
				+ carframeNum + ", engineNum=" + engineNum + ", moneyCountor="
				+ moneyCountor + ", carMode=" + carMode + ", certifyNum="
				+ certifyNum + ", dept=" + dept + ", inDate=" + inDate
				+ ", licensePurseNum=" + licensePurseNum + ", isNewLicense="
				+ isNewLicense + ", operateCard=" + operateCard
				+ ", operateCardTime=" + operateCardTime
				+ ", businessLicenseNum=" + businessLicenseNum
				+ ", businessLicenseBeginDate=" + businessLicenseBeginDate
				+ ", businessLicenseEndDate=" + businessLicenseEndDate + "]";
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	
	
}
