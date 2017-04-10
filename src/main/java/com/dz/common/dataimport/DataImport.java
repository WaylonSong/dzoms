package com.dz.common.dataimport;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;

import com.dz.common.other.TimeComm;
import com.dz.module.contract.BankCard;
import com.dz.module.contract.BankCardDao;
import com.dz.module.contract.Contract;
import com.dz.module.contract.ContractService;
import com.dz.module.driver.Driver;
import com.dz.module.driver.DriverDao;
import com.dz.module.driver.Driverincar;
import com.dz.module.vehicle.Vehicle;
import com.dz.module.vehicle.VehicleDao;

public class DataImport {
	private static DriverDao driverDao;
	private static VehicleDao vehicleDao;
	private static ContractService contractService;
	private static ApplicationContext ctx;
	
	static{
		
	}
	
	public static void main(String[] args){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		driverDao = ctx.getBean(DriverDao.class);
		vehicleDao = ctx.getBean(VehicleDao.class);
		contractService = ctx.getBean(ContractService.class);
		
		/**第一步 导入驾驶员信息*/
		//importDriver();
		/**第二步 导入 车辆信息*/
		//importVehicle();
		/**第三步 生成待签合同*/
		importContract();
		/**第四步 驾驶员自动上车*/
		//bindDriver();
		/**银行卡信息导入*/
		//importBankCard();
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void importDriver(){
		InputStream inputXML = new BufferedInputStream(DataImport.class.getResourceAsStream("RawDriver.xml"));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			//InputStream inputXLS = new BufferedInputStream(DataImport.class.getResourceAsStream("RawDriver.xlsm"));
			InputStream inputXLS = new BufferedInputStream(DataImport.class.getResourceAsStream("64位承包人.xlsx"));
			RawDriver driver = new RawDriver();
		    List<RawDriver> drivers = new ArrayList<RawDriver>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("driver", driver);
		    beans.put("drivers", drivers);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	drivers = (List<RawDriver>) beans.get("drivers");
		    }
		    
		    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
		    
		    for(RawDriver rd:drivers){
		    	Driver d = new Driver();
		    	d.setIdNum(rd.getIdNum());
		    	d.setName(rd.getDriverName());
		    	d.setDriverClass(rd.getDriverClass());
		    	if(!StringUtils.isEmpty(rd.getShiftTime()))
		    		d.setShiftTime(dateFormat2.parse(rd.getShiftTime()));
		    	d.setPhoneNum1(rd.getPhoneNum1());
		    	d.setRestTime(rd.getRestTime());
		    	d.setOperatingStation(rd.getOperatingStation());
		    	d.setEmployeeNum(rd.getEmployeeNum());
		    	
		    	if(StringUtils.equals(rd.getDriverName(), rd.getOwnerName())){
		    		d.setApplyMatter("承包新车");
		    	}else{
		    		d.setApplyMatter("驾驶员");
		    	}
		    	
		    	d.setApplyTime(new Date());
		    	
		    	int sex_p = d.getIdNum().charAt(16) - '0';
		    	d.setSex(sex_p%2==0?false:true);
		    	
		    	Date birthday = new Date();
		    	birthday.setYear(Integer.parseInt(d.getIdNum().substring(6, 10))-1900);
		    	birthday.setMonth(Integer.parseInt(d.getIdNum().substring(10, 12))-1);
		    	birthday.setDate(Integer.parseInt(d.getIdNum().substring(12, 14)));
		    	String months = TimeComm.subDate(birthday, new Date());
		    	
		    	Pattern pattern = Pattern.compile("^[^0-9]*([0-9]+)个月,([0-9]+).*");
		    	Matcher match = pattern.matcher(months);
		    	
		    	if(match.matches()){
		    		//System.out.println(match.group(1));
		    		//System.out.println(birthday);
		    		int age = (Integer.parseInt(match.group(1)) +6)/12 ;
		    		//System.out.println(age);
		    		if(age<0 || age>70){
		    			System.out.println(rd);
		    		}else
		    		
		    		d.setAge((short) age);
		    	}
		    	
		    	d.setRegistrant(1);
		    	d.setIsInCar(false);
		    	
		    	if(d.getIdNum().startsWith("2301")){
		    		d.setAddress("黑龙江哈尔滨");
		    	}else if(d.getIdNum().startsWith("23")){
		    		d.setAddress("黑龙江");
		    	}else{
		    		d.setAddress("外省");
		    	}
		    	
		    	try{
		    		driverDao.driverAdd(d, null);
		    	}catch(Exception e){
		    		System.out.println(rd);
		    		e.printStackTrace();
		    	}
		    }

		} catch (IOException | SAXException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void importVehicle(){
		InputStream inputXML = new BufferedInputStream(DataImport.class.getResourceAsStream("RawVehicle.xml"));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			InputStream inputXLS = new BufferedInputStream(DataImport.class.getResourceAsStream("vehicle.xlsm"));
			RawVehicle vehicle = new RawVehicle();
		    List<RawVehicle> vehicles = new ArrayList<RawVehicle>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("vehicle", vehicle);
		    beans.put("vehicles", vehicles);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	vehicles = (List<RawVehicle>) beans.get("vehicles");
		    }
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    for(RawVehicle rv:vehicles){
		    	Vehicle v = new Vehicle();
		    	
		    	v.setLicenseNum(rv.getLicenseNum());
		    	v.setDriverId(rv.getDriverId());
		    	v.setCarframeNum(rv.getCarframeNum());
		    	v.setEngineNum(rv.getEngineNum());
		    	v.setMoneyCountor(rv.getMoneyCountor());
		    	v.setCarMode(rv.getCarMode());
		    	v.setCertifyNum(rv.getCertifyNum());
		    	
		    	switch(rv.getDept()){
		    	case "大众一公司":
		    		v.setDept("一部");
		    		break;
		    	case "大众二公司":
		    		v.setDept("二部");
		    		break;
		    	case "大众三公司":
		    		v.setDept("三部");
		    		break;
		    	}
		    	
		    	if(rv.getInDate()!=null)
		    		v.setInDate(dateFormat.parse(rv.getInDate()));
		    	v.setLicensePurseNum(rv.getLicensePurseNum());
		    	v.setIsNewLicense(StringUtils.equals(rv.getIsNewLicense(), "拍卖"));
		    	v.setOperateCard(rv.getOperateCard());
		    	if(!StringUtils.isEmpty(rv.getPd()))
		    		v.setPd(dateFormat.parse(rv.getPd()));
		    	if(!StringUtils.isEmpty(rv.getOperateCardTime()))
		    		v.setOperateCardTime(dateFormat.parse(rv.getOperateCardTime()));
//		    	if(!StringUtils.isEmpty(rv.getBusinessLicenseNum()))
//		    		v.setBusinessLicenseNum(rv.getBusinessLicenseNum());
//		    	if(!StringUtils.isEmpty(rv.getBusinessLicenseBeginDate()))
//		    		v.setBusinessLicenseBeginDate(dateFormat.parse(rv.getBusinessLicenseBeginDate()));
//		    	if(!StringUtils.isEmpty(rv.getBusinessLicenseEndDate()))
//		    		v.setBusinessLicenseEndDate(dateFormat.parse(rv.getBusinessLicenseEndDate()));
//		    
		    	 try{
		    		 v.setState(0);
		    		 vehicleDao.addVehicle(v);
			    	}catch(Exception e){
			    		System.out.println(rv);
			    		e.printStackTrace();
			    	}
		    }

		} catch (IOException | SAXException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void importContract(){
		InputStream inputXML = new BufferedInputStream(DataImport.class.getResourceAsStream("RawContract2.xml"));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			InputStream inputXLS = new BufferedInputStream(DataImport.class.getResourceAsStream("3条记录.xlsx"/*"变更后后同657（含废业车）.xlsm"*/));
			RawContract contract = new RawContract();
		    List<RawContract> contracts = new ArrayList<RawContract>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("contract", contract);
		    beans.put("contracts", contracts);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	contracts = (List<RawContract>) beans.get("contracts");
		    }
		    
		    System.out.println(contracts.size());
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    
		    HashMap<String,RawContract> rawMap = new  HashMap<String,RawContract>();
		    for(RawContract rc:contracts){
		    	Date endDate = null;
		    	if(!StringUtils.isEmpty(rc.getContractEndDate()))
		    		endDate = dateFormat.parse(rc.getContractEndDate());
		    	
		    	if(endDate!=null&&endDate.before(new Date()) ){
		    		System.out.println("合同已终止 :"+rc);
		    		//continue;
		    	}
		    	rawMap.put(rc.getCarNum(), rc);
		    }
		    
		    System.out.println("待新增合同数："+rawMap.size());
		    
		    for(RawContract rc:rawMap.values()){
		    	Contract c = new Contract();
		    	
		    	//if(!StringUtils.isEmpty(rc.getContractBeginDate()))
		    	//	c.setContractBeginDate(dateFormat.parse(rc.getContractBeginDate()));
		    	if(!StringUtils.isEmpty(rc.getContractEndDate()))
		    		c.setContractEndDate(dateFormat.parse(rc.getContractEndDate()));
		    	
		    	Vehicle vehicle = new Vehicle();
		    	vehicle.setLicenseNum(rc.getCarNum());
		    	vehicle = vehicleDao.selectByLicense(vehicle);

		    	if(vehicle!=null){
		    		c.setCarframeNum(vehicle.getCarframeNum());
		    	}else{
		    		System.out.println("车辆不存在："+rc);
		    		continue;
		    	}
		    	
		    	if(driverDao.selectById(rc.getIdNum())!=null){
		    		c.setIdNum(rc.getIdNum());
		    	}else{
		    		System.out.println("承包人不存在："+rc);
		    		continue;
		    	}
		    	
		    	if(StringUtils.isEmpty(rc.getPenalty())){
		    		c.setPenalty(0.0);
		    	}else
		    		c.setPenalty( Double.parseDouble(rc.getPenalty()));
		    	c.setContractId(rc.getContractId());
		    	c.setCarNum(rc.getCarNum());
		    	c.setRemark(rc.getRemark());
		    	
		    	
		    	c.setBusinessForm(rc.getBusinessForm());
		    	
		    	if(!StringUtils.isEmpty(rc.getFeeAlter()))
		    		c.setFeeAlter( Double.parseDouble(rc.getFeeAlter()));
		    	c.setBranchFirm(rc.getBranchFirm());
		    	
		    	switch(rc.getBranchFirm()){
		    	case "大众一公司":
		    		c.setBranchFirm("一部");
		    		break;
		    	case "大众二公司":
		    		c.setBranchFirm("二部");
		    		break;
		    	case "大众三公司":
		    		c.setBranchFirm("三部");
		    		break;
		    	}
		    	
		    	if(!StringUtils.isEmpty(rc.getAscription()))
		    		c.setAscription(rc.getAscription().equals("个人"));
		    	if(!StringUtils.isEmpty(rc.getRentFirst()))
		    		c.setRentFirst( Double.parseDouble(rc.getRentFirst()));
		    	if(!StringUtils.isEmpty(rc.getDeposit()))
		    		c.setDeposit( Double.parseDouble(rc.getDeposit()));
		    	if(!StringUtils.isEmpty(rc.getRent()))
		    		c.setRent( Double.parseDouble(rc.getRent()));
		    	
		    	c.setGeneByImport(true);
		    	c.setState((short) 3);
		    	
		    	 try{
		    		 contractService.contractWrite(c);
			    	}catch(Exception e){
			    		System.out.println(rc);
			    		e.printStackTrace();
			    	}
		    }

		} catch (IOException | SAXException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	@Deprecated
	public static void geneContract(){
		InputStream inputXML = new BufferedInputStream(DataImport.class.getResourceAsStream("RawDriver.xml"));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			InputStream inputXLS = new BufferedInputStream(DataImport.class.getResourceAsStream("RawDriver.xlsm"));
		    RawDriver driver = new RawDriver();
		    List<RawDriver> drivers = new ArrayList<RawDriver>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("driver", driver);
		    beans.put("drivers", drivers);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	drivers = (List<RawDriver>) beans.get("drivers");
		    }
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
		    
		    for(RawDriver rd:drivers){	    	
		    	Driver d = driverDao.selectById(rd.getIdNum());
		    	
		    	if(!StringUtils.equals(rd.getDriverName(), rd.getOwnerName())){
		    		continue;
		    	}
		    	
		    	if(d==null){
		    		System.out.println("驾驶人不存在："+rd);
		    		continue;
		    	}
		    	
		    	d.setRestTime(rd.getRestTime());
		    	
		    	Vehicle v = new Vehicle();
		    	v.setLicenseNum(rd.getLicenseNum());
		    	v = vehicleDao.selectByLicense(v);
		    	if(v==null){
		    		System.out.println("车辆不存在："+rd);
		    		continue;
		    	}
		    	
		    	Contract contract = new Contract();
		    	contract.setIdNum(rd.getIdNum());
		    	contract.setCarframeNum(v.getCarframeNum());
		    	contract.setCarNum(v.getLicenseNum());
		    	contract.setBranchFirm(v.getDept());
		    	
		    	
		    	contract.setState((short) 3);
			
		    	try{
		    		 contractService.contractWrite(contract);
		    	}catch(Exception e){
		    		System.out.println(rd);
		    		e.printStackTrace();
		    	}
		    }

		} catch (IOException | SAXException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Deprecated
	public static void geneContractFromDatabase(){		
		List<Vehicle> vl = vehicleDao.selectAll();
		for(Vehicle vehicle:vl){
			Driver d = driverDao.selectById(vehicle.getDriverId());
			if(d==null){
	    		System.out.println("承租人不存在："+vehicle.getCarframeNum()+" "+vehicle.getLicenseNum()+" "+vehicle.getDriverId());
	    		continue;
	    	}
			
			Contract contract = new Contract();
	    	contract.setIdNum(d.getIdNum());
	    	contract.setCarframeNum(vehicle.getCarframeNum());
	    	contract.setCarNum(vehicle.getLicenseNum());
	    	contract.setBranchFirm(vehicle.getDept());
	    	
	    	
	    	contract.setState((short) 3);
		
	    	try{
	    		 contractService.contractWrite(contract);
	    	}catch(Exception e){
	    		System.out.println(vehicle);
	    		e.printStackTrace();
	    	}
			
		}
		
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	public static void bindDriver(){
		InputStream inputXML = new BufferedInputStream(DataImport.class.getResourceAsStream("RawDriver.xml"));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			InputStream inputXLS = new BufferedInputStream(DataImport.class.getResourceAsStream("RawDriver.xlsm"));
		    RawDriver driver = new RawDriver();
		    List<RawDriver> drivers = new ArrayList<RawDriver>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("driver", driver);
		    beans.put("drivers", drivers);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	drivers = (List<RawDriver>) beans.get("drivers");
		    }
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
		    
		    for(RawDriver rd:drivers){	    	
		    	if(StringUtils.equalsIgnoreCase(rd.getIsInCar(), "否")){
		    		continue;
		    	}
		    	
		    	
		    	Driver d = driverDao.selectById(rd.getIdNum());
		    	
		    	if(d==null){
		    		System.out.println("驾驶人不存在："+rd);
		    		continue;
		    	}
		    	
		    	d.setRestTime(rd.getRestTime());
		    	
		    	Vehicle v = new Vehicle();
		    	v.setLicenseNum(rd.getLicenseNum());
		    	v = vehicleDao.selectByLicense(v);
		    	if(v==null){
		    		System.out.println("车辆不存在："+rd);
		    		continue;
		    	}
		    	
				d.setCarframeNum(v.getCarframeNum());
				
				d.setDriverClass(rd.getDriverClass());
				d.setBusinessApplyTime(new Date());
				d.setBusinessApplyRegistrant(1);
				d.setBusinessApplyRegistTime(new Date());
				d.setDept(v.getDept());
				
				d.setBusinessReciveTime(new Date());
				d.setBusinessReciveRegistrant(1);
				d.setBusinessReciveRegistTime(new Date());
				
				d.setIsInCar(true);
				
		    	try{
		    		driverDao.driverUpdate(d, null);
		    		if(d.getDriverClass().equals("主驾")){
		    			v.setFirstDriver(d.getIdNum());
		    		}else if(d.getDriverClass().equals("副驾")){
		    			v.setSecondDriver(d.getIdNum());
		    		}
		    		vehicleDao.vehicleUpdate(v);
		    		driverDao.addDriverInCarRecord(new Driverincar(d.getCarframeNum(),d.getIdNum(),"证照申请",d.getBusinessApplyTime()));
		    		driverDao.addDriverInCarRecord(new Driverincar(d.getCarframeNum(),d.getIdNum(),"上车",d.getBusinessApplyTime()));
		    	}catch(Exception e){
		    		System.out.println(rd);
		    		e.printStackTrace();
		    	}
		    }

		} catch (IOException | SAXException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void importBankCard(){
		BankCardDao bankCardDao = ctx.getBean(BankCardDao.class);
		
		InputStream inputXML = new BufferedInputStream(DataImport.class.getResourceAsStream("BankCard.xml"));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			InputStream inputXLS = new BufferedInputStream(DataImport.class.getResourceAsStream("BankCard.xls"));
		    List<BankCard> cards = new ArrayList<BankCard>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("cards", cards);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	cards = (List<BankCard>) beans.get("cards");
		    }
		   
		    for(BankCard card:cards){
		    	
		    	if(card.getCardNumber().startsWith("a1")){
		    		continue;
		    	}
		    	
		    	try{
		    		Driver driver = driverDao.selectByName(card.getIdNumber());
		    		if(driver==null){
		    			System.out.println("承包人不存在："+card);
		    			continue;
		    		}
		    		card.setIdNumber(driver.getIdNum());
		    	}catch(org.hibernate.NonUniqueResultException ex){
		    		System.out.println("名字重复："+card);
		    		continue;
		    	}
		    	
		    	card.setCardNumber(StringUtils.right(card.getCardNumber(), card.getCardNumber().length()-1));
		    	card.setCardClass("哈尔滨银行");
		    	
		    	card.setOperator(1);
		    	card.setOpeTime(new Date());
		    	
		    	try{
		    		bankCardDao.bankCardAdd(card);
		    	}catch(Exception e){
		    		System.out.println(card);
		    		e.printStackTrace();
		    	}
		    }

		} catch (IOException | SAXException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
