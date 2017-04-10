package com.dz.common.test;

import java.math.BigDecimal;

public class AccountItem {
	private String licenseNum,name,cardNum;
	private BigDecimal Money;
	public String getLicenseNum() {
		return licenseNum;
	}
	public String getName() {
		return name;
	}
	public String getCardNum() {
		return cardNum;
	}
	public BigDecimal getMoney() {
		return Money;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public void setMoney(BigDecimal money) {
		Money = money;
	}
	@Override
	public String toString() {
		return "AccountItem [licenseNum=" + licenseNum + ", name=" + name
				+ ", cardNum=" + cardNum + ", Money=" + Money + "]";
	}
	public AccountItem(String licenseNum, String name, String cardNum,
			BigDecimal money) {
		super();
		this.licenseNum = licenseNum;
		this.name = name;
		this.cardNum = cardNum;
		Money = money;
	}
	public AccountItem(){
		
	}
	
}
