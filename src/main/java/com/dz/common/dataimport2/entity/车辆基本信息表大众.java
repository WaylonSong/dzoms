package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆基本信息表大众 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "车辆基本信息表_大众", catalog = "dzimport")
public class 车辆基本信息表大众 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 表单编号;
	private String 档案号;
	private String 车号;
	private String 厂牌车型;
	private String 车辆名称;
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
	private String 车辆制造企业名称;
	private String 车辆型号;
	private Integer 排量;
	private Integer 功率;
	private String 排放标准;
	private Integer 外廓尺寸数据1;
	private Integer 外廓尺寸数据2;
	private Integer 外廓尺寸数据3;
	private Integer 轮胎数;
	private String 轮胎规格;
	private Integer 轮距前;
	private Integer 轮距后;
	private Integer 轴距;
	private Integer 轴数;
	private String 转向形式;
	private Float 总质量;
	private Float 整备质量;
	private Integer 额定载客;
	private Float 最高车速;
	private Timestamp 车辆制造日期;
	private Timestamp 发证日期;
	private String 发动机型号;
	private String 拓印;
	private String 归属部门;
	private String 承租人;
	private String 承租人身份证号;
	private Float 核定载质量;
	private Integer 年平均行驶里程;
	private Double 新车购置价;
	private String 使用性质;
	private String 行驶区域;
	private String 登记人;
	private Timestamp 登记时间;
	private String 燃气设备厂牌型号;
	private String 燃气设备编号;
	private Timestamp 燃气签订日期;
	private String 关键字;

	// Constructors

	/** default constructor */
	public 车辆基本信息表大众() {
	}

	/** full constructor */
	public 车辆基本信息表大众(String 表单编号, String 档案号, String 车号, String 厂牌车型,
			String 车辆名称, String 颜色, String 燃油类型, String 顶灯号, String 发动机号,
			String 车架号, Timestamp 购车时间, String 发票号, String 合格证号,
			Timestamp 车户口登记日期, String 车户口编号, String 拍卖登记号, String 优惠卡登记号,
			Timestamp 营运证发放日期, String 营运证号, String 计价器号, String 附加费证号,
			String 车辆照片, String 经营权编码, Timestamp 经营权开始日期, Timestamp 经营权终止日期,
			String 交接班时间, String 交接班地点, Timestamp 预计报废日期, Timestamp 实际报废日期,
			String 分公司归属, String 车辆制造企业名称, String 车辆型号, Integer 排量, Integer 功率,
			String 排放标准, Integer 外廓尺寸数据1, Integer 外廓尺寸数据2, Integer 外廓尺寸数据3,
			Integer 轮胎数, String 轮胎规格, Integer 轮距前, Integer 轮距后, Integer 轴距,
			Integer 轴数, String 转向形式, Float 总质量, Float 整备质量, Integer 额定载客,
			Float 最高车速, Timestamp 车辆制造日期, Timestamp 发证日期, String 发动机型号,
			String 拓印, String 归属部门, String 承租人, String 承租人身份证号, Float 核定载质量,
			Integer 年平均行驶里程, Double 新车购置价, String 使用性质, String 行驶区域,
			String 登记人, Timestamp 登记时间, String 燃气设备厂牌型号, String 燃气设备编号,
			Timestamp 燃气签订日期, String 关键字) {
		this.表单编号 = 表单编号;
		this.档案号 = 档案号;
		this.车号 = 车号;
		this.厂牌车型 = 厂牌车型;
		this.车辆名称 = 车辆名称;
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
		this.车辆制造企业名称 = 车辆制造企业名称;
		this.车辆型号 = 车辆型号;
		this.排量 = 排量;
		this.功率 = 功率;
		this.排放标准 = 排放标准;
		this.外廓尺寸数据1 = 外廓尺寸数据1;
		this.外廓尺寸数据2 = 外廓尺寸数据2;
		this.外廓尺寸数据3 = 外廓尺寸数据3;
		this.轮胎数 = 轮胎数;
		this.轮胎规格 = 轮胎规格;
		this.轮距前 = 轮距前;
		this.轮距后 = 轮距后;
		this.轴距 = 轴距;
		this.轴数 = 轴数;
		this.转向形式 = 转向形式;
		this.总质量 = 总质量;
		this.整备质量 = 整备质量;
		this.额定载客 = 额定载客;
		this.最高车速 = 最高车速;
		this.车辆制造日期 = 车辆制造日期;
		this.发证日期 = 发证日期;
		this.发动机型号 = 发动机型号;
		this.拓印 = 拓印;
		this.归属部门 = 归属部门;
		this.承租人 = 承租人;
		this.承租人身份证号 = 承租人身份证号;
		this.核定载质量 = 核定载质量;
		this.年平均行驶里程 = 年平均行驶里程;
		this.新车购置价 = 新车购置价;
		this.使用性质 = 使用性质;
		this.行驶区域 = 行驶区域;
		this.登记人 = 登记人;
		this.登记时间 = 登记时间;
		this.燃气设备厂牌型号 = 燃气设备厂牌型号;
		this.燃气设备编号 = 燃气设备编号;
		this.燃气签订日期 = 燃气签订日期;
		this.关键字 = 关键字;
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

	@Column(name = "车辆名称", length = 50)
	public String get车辆名称() {
		return this.车辆名称;
	}

	public void set车辆名称(String 车辆名称) {
		this.车辆名称 = 车辆名称;
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

	@Column(name = "车辆制造企业名称", length = 50)
	public String get车辆制造企业名称() {
		return this.车辆制造企业名称;
	}

	public void set车辆制造企业名称(String 车辆制造企业名称) {
		this.车辆制造企业名称 = 车辆制造企业名称;
	}

	@Column(name = "车辆型号", length = 50)
	public String get车辆型号() {
		return this.车辆型号;
	}

	public void set车辆型号(String 车辆型号) {
		this.车辆型号 = 车辆型号;
	}

	@Column(name = "排量")
	public Integer get排量() {
		return this.排量;
	}

	public void set排量(Integer 排量) {
		this.排量 = 排量;
	}

	@Column(name = "功率")
	public Integer get功率() {
		return this.功率;
	}

	public void set功率(Integer 功率) {
		this.功率 = 功率;
	}

	@Column(name = "排放标准", length = 50)
	public String get排放标准() {
		return this.排放标准;
	}

	public void set排放标准(String 排放标准) {
		this.排放标准 = 排放标准;
	}

	@Column(name = "外廓尺寸数据1")
	public Integer get外廓尺寸数据1() {
		return this.外廓尺寸数据1;
	}

	public void set外廓尺寸数据1(Integer 外廓尺寸数据1) {
		this.外廓尺寸数据1 = 外廓尺寸数据1;
	}

	@Column(name = "外廓尺寸数据2")
	public Integer get外廓尺寸数据2() {
		return this.外廓尺寸数据2;
	}

	public void set外廓尺寸数据2(Integer 外廓尺寸数据2) {
		this.外廓尺寸数据2 = 外廓尺寸数据2;
	}

	@Column(name = "外廓尺寸数据3")
	public Integer get外廓尺寸数据3() {
		return this.外廓尺寸数据3;
	}

	public void set外廓尺寸数据3(Integer 外廓尺寸数据3) {
		this.外廓尺寸数据3 = 外廓尺寸数据3;
	}

	@Column(name = "轮胎数")
	public Integer get轮胎数() {
		return this.轮胎数;
	}

	public void set轮胎数(Integer 轮胎数) {
		this.轮胎数 = 轮胎数;
	}

	@Column(name = "轮胎规格", length = 50)
	public String get轮胎规格() {
		return this.轮胎规格;
	}

	public void set轮胎规格(String 轮胎规格) {
		this.轮胎规格 = 轮胎规格;
	}

	@Column(name = "轮距前")
	public Integer get轮距前() {
		return this.轮距前;
	}

	public void set轮距前(Integer 轮距前) {
		this.轮距前 = 轮距前;
	}

	@Column(name = "轮距后")
	public Integer get轮距后() {
		return this.轮距后;
	}

	public void set轮距后(Integer 轮距后) {
		this.轮距后 = 轮距后;
	}

	@Column(name = "轴距")
	public Integer get轴距() {
		return this.轴距;
	}

	public void set轴距(Integer 轴距) {
		this.轴距 = 轴距;
	}

	@Column(name = "轴数")
	public Integer get轴数() {
		return this.轴数;
	}

	public void set轴数(Integer 轴数) {
		this.轴数 = 轴数;
	}

	@Column(name = "转向形式", length = 50)
	public String get转向形式() {
		return this.转向形式;
	}

	public void set转向形式(String 转向形式) {
		this.转向形式 = 转向形式;
	}

	@Column(name = "总质量", precision = 12, scale = 0)
	public Float get总质量() {
		return this.总质量;
	}

	public void set总质量(Float 总质量) {
		this.总质量 = 总质量;
	}

	@Column(name = "整备质量", precision = 12, scale = 0)
	public Float get整备质量() {
		return this.整备质量;
	}

	public void set整备质量(Float 整备质量) {
		this.整备质量 = 整备质量;
	}

	@Column(name = "额定载客")
	public Integer get额定载客() {
		return this.额定载客;
	}

	public void set额定载客(Integer 额定载客) {
		this.额定载客 = 额定载客;
	}

	@Column(name = "最高车速", precision = 12, scale = 0)
	public Float get最高车速() {
		return this.最高车速;
	}

	public void set最高车速(Float 最高车速) {
		this.最高车速 = 最高车速;
	}

	@Column(name = "车辆制造日期", length = 0)
	public Timestamp get车辆制造日期() {
		return this.车辆制造日期;
	}

	public void set车辆制造日期(Timestamp 车辆制造日期) {
		this.车辆制造日期 = 车辆制造日期;
	}

	@Column(name = "发证日期", length = 0)
	public Timestamp get发证日期() {
		return this.发证日期;
	}

	public void set发证日期(Timestamp 发证日期) {
		this.发证日期 = 发证日期;
	}

	@Column(name = "发动机型号", length = 50)
	public String get发动机型号() {
		return this.发动机型号;
	}

	public void set发动机型号(String 发动机型号) {
		this.发动机型号 = 发动机型号;
	}

	@Column(name = "拓印")
	public String get拓印() {
		return this.拓印;
	}

	public void set拓印(String 拓印) {
		this.拓印 = 拓印;
	}

	@Column(name = "归属部门", length = 50)
	public String get归属部门() {
		return this.归属部门;
	}

	public void set归属部门(String 归属部门) {
		this.归属部门 = 归属部门;
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

	@Column(name = "核定载质量", precision = 12, scale = 0)
	public Float get核定载质量() {
		return this.核定载质量;
	}

	public void set核定载质量(Float 核定载质量) {
		this.核定载质量 = 核定载质量;
	}

	@Column(name = "年平均行驶里程")
	public Integer get年平均行驶里程() {
		return this.年平均行驶里程;
	}

	public void set年平均行驶里程(Integer 年平均行驶里程) {
		this.年平均行驶里程 = 年平均行驶里程;
	}

	@Column(name = "新车购置价", precision = 18)
	public Double get新车购置价() {
		return this.新车购置价;
	}

	public void set新车购置价(Double 新车购置价) {
		this.新车购置价 = 新车购置价;
	}

	@Column(name = "使用性质", length = 50)
	public String get使用性质() {
		return this.使用性质;
	}

	public void set使用性质(String 使用性质) {
		this.使用性质 = 使用性质;
	}

	@Column(name = "行驶区域", length = 500)
	public String get行驶区域() {
		return this.行驶区域;
	}

	public void set行驶区域(String 行驶区域) {
		this.行驶区域 = 行驶区域;
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

}