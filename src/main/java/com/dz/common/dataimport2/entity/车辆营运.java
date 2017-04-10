package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆营运 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "车辆营运", catalog = "dzimport")
public class 车辆营运 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 营运编号;
	private String 车架号;
	private String 车牌号;
	private Timestamp 营运证发放日期;
	private String 营运证号;
	private String 经营许可证号;
	private Timestamp 二级维护开始日期;
	private Timestamp 二级维护结束日期;
	private Timestamp 计价器检测开始日期;
	private Timestamp 计价器检测结束日期;
	private String 电子档案号;
	private String 档案号;
	private String 计价器号;
	private String 登记人;
	private Timestamp 登记时间;
	private Timestamp 经营权起始日期;
	private Timestamp 经营权终止日期;

	// Constructors

	/** default constructor */
	public 车辆营运() {
	}

	/** full constructor */
	public 车辆营运(String 营运编号, String 车架号, String 车牌号, Timestamp 营运证发放日期,
			String 营运证号, String 经营许可证号, Timestamp 二级维护开始日期, Timestamp 二级维护结束日期,
			Timestamp 计价器检测开始日期, Timestamp 计价器检测结束日期, String 电子档案号, String 档案号,
			String 计价器号, String 登记人, Timestamp 登记时间, Timestamp 经营权起始日期,
			Timestamp 经营权终止日期) {
		this.营运编号 = 营运编号;
		this.车架号 = 车架号;
		this.车牌号 = 车牌号;
		this.营运证发放日期 = 营运证发放日期;
		this.营运证号 = 营运证号;
		this.经营许可证号 = 经营许可证号;
		this.二级维护开始日期 = 二级维护开始日期;
		this.二级维护结束日期 = 二级维护结束日期;
		this.计价器检测开始日期 = 计价器检测开始日期;
		this.计价器检测结束日期 = 计价器检测结束日期;
		this.电子档案号 = 电子档案号;
		this.档案号 = 档案号;
		this.计价器号 = 计价器号;
		this.登记人 = 登记人;
		this.登记时间 = 登记时间;
		this.经营权起始日期 = 经营权起始日期;
		this.经营权终止日期 = 经营权终止日期;
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

	@Column(name = "营运编号", length = 50)
	public String get营运编号() {
		return this.营运编号;
	}

	public void set营运编号(String 营运编号) {
		this.营运编号 = 营运编号;
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

	@Column(name = "营运证发放日期", length = 0)
	public Timestamp get营运证发放日期() {
		return this.营运证发放日期;
	}

	public void set营运证发放日期(Timestamp 营运证发放日期) {
		this.营运证发放日期 = 营运证发放日期;
	}

	@Column(name = "营运证号", length = 50)
	public String get营运证号() {
		return this.营运证号;
	}

	public void set营运证号(String 营运证号) {
		this.营运证号 = 营运证号;
	}

	@Column(name = "经营许可证号", length = 50)
	public String get经营许可证号() {
		return this.经营许可证号;
	}

	public void set经营许可证号(String 经营许可证号) {
		this.经营许可证号 = 经营许可证号;
	}

	@Column(name = "二级维护开始日期", length = 0)
	public Timestamp get二级维护开始日期() {
		return this.二级维护开始日期;
	}

	public void set二级维护开始日期(Timestamp 二级维护开始日期) {
		this.二级维护开始日期 = 二级维护开始日期;
	}

	@Column(name = "二级维护结束日期", length = 0)
	public Timestamp get二级维护结束日期() {
		return this.二级维护结束日期;
	}

	public void set二级维护结束日期(Timestamp 二级维护结束日期) {
		this.二级维护结束日期 = 二级维护结束日期;
	}

	@Column(name = "计价器检测开始日期", length = 0)
	public Timestamp get计价器检测开始日期() {
		return this.计价器检测开始日期;
	}

	public void set计价器检测开始日期(Timestamp 计价器检测开始日期) {
		this.计价器检测开始日期 = 计价器检测开始日期;
	}

	@Column(name = "计价器检测结束日期", length = 0)
	public Timestamp get计价器检测结束日期() {
		return this.计价器检测结束日期;
	}

	public void set计价器检测结束日期(Timestamp 计价器检测结束日期) {
		this.计价器检测结束日期 = 计价器检测结束日期;
	}

	@Column(name = "电子档案号", length = 50)
	public String get电子档案号() {
		return this.电子档案号;
	}

	public void set电子档案号(String 电子档案号) {
		this.电子档案号 = 电子档案号;
	}

	@Column(name = "档案号", length = 50)
	public String get档案号() {
		return this.档案号;
	}

	public void set档案号(String 档案号) {
		this.档案号 = 档案号;
	}

	@Column(name = "计价器号", length = 50)
	public String get计价器号() {
		return this.计价器号;
	}

	public void set计价器号(String 计价器号) {
		this.计价器号 = 计价器号;
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

	@Column(name = "经营权起始日期", length = 0)
	public Timestamp get经营权起始日期() {
		return this.经营权起始日期;
	}

	public void set经营权起始日期(Timestamp 经营权起始日期) {
		this.经营权起始日期 = 经营权起始日期;
	}

	@Column(name = "经营权终止日期", length = 0)
	public Timestamp get经营权终止日期() {
		return this.经营权终止日期;
	}

	public void set经营权终止日期(Timestamp 经营权终止日期) {
		this.经营权终止日期 = 经营权终止日期;
	}

}