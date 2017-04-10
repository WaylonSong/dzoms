package com.dz.common.dataimport2.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RawVehicle", catalog = "dzimport")
public class RawVehicle implements java.io.Serializable {
	// Fields

		private Integer 编号;
		private String 档案号;
		private String 车号;
		private String 发动机号;
		private String 车架号;
		private Timestamp 购车时间;
		private String 发票号;
		private String 合格证号;
		private String 拍卖登记号;
		private Timestamp 营运证发放日期;
		private String 营运证号;
		private String 计价器号;
		private String 分公司归属;
		private String 车辆制造企业名称;
		private String 车辆型号;
		private String 承租人;
		private String 承租人身份证号;
		private String 牌照类别;
		private Timestamp 车辆制造日期;


		// Constructors

		/** default constructor */
		public RawVehicle() {
		}

		/** minimal constructor */
		public RawVehicle(Integer 编号) {
			this.编号 = 编号;
		}

		/** full constructor */
		public RawVehicle(Integer 编号, String 档案号, String 车号, String 发动机号,
				String 车架号, Timestamp 购车时间, String 发票号, String 合格证号, String 拍卖登记号,
				Timestamp 营运证发放日期, String 营运证号, String 计价器号, String 分公司归属,
				String 车辆制造企业名称, String 车辆型号, String 承租人,
				String 承租人身份证号, String 牌照类别) {
			this.编号 = 编号;
			this.档案号 = 档案号;
			this.车号 = 车号;
			this.发动机号 = 发动机号;
			this.车架号 = 车架号;
			this.购车时间 = 购车时间;
			this.发票号 = 发票号;
			this.合格证号 = 合格证号;
			this.拍卖登记号 = 拍卖登记号;
			this.营运证发放日期 = 营运证发放日期;
			this.营运证号 = 营运证号;
			this.计价器号 = 计价器号;
			this.分公司归属 = 分公司归属;
			this.车辆制造企业名称 = 车辆制造企业名称;
			this.车辆型号 = 车辆型号;
			this.承租人 = 承租人;
			this.承租人身份证号 = 承租人身份证号;
			this.牌照类别 = 牌照类别;
		}

		// Property accessors
		@Id
		@Column(name = "编号", nullable = false)
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

		@Column(name = "拍卖登记号", length = 50)
		public String get拍卖登记号() {
			return this.拍卖登记号;
		}

		public void set拍卖登记号(String 拍卖登记号) {
			this.拍卖登记号 = 拍卖登记号;
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

		@Column(name = "牌照类别", length = 50)
		public String get牌照类别() {
			return this.牌照类别;
		}

		public void set牌照类别(String 牌照类别) {
			this.牌照类别 = 牌照类别;
		}
		
		@Column(name = "车辆制造日期", length = 0)
		public Timestamp get车辆制造日期() {
			return this.车辆制造日期;
		}

		public void set车辆制造日期(Timestamp 车辆制造日期) {
			this.车辆制造日期 = 车辆制造日期;
		}

		@Override
		public String toString() {
			return "RawVehicle [编号=" + 编号 + ", 档案号=" + 档案号 + ", 车号=" + 车号
					+ ", 发动机号=" + 发动机号 + ", 车架号=" + 车架号 + ", 购车时间=" + 购车时间
					+ ", 发票号=" + 发票号 + ", 合格证号=" + 合格证号 + ", 拍卖登记号=" + 拍卖登记号
					+ ", 营运证发放日期=" + 营运证发放日期 + ", 营运证号=" + 营运证号 + ", 计价器号="
					+ 计价器号 + ", 分公司归属=" + 分公司归属 + ", 车辆制造企业名称=" + 车辆制造企业名称
					+ ", 车辆型号=" + 车辆型号 + ", 承租人=" + 承租人 + ", 承租人身份证号="
					+ 承租人身份证号 + ", 牌照类别=" + 牌照类别 + ", 车辆制造日期=" + 车辆制造日期 + "]";
		}
		
		
}
