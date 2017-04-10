package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆型号详细表 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "车辆型号详细表", catalog = "dzimport")
public class 车辆型号详细表 implements java.io.Serializable {

	// Fields

	private Integer 编号;
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
	private String 发动机型号;
	private String 发动机号;
	private String 厂牌车型;
	private String 车辆制造企业名称;
	private String 车辆名称;
	private Float 核定载质量;
	private Timestamp 发证日期;
	private String 车身颜色;
	private String 燃油类型;
	private Integer 年平均行驶里程;
	private Double 新车购置价;
	private String 使用性质;
	private String 行驶区域;
	private Double 发票金额;
	private Double 购置税;

	// Constructors

	/** default constructor */
	public 车辆型号详细表() {
	}

	/** full constructor */
	public 车辆型号详细表(String 车辆型号, Integer 排量, Integer 功率, String 排放标准,
			Integer 外廓尺寸数据1, Integer 外廓尺寸数据2, Integer 外廓尺寸数据3, Integer 轮胎数,
			String 轮胎规格, Integer 轮距前, Integer 轮距后, Integer 轴距, Integer 轴数,
			String 转向形式, Float 总质量, Float 整备质量, Integer 额定载客, Float 最高车速,
			String 发动机型号, String 发动机号, String 厂牌车型, String 车辆制造企业名称,
			String 车辆名称, Float 核定载质量, Timestamp 发证日期, String 车身颜色, String 燃油类型,
			Integer 年平均行驶里程, Double 新车购置价, String 使用性质, String 行驶区域,
			Double 发票金额, Double 购置税) {
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
		this.发动机型号 = 发动机型号;
		this.发动机号 = 发动机号;
		this.厂牌车型 = 厂牌车型;
		this.车辆制造企业名称 = 车辆制造企业名称;
		this.车辆名称 = 车辆名称;
		this.核定载质量 = 核定载质量;
		this.发证日期 = 发证日期;
		this.车身颜色 = 车身颜色;
		this.燃油类型 = 燃油类型;
		this.年平均行驶里程 = 年平均行驶里程;
		this.新车购置价 = 新车购置价;
		this.使用性质 = 使用性质;
		this.行驶区域 = 行驶区域;
		this.发票金额 = 发票金额;
		this.购置税 = 购置税;
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

	@Column(name = "发动机型号", length = 50)
	public String get发动机型号() {
		return this.发动机型号;
	}

	public void set发动机型号(String 发动机型号) {
		this.发动机型号 = 发动机型号;
	}

	@Column(name = "发动机号", length = 50)
	public String get发动机号() {
		return this.发动机号;
	}

	public void set发动机号(String 发动机号) {
		this.发动机号 = 发动机号;
	}

	@Column(name = "厂牌车型", length = 50)
	public String get厂牌车型() {
		return this.厂牌车型;
	}

	public void set厂牌车型(String 厂牌车型) {
		this.厂牌车型 = 厂牌车型;
	}

	@Column(name = "车辆制造企业名称", length = 50)
	public String get车辆制造企业名称() {
		return this.车辆制造企业名称;
	}

	public void set车辆制造企业名称(String 车辆制造企业名称) {
		this.车辆制造企业名称 = 车辆制造企业名称;
	}

	@Column(name = "车辆名称", length = 50)
	public String get车辆名称() {
		return this.车辆名称;
	}

	public void set车辆名称(String 车辆名称) {
		this.车辆名称 = 车辆名称;
	}

	@Column(name = "核定载质量", precision = 12, scale = 0)
	public Float get核定载质量() {
		return this.核定载质量;
	}

	public void set核定载质量(Float 核定载质量) {
		this.核定载质量 = 核定载质量;
	}

	@Column(name = "发证日期", length = 0)
	public Timestamp get发证日期() {
		return this.发证日期;
	}

	public void set发证日期(Timestamp 发证日期) {
		this.发证日期 = 发证日期;
	}

	@Column(name = "车身颜色", length = 50)
	public String get车身颜色() {
		return this.车身颜色;
	}

	public void set车身颜色(String 车身颜色) {
		this.车身颜色 = 车身颜色;
	}

	@Column(name = "燃油类型", length = 50)
	public String get燃油类型() {
		return this.燃油类型;
	}

	public void set燃油类型(String 燃油类型) {
		this.燃油类型 = 燃油类型;
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

	@Column(name = "发票金额", precision = 18)
	public Double get发票金额() {
		return this.发票金额;
	}

	public void set发票金额(Double 发票金额) {
		this.发票金额 = 发票金额;
	}

	@Column(name = "购置税", precision = 18)
	public Double get购置税() {
		return this.购置税;
	}

	public void set购置税(Double 购置税) {
		this.购置税 = 购置税;
	}

}