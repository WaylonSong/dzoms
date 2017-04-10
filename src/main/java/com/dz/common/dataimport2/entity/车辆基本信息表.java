package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆基本信息表 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "车辆基本信息表", catalog = "dzimport")
public class 车辆基本信息表 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 档案号;
	private String 车号;
	private String 厂牌车型;
	private String 颜色;
	private String 燃油类型;
	private String 顶灯号;
	private String 发动机号;
	private String 车架号;
	private Timestamp 购车时间;
	private String 发票号;
	private String 合格证号;
	private Timestamp 车户口登记日期;
	private String 车户口编号;
	private String 拍卖登记号;
	private String 优惠卡登记号;
	private Timestamp 营运证发放日期;
	private String 营运证号;
	private String 计价器号;
	private String 附加费证号;
	private String 车辆照片;
	private String 经营权编码;
	private Timestamp 经营权开始日期;
	private Timestamp 经营权终止日期;
	private String 交接班时间;
	private String 交接班地点;
	private Timestamp 预计报废日期;
	private Timestamp 实际报废日期;
	private String 分公司归属;
	private Boolean 是否自购;
	private Boolean 报废状态;
	private String 牌照类别;
	private String 承租人;
	private String 承租人身份证号;
	private String 燃气设备厂牌型号;
	private String 燃气设备编号;
	private Timestamp 燃气签订日期;
	private String 关键字;
	private String 条码;
	private String 卡号;
	private Boolean scbiaoji;

	// Constructors

	/** default constructor */
	public 车辆基本信息表() {
	}

	/** full constructor */
	public 车辆基本信息表(String 档案号, String 车号, String 厂牌车型, String 颜色, String 燃油类型,
			String 顶灯号, String 发动机号, String 车架号, Timestamp 购车时间, String 发票号,
			String 合格证号, Timestamp 车户口登记日期, String 车户口编号, String 拍卖登记号,
			String 优惠卡登记号, Timestamp 营运证发放日期, String 营运证号, String 计价器号,
			String 附加费证号, String 车辆照片, String 经营权编码, Timestamp 经营权开始日期,
			Timestamp 经营权终止日期, String 交接班时间, String 交接班地点, Timestamp 预计报废日期,
			Timestamp 实际报废日期, String 分公司归属, Boolean 是否自购, Boolean 报废状态,
			String 牌照类别, String 承租人, String 承租人身份证号, String 燃气设备厂牌型号,
			String 燃气设备编号, Timestamp 燃气签订日期, String 关键字, String 条码, String 卡号,
			Boolean scbiaoji) {
		this.档案号 = 档案号;
		this.车号 = 车号;
		this.厂牌车型 = 厂牌车型;
		this.颜色 = 颜色;
		this.燃油类型 = 燃油类型;
		this.顶灯号 = 顶灯号;
		this.发动机号 = 发动机号;
		this.车架号 = 车架号;
		this.购车时间 = 购车时间;
		this.发票号 = 发票号;
		this.合格证号 = 合格证号;
		this.车户口登记日期 = 车户口登记日期;
		this.车户口编号 = 车户口编号;
		this.拍卖登记号 = 拍卖登记号;
		this.优惠卡登记号 = 优惠卡登记号;
		this.营运证发放日期 = 营运证发放日期;
		this.营运证号 = 营运证号;
		this.计价器号 = 计价器号;
		this.附加费证号 = 附加费证号;
		this.车辆照片 = 车辆照片;
		this.经营权编码 = 经营权编码;
		this.经营权开始日期 = 经营权开始日期;
		this.经营权终止日期 = 经营权终止日期;
		this.交接班时间 = 交接班时间;
		this.交接班地点 = 交接班地点;
		this.预计报废日期 = 预计报废日期;
		this.实际报废日期 = 实际报废日期;
		this.分公司归属 = 分公司归属;
		this.是否自购 = 是否自购;
		this.报废状态 = 报废状态;
		this.牌照类别 = 牌照类别;
		this.承租人 = 承租人;
		this.承租人身份证号 = 承租人身份证号;
		this.燃气设备厂牌型号 = 燃气设备厂牌型号;
		this.燃气设备编号 = 燃气设备编号;
		this.燃气签订日期 = 燃气签订日期;
		this.关键字 = 关键字;
		this.条码 = 条码;
		this.卡号 = 卡号;
		this.scbiaoji = scbiaoji;
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

	@Column(name = "档案号", length = 50)
	public String get档案号() {
		return this.档案号;
	}

	public void set档案号(String 档案号) {
		this.档案号 = 档案号;
	}

	@Column(name = "车号", length = 50)
	public String get车号() {
		return this.车号;
	}

	public void set车号(String 车号) {
		this.车号 = 车号;
	}

	@Column(name = "厂牌车型", length = 50)
	public String get厂牌车型() {
		return this.厂牌车型;
	}

	public void set厂牌车型(String 厂牌车型) {
		this.厂牌车型 = 厂牌车型;
	}

	@Column(name = "颜色", length = 50)
	public String get颜色() {
		return this.颜色;
	}

	public void set颜色(String 颜色) {
		this.颜色 = 颜色;
	}

	@Column(name = "燃油类型", length = 50)
	public String get燃油类型() {
		return this.燃油类型;
	}

	public void set燃油类型(String 燃油类型) {
		this.燃油类型 = 燃油类型;
	}

	@Column(name = "顶灯号", length = 50)
	public String get顶灯号() {
		return this.顶灯号;
	}

	public void set顶灯号(String 顶灯号) {
		this.顶灯号 = 顶灯号;
	}

	@Column(name = "发动机号", length = 50)
	public String get发动机号() {
		return this.发动机号;
	}

	public void set发动机号(String 发动机号) {
		this.发动机号 = 发动机号;
	}

	@Column(name = "车架号", length = 50)
	public String get车架号() {
		return this.车架号;
	}

	public void set车架号(String 车架号) {
		this.车架号 = 车架号;
	}

	@Column(name = "购车时间", length = 0)
	public Timestamp get购车时间() {
		return this.购车时间;
	}

	public void set购车时间(Timestamp 购车时间) {
		this.购车时间 = 购车时间;
	}

	@Column(name = "发票号", length = 50)
	public String get发票号() {
		return this.发票号;
	}

	public void set发票号(String 发票号) {
		this.发票号 = 发票号;
	}

	@Column(name = "合格证号", length = 50)
	public String get合格证号() {
		return this.合格证号;
	}

	public void set合格证号(String 合格证号) {
		this.合格证号 = 合格证号;
	}

	@Column(name = "车户口登记日期", length = 0)
	public Timestamp get车户口登记日期() {
		return this.车户口登记日期;
	}

	public void set车户口登记日期(Timestamp 车户口登记日期) {
		this.车户口登记日期 = 车户口登记日期;
	}

	@Column(name = "车户口编号", length = 50)
	public String get车户口编号() {
		return this.车户口编号;
	}

	public void set车户口编号(String 车户口编号) {
		this.车户口编号 = 车户口编号;
	}

	@Column(name = "拍卖登记号", length = 50)
	public String get拍卖登记号() {
		return this.拍卖登记号;
	}

	public void set拍卖登记号(String 拍卖登记号) {
		this.拍卖登记号 = 拍卖登记号;
	}

	@Column(name = "优惠卡登记号", length = 50)
	public String get优惠卡登记号() {
		return this.优惠卡登记号;
	}

	public void set优惠卡登记号(String 优惠卡登记号) {
		this.优惠卡登记号 = 优惠卡登记号;
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

	@Column(name = "计价器号", length = 50)
	public String get计价器号() {
		return this.计价器号;
	}

	public void set计价器号(String 计价器号) {
		this.计价器号 = 计价器号;
	}

	@Column(name = "附加费证号", length = 50)
	public String get附加费证号() {
		return this.附加费证号;
	}

	public void set附加费证号(String 附加费证号) {
		this.附加费证号 = 附加费证号;
	}

	@Column(name = "车辆照片")
	public String get车辆照片() {
		return this.车辆照片;
	}

	public void set车辆照片(String 车辆照片) {
		this.车辆照片 = 车辆照片;
	}

	@Column(name = "经营权编码", length = 50)
	public String get经营权编码() {
		return this.经营权编码;
	}

	public void set经营权编码(String 经营权编码) {
		this.经营权编码 = 经营权编码;
	}

	@Column(name = "经营权开始日期", length = 0)
	public Timestamp get经营权开始日期() {
		return this.经营权开始日期;
	}

	public void set经营权开始日期(Timestamp 经营权开始日期) {
		this.经营权开始日期 = 经营权开始日期;
	}

	@Column(name = "经营权终止日期", length = 0)
	public Timestamp get经营权终止日期() {
		return this.经营权终止日期;
	}

	public void set经营权终止日期(Timestamp 经营权终止日期) {
		this.经营权终止日期 = 经营权终止日期;
	}

	@Column(name = "交接班时间", length = 50)
	public String get交接班时间() {
		return this.交接班时间;
	}

	public void set交接班时间(String 交接班时间) {
		this.交接班时间 = 交接班时间;
	}

	@Column(name = "交接班地点", length = 50)
	public String get交接班地点() {
		return this.交接班地点;
	}

	public void set交接班地点(String 交接班地点) {
		this.交接班地点 = 交接班地点;
	}

	@Column(name = "预计报废日期", length = 0)
	public Timestamp get预计报废日期() {
		return this.预计报废日期;
	}

	public void set预计报废日期(Timestamp 预计报废日期) {
		this.预计报废日期 = 预计报废日期;
	}

	@Column(name = "实际报废日期", length = 0)
	public Timestamp get实际报废日期() {
		return this.实际报废日期;
	}

	public void set实际报废日期(Timestamp 实际报废日期) {
		this.实际报废日期 = 实际报废日期;
	}

	@Column(name = "分公司归属", length = 50)
	public String get分公司归属() {
		return this.分公司归属;
	}

	public void set分公司归属(String 分公司归属) {
		this.分公司归属 = 分公司归属;
	}

	@Column(name = "是否自购")
	public Boolean get是否自购() {
		return this.是否自购;
	}

	public void set是否自购(Boolean 是否自购) {
		this.是否自购 = 是否自购;
	}

	@Column(name = "报废状态")
	public Boolean get报废状态() {
		return this.报废状态;
	}

	public void set报废状态(Boolean 报废状态) {
		this.报废状态 = 报废状态;
	}

	@Column(name = "牌照类别", length = 50)
	public String get牌照类别() {
		return this.牌照类别;
	}

	public void set牌照类别(String 牌照类别) {
		this.牌照类别 = 牌照类别;
	}

	@Column(name = "承租人", length = 50)
	public String get承租人() {
		return this.承租人;
	}

	public void set承租人(String 承租人) {
		this.承租人 = 承租人;
	}

	@Column(name = "承租人身份证号", length = 50)
	public String get承租人身份证号() {
		return this.承租人身份证号;
	}

	public void set承租人身份证号(String 承租人身份证号) {
		this.承租人身份证号 = 承租人身份证号;
	}

	@Column(name = "燃气设备厂牌型号", length = 50)
	public String get燃气设备厂牌型号() {
		return this.燃气设备厂牌型号;
	}

	public void set燃气设备厂牌型号(String 燃气设备厂牌型号) {
		this.燃气设备厂牌型号 = 燃气设备厂牌型号;
	}

	@Column(name = "燃气设备编号", length = 50)
	public String get燃气设备编号() {
		return this.燃气设备编号;
	}

	public void set燃气设备编号(String 燃气设备编号) {
		this.燃气设备编号 = 燃气设备编号;
	}

	@Column(name = "燃气签订日期", length = 0)
	public Timestamp get燃气签订日期() {
		return this.燃气签订日期;
	}

	public void set燃气签订日期(Timestamp 燃气签订日期) {
		this.燃气签订日期 = 燃气签订日期;
	}

	@Column(name = "关键字", length = 50)
	public String get关键字() {
		return this.关键字;
	}

	public void set关键字(String 关键字) {
		this.关键字 = 关键字;
	}

	@Column(name = "条码", length = 50)
	public String get条码() {
		return this.条码;
	}

	public void set条码(String 条码) {
		this.条码 = 条码;
	}

	@Column(name = "卡号", length = 50)
	public String get卡号() {
		return this.卡号;
	}

	public void set卡号(String 卡号) {
		this.卡号 = 卡号;
	}

	@Column(name = "scbiaoji")
	public Boolean getScbiaoji() {
		return this.scbiaoji;
	}

	public void setScbiaoji(Boolean scbiaoji) {
		this.scbiaoji = scbiaoji;
	}

}