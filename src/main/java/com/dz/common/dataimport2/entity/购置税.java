package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 购置税 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "购置税", catalog = "dzimport")
public class 购置税 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 购置税号;
	private String 发证机关;
	private String 车架号;
	private Timestamp 核发日期;
	private Double 购置税应收金额;
	private String 购置税大写;
	private String 登记人;
	private Timestamp 登记时间;

	// Constructors

	/** default constructor */
	public 购置税() {
	}

	/** full constructor */
	public 购置税(String 购置税号, String 发证机关, String 车架号, Timestamp 核发日期,
			Double 购置税应收金额, String 购置税大写, String 登记人, Timestamp 登记时间) {
		this.购置税号 = 购置税号;
		this.发证机关 = 发证机关;
		this.车架号 = 车架号;
		this.核发日期 = 核发日期;
		this.购置税应收金额 = 购置税应收金额;
		this.购置税大写 = 购置税大写;
		this.登记人 = 登记人;
		this.登记时间 = 登记时间;
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

	@Column(name = "购置税号", length = 50)
	public String get购置税号() {
		return this.购置税号;
	}

	public void set购置税号(String 购置税号) {
		this.购置税号 = 购置税号;
	}

	@Column(name = "发证机关", length = 50)
	public String get发证机关() {
		return this.发证机关;
	}

	public void set发证机关(String 发证机关) {
		this.发证机关 = 发证机关;
	}

	@Column(name = "车架号", length = 50)
	public String get车架号() {
		return this.车架号;
	}

	public void set车架号(String 车架号) {
		this.车架号 = 车架号;
	}

	@Column(name = "核发日期", length = 0)
	public Timestamp get核发日期() {
		return this.核发日期;
	}

	public void set核发日期(Timestamp 核发日期) {
		this.核发日期 = 核发日期;
	}

	@Column(name = "购置税应收金额", precision = 18)
	public Double get购置税应收金额() {
		return this.购置税应收金额;
	}

	public void set购置税应收金额(Double 购置税应收金额) {
		this.购置税应收金额 = 购置税应收金额;
	}

	@Column(name = "购置税大写", length = 50)
	public String get购置税大写() {
		return this.购置税大写;
	}

	public void set购置税大写(String 购置税大写) {
		this.购置税大写 = 购置税大写;
	}

	@Column(name = "登记人", length = 50)
	public String get登记人() {
		return this.登记人;
	}

	public void set登记人(String 登记人) {
		this.登记人 = 登记人;
	}

	@Column(name = "登记时间", length = 0)
	public Timestamp get登记时间() {
		return this.登记时间;
	}

	public void set登记时间(Timestamp 登记时间) {
		this.登记时间 = 登记时间;
	}

}