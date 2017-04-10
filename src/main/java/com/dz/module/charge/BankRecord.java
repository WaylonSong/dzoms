package com.dz.module.charge;

import com.dz.module.contract.BankCard;
import com.dz.module.contract.BankCardDao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author doggy
 *         Created on 15-11-17.
 */
@Entity 
public class BankRecord {
    private String licenseNum,carframeNum;
    private String driverName,idNum;
    private Map<String,BankCard> bankCards;
    private BigDecimal money;
    private Double derserve,left;
    private Date inTime;

    public BankRecord() {
		super();
	}
    
    private static BankCardDao bankCardDao;
//    private static ChargeService chargeService;
    
    private static void initStatic(){
    	ServletContext sc = ServletActionContext.getServletContext();
    	ApplicationContext appc = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
    	bankCardDao = appc.getBean(BankCardDao.class);
//    	chargeService = appc.getBean(ChargeService.class);
    }
    
    public BankRecord(String idNum,String driverName,String carframeNum,String licenseNum,Double derserve,Double left) {		
		this.idNum = idNum;
		this.driverName = driverName;
		this.carframeNum = carframeNum;
		this.licenseNum = licenseNum;
		this.derserve = derserve==null?0:derserve;
		this.left = left==null?0:left;
		
//		System.out.println(derserve+"|"+left);
		
		init();
	}
    
    public void init(){
    	if(bankCardDao==null){
    		initStatic();
    	}
    	
    	money = BigDecimal.valueOf(derserve).add(BigDecimal.valueOf(left));
		if(money.compareTo(BigDecimal.ZERO)>0){
			money = BigDecimal.ZERO;
		}else{
			money = money.abs();
		}
		
    	BankCard bc = bankCardDao.getBankCardForPayByDriverIdWithoutCloseSession(idNum,carframeNum);
		
		bankCards = new HashMap<>();
		if(bc!=null)
			bankCards.put(bc.getCardClass().contains("哈尔滨")?"hrb":"other", bc);
    }
    

	public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Map<String, BankCard> getBankCards() {
        return bankCards;
    }

    @Override
    public String toString() {
        return "BankRecord{" +
                "licenseNum='" + licenseNum + '\'' +
                ", driverName='" + driverName + '\'' +
                ", bankCards=" + bankCards +
                ", money=" + money +
                '}';
    }

    public void setBankCards(Map<String, BankCard> bankCards) {
        this.bankCards = bankCards;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }


	public Double getDerserve() {
		return derserve;
	}


	public void setDerserve(Double derserve) {
		this.derserve = derserve;
	}


	public Double getLeft() {
		return left;
	}


	public void setLeft(Double left) {
		this.left = left;
	}


	public String getCarframeNum() {
		return carframeNum;
	}


	public void setCarframeNum(String carframeNum) {
		this.carframeNum = carframeNum;
	}


	public String getIdNum() {
		return idNum;
	}


	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
    
    
}
