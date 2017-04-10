package com.dz.common.dataimport;

public class RawContract {
	private String contractId;
	private String carframeNum;
	private String carNum;
	private String businessForm;
	private String ascription;
	private String penalty;
	private String rentFirst;
	private String rent;
	private String deposit;
	private String feeAlter;
	private String contractBeginDate;
	private String contractEndDate;
	private String branchFirm;
	private String idNum;
	private String idName;
	private String remark;
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getCarframeNum() {
		return carframeNum;
	}
	public void setCarframeNum(String carframeNum) {
		this.carframeNum = carframeNum;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getBusinessForm() {
		return businessForm;
	}
	public void setBusinessForm(String businessForm) {
		this.businessForm = businessForm;
	}
	public String getAscription() {
		return ascription;
	}
	public void setAscription(String ascription) {
		this.ascription = ascription;
	}
	public String getPenalty() {
		return penalty;
	}
	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
	public String getRentFirst() {
		return rentFirst;
	}
	public void setRentFirst(String rentFirst) {
		this.rentFirst = rentFirst;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getFeeAlter() {
		return feeAlter;
	}
	public void setFeeAlter(String feeAlter) {
		this.feeAlter = feeAlter;
	}
	public String getContractBeginDate() {
		return contractBeginDate;
	}
	public void setContractBeginDate(String contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getBranchFirm() {
		return branchFirm;
	}
	public void setBranchFirm(String branchFirm) {
		this.branchFirm = branchFirm;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	@Override
	public String toString() {
		return "RawContract [contractId=" + contractId + ", carframeNum="
				+ carframeNum + ", carNum=" + carNum + ", businessForm="
				+ businessForm + ", ascription=" + ascription + ", penalty="
				+ penalty + ", rentFirst=" + rentFirst + ", rent=" + rent
				+ ", deposit=" + deposit + ", feeAlter=" + feeAlter
				+ ", contractBeginDate=" + contractBeginDate
				+ ", contractEndDate=" + contractEndDate + ", branchFirm="
				+ branchFirm + ", idNum=" + idNum + ", idName=" + idName
				+ ", remark=" + remark + "]";
	}
	
	
}
