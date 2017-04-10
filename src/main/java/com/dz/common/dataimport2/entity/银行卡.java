package com.dz.common.dataimport2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 银行卡 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "银行卡", catalog = "dzimport")
public class 银行卡 implements java.io.Serializable {

	// Fields

	private String 卡号;
	private String 部门;
	private String 车号;
	private String 姓名;

	// Constructors

	/** default constructor */
	public 银行卡() {
	}

	/** minimal constructor */
	public 银行卡(String 卡号) {
		this.卡号 = 卡号;
	}

	/** full constructor */
	public 银行卡(String 卡号, String 部门, String 车号, String 姓名) {
		this.卡号 = 卡号;
		this.部门 = 部门;
		this.车号 = 车号;
		this.姓名 = 姓名;
	}

	// Property accessors
	@Id
	@Column(name = "卡号", unique = true, nullable = false, length = 30)
	public String get卡号() {
		return this.卡号;
	}

	public void set卡号(String 卡号) {
		this.卡号 = 卡号;
	}

	@Column(name = "部门", length = 30)
	public String get部门() {
		return this.部门;
	}

	public void set部门(String 部门) {
		this.部门 = 部门;
	}

	@Column(name = "车号", length = 30)
	public String get车号() {
		return this.车号;
	}

	public void set车号(String 车号) {
		this.车号 = 车号;
	}

	@Column(name = "姓名", length = 30)
	public String get姓名() {
		return this.姓名;
	}

	public void set姓名(String 姓名) {
		this.姓名 = 姓名;
	}

	@Override
	public String toString() {
		return "银行卡 [卡号=" + 卡号 + ", 部门=" + 部门 + ", 车号=" + 车号 + ", 姓名=" + 姓名
				+ "]";
	}

}