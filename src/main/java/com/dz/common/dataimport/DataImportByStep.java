package com.dz.common.dataimport;

import java.io.BufferedInputStream;
import java.io.File;
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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;

import com.dz.common.other.TimeComm;
import com.dz.module.contract.ContractService;
import com.dz.module.driver.Driver;
import com.dz.module.driver.DriverDao;
import com.dz.module.vehicle.Vehicle;
import com.dz.module.vehicle.VehicleDao;

public class DataImportByStep {

	private static DriverDao driverDao;
	private static VehicleDao vehicleDao;
	private static ContractService contractService;
	private static ApplicationContext ctx;
	
	static{
		
	}
	
	public static void main(String[] args) {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		driverDao = ctx.getBean(DriverDao.class);
		vehicleDao = ctx.getBean(VehicleDao.class);
		contractService = ctx.getBean(ContractService.class);
		
		try {
			importVehicle("C:\\Users\\Xiaoyao\\Desktop\\项目\\电子违章数据导出\\RawVehicle.xml","C:\\Users\\Xiaoyao\\Desktop\\项目\\电子违章数据导出\\在籍车辆.xls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//RawDriver.xml 64位承包人.xlsx RawDriver.xlsm
	/**
	 * 只导入人的信息，不上车
	 * @param inputXml
	 * @param inputXls
	 * @throws IOException
	 */
	public static void importDriver(String inputXml,String inputXls) throws IOException{
		InputStream inputXML = new BufferedInputStream(FileUtils.openInputStream(new File(inputXml)));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			InputStream inputXLS = new BufferedInputStream(FileUtils.openInputStream(new File(inputXls)));
			RawDriver driver = new RawDriver();
		    List<RawDriver> drivers = new ArrayList<RawDriver>();
		    Map<String, Object> beans = new HashMap<String, Object>();
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
	
	//RawVehicle.xml vehicle.xlsm
	//只添加基本信息
	public static void importVehicle(String inputXml,String inputXls) throws IOException{
		InputStream inputXML = new BufferedInputStream(FileUtils.openInputStream(new File(inputXml)));
	    XLSReader mainReader;
		try {
			mainReader = ReaderBuilder.buildFromXML( inputXML );
			InputStream inputXLS = new BufferedInputStream(FileUtils.openInputStream(new File(inputXls)));
			RawVehicle vehicle = new RawVehicle();
		    List<RawVehicle> vehicles = new ArrayList<RawVehicle>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("vehicles", vehicles);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	vehicles = (List<RawVehicle>) beans.get("vehicles");
		    }
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    for(RawVehicle rv:vehicles){
		    	Vehicle v = new Vehicle();
		    	
		    	v.setLicenseNum(rv.getLicenseNum());
		    	v.setCarframeNum(rv.getCarframeNum());
		    	v.setEngineNum(rv.getEngineNum());
		    	v.setMoneyCountor(rv.getMoneyCountor());
		    	if(rv.getCarMode()==null){
		    		rv.setCarMode("FV7160CG");
		    	}
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
		    	else{
		    		v.setInDate(new Date());
		    	}
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

}
