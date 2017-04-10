package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 驾驶员登记表大众 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "驾驶员登记表_大众", catalog = "dzimport")
public class 驾驶员登记表大众 implements java.io.Serializable {

	// Fields

	private Integer 编号;
	private String 表单编号;
	private String 车牌号;
	private String 承租人;
	private String 档案号;
	private String 姓名;
	private String 性别;
	private Integer 年龄;
	private String 身份证号;
	private String 户口所在地;
	private String 家庭现住址;
	private String 联系电话1;
	private String 联系电话2;
	private String 身高;
	private String 面部有无疤痕;
	private String 婚姻状况;
	private String 政治面貌;
	private String 是否从事过出租车营运;
	private Integer 从事过出租车营运年数;
	private String 原所在公司;
	private String 原所在公司车号;
	private String 驾驶员作息时间;
	private String 驾驶员属性;
	private String 驾驶证类型;
	private String 申请事项;
	private String 个人简历;
	private String 兴趣爱好;
	private String 特长;
	private String qq;
	private Timestamp 驾驶证初领日期;
	private Timestamp 驾驶证有效期至;
	private String 驾驶证档案号;
	private Timestamp 资格证有效日期;
	private Timestamp 资格证初领日期;
	private String 资格证号;
	private String 文化程度;
	private String 身体健康状况;
	private String 语言表达能力;
	private String 对哈市熟悉程度;
	private String 有无前科劣迹;
	private String 违章记录;
	private String 测试成绩一次;
	private String 测试成绩二次;
	private String 测试成绩三次;
	private String 主管副总经理意见;
	private String 登记人;
	private String 测试人;
	private Timestamp 申请时间;
	private Timestamp 录用时间;
	private Boolean 是否录用;
	private String 驾驶员照片;
	private Timestamp 营运证申请日期;
	private Timestamp 营运证注销申请日期;
	private Timestamp 营运证发证日期;
	private Timestamp 营运证注销日期;
	private Boolean 是否办理证照;
	private Boolean 是否在车;
	private Boolean 是否岗前考试;
	private String 驾驶证号;
	private String 分公司归属;
	private String 交接班地点;
	private String 交接班时间;
	private String 加油地点;
	private String 营运场站;
	private String 办领证照经手人;
	private Timestamp 办领证照时间;
	private String 人车照编号;
	private Timestamp 登记时间;
	private String 申请登记证照经手人;
	private Timestamp 申请登记证照时间;
	private String 申请注销证照经手人;
	private Timestamp 申请注销证照时间;
	private String 办理注销证照经手人;
	private Timestamp 办理注销证照时间;
	private String 人车照片;
	private String 民族;
	private String 计财部确认人;
	private Timestamp 计财部确认时间;
	private String 运营管理部确认人;
	private Timestamp 运营管理部确认时间;
	private String 综合办公室确认人;
	private Timestamp 综合办公室确认时间;
	private Double 服务保证金;
	private String 事故记录;
	private String 考核人确认;
	private Timestamp 考核人确认时间;
	private String 运营管理经理确认;
	private Timestamp 运营管理经理确认时间;
	private String 车队名称;
	private String 指纹编号;
	private String 员工号;
	private String 星级;
	private String 银行卡类别;
	private String 银行卡号;

	// Constructors

	/** default constructor */
	public 驾驶员登记表大众() {
	}

	/** full constructor */
	public 驾驶员登记表大众(String 表单编号, String 车牌号, String 承租人, String 档案号, String 姓名,
			String 性别, Integer 年龄, String 身份证号, String 户口所在地, String 家庭现住址,
			String 联系电话1, String 联系电话2, String 身高, String 面部有无疤痕, String 婚姻状况,
			String 政治面貌, String 是否从事过出租车营运, Integer 从事过出租车营运年数, String 原所在公司,
			String 原所在公司车号, String 驾驶员作息时间, String 驾驶员属性, String 驾驶证类型,
			String 申请事项, String 个人简历, String 兴趣爱好, String 特长, String qq,
			Timestamp 驾驶证初领日期, Timestamp 驾驶证有效期至, String 驾驶证档案号,
			Timestamp 资格证有效日期, Timestamp 资格证初领日期, String 资格证号, String 文化程度,
			String 身体健康状况, String 语言表达能力, String 对哈市熟悉程度, String 有无前科劣迹,
			String 违章记录, String 测试成绩一次, String 测试成绩二次, String 测试成绩三次,
			String 主管副总经理意见, String 登记人, String 测试人, Timestamp 申请时间,
			Timestamp 录用时间, Boolean 是否录用, String 驾驶员照片, Timestamp 营运证申请日期,
			Timestamp 营运证注销申请日期, Timestamp 营运证发证日期, Timestamp 营运证注销日期,
			Boolean 是否办理证照, Boolean 是否在车, Boolean 是否岗前考试, String 驾驶证号,
			String 分公司归属, String 交接班地点, String 交接班时间, String 加油地点, String 营运场站,
			String 办领证照经手人, Timestamp 办领证照时间, String 人车照编号, Timestamp 登记时间,
			String 申请登记证照经手人, Timestamp 申请登记证照时间, String 申请注销证照经手人,
			Timestamp 申请注销证照时间, String 办理注销证照经手人, Timestamp 办理注销证照时间,
			String 人车照片, String 民族, String 计财部确认人, Timestamp 计财部确认时间,
			String 运营管理部确认人, Timestamp 运营管理部确认时间, String 综合办公室确认人,
			Timestamp 综合办公室确认时间, Double 服务保证金, String 事故记录, String 考核人确认,
			Timestamp 考核人确认时间, String 运营管理经理确认, Timestamp 运营管理经理确认时间,
			String 车队名称, String 指纹编号, String 员工号, String 星级, String 银行卡类别,
			String 银行卡号) {
		this.表单编号 = 表单编号;
		this.车牌号 = 车牌号;
		this.承租人 = 承租人;
		this.档案号 = 档案号;
		this.姓名 = 姓名;
		this.性别 = 性别;
		this.年龄 = 年龄;
		this.身份证号 = 身份证号;
		this.户口所在地 = 户口所在地;
		this.家庭现住址 = 家庭现住址;
		this.联系电话1 = 联系电话1;
		this.联系电话2 = 联系电话2;
		this.身高 = 身高;
		this.面部有无疤痕 = 面部有无疤痕;
		this.婚姻状况 = 婚姻状况;
		this.政治面貌 = 政治面貌;
		this.是否从事过出租车营运 = 是否从事过出租车营运;
		this.从事过出租车营运年数 = 从事过出租车营运年数;
		this.原所在公司 = 原所在公司;
		this.原所在公司车号 = 原所在公司车号;
		this.驾驶员作息时间 = 驾驶员作息时间;
		this.驾驶员属性 = 驾驶员属性;
		this.驾驶证类型 = 驾驶证类型;
		this.申请事项 = 申请事项;
		this.个人简历 = 个人简历;
		this.兴趣爱好 = 兴趣爱好;
		this.特长 = 特长;
		this.qq = qq;
		this.驾驶证初领日期 = 驾驶证初领日期;
		this.驾驶证有效期至 = 驾驶证有效期至;
		this.驾驶证档案号 = 驾驶证档案号;
		this.资格证有效日期 = 资格证有效日期;
		this.资格证初领日期 = 资格证初领日期;
		this.资格证号 = 资格证号;
		this.文化程度 = 文化程度;
		this.身体健康状况 = 身体健康状况;
		this.语言表达能力 = 语言表达能力;
		this.对哈市熟悉程度 = 对哈市熟悉程度;
		this.有无前科劣迹 = 有无前科劣迹;
		this.违章记录 = 违章记录;
		this.测试成绩一次 = 测试成绩一次;
		this.测试成绩二次 = 测试成绩二次;
		this.测试成绩三次 = 测试成绩三次;
		this.主管副总经理意见 = 主管副总经理意见;
		this.登记人 = 登记人;
		this.测试人 = 测试人;
		this.申请时间 = 申请时间;
		this.录用时间 = 录用时间;
		this.是否录用 = 是否录用;
		this.驾驶员照片 = 驾驶员照片;
		this.营运证申请日期 = 营运证申请日期;
		this.营运证注销申请日期 = 营运证注销申请日期;
		this.营运证发证日期 = 营运证发证日期;
		this.营运证注销日期 = 营运证注销日期;
		this.是否办理证照 = 是否办理证照;
		this.是否在车 = 是否在车;
		this.是否岗前考试 = 是否岗前考试;
		this.驾驶证号 = 驾驶证号;
		this.分公司归属 = 分公司归属;
		this.交接班地点 = 交接班地点;
		this.交接班时间 = 交接班时间;
		this.加油地点 = 加油地点;
		this.营运场站 = 营运场站;
		this.办领证照经手人 = 办领证照经手人;
		this.办领证照时间 = 办领证照时间;
		this.人车照编号 = 人车照编号;
		this.登记时间 = 登记时间;
		this.申请登记证照经手人 = 申请登记证照经手人;
		this.申请登记证照时间 = 申请登记证照时间;
		this.申请注销证照经手人 = 申请注销证照经手人;
		this.申请注销证照时间 = 申请注销证照时间;
		this.办理注销证照经手人 = 办理注销证照经手人;
		this.办理注销证照时间 = 办理注销证照时间;
		this.人车照片 = 人车照片;
		this.民族 = 民族;
		this.计财部确认人 = 计财部确认人;
		this.计财部确认时间 = 计财部确认时间;
		this.运营管理部确认人 = 运营管理部确认人;
		this.运营管理部确认时间 = 运营管理部确认时间;
		this.综合办公室确认人 = 综合办公室确认人;
		this.综合办公室确认时间 = 综合办公室确认时间;
		this.服务保证金 = 服务保证金;
		this.事故记录 = 事故记录;
		this.考核人确认 = 考核人确认;
		this.考核人确认时间 = 考核人确认时间;
		this.运营管理经理确认 = 运营管理经理确认;
		this.运营管理经理确认时间 = 运营管理经理确认时间;
		this.车队名称 = 车队名称;
		this.指纹编号 = 指纹编号;
		this.员工号 = 员工号;
		this.星级 = 星级;
		this.银行卡类别 = 银行卡类别;
		this.银行卡号 = 银行卡号;
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

	@Column(name = "车牌号", length = 50)
	public String get车牌号() {
		return this.车牌号;
	}

	public void set车牌号(String 车牌号) {
		this.车牌号 = 车牌号;
	}

	@Column(name = "承租人", length = 50)
	public String get承租人() {
		return this.承租人;
	}

	public void set承租人(String 承租人) {
		this.承租人 = 承租人;
	}

	@Column(name = "档案号", length = 50)
	public String get档案号() {
		return this.档案号;
	}

	public void set档案号(String 档案号) {
		this.档案号 = 档案号;
	}

	@Column(name = "姓名", length = 50)
	public String get姓名() {
		return this.姓名;
	}

	public void set姓名(String 姓名) {
		this.姓名 = 姓名;
	}

	@Column(name = "性别", length = 50)
	public String get性别() {
		return this.性别;
	}

	public void set性别(String 性别) {
		this.性别 = 性别;
	}

	@Column(name = "年龄")
	public Integer get年龄() {
		return this.年龄;
	}

	public void set年龄(Integer 年龄) {
		this.年龄 = 年龄;
	}

	@Column(name = "身份证号", length = 50)
	public String get身份证号() {
		return this.身份证号;
	}

	public void set身份证号(String 身份证号) {
		this.身份证号 = 身份证号;
	}

	@Column(name = "户口所在地", length = 500)
	public String get户口所在地() {
		return this.户口所在地;
	}

	public void set户口所在地(String 户口所在地) {
		this.户口所在地 = 户口所在地;
	}

	@Column(name = "家庭现住址", length = 500)
	public String get家庭现住址() {
		return this.家庭现住址;
	}

	public void set家庭现住址(String 家庭现住址) {
		this.家庭现住址 = 家庭现住址;
	}

	@Column(name = "联系电话1", length = 50)
	public String get联系电话1() {
		return this.联系电话1;
	}

	public void set联系电话1(String 联系电话1) {
		this.联系电话1 = 联系电话1;
	}

	@Column(name = "联系电话2", length = 50)
	public String get联系电话2() {
		return this.联系电话2;
	}

	public void set联系电话2(String 联系电话2) {
		this.联系电话2 = 联系电话2;
	}

	@Column(name = "身高", length = 50)
	public String get身高() {
		return this.身高;
	}

	public void set身高(String 身高) {
		this.身高 = 身高;
	}

	@Column(name = "面部有无疤痕", length = 50)
	public String get面部有无疤痕() {
		return this.面部有无疤痕;
	}

	public void set面部有无疤痕(String 面部有无疤痕) {
		this.面部有无疤痕 = 面部有无疤痕;
	}

	@Column(name = "婚姻状况", length = 50)
	public String get婚姻状况() {
		return this.婚姻状况;
	}

	public void set婚姻状况(String 婚姻状况) {
		this.婚姻状况 = 婚姻状况;
	}

	@Column(name = "政治面貌", length = 50)
	public String get政治面貌() {
		return this.政治面貌;
	}

	public void set政治面貌(String 政治面貌) {
		this.政治面貌 = 政治面貌;
	}

	@Column(name = "是否从事过出租车营运", length = 50)
	public String get是否从事过出租车营运() {
		return this.是否从事过出租车营运;
	}

	public void set是否从事过出租车营运(String 是否从事过出租车营运) {
		this.是否从事过出租车营运 = 是否从事过出租车营运;
	}

	@Column(name = "从事过出租车营运年数")
	public Integer get从事过出租车营运年数() {
		return this.从事过出租车营运年数;
	}

	public void set从事过出租车营运年数(Integer 从事过出租车营运年数) {
		this.从事过出租车营运年数 = 从事过出租车营运年数;
	}

	@Column(name = "原所在公司", length = 500)
	public String get原所在公司() {
		return this.原所在公司;
	}

	public void set原所在公司(String 原所在公司) {
		this.原所在公司 = 原所在公司;
	}

	@Column(name = "原所在公司车号", length = 50)
	public String get原所在公司车号() {
		return this.原所在公司车号;
	}

	public void set原所在公司车号(String 原所在公司车号) {
		this.原所在公司车号 = 原所在公司车号;
	}

	@Column(name = "驾驶员作息时间", length = 50)
	public String get驾驶员作息时间() {
		return this.驾驶员作息时间;
	}

	public void set驾驶员作息时间(String 驾驶员作息时间) {
		this.驾驶员作息时间 = 驾驶员作息时间;
	}

	@Column(name = "驾驶员属性", length = 50)
	public String get驾驶员属性() {
		return this.驾驶员属性;
	}

	public void set驾驶员属性(String 驾驶员属性) {
		this.驾驶员属性 = 驾驶员属性;
	}

	@Column(name = "驾驶证类型", length = 50)
	public String get驾驶证类型() {
		return this.驾驶证类型;
	}

	public void set驾驶证类型(String 驾驶证类型) {
		this.驾驶证类型 = 驾驶证类型;
	}

	@Column(name = "申请事项", length = 50)
	public String get申请事项() {
		return this.申请事项;
	}

	public void set申请事项(String 申请事项) {
		this.申请事项 = 申请事项;
	}

	@Column(name = "个人简历", length = 5000)
	public String get个人简历() {
		return this.个人简历;
	}

	public void set个人简历(String 个人简历) {
		this.个人简历 = 个人简历;
	}

	@Column(name = "兴趣爱好", length = 500)
	public String get兴趣爱好() {
		return this.兴趣爱好;
	}

	public void set兴趣爱好(String 兴趣爱好) {
		this.兴趣爱好 = 兴趣爱好;
	}

	@Column(name = "特长", length = 500)
	public String get特长() {
		return this.特长;
	}

	public void set特长(String 特长) {
		this.特长 = 特长;
	}

	@Column(name = "QQ", length = 50)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "驾驶证初领日期", length = 0)
	public Timestamp get驾驶证初领日期() {
		return this.驾驶证初领日期;
	}

	public void set驾驶证初领日期(Timestamp 驾驶证初领日期) {
		this.驾驶证初领日期 = 驾驶证初领日期;
	}

	@Column(name = "驾驶证有效期至", length = 0)
	public Timestamp get驾驶证有效期至() {
		return this.驾驶证有效期至;
	}

	public void set驾驶证有效期至(Timestamp 驾驶证有效期至) {
		this.驾驶证有效期至 = 驾驶证有效期至;
	}

	@Column(name = "驾驶证档案号", length = 50)
	public String get驾驶证档案号() {
		return this.驾驶证档案号;
	}

	public void set驾驶证档案号(String 驾驶证档案号) {
		this.驾驶证档案号 = 驾驶证档案号;
	}

	@Column(name = "资格证有效日期", length = 0)
	public Timestamp get资格证有效日期() {
		return this.资格证有效日期;
	}

	public void set资格证有效日期(Timestamp 资格证有效日期) {
		this.资格证有效日期 = 资格证有效日期;
	}

	@Column(name = "资格证初领日期", length = 0)
	public Timestamp get资格证初领日期() {
		return this.资格证初领日期;
	}

	public void set资格证初领日期(Timestamp 资格证初领日期) {
		this.资格证初领日期 = 资格证初领日期;
	}

	@Column(name = "资格证号", length = 50)
	public String get资格证号() {
		return this.资格证号;
	}

	public void set资格证号(String 资格证号) {
		this.资格证号 = 资格证号;
	}

	@Column(name = "文化程度", length = 50)
	public String get文化程度() {
		return this.文化程度;
	}

	public void set文化程度(String 文化程度) {
		this.文化程度 = 文化程度;
	}

	@Column(name = "身体健康状况", length = 50)
	public String get身体健康状况() {
		return this.身体健康状况;
	}

	public void set身体健康状况(String 身体健康状况) {
		this.身体健康状况 = 身体健康状况;
	}

	@Column(name = "语言表达能力", length = 50)
	public String get语言表达能力() {
		return this.语言表达能力;
	}

	public void set语言表达能力(String 语言表达能力) {
		this.语言表达能力 = 语言表达能力;
	}

	@Column(name = "对哈市熟悉程度", length = 50)
	public String get对哈市熟悉程度() {
		return this.对哈市熟悉程度;
	}

	public void set对哈市熟悉程度(String 对哈市熟悉程度) {
		this.对哈市熟悉程度 = 对哈市熟悉程度;
	}

	@Column(name = "有无前科劣迹", length = 50)
	public String get有无前科劣迹() {
		return this.有无前科劣迹;
	}

	public void set有无前科劣迹(String 有无前科劣迹) {
		this.有无前科劣迹 = 有无前科劣迹;
	}

	@Column(name = "违章记录", length = 50)
	public String get违章记录() {
		return this.违章记录;
	}

	public void set违章记录(String 违章记录) {
		this.违章记录 = 违章记录;
	}

	@Column(name = "测试成绩一次", length = 50)
	public String get测试成绩一次() {
		return this.测试成绩一次;
	}

	public void set测试成绩一次(String 测试成绩一次) {
		this.测试成绩一次 = 测试成绩一次;
	}

	@Column(name = "测试成绩二次", length = 50)
	public String get测试成绩二次() {
		return this.测试成绩二次;
	}

	public void set测试成绩二次(String 测试成绩二次) {
		this.测试成绩二次 = 测试成绩二次;
	}

	@Column(name = "测试成绩三次", length = 50)
	public String get测试成绩三次() {
		return this.测试成绩三次;
	}

	public void set测试成绩三次(String 测试成绩三次) {
		this.测试成绩三次 = 测试成绩三次;
	}

	@Column(name = "主管副总经理意见", length = 500)
	public String get主管副总经理意见() {
		return this.主管副总经理意见;
	}

	public void set主管副总经理意见(String 主管副总经理意见) {
		this.主管副总经理意见 = 主管副总经理意见;
	}

	@Column(name = "登记人", length = 50)
	public String get登记人() {
		return this.登记人;
	}

	public void set登记人(String 登记人) {
		this.登记人 = 登记人;
	}

	@Column(name = "测试人", length = 50)
	public String get测试人() {
		return this.测试人;
	}

	public void set测试人(String 测试人) {
		this.测试人 = 测试人;
	}

	@Column(name = "申请时间", length = 0)
	public Timestamp get申请时间() {
		return this.申请时间;
	}

	public void set申请时间(Timestamp 申请时间) {
		this.申请时间 = 申请时间;
	}

	@Column(name = "录用时间", length = 0)
	public Timestamp get录用时间() {
		return this.录用时间;
	}

	public void set录用时间(Timestamp 录用时间) {
		this.录用时间 = 录用时间;
	}

	@Column(name = "是否录用")
	public Boolean get是否录用() {
		return this.是否录用;
	}

	public void set是否录用(Boolean 是否录用) {
		this.是否录用 = 是否录用;
	}

	@Column(name = "驾驶员照片")
	public String get驾驶员照片() {
		return this.驾驶员照片;
	}

	public void set驾驶员照片(String 驾驶员照片) {
		this.驾驶员照片 = 驾驶员照片;
	}

	@Column(name = "营运证申请日期", length = 0)
	public Timestamp get营运证申请日期() {
		return this.营运证申请日期;
	}

	public void set营运证申请日期(Timestamp 营运证申请日期) {
		this.营运证申请日期 = 营运证申请日期;
	}

	@Column(name = "营运证注销申请日期", length = 0)
	public Timestamp get营运证注销申请日期() {
		return this.营运证注销申请日期;
	}

	public void set营运证注销申请日期(Timestamp 营运证注销申请日期) {
		this.营运证注销申请日期 = 营运证注销申请日期;
	}

	@Column(name = "营运证发证日期", length = 0)
	public Timestamp get营运证发证日期() {
		return this.营运证发证日期;
	}

	public void set营运证发证日期(Timestamp 营运证发证日期) {
		this.营运证发证日期 = 营运证发证日期;
	}

	@Column(name = "营运证注销日期", length = 0)
	public Timestamp get营运证注销日期() {
		return this.营运证注销日期;
	}

	public void set营运证注销日期(Timestamp 营运证注销日期) {
		this.营运证注销日期 = 营运证注销日期;
	}

	@Column(name = "是否办理证照")
	public Boolean get是否办理证照() {
		return this.是否办理证照;
	}

	public void set是否办理证照(Boolean 是否办理证照) {
		this.是否办理证照 = 是否办理证照;
	}

	@Column(name = "是否在车")
	public Boolean get是否在车() {
		return this.是否在车;
	}

	public void set是否在车(Boolean 是否在车) {
		this.是否在车 = 是否在车;
	}

	@Column(name = "是否岗前考试")
	public Boolean get是否岗前考试() {
		return this.是否岗前考试;
	}

	public void set是否岗前考试(Boolean 是否岗前考试) {
		this.是否岗前考试 = 是否岗前考试;
	}

	@Column(name = "驾驶证号", length = 50)
	public String get驾驶证号() {
		return this.驾驶证号;
	}

	public void set驾驶证号(String 驾驶证号) {
		this.驾驶证号 = 驾驶证号;
	}

	@Column(name = "分公司归属", length = 100)
	public String get分公司归属() {
		return this.分公司归属;
	}

	public void set分公司归属(String 分公司归属) {
		this.分公司归属 = 分公司归属;
	}

	@Column(name = "交接班地点", length = 100)
	public String get交接班地点() {
		return this.交接班地点;
	}

	public void set交接班地点(String 交接班地点) {
		this.交接班地点 = 交接班地点;
	}

	@Column(name = "交接班时间", length = 100)
	public String get交接班时间() {
		return this.交接班时间;
	}

	public void set交接班时间(String 交接班时间) {
		this.交接班时间 = 交接班时间;
	}

	@Column(name = "加油地点", length = 100)
	public String get加油地点() {
		return this.加油地点;
	}

	public void set加油地点(String 加油地点) {
		this.加油地点 = 加油地点;
	}

	@Column(name = "营运场站", length = 100)
	public String get营运场站() {
		return this.营运场站;
	}

	public void set营运场站(String 营运场站) {
		this.营运场站 = 营运场站;
	}

	@Column(name = "办领证照经手人", length = 50)
	public String get办领证照经手人() {
		return this.办领证照经手人;
	}

	public void set办领证照经手人(String 办领证照经手人) {
		this.办领证照经手人 = 办领证照经手人;
	}

	@Column(name = "办领证照时间", length = 0)
	public Timestamp get办领证照时间() {
		return this.办领证照时间;
	}

	public void set办领证照时间(Timestamp 办领证照时间) {
		this.办领证照时间 = 办领证照时间;
	}

	@Column(name = "人车照编号", length = 50)
	public String get人车照编号() {
		return this.人车照编号;
	}

	public void set人车照编号(String 人车照编号) {
		this.人车照编号 = 人车照编号;
	}

	@Column(name = "登记时间", length = 0)
	public Timestamp get登记时间() {
		return this.登记时间;
	}

	public void set登记时间(Timestamp 登记时间) {
		this.登记时间 = 登记时间;
	}

	@Column(name = "申请登记证照经手人", length = 50)
	public String get申请登记证照经手人() {
		return this.申请登记证照经手人;
	}

	public void set申请登记证照经手人(String 申请登记证照经手人) {
		this.申请登记证照经手人 = 申请登记证照经手人;
	}

	@Column(name = "申请登记证照时间", length = 0)
	public Timestamp get申请登记证照时间() {
		return this.申请登记证照时间;
	}

	public void set申请登记证照时间(Timestamp 申请登记证照时间) {
		this.申请登记证照时间 = 申请登记证照时间;
	}

	@Column(name = "申请注销证照经手人", length = 50)
	public String get申请注销证照经手人() {
		return this.申请注销证照经手人;
	}

	public void set申请注销证照经手人(String 申请注销证照经手人) {
		this.申请注销证照经手人 = 申请注销证照经手人;
	}

	@Column(name = "申请注销证照时间", length = 0)
	public Timestamp get申请注销证照时间() {
		return this.申请注销证照时间;
	}

	public void set申请注销证照时间(Timestamp 申请注销证照时间) {
		this.申请注销证照时间 = 申请注销证照时间;
	}

	@Column(name = "办理注销证照经手人", length = 50)
	public String get办理注销证照经手人() {
		return this.办理注销证照经手人;
	}

	public void set办理注销证照经手人(String 办理注销证照经手人) {
		this.办理注销证照经手人 = 办理注销证照经手人;
	}

	@Column(name = "办理注销证照时间", length = 0)
	public Timestamp get办理注销证照时间() {
		return this.办理注销证照时间;
	}

	public void set办理注销证照时间(Timestamp 办理注销证照时间) {
		this.办理注销证照时间 = 办理注销证照时间;
	}

	@Column(name = "人车照片")
	public String get人车照片() {
		return this.人车照片;
	}

	public void set人车照片(String 人车照片) {
		this.人车照片 = 人车照片;
	}

	@Column(name = "民族", length = 50)
	public String get民族() {
		return this.民族;
	}

	public void set民族(String 民族) {
		this.民族 = 民族;
	}

	@Column(name = "计财部确认人", length = 50)
	public String get计财部确认人() {
		return this.计财部确认人;
	}

	public void set计财部确认人(String 计财部确认人) {
		this.计财部确认人 = 计财部确认人;
	}

	@Column(name = "计财部确认时间", length = 0)
	public Timestamp get计财部确认时间() {
		return this.计财部确认时间;
	}

	public void set计财部确认时间(Timestamp 计财部确认时间) {
		this.计财部确认时间 = 计财部确认时间;
	}

	@Column(name = "运营管理部确认人", length = 50)
	public String get运营管理部确认人() {
		return this.运营管理部确认人;
	}

	public void set运营管理部确认人(String 运营管理部确认人) {
		this.运营管理部确认人 = 运营管理部确认人;
	}

	@Column(name = "运营管理部确认时间", length = 0)
	public Timestamp get运营管理部确认时间() {
		return this.运营管理部确认时间;
	}

	public void set运营管理部确认时间(Timestamp 运营管理部确认时间) {
		this.运营管理部确认时间 = 运营管理部确认时间;
	}

	@Column(name = "综合办公室确认人", length = 50)
	public String get综合办公室确认人() {
		return this.综合办公室确认人;
	}

	public void set综合办公室确认人(String 综合办公室确认人) {
		this.综合办公室确认人 = 综合办公室确认人;
	}

	@Column(name = "综合办公室确认时间", length = 0)
	public Timestamp get综合办公室确认时间() {
		return this.综合办公室确认时间;
	}

	public void set综合办公室确认时间(Timestamp 综合办公室确认时间) {
		this.综合办公室确认时间 = 综合办公室确认时间;
	}

	@Column(name = "服务保证金", precision = 18)
	public Double get服务保证金() {
		return this.服务保证金;
	}

	public void set服务保证金(Double 服务保证金) {
		this.服务保证金 = 服务保证金;
	}

	@Column(name = "事故记录", length = 50)
	public String get事故记录() {
		return this.事故记录;
	}

	public void set事故记录(String 事故记录) {
		this.事故记录 = 事故记录;
	}

	@Column(name = "考核人确认", length = 50)
	public String get考核人确认() {
		return this.考核人确认;
	}

	public void set考核人确认(String 考核人确认) {
		this.考核人确认 = 考核人确认;
	}

	@Column(name = "考核人确认时间", length = 0)
	public Timestamp get考核人确认时间() {
		return this.考核人确认时间;
	}

	public void set考核人确认时间(Timestamp 考核人确认时间) {
		this.考核人确认时间 = 考核人确认时间;
	}

	@Column(name = "运营管理经理确认", length = 50)
	public String get运营管理经理确认() {
		return this.运营管理经理确认;
	}

	public void set运营管理经理确认(String 运营管理经理确认) {
		this.运营管理经理确认 = 运营管理经理确认;
	}

	@Column(name = "运营管理经理确认时间", length = 0)
	public Timestamp get运营管理经理确认时间() {
		return this.运营管理经理确认时间;
	}

	public void set运营管理经理确认时间(Timestamp 运营管理经理确认时间) {
		this.运营管理经理确认时间 = 运营管理经理确认时间;
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

	@Column(name = "银行卡类别", length = 50)
	public String get银行卡类别() {
		return this.银行卡类别;
	}

	public void set银行卡类别(String 银行卡类别) {
		this.银行卡类别 = 银行卡类别;
	}

	@Column(name = "银行卡号", length = 50)
	public String get银行卡号() {
		return this.银行卡号;
	}

	public void set银行卡号(String 银行卡号) {
		this.银行卡号 = 银行卡号;
	}

}