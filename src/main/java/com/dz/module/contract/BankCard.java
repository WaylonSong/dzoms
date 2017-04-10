package com.dz.module.contract;

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
import static com.dz.common.other.TimeComm.*;

/**
 * BankCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bank_card", catalog = "dzomsdb", uniqueConstraints = {
		@UniqueConstraint(columnNames = "cardNumber") })
public class BankCard implements java.io.Serializable {

	// Fields
	private Integer id;
	private String idNumber;
	private String carNum;
	private String cardClass;
	private String cardNumber;
	private Boolean isDefaultPay;
	private Boolean isDefaultRecive;
	private Integer operator;
	private Date opeTime;

	// Constructors

	/** default constructor */
	public BankCard() {
	}

	/** minimal constructor */
	public BankCard(Boolean isDefaultPay, Boolean isDefaultRecive) {
		this.isDefaultPay = isDefaultPay;
		this.isDefaultRecive = isDefaultRecive;
	}

	/** full constructor */
	public BankCard(String idNumber, String cardClass, String cardNumber,
			Boolean isDefaultPay, Boolean isDefaultRecive, Integer operator,
			Date opeTime) {
		this.idNumber = idNumber;
		this.cardClass = cardClass;
		this.cardNumber = cardNumber;
		this.isDefaultPay = isDefaultPay;
		this.isDefaultRecive = isDefaultRecive;
		this.operator = operator;
		this.opeTime = opeTime;
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

	@Column(name = "idNumber", length = 30)
	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Column(name = "cardClass", length = 20)
	public String getCardClass() {
		return this.cardClass;
	}

	public void setCardClass(String cardClass) {
		this.cardClass = cardClass;
	}

	@Column(name = "cardNumber", unique = true, length = 20)
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name = "isDefaultPay", nullable = false)
	public Boolean getIsDefaultPay() {
		return this.isDefaultPay;
	}

	public void setIsDefaultPay(Boolean isDefaultPay) {
		this.isDefaultPay = isDefaultPay;
	}

	@Column(name = "isDefaultRecive", nullable = false)
	public Boolean getIsDefaultRecive() {
		return this.isDefaultRecive;
	}

	public void setIsDefaultRecive(Boolean isDefaultRecive) {
		this.isDefaultRecive = isDefaultRecive;
	}

	@Column(name = "operator")
	public Integer getOperator() {
		return this.operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opeTime", length = 0)
	public Date getOpeTime() {
		return this.opeTime;
	}

	public void setOpeTime(Date opeTime) {
		this.opeTime = convertDate(opeTime);
	}

	@Column(name = "carNum", length = 30)
	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

}