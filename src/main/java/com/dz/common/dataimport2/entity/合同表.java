package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 合同表 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "合同表", catalog = "dzimport", uniqueConstraints = @UniqueConstraint(columnNames = "档案号"))
public class 合同表 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 车架号;
	private String 承包人;
	private Boolean 是否上打租金;
	private Integer 违约金;
	private String 档案号;
	private String 车牌号;
	private String 备注;
	private String 车照片;
	private String 承包人照片;
	private Boolean 是否生效;
	private Timestamp 起始日期;
	private Timestamp 终止日期;
	private String 承包形式;
	private String 性别;
	private String 身份证号;
	private String 驾驶证号;
	private String 资格证号;
	private String 证件地址;
	private String 归属区域;
	private String 现居住址;
	private String 现归属区域;
	private String 宅电号码区号;
	private String 宅电号码;
	private String 手机号码;
	private String 担保人姓名;
	private String 担保人身份证号;
	private String 担保人证件地址;
	private String 担保人现居住址;
	private String 担保人宅电号码区号;
	private String 担保人宅电号码;
	private String 担保人手机号码;
	private Double 转让车辆费用;
	private Integer 欠费时间警戒;
	private Long 欠费金额警戒;
	private String 担保人照片;
	private String 电子档案号;
	private Boolean 是否为当前车主;
	private Boolean 是否含保险;
	private String 录入人;
	private String 身份证归属区域省;
	private String 身份证归属区域市;
	private String 身份证归属区域区;
	private String 分公司归属;
	private String 营运手续归属;
	private Double 预付租金;
	private Double 定金;
	private String 合同种类;
	private String 标记;
	private Double 月租金;
	private Boolean 是否更新车辆;
	private String 原车牌号;
	private String 新车牌号;
	private String 车辆废止标识;
	private Timestamp 录入时间;
	private Timestamp 实际终止日期;
	private String 录入方式;

	// Constructors

	/** default constructor */
	public 合同表() {
	}

	/** minimal constructor */
	public 合同表(Boolean 是否上打租金, Integer 违约金, String 档案号, String 车牌号,
			Boolean 是否生效, Boolean 是否为当前车主) {
		this.是否上打租金 = 是否上打租金;
		this.违约金 = 违约金;
		this.档案号 = 档案号;
		this.车牌号 = 车牌号;
		this.是否生效 = 是否生效;
		this.是否为当前车主 = 是否为当前车主;
	}

	/** full constructor */
	public 合同表(String 车架号, String 承包人, Boolean 是否上打租金, Integer 违约金, String 档案号,
			String 车牌号, String 备注, String 车照片, String 承包人照片, Boolean 是否生效,
			Timestamp 起始日期, Timestamp 终止日期, String 承包形式, String 性别,
			String 身份证号, String 驾驶证号, String 资格证号, String 证件地址, String 归属区域,
			String 现居住址, String 现归属区域, String 宅电号码区号, String 宅电号码, String 手机号码,
			String 担保人姓名, String 担保人身份证号, String 担保人证件地址, String 担保人现居住址,
			String 担保人宅电号码区号, String 担保人宅电号码, String 担保人手机号码, Double 转让车辆费用,
			Integer 欠费时间警戒, Long 欠费金额警戒, String 担保人照片, String 电子档案号,
			Boolean 是否为当前车主, Boolean 是否含保险, String 录入人, String 身份证归属区域省,
			String 身份证归属区域市, String 身份证归属区域区, String 分公司归属, String 营运手续归属,
			Double 预付租金, Double 定金, String 合同种类, String 标记, Double 月租金,
			Boolean 是否更新车辆, String 原车牌号, String 新车牌号, String 车辆废止标识,
			Timestamp 录入时间, Timestamp 实际终止日期, String 录入方式) {
		this.车架号 = 车架号;
		this.承包人 = 承包人;
		this.是否上打租金 = 是否上打租金;
		this.违约金 = 违约金;
		this.档案号 = 档案号;
		this.车牌号 = 车牌号;
		this.备注 = 备注;
		this.车照片 = 车照片;
		this.承包人照片 = 承包人照片;
		this.是否生效 = 是否生效;
		this.起始日期 = 起始日期;
		this.终止日期 = 终止日期;
		this.承包形式 = 承包形式;
		this.性别 = 性别;
		this.身份证号 = 身份证号;
		this.驾驶证号 = 驾驶证号;
		this.资格证号 = 资格证号;
		this.证件地址 = 证件地址;
		this.归属区域 = 归属区域;
		this.现居住址 = 现居住址;
		this.现归属区域 = 现归属区域;
		this.宅电号码区号 = 宅电号码区号;
		this.宅电号码 = 宅电号码;
		this.手机号码 = 手机号码;
		this.担保人姓名 = 担保人姓名;
		this.担保人身份证号 = 担保人身份证号;
		this.担保人证件地址 = 担保人证件地址;
		this.担保人现居住址 = 担保人现居住址;
		this.担保人宅电号码区号 = 担保人宅电号码区号;
		this.担保人宅电号码 = 担保人宅电号码;
		this.担保人手机号码 = 担保人手机号码;
		this.转让车辆费用 = 转让车辆费用;
		this.欠费时间警戒 = 欠费时间警戒;
		this.欠费金额警戒 = 欠费金额警戒;
		this.担保人照片 = 担保人照片;
		this.电子档案号 = 电子档案号;
		this.是否为当前车主 = 是否为当前车主;
		this.是否含保险 = 是否含保险;
		this.录入人 = 录入人;
		this.身份证归属区域省 = 身份证归属区域省;
		this.身份证归属区域市 = 身份证归属区域市;
		this.身份证归属区域区 = 身份证归属区域区;
		this.分公司归属 = 分公司归属;
		this.营运手续归属 = 营运手续归属;
		this.预付租金 = 预付租金;
		this.定金 = 定金;
		this.合同种类 = 合同种类;
		this.标记 = 标记;
		this.月租金 = 月租金;
		this.是否更新车辆 = 是否更新车辆;
		this.原车牌号 = 原车牌号;
		this.新车牌号 = 新车牌号;
		this.车辆废止标识 = 车辆废止标识;
		this.录入时间 = 录入时间;
		this.实际终止日期 = 实际终止日期;
		this.录入方式 = 录入方式;
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

	@Column(name = "承包人", length = 50)
	public String get承包人() {
		return this.承包人;
	}

	public void set承包人(String 承包人) {
		this.承包人 = 承包人;
	}

	@Column(name = "是否上打租金", nullable = false)
	public Boolean get是否上打租金() {
		return this.是否上打租金;
	}

	public void set是否上打租金(Boolean 是否上打租金) {
		this.是否上打租金 = 是否上打租金;
	}

	@Column(name = "违约金", nullable = false)
	public Integer get违约金() {
		return this.违约金;
	}

	public void set违约金(Integer 违约金) {
		this.违约金 = 违约金;
	}

	@Column(name = "档案号", unique = true, nullable = false, length = 50)
	public String get档案号() {
		return this.档案号;
	}

	public void set档案号(String 档案号) {
		this.档案号 = 档案号;
	}

	@Column(name = "车牌号", nullable = false, length = 50)
	public String get车牌号() {
		return this.车牌号;
	}

	public void set车牌号(String 车牌号) {
		this.车牌号 = 车牌号;
	}

	@Column(name = "备注")
	public String get备注() {
		return this.备注;
	}

	public void set备注(String 备注) {
		this.备注 = 备注;
	}

	@Column(name = "车照片")
	public String get车照片() {
		return this.车照片;
	}

	public void set车照片(String 车照片) {
		this.车照片 = 车照片;
	}

	@Column(name = "承包人照片")
	public String get承包人照片() {
		return this.承包人照片;
	}

	public void set承包人照片(String 承包人照片) {
		this.承包人照片 = 承包人照片;
	}

	@Column(name = "是否生效", nullable = false)
	public Boolean get是否生效() {
		return this.是否生效;
	}

	public void set是否生效(Boolean 是否生效) {
		this.是否生效 = 是否生效;
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

	@Column(name = "承包形式", length = 50)
	public String get承包形式() {
		return this.承包形式;
	}

	public void set承包形式(String 承包形式) {
		this.承包形式 = 承包形式;
	}

	@Column(name = "性别", length = 50)
	public String get性别() {
		return this.性别;
	}

	public void set性别(String 性别) {
		this.性别 = 性别;
	}

	@Column(name = "身份证号", length = 50)
	public String get身份证号() {
		return this.身份证号;
	}

	public void set身份证号(String 身份证号) {
		this.身份证号 = 身份证号;
	}

	@Column(name = "驾驶证号", length = 50)
	public String get驾驶证号() {
		return this.驾驶证号;
	}

	public void set驾驶证号(String 驾驶证号) {
		this.驾驶证号 = 驾驶证号;
	}

	@Column(name = "资格证号", length = 50)
	public String get资格证号() {
		return this.资格证号;
	}

	public void set资格证号(String 资格证号) {
		this.资格证号 = 资格证号;
	}

	@Column(name = "证件地址", length = 100)
	public String get证件地址() {
		return this.证件地址;
	}

	public void set证件地址(String 证件地址) {
		this.证件地址 = 证件地址;
	}

	@Column(name = "归属区域", length = 50)
	public String get归属区域() {
		return this.归属区域;
	}

	public void set归属区域(String 归属区域) {
		this.归属区域 = 归属区域;
	}

	@Column(name = "现居住址", length = 100)
	public String get现居住址() {
		return this.现居住址;
	}

	public void set现居住址(String 现居住址) {
		this.现居住址 = 现居住址;
	}

	@Column(name = "现归属区域", length = 50)
	public String get现归属区域() {
		return this.现归属区域;
	}

	public void set现归属区域(String 现归属区域) {
		this.现归属区域 = 现归属区域;
	}

	@Column(name = "宅电号码区号", length = 50)
	public String get宅电号码区号() {
		return this.宅电号码区号;
	}

	public void set宅电号码区号(String 宅电号码区号) {
		this.宅电号码区号 = 宅电号码区号;
	}

	@Column(name = "宅电号码", length = 50)
	public String get宅电号码() {
		return this.宅电号码;
	}

	public void set宅电号码(String 宅电号码) {
		this.宅电号码 = 宅电号码;
	}

	@Column(name = "手机号码", length = 50)
	public String get手机号码() {
		return this.手机号码;
	}

	public void set手机号码(String 手机号码) {
		this.手机号码 = 手机号码;
	}

	@Column(name = "担保人姓名", length = 50)
	public String get担保人姓名() {
		return this.担保人姓名;
	}

	public void set担保人姓名(String 担保人姓名) {
		this.担保人姓名 = 担保人姓名;
	}

	@Column(name = "担保人身份证号", length = 50)
	public String get担保人身份证号() {
		return this.担保人身份证号;
	}

	public void set担保人身份证号(String 担保人身份证号) {
		this.担保人身份证号 = 担保人身份证号;
	}

	@Column(name = "担保人证件地址", length = 50)
	public String get担保人证件地址() {
		return this.担保人证件地址;
	}

	public void set担保人证件地址(String 担保人证件地址) {
		this.担保人证件地址 = 担保人证件地址;
	}

	@Column(name = "担保人现居住址", length = 50)
	public String get担保人现居住址() {
		return this.担保人现居住址;
	}

	public void set担保人现居住址(String 担保人现居住址) {
		this.担保人现居住址 = 担保人现居住址;
	}

	@Column(name = "担保人宅电号码区号", length = 50)
	public String get担保人宅电号码区号() {
		return this.担保人宅电号码区号;
	}

	public void set担保人宅电号码区号(String 担保人宅电号码区号) {
		this.担保人宅电号码区号 = 担保人宅电号码区号;
	}

	@Column(name = "担保人宅电号码", length = 50)
	public String get担保人宅电号码() {
		return this.担保人宅电号码;
	}

	public void set担保人宅电号码(String 担保人宅电号码) {
		this.担保人宅电号码 = 担保人宅电号码;
	}

	@Column(name = "担保人手机号码", length = 50)
	public String get担保人手机号码() {
		return this.担保人手机号码;
	}

	public void set担保人手机号码(String 担保人手机号码) {
		this.担保人手机号码 = 担保人手机号码;
	}

	@Column(name = "转让车辆费用", precision = 18)
	public Double get转让车辆费用() {
		return this.转让车辆费用;
	}

	public void set转让车辆费用(Double 转让车辆费用) {
		this.转让车辆费用 = 转让车辆费用;
	}

	@Column(name = "欠费时间警戒")
	public Integer get欠费时间警戒() {
		return this.欠费时间警戒;
	}

	public void set欠费时间警戒(Integer 欠费时间警戒) {
		this.欠费时间警戒 = 欠费时间警戒;
	}

	@Column(name = "欠费金额警戒", precision = 18, scale = 0)
	public Long get欠费金额警戒() {
		return this.欠费金额警戒;
	}

	public void set欠费金额警戒(Long 欠费金额警戒) {
		this.欠费金额警戒 = 欠费金额警戒;
	}

	@Column(name = "担保人照片")
	public String get担保人照片() {
		return this.担保人照片;
	}

	public void set担保人照片(String 担保人照片) {
		this.担保人照片 = 担保人照片;
	}

	@Column(name = "电子档案号", length = 50)
	public String get电子档案号() {
		return this.电子档案号;
	}

	public void set电子档案号(String 电子档案号) {
		this.电子档案号 = 电子档案号;
	}

	@Column(name = "是否为当前车主", nullable = false)
	public Boolean get是否为当前车主() {
		return this.是否为当前车主;
	}

	public void set是否为当前车主(Boolean 是否为当前车主) {
		this.是否为当前车主 = 是否为当前车主;
	}

	@Column(name = "是否含保险")
	public Boolean get是否含保险() {
		return this.是否含保险;
	}

	public void set是否含保险(Boolean 是否含保险) {
		this.是否含保险 = 是否含保险;
	}

	@Column(name = "录入人", length = 50)
	public String get录入人() {
		return this.录入人;
	}

	public void set录入人(String 录入人) {
		this.录入人 = 录入人;
	}

	@Column(name = "身份证归属区域省", length = 50)
	public String get身份证归属区域省() {
		return this.身份证归属区域省;
	}

	public void set身份证归属区域省(String 身份证归属区域省) {
		this.身份证归属区域省 = 身份证归属区域省;
	}

	@Column(name = "身份证归属区域市", length = 50)
	public String get身份证归属区域市() {
		return this.身份证归属区域市;
	}

	public void set身份证归属区域市(String 身份证归属区域市) {
		this.身份证归属区域市 = 身份证归属区域市;
	}

	@Column(name = "身份证归属区域区", length = 50)
	public String get身份证归属区域区() {
		return this.身份证归属区域区;
	}

	public void set身份证归属区域区(String 身份证归属区域区) {
		this.身份证归属区域区 = 身份证归属区域区;
	}

	@Column(name = "分公司归属", length = 100)
	public String get分公司归属() {
		return this.分公司归属;
	}

	public void set分公司归属(String 分公司归属) {
		this.分公司归属 = 分公司归属;
	}

	@Column(name = "营运手续归属", length = 100)
	public String get营运手续归属() {
		return this.营运手续归属;
	}

	public void set营运手续归属(String 营运手续归属) {
		this.营运手续归属 = 营运手续归属;
	}

	@Column(name = "预付租金", precision = 18)
	public Double get预付租金() {
		return this.预付租金;
	}

	public void set预付租金(Double 预付租金) {
		this.预付租金 = 预付租金;
	}

	@Column(name = "定金", precision = 18)
	public Double get定金() {
		return this.定金;
	}

	public void set定金(Double 定金) {
		this.定金 = 定金;
	}

	@Column(name = "合同种类", length = 100)
	public String get合同种类() {
		return this.合同种类;
	}

	public void set合同种类(String 合同种类) {
		this.合同种类 = 合同种类;
	}

	@Column(name = "标记", length = 50)
	public String get标记() {
		return this.标记;
	}

	public void set标记(String 标记) {
		this.标记 = 标记;
	}

	@Column(name = "月租金", precision = 18)
	public Double get月租金() {
		return this.月租金;
	}

	public void set月租金(Double 月租金) {
		this.月租金 = 月租金;
	}

	@Column(name = "是否更新车辆")
	public Boolean get是否更新车辆() {
		return this.是否更新车辆;
	}

	public void set是否更新车辆(Boolean 是否更新车辆) {
		this.是否更新车辆 = 是否更新车辆;
	}

	@Column(name = "原车牌号", length = 50)
	public String get原车牌号() {
		return this.原车牌号;
	}

	public void set原车牌号(String 原车牌号) {
		this.原车牌号 = 原车牌号;
	}

	@Column(name = "新车牌号", length = 50)
	public String get新车牌号() {
		return this.新车牌号;
	}

	public void set新车牌号(String 新车牌号) {
		this.新车牌号 = 新车牌号;
	}

	@Column(name = "车辆废止标识", length = 50)
	public String get车辆废止标识() {
		return this.车辆废止标识;
	}

	public void set车辆废止标识(String 车辆废止标识) {
		this.车辆废止标识 = 车辆废止标识;
	}

	@Column(name = "录入时间", length = 0)
	public Timestamp get录入时间() {
		return this.录入时间;
	}

	public void set录入时间(Timestamp 录入时间) {
		this.录入时间 = 录入时间;
	}

	@Column(name = "实际终止日期", length = 0)
	public Timestamp get实际终止日期() {
		return this.实际终止日期;
	}

	public void set实际终止日期(Timestamp 实际终止日期) {
		this.实际终止日期 = 实际终止日期;
	}

	@Column(name = "录入方式", length = 50)
	public String get录入方式() {
		return this.录入方式;
	}

	public void set录入方式(String 录入方式) {
		this.录入方式 = 录入方式;
	}

	@Override
	public String toString() {
		return "合同表 [编号=" + 编号 + ", 车架号=" + 车架号 + ", 承包人=" + 承包人 + ", 是否上打租金="
				+ 是否上打租金 + ", 违约金=" + 违约金 + ", 档案号=" + 档案号 + ", 车牌号=" + 车牌号
				+ ", 备注=" + 备注 + ", 车照片=" + 车照片 + ", 承包人照片=" + 承包人照片
				+ ", 是否生效=" + 是否生效 + ", 起始日期=" + 起始日期 + ", 终止日期=" + 终止日期
				+ ", 承包形式=" + 承包形式 + ", 性别=" + 性别 + ", 身份证号=" + 身份证号
				+ ", 驾驶证号=" + 驾驶证号 + ", 资格证号=" + 资格证号 + ", 证件地址=" + 证件地址
				+ ", 归属区域=" + 归属区域 + ", 现居住址=" + 现居住址 + ", 现归属区域=" + 现归属区域
				+ ", 宅电号码区号=" + 宅电号码区号 + ", 宅电号码=" + 宅电号码 + ", 手机号码=" + 手机号码
				+ ", 担保人姓名=" + 担保人姓名 + ", 担保人身份证号=" + 担保人身份证号 + ", 担保人证件地址="
				+ 担保人证件地址 + ", 担保人现居住址=" + 担保人现居住址 + ", 担保人宅电号码区号=" + 担保人宅电号码区号
				+ ", 担保人宅电号码=" + 担保人宅电号码 + ", 担保人手机号码=" + 担保人手机号码 + ", 转让车辆费用="
				+ 转让车辆费用 + ", 欠费时间警戒=" + 欠费时间警戒 + ", 欠费金额警戒=" + 欠费金额警戒
				+ ", 担保人照片=" + 担保人照片 + ", 电子档案号=" + 电子档案号 + ", 是否为当前车主="
				+ 是否为当前车主 + ", 是否含保险=" + 是否含保险 + ", 录入人=" + 录入人 + ", 身份证归属区域省="
				+ 身份证归属区域省 + ", 身份证归属区域市=" + 身份证归属区域市 + ", 身份证归属区域区="
				+ 身份证归属区域区 + ", 分公司归属=" + 分公司归属 + ", 营运手续归属=" + 营运手续归属
				+ ", 预付租金=" + 预付租金 + ", 定金=" + 定金 + ", 合同种类=" + 合同种类 + ", 标记="
				+ 标记 + ", 月租金=" + 月租金 + ", 是否更新车辆=" + 是否更新车辆 + ", 原车牌号=" + 原车牌号
				+ ", 新车牌号=" + 新车牌号 + ", 车辆废止标识=" + 车辆废止标识 + ", 录入时间=" + 录入时间
				+ ", 实际终止日期=" + 实际终止日期 + ", 录入方式=" + 录入方式 + "]";
	}

	
}