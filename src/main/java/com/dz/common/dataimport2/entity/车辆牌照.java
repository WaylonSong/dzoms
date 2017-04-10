package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆牌照 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "车辆牌照", catalog = "dzimport")
public class 车辆牌照 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 牌照类别;
	private String 旧车牌号;
	private String 新车牌号;
	private String 车架号;
	private Double 拍卖费;
	private Timestamp 更新登记日期;
	private String 登记证书号;
	private String 牌照编号;
	private String 电子档案号;
	private String 档案号;
	private Timestamp 实际报废日期;
	private String 拍卖号;
	private String 车辆照片;
	private Boolean 抵押;
	private Boolean 不抵押;
	private String 抵押权人;
	private Timestamp 抵押时间;
	private Timestamp 解除抵押时间;
	private String 登记人;
	private Timestamp 登记时间;

	// Constructors

	/** default constructor */
	public 车辆牌照() {
	}

	/** full constructor */
	public 车辆牌照(String 牌照类别, String 旧车牌号, String 新车牌号, String 车架号, Double 拍卖费,
			Timestamp 更新登记日期, String 登记证书号, String 牌照编号, String 电子档案号,
			String 档案号, Timestamp 实际报废日期, String 拍卖号, String 车辆照片, Boolean 抵押,
			Boolean 不抵押, String 抵押权人, Timestamp 抵押时间, Timestamp 解除抵押时间,
			String 登记人, Timestamp 登记时间) {
		this.牌照类别 = 牌照类别;
		this.旧车牌号 = 旧车牌号;
		this.新车牌号 = 新车牌号;
		this.车架号 = 车架号;
		this.拍卖费 = 拍卖费;
		this.更新登记日期 = 更新登记日期;
		this.登记证书号 = 登记证书号;
		this.牌照编号 = 牌照编号;
		this.电子档案号 = 电子档案号;
		this.档案号 = 档案号;
		this.实际报废日期 = 实际报废日期;
		this.拍卖号 = 拍卖号;
		this.车辆照片 = 车辆照片;
		this.抵押 = 抵押;
		this.不抵押 = 不抵押;
		this.抵押权人 = 抵押权人;
		this.抵押时间 = 抵押时间;
		this.解除抵押时间 = 解除抵押时间;
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

	@Column(name = "牌照类别", length = 50)
	public String get牌照类别() {
		return this.牌照类别;
	}

	public void set牌照类别(String 牌照类别) {
		this.牌照类别 = 牌照类别;
	}

	@Column(name = "旧车牌号", length = 50)
	public String get旧车牌号() {
		return this.旧车牌号;
	}

	public void set旧车牌号(String 旧车牌号) {
		this.旧车牌号 = 旧车牌号;
	}

	@Column(name = "新车牌号", length = 50)
	public String get新车牌号() {
		return this.新车牌号;
	}

	public void set新车牌号(String 新车牌号) {
		this.新车牌号 = 新车牌号;
	}

	@Column(name = "车架号", length = 50)
	public String get车架号() {
		return this.车架号;
	}

	public void set车架号(String 车架号) {
		this.车架号 = 车架号;
	}

	@Column(name = "拍卖费", precision = 18)
	public Double get拍卖费() {
		return this.拍卖费;
	}

	public void set拍卖费(Double 拍卖费) {
		this.拍卖费 = 拍卖费;
	}

	@Column(name = "更新登记日期", length = 0)
	public Timestamp get更新登记日期() {
		return this.更新登记日期;
	}

	public void set更新登记日期(Timestamp 更新登记日期) {
		this.更新登记日期 = 更新登记日期;
	}

	@Column(name = "登记证书号", length = 50)
	public String get登记证书号() {
		return this.登记证书号;
	}

	public void set登记证书号(String 登记证书号) {
		this.登记证书号 = 登记证书号;
	}

	@Column(name = "牌照编号", length = 50)
	public String get牌照编号() {
		return this.牌照编号;
	}

	public void set牌照编号(String 牌照编号) {
		this.牌照编号 = 牌照编号;
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

	@Column(name = "实际报废日期", length = 0)
	public Timestamp get实际报废日期() {
		return this.实际报废日期;
	}

	public void set实际报废日期(Timestamp 实际报废日期) {
		this.实际报废日期 = 实际报废日期;
	}

	@Column(name = "拍卖号", length = 50)
	public String get拍卖号() {
		return this.拍卖号;
	}

	public void set拍卖号(String 拍卖号) {
		this.拍卖号 = 拍卖号;
	}

	@Column(name = "车辆照片")
	public String get车辆照片() {
		return this.车辆照片;
	}

	public void set车辆照片(String 车辆照片) {
		this.车辆照片 = 车辆照片;
	}

	@Column(name = "抵押")
	public Boolean get抵押() {
		return this.抵押;
	}

	public void set抵押(Boolean 抵押) {
		this.抵押 = 抵押;
	}

	@Column(name = "不抵押")
	public Boolean get不抵押() {
		return this.不抵押;
	}

	public void set不抵押(Boolean 不抵押) {
		this.不抵押 = 不抵押;
	}

	@Column(name = "抵押权人", length = 50)
	public String get抵押权人() {
		return this.抵押权人;
	}

	public void set抵押权人(String 抵押权人) {
		this.抵押权人 = 抵押权人;
	}

	@Column(name = "抵押时间", length = 0)
	public Timestamp get抵押时间() {
		return this.抵押时间;
	}

	public void set抵押时间(Timestamp 抵押时间) {
		this.抵押时间 = 抵押时间;
	}

	@Column(name = "解除抵押时间", length = 0)
	public Timestamp get解除抵押时间() {
		return this.解除抵押时间;
	}

	public void set解除抵押时间(Timestamp 解除抵押时间) {
		this.解除抵押时间 = 解除抵押时间;
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