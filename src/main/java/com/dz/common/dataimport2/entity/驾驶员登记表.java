package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 驾驶员登记表 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "驾驶员登记表", catalog = "dzimport")
public class 驾驶员登记表 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 表单编号;
	private String 档案号;
	private String 车号;
	private String 承包人;
	private String 驾驶员;
	private String 驾驶员性别;
	private String 驾驶员属性;
	private String 宅电区号;
	private String 驾驶员宅电;
	private String 驾驶员手机;
	private Timestamp 准驾证起始日期;
	private Timestamp 准驾证终止日期;
	private String 身份证号;
	private String 身份证所在地;
	private String 身份证归属区域;
	private String 现住址;
	private String 现住址归属区域;
	private String 驾驶员照片;
	private Boolean 选择;
	private Float 考核分数;
	private Timestamp 上次清零时间;
	private String 驾驶员作息时间;
	private String 备用电话;
	private String 考试编号;
	private String 驾驶证号;
	private String 驾驶证档案号;
	private Timestamp 初次领驾驶证日期;
	private String 驾驶证类型;
	private String 资格证号;
	private Timestamp 资格证有效日期;
	private Timestamp 资格证领证日期;
	private String 身份证归属区域省;
	private String 身份证归属区域市;
	private String 身份证归属区域区;
	private String 现住址归属区域省;
	private String 现住址归属区域市;
	private String 现住址归属区域区;
	private String 分公司归属;
	private Boolean 是否在车;
	private String 人车照编号;
	private String 车队名称;
	private String 指纹编号;
	private String 员工号;
	private String 星级;
	private Boolean scbiaoji;
	private Boolean biaoji;

	// Constructors

	/** default constructor */
	public 驾驶员登记表() {
	}

	/** full constructor */
	public 驾驶员登记表(String 表单编号, String 档案号, String 车号, String 承包人, String 驾驶员,
			String 驾驶员性别, String 驾驶员属性, String 宅电区号, String 驾驶员宅电,
			String 驾驶员手机, Timestamp 准驾证起始日期, Timestamp 准驾证终止日期, String 身份证号,
			String 身份证所在地, String 身份证归属区域, String 现住址, String 现住址归属区域,
			String 驾驶员照片, Boolean 选择, Float 考核分数, Timestamp 上次清零时间,
			String 驾驶员作息时间, String 备用电话, String 考试编号, String 驾驶证号,
			String 驾驶证档案号, Timestamp 初次领驾驶证日期, String 驾驶证类型, String 资格证号,
			Timestamp 资格证有效日期, Timestamp 资格证领证日期, String 身份证归属区域省,
			String 身份证归属区域市, String 身份证归属区域区, String 现住址归属区域省, String 现住址归属区域市,
			String 现住址归属区域区, String 分公司归属, Boolean 是否在车, String 人车照编号,
			String 车队名称, String 指纹编号, String 员工号, String 星级, Boolean scbiaoji,
			Boolean biaoji) {
		this.表单编号 = 表单编号;
		this.档案号 = 档案号;
		this.车号 = 车号;
		this.承包人 = 承包人;
		this.驾驶员 = 驾驶员;
		this.驾驶员性别 = 驾驶员性别;
		this.驾驶员属性 = 驾驶员属性;
		this.宅电区号 = 宅电区号;
		this.驾驶员宅电 = 驾驶员宅电;
		this.驾驶员手机 = 驾驶员手机;
		this.准驾证起始日期 = 准驾证起始日期;
		this.准驾证终止日期 = 准驾证终止日期;
		this.身份证号 = 身份证号;
		this.身份证所在地 = 身份证所在地;
		this.身份证归属区域 = 身份证归属区域;
		this.现住址 = 现住址;
		this.现住址归属区域 = 现住址归属区域;
		this.驾驶员照片 = 驾驶员照片;
		this.选择 = 选择;
		this.考核分数 = 考核分数;
		this.上次清零时间 = 上次清零时间;
		this.驾驶员作息时间 = 驾驶员作息时间;
		this.备用电话 = 备用电话;
		this.考试编号 = 考试编号;
		this.驾驶证号 = 驾驶证号;
		this.驾驶证档案号 = 驾驶证档案号;
		this.初次领驾驶证日期 = 初次领驾驶证日期;
		this.驾驶证类型 = 驾驶证类型;
		this.资格证号 = 资格证号;
		this.资格证有效日期 = 资格证有效日期;
		this.资格证领证日期 = 资格证领证日期;
		this.身份证归属区域省 = 身份证归属区域省;
		this.身份证归属区域市 = 身份证归属区域市;
		this.身份证归属区域区 = 身份证归属区域区;
		this.现住址归属区域省 = 现住址归属区域省;
		this.现住址归属区域市 = 现住址归属区域市;
		this.现住址归属区域区 = 现住址归属区域区;
		this.分公司归属 = 分公司归属;
		this.是否在车 = 是否在车;
		this.人车照编号 = 人车照编号;
		this.车队名称 = 车队名称;
		this.指纹编号 = 指纹编号;
		this.员工号 = 员工号;
		this.星级 = 星级;
		this.scbiaoji = scbiaoji;
		this.biaoji = biaoji;
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

	@Column(name = "承包人", length = 50)
	public String get承包人() {
		return this.承包人;
	}

	public void set承包人(String 承包人) {
		this.承包人 = 承包人;
	}

	@Column(name = "驾驶员", length = 50)
	public String get驾驶员() {
		return this.驾驶员;
	}

	public void set驾驶员(String 驾驶员) {
		this.驾驶员 = 驾驶员;
	}

	@Column(name = "驾驶员性别", length = 50)
	public String get驾驶员性别() {
		return this.驾驶员性别;
	}

	public void set驾驶员性别(String 驾驶员性别) {
		this.驾驶员性别 = 驾驶员性别;
	}

	@Column(name = "驾驶员属性", length = 50)
	public String get驾驶员属性() {
		return this.驾驶员属性;
	}

	public void set驾驶员属性(String 驾驶员属性) {
		this.驾驶员属性 = 驾驶员属性;
	}

	@Column(name = "宅电区号", length = 50)
	public String get宅电区号() {
		return this.宅电区号;
	}

	public void set宅电区号(String 宅电区号) {
		this.宅电区号 = 宅电区号;
	}

	@Column(name = "驾驶员宅电", length = 50)
	public String get驾驶员宅电() {
		return this.驾驶员宅电;
	}

	public void set驾驶员宅电(String 驾驶员宅电) {
		this.驾驶员宅电 = 驾驶员宅电;
	}

	@Column(name = "驾驶员手机", length = 50)
	public String get驾驶员手机() {
		return this.驾驶员手机;
	}

	public void set驾驶员手机(String 驾驶员手机) {
		this.驾驶员手机 = 驾驶员手机;
	}

	@Column(name = "准驾证起始日期", length = 0)
	public Timestamp get准驾证起始日期() {
		return this.准驾证起始日期;
	}

	public void set准驾证起始日期(Timestamp 准驾证起始日期) {
		this.准驾证起始日期 = 准驾证起始日期;
	}

	@Column(name = "准驾证终止日期", length = 0)
	public Timestamp get准驾证终止日期() {
		return this.准驾证终止日期;
	}

	public void set准驾证终止日期(Timestamp 准驾证终止日期) {
		this.准驾证终止日期 = 准驾证终止日期;
	}

	@Column(name = "身份证号", length = 50)
	public String get身份证号() {
		return this.身份证号;
	}

	public void set身份证号(String 身份证号) {
		this.身份证号 = 身份证号;
	}

	@Column(name = "身份证所在地", length = 100)
	public String get身份证所在地() {
		return this.身份证所在地;
	}

	public void set身份证所在地(String 身份证所在地) {
		this.身份证所在地 = 身份证所在地;
	}

	@Column(name = "身份证归属区域", length = 100)
	public String get身份证归属区域() {
		return this.身份证归属区域;
	}

	public void set身份证归属区域(String 身份证归属区域) {
		this.身份证归属区域 = 身份证归属区域;
	}

	@Column(name = "现住址", length = 100)
	public String get现住址() {
		return this.现住址;
	}

	public void set现住址(String 现住址) {
		this.现住址 = 现住址;
	}

	@Column(name = "现住址归属区域", length = 100)
	public String get现住址归属区域() {
		return this.现住址归属区域;
	}

	public void set现住址归属区域(String 现住址归属区域) {
		this.现住址归属区域 = 现住址归属区域;
	}

	@Column(name = "驾驶员照片")
	public String get驾驶员照片() {
		return this.驾驶员照片;
	}

	public void set驾驶员照片(String 驾驶员照片) {
		this.驾驶员照片 = 驾驶员照片;
	}

	@Column(name = "选择")
	public Boolean get选择() {
		return this.选择;
	}

	public void set选择(Boolean 选择) {
		this.选择 = 选择;
	}

	@Column(name = "考核分数", precision = 12, scale = 0)
	public Float get考核分数() {
		return this.考核分数;
	}

	public void set考核分数(Float 考核分数) {
		this.考核分数 = 考核分数;
	}

	@Column(name = "上次清零时间", length = 0)
	public Timestamp get上次清零时间() {
		return this.上次清零时间;
	}

	public void set上次清零时间(Timestamp 上次清零时间) {
		this.上次清零时间 = 上次清零时间;
	}

	@Column(name = "驾驶员作息时间", length = 50)
	public String get驾驶员作息时间() {
		return this.驾驶员作息时间;
	}

	public void set驾驶员作息时间(String 驾驶员作息时间) {
		this.驾驶员作息时间 = 驾驶员作息时间;
	}

	@Column(name = "备用电话", length = 50)
	public String get备用电话() {
		return this.备用电话;
	}

	public void set备用电话(String 备用电话) {
		this.备用电话 = 备用电话;
	}

	@Column(name = "考试编号", length = 50)
	public String get考试编号() {
		return this.考试编号;
	}

	public void set考试编号(String 考试编号) {
		this.考试编号 = 考试编号;
	}

	@Column(name = "驾驶证号", length = 50)
	public String get驾驶证号() {
		return this.驾驶证号;
	}

	public void set驾驶证号(String 驾驶证号) {
		this.驾驶证号 = 驾驶证号;
	}

	@Column(name = "驾驶证档案号", length = 50)
	public String get驾驶证档案号() {
		return this.驾驶证档案号;
	}

	public void set驾驶证档案号(String 驾驶证档案号) {
		this.驾驶证档案号 = 驾驶证档案号;
	}

	@Column(name = "初次领驾驶证日期", length = 0)
	public Timestamp get初次领驾驶证日期() {
		return this.初次领驾驶证日期;
	}

	public void set初次领驾驶证日期(Timestamp 初次领驾驶证日期) {
		this.初次领驾驶证日期 = 初次领驾驶证日期;
	}

	@Column(name = "驾驶证类型", length = 50)
	public String get驾驶证类型() {
		return this.驾驶证类型;
	}

	public void set驾驶证类型(String 驾驶证类型) {
		this.驾驶证类型 = 驾驶证类型;
	}

	@Column(name = "资格证号", length = 50)
	public String get资格证号() {
		return this.资格证号;
	}

	public void set资格证号(String 资格证号) {
		this.资格证号 = 资格证号;
	}

	@Column(name = "资格证有效日期", length = 0)
	public Timestamp get资格证有效日期() {
		return this.资格证有效日期;
	}

	public void set资格证有效日期(Timestamp 资格证有效日期) {
		this.资格证有效日期 = 资格证有效日期;
	}

	@Column(name = "资格证领证日期", length = 0)
	public Timestamp get资格证领证日期() {
		return this.资格证领证日期;
	}

	public void set资格证领证日期(Timestamp 资格证领证日期) {
		this.资格证领证日期 = 资格证领证日期;
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

	@Column(name = "现住址归属区域省", length = 50)
	public String get现住址归属区域省() {
		return this.现住址归属区域省;
	}

	public void set现住址归属区域省(String 现住址归属区域省) {
		this.现住址归属区域省 = 现住址归属区域省;
	}

	@Column(name = "现住址归属区域市", length = 50)
	public String get现住址归属区域市() {
		return this.现住址归属区域市;
	}

	public void set现住址归属区域市(String 现住址归属区域市) {
		this.现住址归属区域市 = 现住址归属区域市;
	}

	@Column(name = "现住址归属区域区", length = 50)
	public String get现住址归属区域区() {
		return this.现住址归属区域区;
	}

	public void set现住址归属区域区(String 现住址归属区域区) {
		this.现住址归属区域区 = 现住址归属区域区;
	}

	@Column(name = "分公司归属", length = 50)
	public String get分公司归属() {
		return this.分公司归属;
	}

	public void set分公司归属(String 分公司归属) {
		this.分公司归属 = 分公司归属;
	}

	@Column(name = "是否在车")
	public Boolean get是否在车() {
		return this.是否在车;
	}

	public void set是否在车(Boolean 是否在车) {
		this.是否在车 = 是否在车;
	}

	@Column(name = "人车照编号", length = 50)
	public String get人车照编号() {
		return this.人车照编号;
	}

	public void set人车照编号(String 人车照编号) {
		this.人车照编号 = 人车照编号;
	}

	@Column(name = "车队名称", length = 50)
	public String get车队名称() {
		return this.车队名称;
	}

	public void set车队名称(String 车队名称) {
		this.车队名称 = 车队名称;
	}

	@Column(name = "指纹编号", length = 50)
	public String get指纹编号() {
		return this.指纹编号;
	}

	public void set指纹编号(String 指纹编号) {
		this.指纹编号 = 指纹编号;
	}

	@Column(name = "员工号", length = 50)
	public String get员工号() {
		return this.员工号;
	}

	public void set员工号(String 员工号) {
		this.员工号 = 员工号;
	}

	@Column(name = "星级", length = 50)
	public String get星级() {
		return this.星级;
	}

	public void set星级(String 星级) {
		this.星级 = 星级;
	}

	@Column(name = "scbiaoji")
	public Boolean getScbiaoji() {
		return this.scbiaoji;
	}

	public void setScbiaoji(Boolean scbiaoji) {
		this.scbiaoji = scbiaoji;
	}

	@Column(name = "biaoji")
	public Boolean getBiaoji() {
		return this.biaoji;
	}

	public void setBiaoji(Boolean biaoji) {
		this.biaoji = biaoji;
	}

	@Override
	public String toString() {
		return "驾驶员登记表 [编号=" + 编号 + ", 表单编号=" + 表单编号 + ", 档案号=" + 档案号 + ", 车号="
				+ 车号 + ", 承包人=" + 承包人 + ", 驾驶员=" + 驾驶员 + ", 驾驶员性别=" + 驾驶员性别
				+ ", 驾驶员属性=" + 驾驶员属性 + ", 宅电区号=" + 宅电区号 + ", 驾驶员宅电=" + 驾驶员宅电
				+ ", 驾驶员手机=" + 驾驶员手机 + ", 准驾证起始日期=" + 准驾证起始日期 + ", 准驾证终止日期="
				+ 准驾证终止日期 + ", 身份证号=" + 身份证号 + ", 身份证所在地=" + 身份证所在地
				+ ", 身份证归属区域=" + 身份证归属区域 + ", 现住址=" + 现住址 + ", 现住址归属区域="
				+ 现住址归属区域 + ", 驾驶员照片=" + 驾驶员照片 + ", 选择=" + 选择 + ", 考核分数="
				+ 考核分数 + ", 上次清零时间=" + 上次清零时间 + ", 驾驶员作息时间=" + 驾驶员作息时间
				+ ", 备用电话=" + 备用电话 + ", 考试编号=" + 考试编号 + ", 驾驶证号=" + 驾驶证号
				+ ", 驾驶证档案号=" + 驾驶证档案号 + ", 初次领驾驶证日期=" + 初次领驾驶证日期 + ", 驾驶证类型="
				+ 驾驶证类型 + ", 资格证号=" + 资格证号 + ", 资格证有效日期=" + 资格证有效日期
				+ ", 资格证领证日期=" + 资格证领证日期 + ", 身份证归属区域省=" + 身份证归属区域省
				+ ", 身份证归属区域市=" + 身份证归属区域市 + ", 身份证归属区域区=" + 身份证归属区域区
				+ ", 现住址归属区域省=" + 现住址归属区域省 + ", 现住址归属区域市=" + 现住址归属区域市
				+ ", 现住址归属区域区=" + 现住址归属区域区 + ", 分公司归属=" + 分公司归属 + ", 是否在车="
				+ 是否在车 + ", 人车照编号=" + 人车照编号 + ", 车队名称=" + 车队名称 + ", 指纹编号="
				+ 指纹编号 + ", 员工号=" + 员工号 + ", 星级=" + 星级 + ", scbiaoji="
				+ scbiaoji + ", biaoji=" + biaoji + "]";
	}
	
	

}