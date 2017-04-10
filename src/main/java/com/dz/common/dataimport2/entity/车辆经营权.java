package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆经营权 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "车辆经营权", catalog = "dzimport")
public class 车辆经营权 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 表单编号;
	private String 车架号;
	private String 车牌号;
	private String 经营权证号;
	private Timestamp 起始日期;
	private Timestamp 终止日期;
	private String 登记人;
	private Timestamp 登记时间;
	private String 备注;

	// Constructors

	/** default constructor */
	public 车辆经营权() {
	}

	/** full constructor */
	public 车辆经营权(String 表单编号, String 车架号, String 车牌号, String 经营权证号,
			Timestamp 起始日期, Timestamp 终止日期, String 登记人, Timestamp 登记时间,
			String 备注) {
		this.表单编号 = 表单编号;
		this.车架号 = 车架号;
		this.车牌号 = 车牌号;
		this.经营权证号 = 经营权证号;
		this.起始日期 = 起始日期;
		this.终止日期 = 终止日期;
		this.登记人 = 登记人;
		this.登记时间 = 登记时间;
		this.备注 = 备注;
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

	@Column(name = "车架号", length = 50)
	public String get车架号() {
		return this.车架号;
	}

	public void set车架号(String 车架号) {
		this.车架号 = 车架号;
	}

	@Column(name = "车牌号", length = 50)
	public String get车牌号() {
		return this.车牌号;
	}

	public void set车牌号(String 车牌号) {
		this.车牌号 = 车牌号;
	}

	@Column(name = "经营权证号", length = 50)
	public String get经营权证号() {
		return this.经营权证号;
	}

	public void set经营权证号(String 经营权证号) {
		this.经营权证号 = 经营权证号;
	}

	@Column(name = "起始日期", length = 0)
	public Timestamp get起始日期() {
		return this.起始日期;
	}

	public void set起始日期(Timestamp 起始日期) {
		this.起始日期 = 起始日期;
	}

	@Column(name = "终止日期", length = 0)
	public Timestamp get终止日期() {
		return this.终止日期;
	}

	public void set终止日期(Timestamp 终止日期) {
		this.终止日期 = 终止日期;
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

	@Column(name = "备注", length = 5000)
	public String get备注() {
		return this.备注;
	}

	public void set备注(String 备注) {
		this.备注 = 备注;
	}

}