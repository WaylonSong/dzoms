package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆保险大众 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "车辆保险_大众", catalog = "dzimport")
public class 车辆保险大众 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 保险编号;
	private String 保险类别;
	private String 档案号;
	private String 车牌号;
	private String 车架号;
	private String 发动机号;
	private Timestamp 起始时间;
	private Timestamp 终止时间;
	private Timestamp 签单日期;
	private Double 保险费合计小写;
	private String 保险费合计大写;
	private String 商业保单号;
	private String 强险单号;
	private String 车辆品牌;
	private String 车辆型号;
	private String 厂牌型号;
	private String 机动车种类;
	private String 排量;
	private Integer 已使用年限;
	private String 行驶区域;
	private Float 年平均行驶里程;
	private Float 新车购置价;
	private Timestamp 登记日期;
	private String 功率;
	private String 保险金额模板;
	private String 保险公司模板;
	private String 特别约定;
	private Double 其中补助基金百分比;
	private Double 其中补助基金;
	private String 纳税人识别码;
	private Double 代收车船税合计小写;
	private String 代收车船税合计大写;
	private String 完税凭证号;
	private String 开具税务机关;
	private Double 当年应收;
	private Double 往年补交;
	private Double 滞纳金;
	private Double 浮动比率;
	private String 核定载客;
	private String 整备质量;
	private String 核定载质量;
	private String 使用性质;
	private String 解决方式;
	private Float 死亡伤残赔偿限额;
	private Float 无条件死亡伤残赔偿限额;
	private Float 财产损失赔偿限额;
	private Float 无条件财产损失赔偿限额;
	private Float 医疗费用赔偿限额;
	private Float 无条件医疗费用赔偿限额;
	private Timestamp 生成保单时间;
	private Timestamp 收费确认时间;
	private Timestamp 保单打印时间;
	private String 核保;
	private String 制单;
	private String 经办;
	private String 保存方式;
	private Integer 状态;
	private String 保险人公司名称;
	private String 保险人地址;
	private String 保险人网址;
	private String 保险人邮政编码;
	private String 保险人联系电话1;
	private String 保险人联系电话2;
	private String 被保险人;
	private String 被保险人身份证号码;
	private String 被保险人地址;
	private String 被保险人联系电话;
	private String 登记人;
	private Timestamp 登记时间;
	private String 关联编号;
	private Double 合计金额;
	private Boolean 是否续保;
	private Boolean 是否有效;
	private String 分公司归属;

	// Constructors

	/** default constructor */
	public 车辆保险大众() {
	}

	/** minimal constructor */
	public 车辆保险大众(String 保险类别) {
		this.保险类别 = 保险类别;
	}

	/** full constructor */
	public 车辆保险大众(String 保险编号, String 保险类别, String 档案号, String 车牌号, String 车架号,
			String 发动机号, Timestamp 起始时间, Timestamp 终止时间, Timestamp 签单日期,
			Double 保险费合计小写, String 保险费合计大写, String 商业保单号, String 强险单号,
			String 车辆品牌, String 车辆型号, String 厂牌型号, String 机动车种类, String 排量,
			Integer 已使用年限, String 行驶区域, Float 年平均行驶里程, Float 新车购置价,
			Timestamp 登记日期, String 功率, String 保险金额模板, String 保险公司模板,
			String 特别约定, Double 其中补助基金百分比, Double 其中补助基金, String 纳税人识别码,
			Double 代收车船税合计小写, String 代收车船税合计大写, String 完税凭证号, String 开具税务机关,
			Double 当年应收, Double 往年补交, Double 滞纳金, Double 浮动比率, String 核定载客,
			String 整备质量, String 核定载质量, String 使用性质, String 解决方式,
			Float 死亡伤残赔偿限额, Float 无条件死亡伤残赔偿限额, Float 财产损失赔偿限额,
			Float 无条件财产损失赔偿限额, Float 医疗费用赔偿限额, Float 无条件医疗费用赔偿限额,
			Timestamp 生成保单时间, Timestamp 收费确认时间, Timestamp 保单打印时间, String 核保,
			String 制单, String 经办, String 保存方式, Integer 状态, String 保险人公司名称,
			String 保险人地址, String 保险人网址, String 保险人邮政编码, String 保险人联系电话1,
			String 保险人联系电话2, String 被保险人, String 被保险人身份证号码, String 被保险人地址,
			String 被保险人联系电话, String 登记人, Timestamp 登记时间, String 关联编号,
			Double 合计金额, Boolean 是否续保, Boolean 是否有效, String 分公司归属) {
		this.保险编号 = 保险编号;
		this.保险类别 = 保险类别;
		this.档案号 = 档案号;
		this.车牌号 = 车牌号;
		this.车架号 = 车架号;
		this.发动机号 = 发动机号;
		this.起始时间 = 起始时间;
		this.终止时间 = 终止时间;
		this.签单日期 = 签单日期;
		this.保险费合计小写 = 保险费合计小写;
		this.保险费合计大写 = 保险费合计大写;
		this.商业保单号 = 商业保单号;
		this.强险单号 = 强险单号;
		this.车辆品牌 = 车辆品牌;
		this.车辆型号 = 车辆型号;
		this.厂牌型号 = 厂牌型号;
		this.机动车种类 = 机动车种类;
		this.排量 = 排量;
		this.已使用年限 = 已使用年限;
		this.行驶区域 = 行驶区域;
		this.年平均行驶里程 = 年平均行驶里程;
		this.新车购置价 = 新车购置价;
		this.登记日期 = 登记日期;
		this.功率 = 功率;
		this.保险金额模板 = 保险金额模板;
		this.保险公司模板 = 保险公司模板;
		this.特别约定 = 特别约定;
		this.其中补助基金百分比 = 其中补助基金百分比;
		this.其中补助基金 = 其中补助基金;
		this.纳税人识别码 = 纳税人识别码;
		this.代收车船税合计小写 = 代收车船税合计小写;
		this.代收车船税合计大写 = 代收车船税合计大写;
		this.完税凭证号 = 完税凭证号;
		this.开具税务机关 = 开具税务机关;
		this.当年应收 = 当年应收;
		this.往年补交 = 往年补交;
		this.滞纳金 = 滞纳金;
		this.浮动比率 = 浮动比率;
		this.核定载客 = 核定载客;
		this.整备质量 = 整备质量;
		this.核定载质量 = 核定载质量;
		this.使用性质 = 使用性质;
		this.解决方式 = 解决方式;
		this.死亡伤残赔偿限额 = 死亡伤残赔偿限额;
		this.无条件死亡伤残赔偿限额 = 无条件死亡伤残赔偿限额;
		this.财产损失赔偿限额 = 财产损失赔偿限额;
		this.无条件财产损失赔偿限额 = 无条件财产损失赔偿限额;
		this.医疗费用赔偿限额 = 医疗费用赔偿限额;
		this.无条件医疗费用赔偿限额 = 无条件医疗费用赔偿限额;
		this.生成保单时间 = 生成保单时间;
		this.收费确认时间 = 收费确认时间;
		this.保单打印时间 = 保单打印时间;
		this.核保 = 核保;
		this.制单 = 制单;
		this.经办 = 经办;
		this.保存方式 = 保存方式;
		this.状态 = 状态;
		this.保险人公司名称 = 保险人公司名称;
		this.保险人地址 = 保险人地址;
		this.保险人网址 = 保险人网址;
		this.保险人邮政编码 = 保险人邮政编码;
		this.保险人联系电话1 = 保险人联系电话1;
		this.保险人联系电话2 = 保险人联系电话2;
		this.被保险人 = 被保险人;
		this.被保险人身份证号码 = 被保险人身份证号码;
		this.被保险人地址 = 被保险人地址;
		this.被保险人联系电话 = 被保险人联系电话;
		this.登记人 = 登记人;
		this.登记时间 = 登记时间;
		this.关联编号 = 关联编号;
		this.合计金额 = 合计金额;
		this.是否续保 = 是否续保;
		this.是否有效 = 是否有效;
		this.分公司归属 = 分公司归属;
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

	@Column(name = "保险编号", length = 50)
	public String get保险编号() {
		return this.保险编号;
	}

	public void set保险编号(String 保险编号) {
		this.保险编号 = 保险编号;
	}

	@Column(name = "保险类别", nullable = false, length = 50)
	public String get保险类别() {
		return this.保险类别;
	}

	public void set保险类别(String 保险类别) {
		this.保险类别 = 保险类别;
	}

	@Column(name = "档案号", length = 50)
	public String get档案号() {
		return this.档案号;
	}

	public void set档案号(String 档案号) {
		this.档案号 = 档案号;
	}

	@Column(name = "车牌号", length = 50)
	public String get车牌号() {
		return this.车牌号;
	}

	public void set车牌号(String 车牌号) {
		this.车牌号 = 车牌号;
	}

	@Column(name = "车架号", length = 50)
	public String get车架号() {
		return this.车架号;
	}

	public void set车架号(String 车架号) {
		this.车架号 = 车架号;
	}

	@Column(name = "发动机号", length = 50)
	public String get发动机号() {
		return this.发动机号;
	}

	public void set发动机号(String 发动机号) {
		this.发动机号 = 发动机号;
	}

	@Column(name = "起始时间", length = 0)
	public Timestamp get起始时间() {
		return this.起始时间;
	}

	public void set起始时间(Timestamp 起始时间) {
		this.起始时间 = 起始时间;
	}

	@Column(name = "终止时间", length = 0)
	public Timestamp get终止时间() {
		return this.终止时间;
	}

	public void set终止时间(Timestamp 终止时间) {
		this.终止时间 = 终止时间;
	}

	@Column(name = "签单日期", length = 0)
	public Timestamp get签单日期() {
		return this.签单日期;
	}

	public void set签单日期(Timestamp 签单日期) {
		this.签单日期 = 签单日期;
	}

	@Column(name = "保险费合计小写", precision = 18)
	public Double get保险费合计小写() {
		return this.保险费合计小写;
	}

	public void set保险费合计小写(Double 保险费合计小写) {
		this.保险费合计小写 = 保险费合计小写;
	}

	@Column(name = "保险费合计大写", length = 50)
	public String get保险费合计大写() {
		return this.保险费合计大写;
	}

	public void set保险费合计大写(String 保险费合计大写) {
		this.保险费合计大写 = 保险费合计大写;
	}

	@Column(name = "商业保单号", length = 50)
	public String get商业保单号() {
		return this.商业保单号;
	}

	public void set商业保单号(String 商业保单号) {
		this.商业保单号 = 商业保单号;
	}

	@Column(name = "强险单号", length = 50)
	public String get强险单号() {
		return this.强险单号;
	}

	public void set强险单号(String 强险单号) {
		this.强险单号 = 强险单号;
	}

	@Column(name = "车辆品牌", length = 50)
	public String get车辆品牌() {
		return this.车辆品牌;
	}

	public void set车辆品牌(String 车辆品牌) {
		this.车辆品牌 = 车辆品牌;
	}

	@Column(name = "车辆型号", length = 50)
	public String get车辆型号() {
		return this.车辆型号;
	}

	public void set车辆型号(String 车辆型号) {
		this.车辆型号 = 车辆型号;
	}

	@Column(name = "厂牌型号", length = 50)
	public String get厂牌型号() {
		return this.厂牌型号;
	}

	public void set厂牌型号(String 厂牌型号) {
		this.厂牌型号 = 厂牌型号;
	}

	@Column(name = "机动车种类", length = 50)
	public String get机动车种类() {
		return this.机动车种类;
	}

	public void set机动车种类(String 机动车种类) {
		this.机动车种类 = 机动车种类;
	}

	@Column(name = "排量", length = 50)
	public String get排量() {
		return this.排量;
	}

	public void set排量(String 排量) {
		this.排量 = 排量;
	}

	@Column(name = "已使用年限")
	public Integer get已使用年限() {
		return this.已使用年限;
	}

	public void set已使用年限(Integer 已使用年限) {
		this.已使用年限 = 已使用年限;
	}

	@Column(name = "行驶区域", length = 50)
	public String get行驶区域() {
		return this.行驶区域;
	}

	public void set行驶区域(String 行驶区域) {
		this.行驶区域 = 行驶区域;
	}

	@Column(name = "年平均行驶里程", precision = 12, scale = 0)
	public Float get年平均行驶里程() {
		return this.年平均行驶里程;
	}

	public void set年平均行驶里程(Float 年平均行驶里程) {
		this.年平均行驶里程 = 年平均行驶里程;
	}

	@Column(name = "新车购置价", precision = 12, scale = 0)
	public Float get新车购置价() {
		return this.新车购置价;
	}

	public void set新车购置价(Float 新车购置价) {
		this.新车购置价 = 新车购置价;
	}

	@Column(name = "登记日期", length = 0)
	public Timestamp get登记日期() {
		return this.登记日期;
	}

	public void set登记日期(Timestamp 登记日期) {
		this.登记日期 = 登记日期;
	}

	@Column(name = "功率", length = 50)
	public String get功率() {
		return this.功率;
	}

	public void set功率(String 功率) {
		this.功率 = 功率;
	}

	@Column(name = "保险金额模板", length = 50)
	public String get保险金额模板() {
		return this.保险金额模板;
	}

	public void set保险金额模板(String 保险金额模板) {
		this.保险金额模板 = 保险金额模板;
	}

	@Column(name = "保险公司模板", length = 50)
	public String get保险公司模板() {
		return this.保险公司模板;
	}

	public void set保险公司模板(String 保险公司模板) {
		this.保险公司模板 = 保险公司模板;
	}

	@Column(name = "特别约定", length = 5000)
	public String get特别约定() {
		return this.特别约定;
	}

	public void set特别约定(String 特别约定) {
		this.特别约定 = 特别约定;
	}

	@Column(name = "其中补助基金百分比", precision = 18)
	public Double get其中补助基金百分比() {
		return this.其中补助基金百分比;
	}

	public void set其中补助基金百分比(Double 其中补助基金百分比) {
		this.其中补助基金百分比 = 其中补助基金百分比;
	}

	@Column(name = "其中补助基金", precision = 18)
	public Double get其中补助基金() {
		return this.其中补助基金;
	}

	public void set其中补助基金(Double 其中补助基金) {
		this.其中补助基金 = 其中补助基金;
	}

	@Column(name = "纳税人识别码", length = 50)
	public String get纳税人识别码() {
		return this.纳税人识别码;
	}

	public void set纳税人识别码(String 纳税人识别码) {
		this.纳税人识别码 = 纳税人识别码;
	}

	@Column(name = "代收车船税合计小写", precision = 18)
	public Double get代收车船税合计小写() {
		return this.代收车船税合计小写;
	}

	public void set代收车船税合计小写(Double 代收车船税合计小写) {
		this.代收车船税合计小写 = 代收车船税合计小写;
	}

	@Column(name = "代收车船税合计大写", length = 50)
	public String get代收车船税合计大写() {
		return this.代收车船税合计大写;
	}

	public void set代收车船税合计大写(String 代收车船税合计大写) {
		this.代收车船税合计大写 = 代收车船税合计大写;
	}

	@Column(name = "完税凭证号", length = 50)
	public String get完税凭证号() {
		return this.完税凭证号;
	}

	public void set完税凭证号(String 完税凭证号) {
		this.完税凭证号 = 完税凭证号;
	}

	@Column(name = "开具税务机关", length = 50)
	public String get开具税务机关() {
		return this.开具税务机关;
	}

	public void set开具税务机关(String 开具税务机关) {
		this.开具税务机关 = 开具税务机关;
	}

	@Column(name = "当年应收", precision = 18)
	public Double get当年应收() {
		return this.当年应收;
	}

	public void set当年应收(Double 当年应收) {
		this.当年应收 = 当年应收;
	}

	@Column(name = "往年补交", precision = 18)
	public Double get往年补交() {
		return this.往年补交;
	}

	public void set往年补交(Double 往年补交) {
		this.往年补交 = 往年补交;
	}

	@Column(name = "滞纳金", precision = 18)
	public Double get滞纳金() {
		return this.滞纳金;
	}

	public void set滞纳金(Double 滞纳金) {
		this.滞纳金 = 滞纳金;
	}

	@Column(name = "浮动比率", precision = 18)
	public Double get浮动比率() {
		return this.浮动比率;
	}

	public void set浮动比率(Double 浮动比率) {
		this.浮动比率 = 浮动比率;
	}

	@Column(name = "核定载客", length = 50)
	public String get核定载客() {
		return this.核定载客;
	}

	public void set核定载客(String 核定载客) {
		this.核定载客 = 核定载客;
	}

	@Column(name = "整备质量", length = 50)
	public String get整备质量() {
		return this.整备质量;
	}

	public void set整备质量(String 整备质量) {
		this.整备质量 = 整备质量;
	}

	@Column(name = "核定载质量", length = 50)
	public String get核定载质量() {
		return this.核定载质量;
	}

	public void set核定载质量(String 核定载质量) {
		this.核定载质量 = 核定载质量;
	}

	@Column(name = "使用性质", length = 50)
	public String get使用性质() {
		return this.使用性质;
	}

	public void set使用性质(String 使用性质) {
		this.使用性质 = 使用性质;
	}

	@Column(name = "解决方式", length = 50)
	public String get解决方式() {
		return this.解决方式;
	}

	public void set解决方式(String 解决方式) {
		this.解决方式 = 解决方式;
	}

	@Column(name = "死亡伤残赔偿限额", precision = 12, scale = 0)
	public Float get死亡伤残赔偿限额() {
		return this.死亡伤残赔偿限额;
	}

	public void set死亡伤残赔偿限额(Float 死亡伤残赔偿限额) {
		this.死亡伤残赔偿限额 = 死亡伤残赔偿限额;
	}

	@Column(name = "无条件死亡伤残赔偿限额", precision = 12, scale = 0)
	public Float get无条件死亡伤残赔偿限额() {
		return this.无条件死亡伤残赔偿限额;
	}

	public void set无条件死亡伤残赔偿限额(Float 无条件死亡伤残赔偿限额) {
		this.无条件死亡伤残赔偿限额 = 无条件死亡伤残赔偿限额;
	}

	@Column(name = "财产损失赔偿限额", precision = 12, scale = 0)
	public Float get财产损失赔偿限额() {
		return this.财产损失赔偿限额;
	}

	public void set财产损失赔偿限额(Float 财产损失赔偿限额) {
		this.财产损失赔偿限额 = 财产损失赔偿限额;
	}

	@Column(name = "无条件财产损失赔偿限额", precision = 12, scale = 0)
	public Float get无条件财产损失赔偿限额() {
		return this.无条件财产损失赔偿限额;
	}

	public void set无条件财产损失赔偿限额(Float 无条件财产损失赔偿限额) {
		this.无条件财产损失赔偿限额 = 无条件财产损失赔偿限额;
	}

	@Column(name = "医疗费用赔偿限额", precision = 12, scale = 0)
	public Float get医疗费用赔偿限额() {
		return this.医疗费用赔偿限额;
	}

	public void set医疗费用赔偿限额(Float 医疗费用赔偿限额) {
		this.医疗费用赔偿限额 = 医疗费用赔偿限额;
	}

	@Column(name = "无条件医疗费用赔偿限额", precision = 12, scale = 0)
	public Float get无条件医疗费用赔偿限额() {
		return this.无条件医疗费用赔偿限额;
	}

	public void set无条件医疗费用赔偿限额(Float 无条件医疗费用赔偿限额) {
		this.无条件医疗费用赔偿限额 = 无条件医疗费用赔偿限额;
	}

	@Column(name = "生成保单时间", length = 0)
	public Timestamp get生成保单时间() {
		return this.生成保单时间;
	}

	public void set生成保单时间(Timestamp 生成保单时间) {
		this.生成保单时间 = 生成保单时间;
	}

	@Column(name = "收费确认时间", length = 0)
	public Timestamp get收费确认时间() {
		return this.收费确认时间;
	}

	public void set收费确认时间(Timestamp 收费确认时间) {
		this.收费确认时间 = 收费确认时间;
	}

	@Column(name = "保单打印时间", length = 0)
	public Timestamp get保单打印时间() {
		return this.保单打印时间;
	}

	public void set保单打印时间(Timestamp 保单打印时间) {
		this.保单打印时间 = 保单打印时间;
	}

	@Column(name = "核保", length = 50)
	public String get核保() {
		return this.核保;
	}

	public void set核保(String 核保) {
		this.核保 = 核保;
	}

	@Column(name = "制单", length = 50)
	public String get制单() {
		return this.制单;
	}

	public void set制单(String 制单) {
		this.制单 = 制单;
	}

	@Column(name = "经办", length = 50)
	public String get经办() {
		return this.经办;
	}

	public void set经办(String 经办) {
		this.经办 = 经办;
	}

	@Column(name = "保存方式", length = 50)
	public String get保存方式() {
		return this.保存方式;
	}

	public void set保存方式(String 保存方式) {
		this.保存方式 = 保存方式;
	}

	@Column(name = "状态")
	public Integer get状态() {
		return this.状态;
	}

	public void set状态(Integer 状态) {
		this.状态 = 状态;
	}

	@Column(name = "保险人公司名称", length = 50)
	public String get保险人公司名称() {
		return this.保险人公司名称;
	}

	public void set保险人公司名称(String 保险人公司名称) {
		this.保险人公司名称 = 保险人公司名称;
	}

	@Column(name = "保险人地址", length = 50)
	public String get保险人地址() {
		return this.保险人地址;
	}

	public void set保险人地址(String 保险人地址) {
		this.保险人地址 = 保险人地址;
	}

	@Column(name = "保险人网址", length = 50)
	public String get保险人网址() {
		return this.保险人网址;
	}

	public void set保险人网址(String 保险人网址) {
		this.保险人网址 = 保险人网址;
	}

	@Column(name = "保险人邮政编码", length = 50)
	public String get保险人邮政编码() {
		return this.保险人邮政编码;
	}

	public void set保险人邮政编码(String 保险人邮政编码) {
		this.保险人邮政编码 = 保险人邮政编码;
	}

	@Column(name = "保险人联系电话1", length = 50)
	public String get保险人联系电话1() {
		return this.保险人联系电话1;
	}

	public void set保险人联系电话1(String 保险人联系电话1) {
		this.保险人联系电话1 = 保险人联系电话1;
	}

	@Column(name = "保险人联系电话2", length = 50)
	public String get保险人联系电话2() {
		return this.保险人联系电话2;
	}

	public void set保险人联系电话2(String 保险人联系电话2) {
		this.保险人联系电话2 = 保险人联系电话2;
	}

	@Column(name = "被保险人", length = 50)
	public String get被保险人() {
		return this.被保险人;
	}

	public void set被保险人(String 被保险人) {
		this.被保险人 = 被保险人;
	}

	@Column(name = "被保险人身份证号码", length = 50)
	public String get被保险人身份证号码() {
		return this.被保险人身份证号码;
	}

	public void set被保险人身份证号码(String 被保险人身份证号码) {
		this.被保险人身份证号码 = 被保险人身份证号码;
	}

	@Column(name = "被保险人地址", length = 50)
	public String get被保险人地址() {
		return this.被保险人地址;
	}

	public void set被保险人地址(String 被保险人地址) {
		this.被保险人地址 = 被保险人地址;
	}

	@Column(name = "被保险人联系电话", length = 50)
	public String get被保险人联系电话() {
		return this.被保险人联系电话;
	}

	public void set被保险人联系电话(String 被保险人联系电话) {
		this.被保险人联系电话 = 被保险人联系电话;
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

	@Column(name = "关联编号", length = 50)
	public String get关联编号() {
		return this.关联编号;
	}

	public void set关联编号(String 关联编号) {
		this.关联编号 = 关联编号;
	}

	@Column(name = "合计金额", precision = 18)
	public Double get合计金额() {
		return this.合计金额;
	}

	public void set合计金额(Double 合计金额) {
		this.合计金额 = 合计金额;
	}

	@Column(name = "是否续保")
	public Boolean get是否续保() {
		return this.是否续保;
	}

	public void set是否续保(Boolean 是否续保) {
		this.是否续保 = 是否续保;
	}

	@Column(name = "是否有效")
	public Boolean get是否有效() {
		return this.是否有效;
	}

	public void set是否有效(Boolean 是否有效) {
		this.是否有效 = 是否有效;
	}

	@Column(name = "分公司归属", length = 50)
	public String get分公司归属() {
		return this.分公司归属;
	}

	public void set分公司归属(String 分公司归属) {
		this.分公司归属 = 分公司归属;
	}

}