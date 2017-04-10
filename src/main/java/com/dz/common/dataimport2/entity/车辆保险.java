package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆保险 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "车辆保险", catalog = "dzimport")
public class 车辆保险 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 保险编号;
	private String 合同编号;
	private String 车牌号;
	private String 保险公司;
	private Timestamp 起始时间;
	private Timestamp 终止时间;
	private String 商业保单号;
	private String 强险单号;
	private String 摘要说明;
	private Double 发票金额;
	private Double 保险金额;
	private Double 折后金额;
	private Double 实收金额;
	private String 借方科目编码;
	private String 贷方科目编码;
	private Integer 状态;
	private String 行车证车主;
	private String 被保险人身份证号码;
	private String 被保险人;
	private String 发动机号;
	private String 车辆型号;
	private String 车辆类型;
	private String 排量;
	private String 使用性质;
	private String 新车购置价;
	private String 核定载客;
	private String 车架号;
	private String 车损免赔额;
	private String 类别归属;
	private String 号牌颜色;
	private String 车身颜色;
	private String 行驶区域;
	private String 保险费合计大写;
	private Double 保险费合计小写;
	private Timestamp 保险期限起;
	private Timestamp 保险期限止;
	private String 解决方式;
	private String 特别约定;
	private Double 其中补助基金;
	private Timestamp 初次登记年月;
	private String 被保险人地址;
	private String 联系人;
	private String 联系人电话;
	private String 被保险人邮政编码;
	private String 保险人;
	private String 保险人地址;
	private String 报案电话;
	private Timestamp 签单日期;
	private String 保险人邮政编码;
	private String 保险人公司名称;
	private String 核保;
	private String 制单;
	private String 经办;
	private Timestamp 收费时间;
	private Timestamp 打印时间;
	private Timestamp 生成保单时间;
	private Timestamp 投保时间;
	private String 功率;
	private String 整备质量;
	private String 纳税人识别码;
	private String 代收车船税合计大写;
	private String 完税凭证号;
	private String 开具税务机关;
	private Double 当年应收;
	private Double 往年补交;
	private Double 滞纳金;
	private Double 代收车船税合计小写;
	private String 核定载质量;
	private Boolean 是否有效;
	private String 分公司归属;

	// Constructors

	/** default constructor */
	public 车辆保险() {
	}

	/** full constructor */
	public 车辆保险(String 保险编号, String 合同编号, String 车牌号, String 保险公司,
			Timestamp 起始时间, Timestamp 终止时间, String 商业保单号, String 强险单号,
			String 摘要说明, Double 发票金额, Double 保险金额, Double 折后金额, Double 实收金额,
			String 借方科目编码, String 贷方科目编码, Integer 状态, String 行车证车主,
			String 被保险人身份证号码, String 被保险人, String 发动机号, String 车辆型号,
			String 车辆类型, String 排量, String 使用性质, String 新车购置价, String 核定载客,
			String 车架号, String 车损免赔额, String 类别归属, String 号牌颜色, String 车身颜色,
			String 行驶区域, String 保险费合计大写, Double 保险费合计小写, Timestamp 保险期限起,
			Timestamp 保险期限止, String 解决方式, String 特别约定, Double 其中补助基金,
			Timestamp 初次登记年月, String 被保险人地址, String 联系人, String 联系人电话,
			String 被保险人邮政编码, String 保险人, String 保险人地址, String 报案电话,
			Timestamp 签单日期, String 保险人邮政编码, String 保险人公司名称, String 核保,
			String 制单, String 经办, Timestamp 收费时间, Timestamp 打印时间,
			Timestamp 生成保单时间, Timestamp 投保时间, String 功率, String 整备质量,
			String 纳税人识别码, String 代收车船税合计大写, String 完税凭证号, String 开具税务机关,
			Double 当年应收, Double 往年补交, Double 滞纳金, Double 代收车船税合计小写,
			String 核定载质量, Boolean 是否有效, String 分公司归属) {
		this.保险编号 = 保险编号;
		this.合同编号 = 合同编号;
		this.车牌号 = 车牌号;
		this.保险公司 = 保险公司;
		this.起始时间 = 起始时间;
		this.终止时间 = 终止时间;
		this.商业保单号 = 商业保单号;
		this.强险单号 = 强险单号;
		this.摘要说明 = 摘要说明;
		this.发票金额 = 发票金额;
		this.保险金额 = 保险金额;
		this.折后金额 = 折后金额;
		this.实收金额 = 实收金额;
		this.借方科目编码 = 借方科目编码;
		this.贷方科目编码 = 贷方科目编码;
		this.状态 = 状态;
		this.行车证车主 = 行车证车主;
		this.被保险人身份证号码 = 被保险人身份证号码;
		this.被保险人 = 被保险人;
		this.发动机号 = 发动机号;
		this.车辆型号 = 车辆型号;
		this.车辆类型 = 车辆类型;
		this.排量 = 排量;
		this.使用性质 = 使用性质;
		this.新车购置价 = 新车购置价;
		this.核定载客 = 核定载客;
		this.车架号 = 车架号;
		this.车损免赔额 = 车损免赔额;
		this.类别归属 = 类别归属;
		this.号牌颜色 = 号牌颜色;
		this.车身颜色 = 车身颜色;
		this.行驶区域 = 行驶区域;
		this.保险费合计大写 = 保险费合计大写;
		this.保险费合计小写 = 保险费合计小写;
		this.保险期限起 = 保险期限起;
		this.保险期限止 = 保险期限止;
		this.解决方式 = 解决方式;
		this.特别约定 = 特别约定;
		this.其中补助基金 = 其中补助基金;
		this.初次登记年月 = 初次登记年月;
		this.被保险人地址 = 被保险人地址;
		this.联系人 = 联系人;
		this.联系人电话 = 联系人电话;
		this.被保险人邮政编码 = 被保险人邮政编码;
		this.保险人 = 保险人;
		this.保险人地址 = 保险人地址;
		this.报案电话 = 报案电话;
		this.签单日期 = 签单日期;
		this.保险人邮政编码 = 保险人邮政编码;
		this.保险人公司名称 = 保险人公司名称;
		this.核保 = 核保;
		this.制单 = 制单;
		this.经办 = 经办;
		this.收费时间 = 收费时间;
		this.打印时间 = 打印时间;
		this.生成保单时间 = 生成保单时间;
		this.投保时间 = 投保时间;
		this.功率 = 功率;
		this.整备质量 = 整备质量;
		this.纳税人识别码 = 纳税人识别码;
		this.代收车船税合计大写 = 代收车船税合计大写;
		this.完税凭证号 = 完税凭证号;
		this.开具税务机关 = 开具税务机关;
		this.当年应收 = 当年应收;
		this.往年补交 = 往年补交;
		this.滞纳金 = 滞纳金;
		this.代收车船税合计小写 = 代收车船税合计小写;
		this.核定载质量 = 核定载质量;
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

	@Column(name = "合同编号", length = 50)
	public String get合同编号() {
		return this.合同编号;
	}

	public void set合同编号(String 合同编号) {
		this.合同编号 = 合同编号;
	}

	@Column(name = "车牌号", length = 50)
	public String get车牌号() {
		return this.车牌号;
	}

	public void set车牌号(String 车牌号) {
		this.车牌号 = 车牌号;
	}

	@Column(name = "保险公司", length = 50)
	public String get保险公司() {
		return this.保险公司;
	}

	public void set保险公司(String 保险公司) {
		this.保险公司 = 保险公司;
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

	@Column(name = "摘要说明", length = 200)
	public String get摘要说明() {
		return this.摘要说明;
	}

	public void set摘要说明(String 摘要说明) {
		this.摘要说明 = 摘要说明;
	}

	@Column(name = "发票金额", precision = 18)
	public Double get发票金额() {
		return this.发票金额;
	}

	public void set发票金额(Double 发票金额) {
		this.发票金额 = 发票金额;
	}

	@Column(name = "保险金额", precision = 18)
	public Double get保险金额() {
		return this.保险金额;
	}

	public void set保险金额(Double 保险金额) {
		this.保险金额 = 保险金额;
	}

	@Column(name = "折后金额", precision = 18)
	public Double get折后金额() {
		return this.折后金额;
	}

	public void set折后金额(Double 折后金额) {
		this.折后金额 = 折后金额;
	}

	@Column(name = "实收金额", precision = 18)
	public Double get实收金额() {
		return this.实收金额;
	}

	public void set实收金额(Double 实收金额) {
		this.实收金额 = 实收金额;
	}

	@Column(name = "借方科目编码", length = 50)
	public String get借方科目编码() {
		return this.借方科目编码;
	}

	public void set借方科目编码(String 借方科目编码) {
		this.借方科目编码 = 借方科目编码;
	}

	@Column(name = "贷方科目编码", length = 50)
	public String get贷方科目编码() {
		return this.贷方科目编码;
	}

	public void set贷方科目编码(String 贷方科目编码) {
		this.贷方科目编码 = 贷方科目编码;
	}

	@Column(name = "状态")
	public Integer get状态() {
		return this.状态;
	}

	public void set状态(Integer 状态) {
		this.状态 = 状态;
	}

	@Column(name = "行车证车主", length = 50)
	public String get行车证车主() {
		return this.行车证车主;
	}

	public void set行车证车主(String 行车证车主) {
		this.行车证车主 = 行车证车主;
	}

	@Column(name = "被保险人身份证号码", length = 50)
	public String get被保险人身份证号码() {
		return this.被保险人身份证号码;
	}

	public void set被保险人身份证号码(String 被保险人身份证号码) {
		this.被保险人身份证号码 = 被保险人身份证号码;
	}

	@Column(name = "被保险人", length = 50)
	public String get被保险人() {
		return this.被保险人;
	}

	public void set被保险人(String 被保险人) {
		this.被保险人 = 被保险人;
	}

	@Column(name = "发动机号", length = 50)
	public String get发动机号() {
		return this.发动机号;
	}

	public void set发动机号(String 发动机号) {
		this.发动机号 = 发动机号;
	}

	@Column(name = "车辆型号", length = 50)
	public String get车辆型号() {
		return this.车辆型号;
	}

	public void set车辆型号(String 车辆型号) {
		this.车辆型号 = 车辆型号;
	}

	@Column(name = "车辆类型", length = 50)
	public String get车辆类型() {
		return this.车辆类型;
	}

	public void set车辆类型(String 车辆类型) {
		this.车辆类型 = 车辆类型;
	}

	@Column(name = "排量", length = 50)
	public String get排量() {
		return this.排量;
	}

	public void set排量(String 排量) {
		this.排量 = 排量;
	}

	@Column(name = "使用性质", length = 50)
	public String get使用性质() {
		return this.使用性质;
	}

	public void set使用性质(String 使用性质) {
		this.使用性质 = 使用性质;
	}

	@Column(name = "新车购置价", length = 50)
	public String get新车购置价() {
		return this.新车购置价;
	}

	public void set新车购置价(String 新车购置价) {
		this.新车购置价 = 新车购置价;
	}

	@Column(name = "核定载客", length = 50)
	public String get核定载客() {
		return this.核定载客;
	}

	public void set核定载客(String 核定载客) {
		this.核定载客 = 核定载客;
	}

	@Column(name = "车架号", length = 50)
	public String get车架号() {
		return this.车架号;
	}

	public void set车架号(String 车架号) {
		this.车架号 = 车架号;
	}

	@Column(name = "车损免赔额", length = 50)
	public String get车损免赔额() {
		return this.车损免赔额;
	}

	public void set车损免赔额(String 车损免赔额) {
		this.车损免赔额 = 车损免赔额;
	}

	@Column(name = "类别归属", length = 50)
	public String get类别归属() {
		return this.类别归属;
	}

	public void set类别归属(String 类别归属) {
		this.类别归属 = 类别归属;
	}

	@Column(name = "号牌颜色", length = 50)
	public String get号牌颜色() {
		return this.号牌颜色;
	}

	public void set号牌颜色(String 号牌颜色) {
		this.号牌颜色 = 号牌颜色;
	}

	@Column(name = "车身颜色", length = 50)
	public String get车身颜色() {
		return this.车身颜色;
	}

	public void set车身颜色(String 车身颜色) {
		this.车身颜色 = 车身颜色;
	}

	@Column(name = "行驶区域", length = 50)
	public String get行驶区域() {
		return this.行驶区域;
	}

	public void set行驶区域(String 行驶区域) {
		this.行驶区域 = 行驶区域;
	}

	@Column(name = "保险费合计大写", length = 50)
	public String get保险费合计大写() {
		return this.保险费合计大写;
	}

	public void set保险费合计大写(String 保险费合计大写) {
		this.保险费合计大写 = 保险费合计大写;
	}

	@Column(name = "保险费合计小写", precision = 18)
	public Double get保险费合计小写() {
		return this.保险费合计小写;
	}

	public void set保险费合计小写(Double 保险费合计小写) {
		this.保险费合计小写 = 保险费合计小写;
	}

	@Column(name = "保险期限起", length = 0)
	public Timestamp get保险期限起() {
		return this.保险期限起;
	}

	public void set保险期限起(Timestamp 保险期限起) {
		this.保险期限起 = 保险期限起;
	}

	@Column(name = "保险期限止", length = 0)
	public Timestamp get保险期限止() {
		return this.保险期限止;
	}

	public void set保险期限止(Timestamp 保险期限止) {
		this.保险期限止 = 保险期限止;
	}

	@Column(name = "解决方式", length = 50)
	public String get解决方式() {
		return this.解决方式;
	}

	public void set解决方式(String 解决方式) {
		this.解决方式 = 解决方式;
	}

	@Column(name = "特别约定", length = 5000)
	public String get特别约定() {
		return this.特别约定;
	}

	public void set特别约定(String 特别约定) {
		this.特别约定 = 特别约定;
	}

	@Column(name = "其中补助基金", precision = 18)
	public Double get其中补助基金() {
		return this.其中补助基金;
	}

	public void set其中补助基金(Double 其中补助基金) {
		this.其中补助基金 = 其中补助基金;
	}

	@Column(name = "初次登记年月", length = 0)
	public Timestamp get初次登记年月() {
		return this.初次登记年月;
	}

	public void set初次登记年月(Timestamp 初次登记年月) {
		this.初次登记年月 = 初次登记年月;
	}

	@Column(name = "被保险人地址", length = 50)
	public String get被保险人地址() {
		return this.被保险人地址;
	}

	public void set被保险人地址(String 被保险人地址) {
		this.被保险人地址 = 被保险人地址;
	}

	@Column(name = "联系人", length = 50)
	public String get联系人() {
		return this.联系人;
	}

	public void set联系人(String 联系人) {
		this.联系人 = 联系人;
	}

	@Column(name = "联系人电话", length = 50)
	public String get联系人电话() {
		return this.联系人电话;
	}

	public void set联系人电话(String 联系人电话) {
		this.联系人电话 = 联系人电话;
	}

	@Column(name = "被保险人邮政编码", length = 50)
	public String get被保险人邮政编码() {
		return this.被保险人邮政编码;
	}

	public void set被保险人邮政编码(String 被保险人邮政编码) {
		this.被保险人邮政编码 = 被保险人邮政编码;
	}

	@Column(name = "保险人", length = 50)
	public String get保险人() {
		return this.保险人;
	}

	public void set保险人(String 保险人) {
		this.保险人 = 保险人;
	}

	@Column(name = "保险人地址", length = 50)
	public String get保险人地址() {
		return this.保险人地址;
	}

	public void set保险人地址(String 保险人地址) {
		this.保险人地址 = 保险人地址;
	}

	@Column(name = "报案电话", length = 50)
	public String get报案电话() {
		return this.报案电话;
	}

	public void set报案电话(String 报案电话) {
		this.报案电话 = 报案电话;
	}

	@Column(name = "签单日期", length = 0)
	public Timestamp get签单日期() {
		return this.签单日期;
	}

	public void set签单日期(Timestamp 签单日期) {
		this.签单日期 = 签单日期;
	}

	@Column(name = "保险人邮政编码", length = 50)
	public String get保险人邮政编码() {
		return this.保险人邮政编码;
	}

	public void set保险人邮政编码(String 保险人邮政编码) {
		this.保险人邮政编码 = 保险人邮政编码;
	}

	@Column(name = "保险人公司名称", length = 50)
	public String get保险人公司名称() {
		return this.保险人公司名称;
	}

	public void set保险人公司名称(String 保险人公司名称) {
		this.保险人公司名称 = 保险人公司名称;
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

	@Column(name = "收费时间", length = 0)
	public Timestamp get收费时间() {
		return this.收费时间;
	}

	public void set收费时间(Timestamp 收费时间) {
		this.收费时间 = 收费时间;
	}

	@Column(name = "打印时间", length = 0)
	public Timestamp get打印时间() {
		return this.打印时间;
	}

	public void set打印时间(Timestamp 打印时间) {
		this.打印时间 = 打印时间;
	}

	@Column(name = "生成保单时间", length = 0)
	public Timestamp get生成保单时间() {
		return this.生成保单时间;
	}

	public void set生成保单时间(Timestamp 生成保单时间) {
		this.生成保单时间 = 生成保单时间;
	}

	@Column(name = "投保时间", length = 0)
	public Timestamp get投保时间() {
		return this.投保时间;
	}

	public void set投保时间(Timestamp 投保时间) {
		this.投保时间 = 投保时间;
	}

	@Column(name = "功率", length = 50)
	public String get功率() {
		return this.功率;
	}

	public void set功率(String 功率) {
		this.功率 = 功率;
	}

	@Column(name = "整备质量", length = 50)
	public String get整备质量() {
		return this.整备质量;
	}

	public void set整备质量(String 整备质量) {
		this.整备质量 = 整备质量;
	}

	@Column(name = "纳税人识别码", length = 50)
	public String get纳税人识别码() {
		return this.纳税人识别码;
	}

	public void set纳税人识别码(String 纳税人识别码) {
		this.纳税人识别码 = 纳税人识别码;
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

	@Column(name = "代收车船税合计小写", precision = 18)
	public Double get代收车船税合计小写() {
		return this.代收车船税合计小写;
	}

	public void set代收车船税合计小写(Double 代收车船税合计小写) {
		this.代收车船税合计小写 = 代收车船税合计小写;
	}

	@Column(name = "核定载质量", length = 50)
	public String get核定载质量() {
		return this.核定载质量;
	}

	public void set核定载质量(String 核定载质量) {
		this.核定载质量 = 核定载质量;
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