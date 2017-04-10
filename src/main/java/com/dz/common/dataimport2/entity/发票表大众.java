package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 发票表大众 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "发票表_大众", catalog = "dzimport")
public class 发票表大众 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 车架号;
	private Timestamp 开票日期;
	private String 发票号;
	private Double 价税合计;
	private String 价税合计大写;
	private String 销货单位名称;
	private String 登记人;
	private Timestamp 登记时间;

	// Constructors

	/** default constructor */
	public 发票表大众() {
	}

	/** full constructor */
	public 发票表大众(String 车架号, Timestamp 开票日期, String 发票号, Double 价税合计,
			String 价税合计大写, String 销货单位名称, String 登记人, Timestamp 登记时间) {
		this.车架号 = 车架号;
		this.开票日期 = 开票日期;
		this.发票号 = 发票号;
		this.价税合计 = 价税合计;
		this.价税合计大写 = 价税合计大写;
		this.销货单位名称 = 销货单位名称;
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

	@Column(name = "车架号", length = 50)
	public String get车架号() {
		return this.车架号;
	}

	public void set车架号(String 车架号) {
		this.车架号 = 车架号;
	}

	@Column(name = "开票日期", length = 0)
	public Timestamp get开票日期() {
		return this.开票日期;
	}

	public void set开票日期(Timestamp 开票日期) {
		this.开票日期 = 开票日期;
	}

	@Column(name = "发票号", length = 50)
	public String get发票号() {
		return this.发票号;
	}

	public void set发票号(String 发票号) {
		this.发票号 = 发票号;
	}

	@Column(name = "价税合计", precision = 18)
	public Double get价税合计() {
		return this.价税合计;
	}

	public void set价税合计(Double 价税合计) {
		this.价税合计 = 价税合计;
	}

	@Column(name = "价税合计大写", length = 50)
	public String get价税合计大写() {
		return this.价税合计大写;
	}

	public void set价税合计大写(String 价税合计大写) {
		this.价税合计大写 = 价税合计大写;
	}

	@Column(name = "销货单位名称", length = 50)
	public String get销货单位名称() {
		return this.销货单位名称;
	}

	public void set销货单位名称(String 销货单位名称) {
		this.销货单位名称 = 销货单位名称;
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