package com.dz.common.test;

import java.math.BigDecimal;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dz.common.global.BaseAction;
import com.dz.module.search.*;

@Controller
@Scope("prototype")
public class JsonTest extends BaseAction {
	private String jsonStr;
	private List<AccountItem> list;
	private List<Dv> dvs;
	@Autowired
	private DvDao dvDao;

	public void setDvDao(DvDao dvDao) {
		this.dvDao = dvDao;
	}


	private static DynaBean bean;
	
	static{
		AccountItem ai = new AccountItem();
		ai.setCardNum("cardNum");
		ai.setName("name");
		ai.setLicenseNum("licenseNum");
		ai.setMoney(BigDecimal.valueOf(Double.parseDouble("3.14")));
		bean = new WrapDynaBean(ai);
	}
	
	public List<AccountItem> getList() {
		return list;
	}

	public void setList(List<AccountItem> list) {
		this.list = list;
	}

	public String execute(){
		JSONArray json = JSONArray.fromObject(jsonStr);
		
		@SuppressWarnings("unchecked")
		List<AccountItem> ats=
		(List<AccountItem>) CollectionUtils.collect(json, new Transformer(){
			@Override
			public Object transform(Object obj) {
				JSONObject jso = (JSONObject)obj;
				AccountItem ai = new AccountItem();
				ai.setCardNum(jso.getString("cardNum"));
				ai.setName(jso.getString("name"));
				ai.setLicenseNum(jso.getString("licenseNum"));
				ai.setMoney(BigDecimal.valueOf(Double.parseDouble(jso.getString("Money"))));
				return ai;
			}
		});
		
		list = ats;
		
		dvs = dvDao.selectAll();
		System.out.println(dvs);
		return SUCCESS;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	
	public DynaBean getBean() {
		return bean;
	}

	public List<Dv> getDvs() {
		return dvs;
	}

	public void setDvs(List<Dv> dvs) {
		this.dvs = dvs;
	}
}
