package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 驾驶员银行卡号 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "驾驶员银行卡号", catalog = "dzimport")
public class 驾驶员银行卡号 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 表单编号;
	private String 车牌号;
	private String 驾驶员;
	private String 身份证号;
	private String 银行卡号;
	private String 录入人;
	private Timestamp 录入时间;
	private String 银行卡类别;

	// Constructors

	/** default constructor */
	public 驾驶员银行卡号() {
	}

	/** full constructor */
	public 驾驶员银行卡号(String 表单编号, String 车牌号, String 驾驶员, String 身份证号,
			String 银行卡号, String 录入人, Timestamp 录入时间, String 银行卡类别) {
		this.表单编号 = 表单编号;
		this.车牌号 = 车牌号;
		this.驾驶员 = 驾驶员;
		this.身份证号 = 身份证号;
		this.银行卡号 = 银行卡号;
		this.录入人 = 录入人;
		this.录入时间 = 录入时间;
		this.银行卡类别 = 银行卡类别;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "编号", unique = true, nullable = false)
	public Integer get编号() {
		return this.编号;
	}

	public void set编号(Integer 编号) {
		this.编号 = 编号;
	}

	@Column(name = "表单编号", length = 50)
	public String get表单编号() {
		return this.表单编号;
	}

	public void set表单编号(String 表单编号) {
		this.表单编号 = 表单编号;
	}

	@Column(name = "车牌号", length = 50)
	public String get车牌号() {
		return this.车牌号;
	}

	public void set车牌号(String 车牌号) {
		this.车牌号 = 车牌号;
	}

	@Column(name = "驾驶员", length = 50)
	public String get驾驶员() {
		return this.驾驶员;
	}

	public void set驾驶员(String 驾驶员) {
		this.驾驶员 = 驾驶员;
	}

	@Column(name = "身份证号", length = 50)
	public String get身份证号() {
		return this.身份证号;
	}

	public void set身份证号(String 身份证号) {
		this.身份证号 = 身份证号;
	}

	@Column(name = "银行卡号", length = 50)
	public String get银行卡号() {
		return this.银行卡号;
	}

	public void set银行卡号(String 银行卡号) {
		this.银行卡号 = 银行卡号;
	}

	@Column(name = "录入人", length = 50)
	public String get录入人() {
		return this.录入人;
	}

	public void set录入人(String 录入人) {
		this.录入人 = 录入人;
	}

	@Column(name = "录入时间", length = 0)
	public Timestamp get录入时间() {
		return this.录入时间;
	}

	public void set录入时间(Timestamp 录入时间) {
		this.录入时间 = 录入时间;
	}

	@Column(name = "银行卡类别", length = 50)
	public String get银行卡类别() {
		return this.银行卡类别;
	}

	public void set银行卡类别(String 银行卡类别) {
		this.银行卡类别 = 银行卡类别;
	}

}