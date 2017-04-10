package com.dz.module.vehicle.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.Hibernate;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;

import com.dz.common.dataimport.DataImport;
import com.dz.common.dataimport.RawDriver;
import com.dz.common.global.BaseAction;
import com.dz.common.other.FileUploadUtil;
import com.dz.common.other.ObjectAccess;
import com.dz.module.charge.ClearTime;
import com.dz.module.vehicle.Vehicle;

@Controller
@Scope(value="prototype")
public class ServiceImportAction extends BaseAction {
	private String input;
	
	public String uploadDailyData(){
		try {
			InputStream inputXML = FileUtils.openInputStream(new File(System.getProperty("com.dz.root")+"/vehicle/service/ServiceData.xml"));
			//InputStream inputXML = new BufferedInputStream(new FileInputStream(System.getProperty("com.dz.root")+"/vehicle/service/ServiceData.xml"));
		    XLSReader mainReader = ReaderBuilder.buildFromXML( inputXML );
			//InputStream inputXLS = new BufferedInputStream(DataImport.class.getResourceAsStream("RawDriver.xlsm"));
			InputStream inputXLS = new BufferedInputStream(FileUploadUtil.getFileStream(input));
			
			if(inputXLS==null){
				System.out.println("文件打开失败！！！");
			}
			
			if(inputXML==null){
				System.out.println("XML配置文件打开失败！！！");
			}
			
			RawDriver driver = new RawDriver();
		    List<ServiceImport> sidata = new ArrayList<ServiceImport>();
		    Map<String, Object> beans = new HashMap<String, Object>();
		    beans.put("serviceData", sidata);
		    XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
		    
		    if(readStatus.isStatusOK()){
		    	sidata = (List<ServiceImport>) beans.get("serviceData");
		    }
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    ServiceDetail detail = null;
		    for(ServiceImport rd:sidata){
		    	String licenseNum = rd.getLicenseNum();
		    	if(StringUtils.startsWith(licenseNum, "平均")||StringUtils.startsWith(licenseNum, "合计")){
		    		continue;
		    	}
		    	
		    	String svrDate[] = StringUtils.split(rd.getServiceTimeSpace(), '至');
		    	Date beginDate = dateFormat.parse(svrDate[0]);
		    	Date endDate = dateFormat.parse(svrDate[1]);
		    	
		    	Vehicle v = ObjectAccess.execute("from Vehicle where licenseNum='"+licenseNum+"'");
		    	if(v==null){
		    		ServiceError error = new ServiceError();
		    		error.setServiceBegin(beginDate);
		    		error.setServiceEnd(endDate);
		    		error.setMoney(BigDecimal.valueOf(Double.parseDouble(rd.getMoney())));
		    		error.setAllDistance(BigDecimal.valueOf(Double.parseDouble(rd.getAllDistance())));
		    		error.setEffectiveDistance(BigDecimal.valueOf(Double.parseDouble(rd.getEffectiveDistance())));
		    		error.setUselessDistance(BigDecimal.valueOf(Double.parseDouble(rd.getUselessDistance())));
		    		error.setTimes(Integer.parseInt(rd.getTimes()));
		    		error.setLicenseNum(licenseNum);
		    		
		    		ObjectAccess.saveOrUpdate(error);
		    	}else{
		    		detail = new ServiceDetail();
		    		detail.setServiceBegin(beginDate);
		    		detail.setServiceEnd(endDate);
		    		detail.setMoney(BigDecimal.valueOf(Double.parseDouble(rd.getMoney())));
		    		detail.setAllDistance(BigDecimal.valueOf(Double.parseDouble(rd.getAllDistance())));
		    		detail.setEffectiveDistance(BigDecimal.valueOf(Double.parseDouble(rd.getEffectiveDistance())));
		    		detail.setUselessDistance(BigDecimal.valueOf(Double.parseDouble(rd.getUselessDistance())));
		    		detail.setTimes(Integer.parseInt(rd.getTimes()));
		    		detail.setCarframeNum(v.getCarframeNum());
		    		detail.setDept(v.getDept());
		    		
		    		ObjectAccess.saveOrUpdate(detail);
		    	}
		    }//end of for each
		    
		    Date now = detail.getServiceBegin();
		    
		    String condition = String.format("carframeNum not in "
		    		+ "(select carframeNum from ServiceDetail"
		    		+ " where serviceBegin >= STR_TO_DATE('%tF 00:00','%%Y-%%m-%%d %%H:%%i') "
		    		+ " and serviceBegin < STR_TO_DATE('%tF 23:59','%%Y-%%m-%%d %%H:%%i') )", now,now);
		    List<Vehicle> spacelist = ObjectAccess.query(Vehicle.class, condition);
		    
		    String cond2 = String.format("delete from ServiceSpace "
		    		+ " where date = STR_TO_DATE('%tF','%%Y-%%m-%%d') "
		    		,now);
		    ObjectAccess.execute(cond2);
		    
		    for (Vehicle vehicle : spacelist) {
		    	ServiceSpace space = new ServiceSpace();
		    	space.setCarframeNum(vehicle.getCarframeNum());
		    	space.setDate(now);
		    	space.setDept(vehicle.getDept());
		    	ObjectAccess.saveOrUpdate(space);
			}
		    
		}catch (IOException | SAXException | InvalidFormatException | ParseException e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "请使用Excel打开，将其转为兼容模式后上传。");
		} catch(Exception e1){
			e1.printStackTrace();
			//request.setAttribute("errMsg", "请使用Excel打开，将其转为兼容模式后上传。");
		}
		return SUCCESS;
	}
	
	public String serviceClear(){
		ClearTime serviceClearTime = (ClearTime)ObjectAccess.getObject("com.dz.module.charge.ClearTime",5);
		Date beginDate = serviceClearTime.getCurrent();
	 	
	 	Calendar beginDateCalendar = Calendar.getInstance();
	 	beginDateCalendar.setTime(beginDate);
	 	beginDateCalendar.add(Calendar.DATE, 1);
	 	beginDate=beginDateCalendar.getTime();
	 	
	 	String condition = String.format("from ServiceDetail"
	    		+ " where serviceBegin >= STR_TO_DATE('%tF 00:00','%%Y-%%m-%%d %%H:%%i') "
	    		+ " and serviceBegin <= STR_TO_DATE('%tF 23:59','%%Y-%%m-%%d %%H:%%i') ", beginDate,beginDate);
	 	
	 	int affect = ObjectAccess.execute(
	 			"insert into ServiceDaily(date,dept,number,money,allDistance,effectiveDistance,uselessDistance,times) "
	 			+ " select DATE(serviceBegin),dept,cast(count(*) as integer),SUM(money),SUM(allDistance),SUM(effectiveDistance),SUM(uselessDistance),SUM(cast (times as big_decimal))"
	 			+ " "+condition+"GROUP BY dept");
	 	
	 	if(affect>=3){
	 		serviceClearTime.setCurrent(beginDate);
	 		ObjectAccess.saveOrUpdate(serviceClearTime);
	 		
	 		//检测是否为月末，生成月报表
	 		beginDateCalendar.add(Calendar.DATE, 1);
	 		if(beginDateCalendar.get(Calendar.DATE)==1){
	 			Date nextMonthBegin = beginDateCalendar.getTime();
	 			beginDateCalendar.add(Calendar.MONTH, -1);
	 			Date monthBegin = beginDateCalendar.getTime();
	 			
	 			ServiceMonthly monthly = new ServiceMonthly();
	 		String hql=String.format("INSERT INTO ServiceMonthly (  "
	 					+"	date,                      "
	 					+"	number,                    "
	 					+"	money,                       "
	 					+"	allDistance,                 "
	 					+"	effectiveDistance,           "
	 					+"	times                      "
	 					+") SELECT                       "
	 					+"	DATE(serviceBegin),          "
	 					+"	cast(count(*) as integer),                    "
	 					+"	SUM(money),                  "
	 					+"	SUM(allDistance),            "
	 					+"	SUM(effectiveDistance),      "
	 					+"	SUM(cast (times as big_decimal))                 "
	 					+"FROM                           "
	 					+"	ServiceDetail                "
	 					+"WHERE                          "
	 					+"	serviceBegin >= STR_TO_DATE( "
	 					+"		'%tF 00:00',      "
	 					+"		'%%Y-%%m-%%d %%H:%%i'         "
	 					+"	)                            "
	 					+"AND serviceBegin < STR_TO_DATE("
	 					+"	'%tF 00:00',          "
	 					+"	'%%Y-%%m-%%d %%H:%%i'             "
	 					+")",monthBegin,nextMonthBegin);
	 			int affect2 = ObjectAccess.execute(hql);
	 			if(affect2>=0){
	 				ClearTime serviceClearTimeMonth = (ClearTime)ObjectAccess.getObject("com.dz.module.charge.ClearTime",6);
	 				serviceClearTimeMonth.setCurrent(nextMonthBegin);
	 				ObjectAccess.saveOrUpdate(serviceClearTimeMonth);
	 			}
	 			
	 		}
	 	}
		return SUCCESS;
	}
	
	public String serviceSave(){
		if(serviceDetail!=null){
			ObjectAccess.saveOrUpdate(serviceDetail);
			String cid = serviceDetail.getCarframeNum();
			Date date = serviceDetail.getServiceBegin();
			
			String condition = String.format("delete from ServiceSpace"
		    		+ " where carframeNum='"+cid+"'"
		    		+ " and serviceBegin >= STR_TO_DATE('%tF 00:00','%%Y-%%m-%%d %%H:%%i') "
		    		+ " and serviceBegin <= STR_TO_DATE('%tF 23:59','%%Y-%%m-%%d %%H:%%i') ", date,date);
			ObjectAccess.execute(condition);
		}
		return SUCCESS;
	}
	
	public String serviceSaveFromError(){
		ObjectAccess.saveOrUpdate(serviceDetail);
		
		String condition = "delete from ServiceError where id="+serviceError.getId();
		ObjectAccess.execute(condition);
		return SUCCESS;
	}
	
	public String beforeserviceSave(){
		if(serviceDetail!=null&&serviceDetail.getId()!=null&&serviceDetail.getId()!=0){
			serviceDetail=(ServiceDetail) ObjectAccess.getObject("com.dz.module.vehicle.service.ServiceDetail", serviceDetail.getId());
		}else if(serviceSpace!=null&&serviceSpace.getId()!=null&&serviceSpace.getId()!=0){
			serviceSpace=(ServiceSpace) ObjectAccess.getObject("com.dz.module.vehicle.service.ServiceSpace", serviceSpace.getId());
		}
		return SUCCESS;
	}
	
	public String beforeserviceSaveFromError(){
		if(serviceError!=null&&serviceError.getId()!=null&&serviceError.getId()!=0){
			serviceError=(ServiceError) ObjectAccess.getObject("com.dz.module.vehicle.service.ServiceError", serviceError.getId());
		}
		return SUCCESS;
	}
	
	
	public String singleDailyExport(){
		List<List<? extends Serializable>> datalist;
		List<String> datasrc;
		datasrc = Arrays.asList("details","errors","spaces");
		List<ServiceDetail> dl = ObjectAccess.query(ServiceDetail.class,String.format("serviceBegin >= STR_TO_DATE('%tF 00:00','%%Y-%%m-%%d %%H:%%i') "
	    		+ " and serviceBegin <= STR_TO_DATE('%tF 23:59','%%Y-%%m-%%d %%H:%%i') ", theDate,theDate));
		List<ServiceError> el = ObjectAccess.query(ServiceError.class,String.format("serviceBegin >= STR_TO_DATE('%tF 00:00','%%Y-%%m-%%d %%H:%%i') "
	    		+ " and serviceBegin <= STR_TO_DATE('%tF 23:59','%%Y-%%m-%%d %%H:%%i') ", theDate,theDate));
		List<ServiceSpace> sl = ObjectAccess.query(ServiceSpace.class,String.format("date >= STR_TO_DATE('%tF 00:00','%%Y-%%m-%%d %%H:%%i') "
	    		+ " and date <= STR_TO_DATE('%tF 23:59','%%Y-%%m-%%d %%H:%%i') ", theDate,theDate));
		datalist=Arrays.asList(dl,el,sl);
		request.setAttribute("datasrc", datasrc);
		request.setAttribute("datalist", datalist);
		return SUCCESS;
	}
	
	private Date theDate,beginDate,endDate,theMonth;
	private int theYear;
	
	private ServiceDetail serviceDetail;
	private ServiceError serviceError;
	private ServiceSpace serviceSpace;
	
	public ServiceDetail getServiceDetail() {
		return serviceDetail;
	}

	public void setServiceDetail(ServiceDetail serviceDetail) {
		this.serviceDetail = serviceDetail;
	}

	public ServiceError getServiceError() {
		return serviceError;
	}

	public void setServiceError(ServiceError serviceError) {
		this.serviceError = serviceError;
	}

	public ServiceSpace getServiceSpace() {
		return serviceSpace;
	}

	public void setServiceSpace(ServiceSpace serviceSpace) {
		this.serviceSpace = serviceSpace;
	}

	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
	public Date getTheDate() {
		return theDate;
	}

	public void setTheDate(Date theDate) {
		this.theDate = theDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getTheMonth() {
		return theMonth;
	}

	public void setTheMonth(Date theMonth) {
		this.theMonth = theMonth;
	}

	public int getTheYear() {
		return theYear;
	}

	public void setTheYear(int theYear) {
		this.theYear = theYear;
	}

}
