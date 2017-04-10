package com.dz.module.vehicle;

import com.dz.common.factory.HibernateSessionFactory;
import com.dz.common.global.BaseAction;
import com.dz.common.global.Page;
import com.dz.common.other.FileAccessUtil;
import com.dz.common.other.FileUploadUtil;
import com.dz.common.other.ObjectAccess;
import com.dz.common.other.PageUtil;
import com.dz.module.contract.Contract;
import com.dz.module.contract.ContractService;
import com.dz.module.user.RelationUr;
import com.dz.module.user.User;
import com.dz.module.user.message.Message;
import com.dz.module.user.message.MessageToUser;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.javatuples.Triplet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Controller
@Scope(value = "prototype")
public class VehicleAction extends BaseAction{

	private Vehicle vehicle; 
    @Autowired
	private VehicleService vehicleService; 
    @Autowired
    private ContractService contractService;
    @Autowired
	private FileAccessUtil fileAccessUtil;

	private Contract contract;
	private Object jsonObject;
	
	private String photo;
	
	private String photo_tuoying;
	
	private String condition;
	
	private String driverName;
	
	//For Search
	private Date pd_begin,pd_end;
	private Date inDate_begin,inDate_end;
	private Date operateCardTime_begin,operateCardTime_end;
	
	//行车执照登记日期 审批单
	private Date getCarDate;
	
	
	public void setVehicleService(VehicleService vehicleService){
		this.vehicleService = vehicleService;
	}
	
    public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	public Vehicle getVehicle(){
		return vehicle;
	}
	
	public VehicleMode getVehicleMode() {
		return vehicleMode;
	}

	public void setVehicleMode(VehicleMode vehicleMode) {
		this.vehicleMode = vehicleMode;
	}

	public void setVehicle(Vehicle vehicle){
		this.vehicle = vehicle;
	}

	public String vehicleSele() {
		 int currentPage = 0;
         String currentPagestr = request.getParameter("currentPage");
         if(currentPagestr == null || "".equals(currentPagestr)){
         	currentPage = 1;
         }else{
         	currentPage=Integer.parseInt(currentPagestr);
         }
         
        String hql = " state>=0 ";

        if(vehicle!=null){
        	
        	hql += checkAndGenerate(vehicle,"carframeNum"," and carframeNum like '%%%s%%' ");
        	hql += checkAndGenerate(vehicle,"licenseNum"," and licenseNum like '%%%s%%' ");
        	hql += checkAndGenerate(vehicle,"engineNum"," and engineNum like '%%%s%%' ");
        	hql += checkAndGenerate(vehicle,"dept");
        	hql += checkAndGenerate(vehicle,"invoiceNumber");
        	hql += checkAndGenerate(vehicle,"taxNumber");
        	hql += checkAndGenerate(vehicle,"operateCard");
        	hql += checkAndGenerate(vehicle,"businessLicenseId");
        	//hql += checkAndGenerate(vehicle,"businessLicenseBeginDate"," and businessLicenseBeginDate >= STR_TO_DATE('%tF','%%Y-%%m-%%d') ");
        	//hql += checkAndGenerate(vehicle,"businessLicenseEndDate"," and businessLicenseEndDate <= STR_TO_DATE('%tF','%%Y-%%m-%%d') ");
        	
        	if(!StringUtils.isEmpty(driverName)){
        		hql += " and driverId in (select idNum from Driver where name ='"+driverName+"') ";
        	}
        }
        
        if(StringUtils.isNotEmpty(condition)){
			hql+=condition;
		}
         
        long count = ObjectAccess.execute("select count(*) from Vehicle where " + hql);
        
        //vehicle.setCarMode("123");
        Page page = PageUtil.createPage(15,(int)count, currentPage);
        
        List<Vehicle> l = ObjectAccess.query(Vehicle.class, hql, null, null, null, page);
        
/*		List<Vehicle> l = vehicleService.seleVehicle(page, vehicle,conditions);
*/		request.setAttribute("vehicle", l);
		//request.setAttribute("currentPage", currentPage);
		request.setAttribute("page", page);
		
		if(StringUtils.isEmpty(url))
			return "select";
		
		return "selectToUrl";
	}
	
	public String vehicleBind(){
		Vehicle v = ObjectAccess.getObject(Vehicle.class, vehicle.getCarframeNum());
		Contract c = ObjectAccess.execute("select c from Contract c where c.idNum='"+vehicle.getDriverId()+
				"' and c.state=2 ");
		if(c==null){
			request.setAttribute("msgStr", "添加失败。请先进行新车开业，新车开业后才可进行人辆绑定。");
			return ERROR;
		}
		c.setCarframeNum(v.getCarframeNum());
		c.setCarNum(v.getLicenseNum());
		
		ObjectAccess.saveOrUpdate(c);
		
		v.setDriverId(vehicle.getDriverId());
		ObjectAccess.saveOrUpdate(v);
		
		if(v.getOperateCardTime()!=null){
			VehicleApproval approval = ObjectAccess.execute("from VehicleApproval c where c.contractId="+c.getId()+
					" and c.checkType=0 ");
			approval.setOperateCardDate(v.getOperateCardTime());
			ObjectAccess.saveOrUpdate(approval);
		}
		
		if(v.getGetCarDate()!=null){
			VehicleApproval approval = ObjectAccess.execute("from VehicleApproval c where c.contractId="+c.getId()+
					" and c.checkType=0 ");
			approval.setGetCarDate(v.getGetCarDate());
			ObjectAccess.saveOrUpdate(approval);
		}
		
		if(v.getLicenseNumRegDate()!=null){
			VehicleApproval approval = ObjectAccess.execute("from VehicleApproval c where c.contractId="+c.getId()+
					" and c.checkType=0 ");
			approval.setLicenseRegisterDate(v.getLicenseNumRegDate());
			approval.setOperateApplyDate(new Date());
			ObjectAccess.saveOrUpdate(approval);
		}
		
		return SUCCESS;
	}
	
	public void vehicleSelectValidCount() throws IOException{
		Triplet<String,String,Object> condition = new Triplet<String,String,Object>("state","<",2);
		Triplet<String,String,Object> condition2 = new Triplet<String,String,Object>("state","!=",-1);
		int total = vehicleService.seleVehicleCount(null,condition,condition2);
		
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		out.print(total);
		
		out.flush();
		out.close();
	}

	public String vehicleSelectAll(){
		jsonObject = vehicleService.selectAll();
		return "jsonObject";
	}
	
	public String vehicleAdd() {
		try {
			String basePath = System.getProperty("com.dz.root") +"data/vehicle/"+vehicle.getCarframeNum()+"/";
			File base = new File(basePath);
			if (!base.exists()) {
				base.mkdirs();
			}
			if(StringUtils.length(photo)==30){
				FileUploadUtil.store(photo, new File(base,"photo.jpg"));
			}
			
			if(StringUtils.length(photo_tuoying)==30){
				FileUploadUtil.store(photo_tuoying, new File(base,"photo_tuoying.jpg"));
			}
			
			vehicle.setState(-1);
			
			vehicle.setGetCarDate(getCarDate);
			
			boolean flag = vehicleService.addVehicle(vehicle);
			if (!flag) {
				request.setAttribute("msgStr", "添加失败。");
				return ERROR;
			}
			request.setAttribute("msgStr", "添加成功。");
			vehicle=null;
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return ERROR;
		}
	}
	
	public String vehicleRelook(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Query q = s.createQuery("update Vehicle set state=0 where state=-1");
			q.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "添加成功。");
		return SUCCESS;
	}
	
	public String vehicleDelete(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Vehicle v = (Vehicle) s.get(Vehicle.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=-1){
				request.setAttribute("msgStr", "删除失败。不存在未审核的车架号为"+vehicle.getCarframeNum()+"的车辆。");
				return SUCCESS;
			}else{
				s.delete(v);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "删除失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	
	public void vehicleSearch() throws IOException {
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		int count = vehicleService.seleVehicleCount(vehicle);
		Page page = PageUtil.createPage(count,count, 0);
		List<Vehicle> l = vehicleService.seleVehicle(page, vehicle);
		
		JSONArray json = JSONArray.fromObject(l);
	
		out.print(json.toString());
		
		out.flush();
		out.close();
	}

	public String vehicleSelectByLicenseNum(){
        Vehicle v = (Vehicle)vehicleService.selectByLicenseNum(vehicle);
		v.setDriverId(v.getDriverId()+"p");
		v.setFirstDriver(v.getFirstDriver()+"p");
		v.setSecondDriver(v.getSecondDriver()+"p");
		v.setThirdDriver(v.getThirdDriver()+"p");
		v.setForthDriver(v.getForthDriver()+"p");
		System.out.println(v);
		jsonObject = v;
        return "selectByLicenseNum";
	}
	
	public String vehicleUpdate() {
		boolean flag = vehicleService.updateVehicle(vehicle);
		if(!flag) {
			request.setAttribute("msgStr", "操作失败。");
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	
	public String addInvoice(){
		Invoice i = new Invoice();
		BeanUtils.copyProperties(vehicle,i, new String[]{"state"});
		i.setState(0);
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(i);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String relookInvoice(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Query query = s.createQuery("from Invoice where state=0");
			List<Invoice> is = query.list();
			for(Invoice i:is){
				Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
				BeanUtils.copyProperties(i,v, new String[]{"state"});
				s.update(v);
				i.setState(1);
				s.update(i);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String revokeInvoice(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Invoice v = (Invoice) s.get(Invoice.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=1){
				request.setAttribute("msgStr", "回退失败。");
				return SUCCESS;
			}else{
				v.setState(0);
				s.saveOrUpdate(v);
			}
			
			Vehicle vh = (Vehicle) s.get(Vehicle.class, vehicle.getCarframeNum());
			Invoice ispace = new Invoice();
			BeanUtils.copyProperties(ispace,vh, new String[]{"state","carframeNum"});
			s.saveOrUpdate(vh);
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "回退失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String deleteInvoice(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Invoice v = (Invoice) s.get(Invoice.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=0){
				request.setAttribute("msgStr", "删除失败。");
				return SUCCESS;
			}else{
				s.delete(v);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "删除失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String addTax(){
		Tax i = new Tax();
		BeanUtils.copyProperties(vehicle,i, new String[]{"state"});
		i.setState(0);
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(i);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String relookTax(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Query query = s.createQuery("from Tax where state=0");
			List<Tax> is = query.list();
			for(Tax i:is){
				Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
				BeanUtils.copyProperties(i,v, new String[]{"state"});
				s.update(v);
				i.setState(1);
				s.update(i);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String revokeTax(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Tax v = (Tax) s.get(Tax.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=1){
				request.setAttribute("msgStr", "回退失败。");
				return SUCCESS;
			}else{
				v.setState(0);
				s.saveOrUpdate(v);
			}
			
			Vehicle vh = (Vehicle) s.get(Vehicle.class, vehicle.getCarframeNum());
			Tax ispace = new Tax();
			BeanUtils.copyProperties(ispace,vh, new String[]{"state","carframeNum"});
			s.saveOrUpdate(vh);
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "回退失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String deleteTax(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Tax v = (Tax) s.get(Tax.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=0){
				request.setAttribute("msgStr", "删除失败。");
				return SUCCESS;
			}else{
				s.delete(v);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "删除失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String addLicence(){
		Vehicle v = vehicleService.selectById(vehicle);
		
		if(v==null){
			request.setAttribute("msgStr", "添加失败。车架号错误，该车不存在！");
			return SUCCESS;
		}
		
		String basePath = System.getProperty("com.dz.root") +"data/vehicle/"+vehicle.getCarframeNum()+"/";
		File base = new File(basePath);
		if (!base.exists()) {
			base.mkdirs();
		}
		if(StringUtils.length(photo)==30){
			FileUploadUtil.store(photo, new File(base,"photo.jpg"));
		}
		
		License i = new License();
		BeanUtils.copyProperties(vehicle,i, new String[]{"state"});
		i.setState(0);
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(i);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String relookLicence(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Query query = s.createQuery("from License where state=0");
			List<License> is = query.list();
			for(License i:is){
				Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
				BeanUtils.copyProperties(i,v, new String[]{"state"});
				s.update(v);
				i.setState(1);
				s.update(i);
				
				Query q_c = s.createQuery("select c from Contract c where c.state in (2,3) and c.idNum=:idNum and c.carframeNum=:carframeNum ");
				q_c.setString("idNum", v.getDriverId());
				q_c.setString("carframeNum", v.getCarframeNum());
				q_c.setMaxResults(1);
				Contract c = (Contract) q_c.uniqueResult();
				
				if(c!=null){
					c.setCarNum(v.getLicenseNum());
					
					s.saveOrUpdate(c);
					
					Query q_va = s.createQuery("from VehicleApproval c where c.contractId=:cid and c.checkType=0 ");
					q_va.setInteger("cid", c.getId());
					q_va.setMaxResults(1);
					VehicleApproval approval = (VehicleApproval) q_va.uniqueResult();
		
					approval.setLicenseRegisterDate(v.getLicenseNumRegDate());
					approval.setOperateApplyDate(new Date());
					s.saveOrUpdate(approval);
				}
				
				Message msg = new Message();
				
				User u = (User) s.get(User.class, v.getLicenseRegister());
				msg.setFromUser(v.getLicenseRegister());
				msg.setTime(new Date());
				
				msg.setCarframeNum(v.getCarframeNum());
				msg.setType("车辆牌照录入完毕");
				msg.setMsg(String.format("%tF %s发：\n"
						+ "现有车%s(%s) 已录入完毕，可进行绑定承租人操作。", 
						msg.getTime(),u.getUname(),
						v.getLicenseNum(),v.getCarframeNum()));
				
				s.saveOrUpdate(msg);
				
				Query q_us = s.createQuery("from RelationUr where rid in (select rid from Role where rname = '绑定承租人')");
				List<RelationUr> users = q_us.list();
		
				for (RelationUr relationUr : users) {
					MessageToUser mu = new MessageToUser();
					mu.setUid(relationUr.getUid());
					mu.setMid(msg.getId());
					mu.setAlreadyRead(false);
					s.saveOrUpdate(mu);
				}
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String deleteLicence(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			License v = (License) s.get(License.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=0){
				request.setAttribute("msgStr", "删除失败。");
				return SUCCESS;
			}else{
				s.delete(v);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "删除失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String revokeLicence(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			License i = (License) s.get(License.class, vehicle.getCarframeNum());
			
			if(i == null||i.getState()!=1){
				request.setAttribute("msgStr", "回退失败。");
				return SUCCESS;
			}
			
			Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
			
			Query q_c = s.createQuery("select c from Contract c where c.state=0 and c.idNum=:idNum and c.carframeNum=:carframeNum ");
			q_c.setString("idNum", v.getDriverId());
			q_c.setString("carframeNum", v.getCarframeNum());
			q_c.setMaxResults(1);
			Contract c = (Contract) q_c.uniqueResult();
			
			if(c!=null){
				request.setAttribute("msgStr", "合同已生效，不可修改。");
				return SUCCESS;
			}
			
			License ispace = new License();
			ispace.setCarframeNum(v.getCarframeNum());
			BeanUtils.copyProperties(ispace,v, new String[]{"state"});

			i.setState(0);
			s.saveOrUpdate(i);
			s.saveOrUpdate(v);
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "回退失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	/**
	 * 添加计价器信息
	 */
	public String addService(){
		Vehicle v = vehicleService.selectById(vehicle);
		if(v==null){
			request.setAttribute("msgStr", "添加失败。车架号错误，该车不存在！");
			return SUCCESS;
		}
		
		ServiceInfo i = new ServiceInfo();
		BeanUtils.copyProperties(vehicle,i, new String[]{"state"});
		i.setState(0);
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(i);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String relookService(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Query query = s.createQuery("from ServiceInfo where state=0");
			List<ServiceInfo> is = query.list();
			for(ServiceInfo i:is){
				Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
				BeanUtils.copyProperties(i,v, new String[]{"state"});
				s.update(v);
				i.setState(1);
				s.update(i);
				
				Query q_c = s.createQuery("select c from Contract c where c.state in (2,3) and c.idNum=:idNum and c.carframeNum=:carframeNum ");
				q_c.setString("idNum", v.getDriverId());
				q_c.setString("carframeNum", v.getCarframeNum());
				q_c.setMaxResults(1);
				Contract c = (Contract) q_c.uniqueResult();
				
				if(c!=null){					
					Query q_va = s.createQuery("from VehicleApproval c where c.contractId=:cid and c.checkType=0 ");
					q_va.setInteger("cid", c.getId());
					q_va.setMaxResults(1);
					VehicleApproval approval = (VehicleApproval) q_va.uniqueResult();
					approval.setOperateCardDate(v.getOperateCardTime());
					s.saveOrUpdate(approval);
				}
	
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String deleteService(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			ServiceInfo v = (ServiceInfo) s.get(ServiceInfo.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=0){
				request.setAttribute("msgStr", "删除失败。");
				return SUCCESS;
			}else{
				s.delete(v);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "删除失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	
	public String revokeService(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			ServiceInfo i = (ServiceInfo) s.get(ServiceInfo.class, vehicle.getCarframeNum());
			
			if(i == null||i.getState()!=1){
				request.setAttribute("msgStr", "回退失败。");
				return SUCCESS;
			}
			
			Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
			
			ServiceInfo ispace = new ServiceInfo();
			BeanUtils.copyProperties(ispace,v, new String[]{"state","carframeNum"});

			i.setState(0);
			s.saveOrUpdate(i);
			s.saveOrUpdate(v);
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "回退失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	/**
	 * 添加经营权信息
	 */
	public String addServiceRight(){
		Vehicle v = vehicleService.selectById(vehicle);
		if(v==null){
			request.setAttribute("msgStr", "添加失败。车架号错误，该车不存在！");
			return SUCCESS;
		}
		
		ServiceRightInfo i = new ServiceRightInfo();
		BeanUtils.copyProperties(vehicle,i, new String[]{"state"});
		i.setState(0);
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(i);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String relookServiceRight(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Query query = s.createQuery("from ServiceRightInfo where state=0");
			List<ServiceRightInfo> is = query.list();
			for(ServiceRightInfo i:is){
				Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
				BeanUtils.copyProperties(i,v, new String[]{"state"});
				s.update(v);
				i.setState(1);
				s.update(i);
				
				Query q_c = s.createQuery("select c from Contract c where c.state in (2,3) and c.idNum=:idNum and c.carframeNum=:carframeNum ");
				q_c.setString("idNum", v.getDriverId());
				q_c.setString("carframeNum", v.getCarframeNum());
				q_c.setMaxResults(1);
				Contract c = (Contract) q_c.uniqueResult();
				
				if(c!=null){
					Query q_va = s.createQuery("from VehicleApproval c where c.contractId=:cid and c.checkType=0 ");
					q_va.setInteger("cid", c.getId());
					q_va.setMaxResults(1);
					VehicleApproval approval = (VehicleApproval) q_va.uniqueResult();
					approval.setOperateCardDate(v.getOperateCardTime());
					s.saveOrUpdate(approval);
				}
	
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String deleteServiceRight(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			ServiceRightInfo v = (ServiceRightInfo) s.get(ServiceRightInfo.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=0){
				request.setAttribute("msgStr", "删除失败。");
				return SUCCESS;
			}else{
				s.delete(v);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "删除失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String revokeServiceRight(){
		request.setAttribute("msgStr", "由于与合同计费发生挂钩，功能停用。");
		return SUCCESS;
		
//		Session s = null;
//		Transaction tx = null;
//		try{
//			s = HibernateSessionFactory.getSession();
//			tx = s.beginTransaction();
//			ServiceRightInfo i = (ServiceRightInfo) s.get(ServiceRightInfo.class, vehicle.getCarframeNum());
//			
//			if(i == null||i.getState()!=1){
//				request.setAttribute("msgStr", "回退失败。");
//				return SUCCESS;
//			}
//			
//			Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
//			
//			Query q_c = s.createQuery("select c from Contract c where c.state=0 and c.idNum=:idNum and c.carframeNum=:carframeNum ");
//			q_c.setString("idNum", v.getDriverId());
//			q_c.setString("carframeNum", v.getCarframeNum());
//			q_c.setMaxResults(1);
//			Contract c = (Contract) q_c.uniqueResult();
//			
//			if(c!=null){
//				request.setAttribute("msgStr", "合同已生效，不可修改。");
//				return SUCCESS;
//			}
//			
//			ServiceRightInfo ispace = new ServiceRightInfo();
//			ispace.setCarframeNum(v.getCarframeNum());
//			BeanUtils.copyProperties(ispace,v, new String[]{"state"});
//
//			i.setState(0);
//			s.saveOrUpdate(i);
//			s.saveOrUpdate(v);
//			
//			tx.commit();
//		}catch(HibernateException e){
//			e.printStackTrace();
//			if(tx!=null){
//				tx.rollback();
//			}
//			request.setAttribute("msgStr", "回退失败。原因是"+e.getMessage());
//			return SUCCESS;
//		}finally{
//			HibernateSessionFactory.closeSession();
//		}
//		request.setAttribute("msgStr", "操作成功。");
//		return SUCCESS;
	}
	
	
	private BusinessLicense businessLicense;

	public String addTrade(){
		Vehicle v = vehicleService.selectById(vehicle);
		if(v==null){
			request.setAttribute("msgStr", "添加失败。车架号错误，该车不存在！");
			return SUCCESS;
		}
		
		Trade i = new Trade();
		BeanUtils.copyProperties(businessLicense,i, new String[]{"state"});
		i.setCarframeNum(vehicle.getCarframeNum());
		i.setBusinessLicenseComment(vehicle.getBusinessLicenseComment());
		
		i.setState(0);
		
		String str = "";
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(i);
			
			BusinessLicense bl = (BusinessLicense) s.get(BusinessLicense.class, i.getLicenseNum());
			if(bl!=null){
				str="注意：已存在一条资格证号相同的记录，审核时将自动覆盖。";
			}
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。"+str);
		return SUCCESS;
	}
	
	public String relookTrade(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Query query = s.createQuery("from Trade where state=0");
			List<Trade> is = query.list();
			for(Trade i:is){
				Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
				BusinessLicense ls = new BusinessLicense();
				BeanUtils.copyProperties(i,ls, new String[]{"state"});
				s.saveOrUpdate(ls);
				
				v.setBusinessLicenseComment(i.getBusinessLicenseComment());
				v.setBusinessLicenseId(i.getLicenseNum());
				s.update(v);
				i.setState(1);
				s.update(i);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "添加失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String deleteTrade(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Trade v = (Trade) s.get(Trade.class, vehicle.getCarframeNum());
			if(v == null||v.getState()!=0){
				request.setAttribute("msgStr", "删除失败。");
				return SUCCESS;
			}else{
				s.delete(v);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "删除失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	public String revokeTrade(){
		Session s = null;
		Transaction tx = null;
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			Trade i = (Trade) s.get(Trade.class, vehicle.getCarframeNum());
			
			if(i == null||i.getState()!=1){
				request.setAttribute("msgStr", "回退失败。");
				return SUCCESS;
			}
			
			Vehicle v = (Vehicle) s.get(Vehicle.class, i.getCarframeNum());
			
			BusinessLicense ls = (BusinessLicense) s.get(BusinessLicense.class, i.getLicenseNum());
			if(ls!=null){
				s.delete(ls);
			}
			
			v.setBusinessLicenseComment(null);
			v.setBusinessLicenseId(null);

			i.setState(0);
			s.saveOrUpdate(i);
			s.saveOrUpdate(v);
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			request.setAttribute("msgStr", "回退失败。原因是"+e.getMessage());
			return SUCCESS;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		request.setAttribute("msgStr", "操作成功。");
		return SUCCESS;
	}
	
	private VehicleMode vehicleMode;
	public String vehicleSelectById(){
		Vehicle vs = vehicleService.selectById(vehicle);
		vs.setFirstDriver(vs.getFirstDriver()+ " ");
		vs.setSecondDriver(vs.getSecondDriver()+" ");
		vs.setThirdDriver(vs.getThirdDriver()+" ");
		vs.setForthDriver(vs.getForthDriver()+" ");
		
		vehicleMode  = (VehicleMode) ObjectAccess.getObject("com.dz.module.vehicle.VehicleMode", vs.getCarMode());

		this.vehicle = vs;
		
		jsonObject = vs;
		vehicle.setDriverId(vehicle.getDriverId()+" ");
		return "selectById";
	}

    public Object getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
    }

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto_tuoying() {
		return photo_tuoying;
	}

	public void setPhoto_tuoying(String photo_tuoying) {
		this.photo_tuoying = photo_tuoying;
	}

	public void setFileAccessUtil(FileAccessUtil fileAccessUtil) {
		this.fileAccessUtil = fileAccessUtil;
	}

	public Date getPd_begin() {
		return pd_begin;
	}

	public void setPd_begin(Date pd_begin) {
		this.pd_begin = pd_begin;
	}

	public Date getPd_end() {
		return pd_end;
	}

	public void setPd_end(Date pd_end) {
		this.pd_end = pd_end;
	}

	public Date getInDate_begin() {
		return inDate_begin;
	}

	public void setInDate_begin(Date inDate_begin) {
		this.inDate_begin = inDate_begin;
	}

	public Date getInDate_end() {
		return inDate_end;
	}

	public void setInDate_end(Date inDate_end) {
		this.inDate_end = inDate_end;
	}

	public Date getOperateCardTime_begin() {
		return operateCardTime_begin;
	}

	public void setOperateCardTime_begin(Date operateCardTime_begin) {
		this.operateCardTime_begin = operateCardTime_begin;
	}

	public Date getOperateCardTime_end() {
		return operateCardTime_end;
	}

	public void setOperateCardTime_end(Date operateCardTime_end) {
		this.operateCardTime_end = operateCardTime_end;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Date getGetCarDate() {
		return getCarDate;
	}

	public void setGetCarDate(Date getCarDate) {
		this.getCarDate = getCarDate;
	}

	public BusinessLicense getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(BusinessLicense businessLicense) {
		this.businessLicense = businessLicense;
	}
	
}
