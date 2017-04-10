package com.dz.module.driver.complain;

import com.dz.common.global.Page;
import com.dz.common.other.FileAccessUtil;
import com.dz.common.other.ObjectAccess;
import com.dz.common.other.PageUtil;
import com.dz.module.driver.Driver;
import com.dz.module.user.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ArrayUtils;

import net.sf.json.JSONObject;

import org.apache.commons.collections.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
public class ComplainAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6783358540440583987L;
	private HttpServletRequest request;
	private HttpSession session;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	@Autowired
	private ComplainService complainService;
	@Autowired
	private FileAccessUtil fileAccessUtil;
	
	public void setComplainService(ComplainService complainService) {
		this.complainService = complainService;
	}
	
	public void setFileAccessUtil(FileAccessUtil fileAccessUtil) {
		this.fileAccessUtil = fileAccessUtil;
	}

	private Complain complain;
	
	public Complain getComplain() {
		return complain;
	}
	public void setComplain(Complain complain) {
		this.complain = complain;
	}

	private File[] fileUploads;
	private String[] fileUploadsFileName;
	private String[] fileUploadsContentType;
	
	public String addComplain(){
		try {
			User user = (User) session.getAttribute("user");
			complain.setRegistrant(user.getUid());
			complain.setState(0);
//			complain.setComplainTime(new Date());
			
			complainService.addComplain(complain);
			
			if(!ArrayUtils.isEmpty(fileUploads)){
				String basePath = session.getServletContext().getRealPath("/data/driver/complain");
				fileAccessUtil.store(basePath,""+complain.getId(), fileUploads, fileUploadsFileName);
				//int fileListNumber = fileAccessUtil.store(basePath, fileUploads, fileUploadsFileName);
				int fileListNumber = fileUploads==null?0:fileUploads.length;
				complain.setComplainFile(fileListNumber);
			}
		} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
		}
		return SUCCESS;
	}
	
	private Boolean confirmReault;
	
	
	public Boolean getConfirmReault() {
		return confirmReault;
	}

	public void setConfirmReault(Boolean confirmReault) {
		this.confirmReault = confirmReault;
	}

	public String confirmComplain(){
		try{
			Complain oc = complainService.selectById(complain);
			oc.setConfirmTime(complain.getConfirmTime());
			if(confirmReault)
				oc.setState(1);
			else
				oc.setState(-1);
			User user = (User) session.getAttribute("user");
			oc.setConfirmPerson(user.getUid());

			complainService.updateComplain(oc);
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String dealComplain(){
		try{
			Complain oc = complainService.selectById(complain);
			oc.setDealReault(complain.getDealReault());
			oc.setDealTime(complain.getDealTime());
			oc.setState(2);
			oc.setGrade(complain.getGrade());

			User user = (User) session.getAttribute("user");
			oc.setDealPerson(user.getUid());

			complainService.updateComplain(oc);
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String handledComplain(){
		try{
			Complain oc = complainService.selectById(complain);
			oc.setFinishTime(complain.getVisitBackTime());
			oc.setState(4);

			User user = (User) session.getAttribute("user");
			oc.setFinishPerson(user.getUid());

			complainService.updateComplain(oc);
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	//Handled
	public String visitBackComplain(){
		try{
			Complain oc = complainService.selectById(complain);
			oc.setVisitBackResult(complain.getVisitBackResult());
			oc.setVisitBackTime(complain.getVisitBackTime());
			//oc.setState(3);
			
			User user = (User) session.getAttribute("user");
			oc.setVisitBackPerson(user.getUid());
			
			oc.setFinishTime(new Date());
			oc.setState(4);

			oc.setFinishPerson(user.getUid());

			complainService.updateComplain(oc);
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	private String[] fileRemoved;
	public String[] getFileRemoved() {
		return fileRemoved;
	}

	public void setFileRemoved(String[] fileRemoved) {
		this.fileRemoved = fileRemoved;
	}
	
	public String updateComplain(){
		try {
			//Complain oc = complainService.selectById(complain);
			Complain oc = ObjectAccess.getObject(Complain.class, complain.getId());
			Integer fileListNumber = oc.getComplainFile();
			if(fileListNumber!=null && fileListNumber!=0){
			if(!ArrayUtils.isEmpty(fileRemoved)){
				fileAccessUtil.remove(fileListNumber, fileRemoved);
			}
			
			if(!ArrayUtils.isEmpty(fileUploads)){
				fileAccessUtil.addMoreFile(fileListNumber, fileUploads, fileUploadsFileName);
			}
			
			complain.setComplainFile(fileListNumber);
			
			}
			complainService.updateComplain(complain);
			
		} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
		}
		return SUCCESS;
	}
	
	public String attachComplain(){
		try {
			Complain oc = complainService.selectById(complain);
			oc.setAttachDetail(complain.getAttachDetail());
			ObjectAccess.saveOrUpdate(oc);
		} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
		}
		return SUCCESS;
	}
	
	public String deleteComplain(){
		try {
			Complain oc = complainService.selectById(complain);
			int fileListNumber = ObjectUtils.defaultIfNull(oc.getComplainFile(), 0);
			if(fileListNumber!=0){
				String basePath = session.getServletContext().getRealPath("/data/driver/complain");
				fileAccessUtil.remove(basePath, ""+complain.getId());
			}
			
			complainService.deleteComplain(complain);
		} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
		}
		return SUCCESS;
	}
	
	private Date beginDate,endDate;
	
	public Date getBeginDate() {
		return beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
/*
	public void selectAll() throws IOException{
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		
		Page page = PageUtil.createPage(15,
				complainService.selectAllCount(beginDate,endDate), currentPage);
		List<Complain> complainList = complainService.selectAll(page,beginDate,endDate);
		
		JSONObject json = new JSONObject();
		json.put("complains", complainList);
		
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		pw.print(json.toString());
		pw.flush();
		pw.close();
	}
	
	public void selectAllWaitForDeal() throws IOException{
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		
		Page page = PageUtil.createPage(15,
				complainService.selectAllWaitForDealCount(beginDate, endDate), currentPage);
		List<Complain> complainList = complainService.selectAllWaitForDeal(page,beginDate,endDate);
		
		JSONObject json = new JSONObject();
		json.accumulate("complains", complainList);
		
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		pw.print(json.toString());
		pw.flush();
		pw.close();
	}
	
	public void selectAllWaitForVisitBack() throws IOException{
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		
		Page page = PageUtil.createPage(15,
				complainService.selectAllWaitForVisitBackCount(beginDate,endDate), currentPage);
		List<Complain> complainList = complainService.selectAllWaitForVisitBack(page,beginDate,endDate);
		
		JSONObject json = new JSONObject();
		json.accumulate("complains", complainList);
		
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		pw.print(json.toString());
		pw.flush();
		pw.close();
	}
	
	public void selectAllHandled() throws IOException{
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		
		Page page = PageUtil.createPage(15,
				complainService.selectAllHandledCount(beginDate,endDate), currentPage);
		List<Complain> complainList = complainService.selectAllHandled(page,beginDate,endDate);
		
		JSONObject json = new JSONObject();
		json.accumulate("complains", complainList);
		
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		pw.print(json.toString());
		pw.flush();
		pw.close();
	}
	
	public void selectAllByState() throws IOException{
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		
		Page page = PageUtil.createPage(15,
				complainService.selectAllByStateCount(beginDate,endDate,complain.getState()), currentPage);
		List<Complain> complainList = complainService.selectAllByState(page,beginDate,endDate,complain.getState());
		
		JSONObject json = new JSONObject();
		json.accumulate("complains", complainList);
		
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		pw.print(json.toString());
		pw.flush();
		pw.close();
	}*/
	
	private String order,dept;
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String searchComplain(){
		int currentPage = 0;
        String currentPagestr = request.getParameter("currentPage");
        if(currentPagestr == null || "".equals(currentPagestr)){
        	currentPage = 1;
        }else{
        	currentPage=Integer.parseInt(currentPagestr);
        }
        
        Short[] states={};
        String[] ss = request.getParameterValues("states");
        if(ss!=null){
        	@SuppressWarnings("unchecked")
			List<Short> statelist = (List<Short>) CollectionUtils.collect(Arrays.asList(ss), new Transformer(){

				@Override
				public Object transform(Object input) {
					String str = (String)input;
					return Short.parseShort(str);
				}
        		
        	});
        	states = new Short[statelist.size()];
        	states = statelist.toArray(states);
        }
       //vehicle.setCarMode("123");
        
//        System.out.println("ComplainAction.searchComplain(),"+complain);
        
       Page page = PageUtil.createPage(15, complainService.selectAllByStatesCount(complain,beginDate,endDate,dept,states), currentPage);
		List<Complain> l = complainService.selectByStates(page,complain, beginDate,endDate,dept,states,order);
		request.setAttribute("list", l);
		request.setAttribute("page", page);
		return SUCCESS;
	}
	private Driver driver;
	public void selectByDriver() throws IOException{
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		
		Page page = PageUtil.createPage(15,
				complainService.selectByDriverCount(driver), currentPage);
		List<Complain> complainList = complainService.selectByDriver(driver,page);
		
		JSONObject json = new JSONObject();
		json.accumulate("complains", complainList);
		
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		pw.print(json.toString());
		pw.flush();
		pw.close();
	}
	
	public String selectComplainById(){
		//complain = complainService.selectById(complain);
		complain = ObjectAccess.getObject(Complain.class, complain.getId());
		return SUCCESS;
	}
	
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver dirver) {
		this.driver = dirver;
	}

	public File[] getFileUploads() {
		return fileUploads;
	}
	public String[] getFileUploadsFileName() {
		return fileUploadsFileName;
	}
	public String[] getFileUploadsContentType() {
		return fileUploadsContentType;
	}
	public void setFileUploads(File[] fileUploads) {
		this.fileUploads = fileUploads;
	}
	public void setFileUploadsFileName(String[] fileUploadsFileName) {
		this.fileUploadsFileName = fileUploadsFileName;
	}
	public void setFileUploadsContentType(String[] fileUploadsContentType) {
		this.fileUploadsContentType = fileUploadsContentType;
	}
	
	private String complainObject2,complainFromIn2,complainFromIn3,idNum,telephone,company;

	public String getComplainObject2() {
		return complainObject2;
	}

	public String getComplainFromIn2() {
		return complainFromIn2;
	}

	public String getComplainFromIn3() {
		return complainFromIn3;
	}

	public String getIdNum() {
		return idNum;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getCompany() {
		return company;
	}

	public void setComplainObject2(String complainObject2) {
		this.complainObject2 = complainObject2;
	}

	public void setComplainFromIn2(String complainFromIn2) {
		this.complainFromIn2 = complainFromIn2;
	}

	public void setComplainFromIn3(String complainFromIn3) {
		this.complainFromIn3 = complainFromIn3;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
	
}
