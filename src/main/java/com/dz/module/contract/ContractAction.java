package com.dz.module.contract;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.dz.common.factory.HibernateSessionFactory;
import com.dz.common.global.BaseAction;
import com.dz.common.global.DateUtil;
import com.dz.common.global.Page;
import com.dz.common.other.ObjectAccess;
import com.dz.common.other.PageUtil;
import com.dz.module.charge.BatchPlan;
import com.dz.module.charge.ChargePlan;
import com.dz.module.charge.ChargeService;
import com.dz.module.driver.Driver;
import com.dz.module.user.User;
import com.dz.module.vehicle.Vehicle;
import com.dz.module.vehicle.VehicleService;
import com.dz.module.vehicle.electric.ElectricHistory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller
@Scope("prototype")
public class ContractAction extends BaseAction {
	private Contract contract;
	private Driver driver;
	private Vehicle vehicle;
	
	private Date beginDate,endDate;
	
	
	@Autowired
	private ContractService contractService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ChargeService chargeService;
	
	
	public void setChargeService(ChargeService chargeService) {
		this.chargeService = chargeService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	private Integer id;
	//jsonObject
	private Object jsonObject;
	
	public ContractService getContractService(){
		return contractService;
	}
	
	public void setContractService(ContractService contractService){
		this.contractService = contractService;
	}
	
	public Contract getContract(){
		return contract;
	}
	
	public void setContract(Contract contract){
		this.contract = contract;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	private String zhuanbao_contractWrite(){
		contract.setPhotoGuarantor(" ");

		//String rentArrStr = request.getParameterValues("rentArr");

		String rentArr = request.getParameter("rentArr");// rentArrStr.split(",");
		JSONArray jarray = JSONArray.fromObject(rentArr);
		//	System.out.println(jarray.size());
		/*String r[] = new String[96];
		int c=0;
		for(int i=0;i<rentArr.length;i++){
			if(!rentArr[i].equals(""))
				r[c++] = rentArr[i];
		}*/
		contract.setAccount(new BigDecimal(0));

		String planList = request.getParameter("planList");
		contract.setPlanList(planList);

		Session hsession = HibernateSessionFactory.getSession();
		Transaction tx = null;
		
		try{
			tx=hsession.beginTransaction();
			
//			hsession.save(contract);
			
			contract.setState((short)0);
			Vehicle vehicle = (Vehicle) hsession.get(Vehicle.class, contract.getCarframeNum());
			if(vehicle!=null){
				vehicle.setState(1);
				vehicle.setDriverId(contract.getIdNum());
				hsession.update(vehicle);
			}
			hsession.update(contract);
			
			int y = 0;
			int m = 0;
			Date contractBeginDate = contract.getContractBeginDate();
			Calendar contractBeginDate_calendar = Calendar.getInstance();
			contractBeginDate_calendar.setTime(contractBeginDate);
			y = contractBeginDate_calendar.get(Calendar.YEAR);
			m = contractBeginDate.getMonth();

			if(contractBeginDate.getDate()>26){
				if(m==11){
					y++;
					m=0;
				}else{
					m++;
				}
			}
			
			/**原车剩余的当月费用处理*/
			Contract contract_old = (Contract) hsession.get(Contract.class,contract.getContractFrom());
			
			Calendar oldEndDate = Calendar.getInstance();
			oldEndDate.setTime(contractBeginDate);
			oldEndDate.add(Calendar.DATE, -1);
			contract_old.setAbandonedFinalTime(oldEndDate.getTime());
			
			chargeService.addAndDiv(contract_old.getId(),contract.getContractBeginDate(),hsession);
			chargeService.setCleared(contract_old.getId(), contract.getContractBeginDate(),hsession);
			
			hsession.update(contract_old);
			
			User user  = (User) session.getAttribute("user");
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(y, m, 1);
			
			//首月计算 计入原合同中
			if(jarray.size()>0)
			{
				ChargePlan chargePlan = new ChargePlan();
				chargePlan.setFeeType("plan_base_contract");
				chargePlan.setIsClear(false);
				chargePlan.setRegister(user.getUname());
				chargePlan.setContractId(contract_old.getId());
				chargePlan.setFee(BigDecimal.valueOf(Double.parseDouble(jarray.get(0).toString())));
				
				chargePlan.setTime(calendar.getTime());
				
				calendar.add(Calendar.MONTH, 1);
				
//				chargeService.addChargePlan(chargePlan,hsession);
		        hsession.save(chargePlan);
			}
			
			for(int i=1 ;i<jarray.size();i++){
				ChargePlan chargePlan = new ChargePlan();
				chargePlan.setFeeType("plan_base_contract");
				chargePlan.setIsClear(false);
				chargePlan.setRegister(user.getUname());
				chargePlan.setContractId(contract.getId());
				chargePlan.setFee(BigDecimal.valueOf(Double.parseDouble(jarray.get(i).toString())));
				
				chargePlan.setTime(calendar.getTime());
				
				calendar.add(Calendar.MONTH, 1);
				
//				chargeService.addChargePlan(chargePlan,hsession);
		        hsession.save(chargePlan);
			}
			
			Query q_move = hsession.createQuery("update ChargePlan set contractId=:nid where contractId=:oid and isClear=false");
			q_move.setInteger("nid", contract.getId());
			q_move.setInteger("oid", contract_old.getId());
			q_move.executeUpdate();
			
			tx.commit();
		}catch(HibernateException ex){
			ex.printStackTrace();
			if(tx!=null)
				tx.rollback();
			return ERROR;
		}finally{
			HibernateSessionFactory.closeSession();
		}

		return SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	public String contractWrite(){
		if(contract.getContractFrom()!=null)
			return zhuanbao_contractWrite();
		contract.setPhotoGuarantor(" ");
		
		//String rentArrStr = request.getParameterValues("rentArr");
		
		String rentArr = request.getParameter("rentArr");// rentArrStr.split(",");
		JSONArray jarray = JSONArray.fromObject(rentArr);
	//	System.out.println(jarray.size());
		/*String r[] = new String[96];
		int c=0;
		for(int i=0;i<rentArr.length;i++){
			if(!rentArr[i].equals(""))
				r[c++] = rentArr[i];
		}*/
		contract.setAccount(new BigDecimal(0));
		
		String planList = request.getParameter("planList");
		contract.setPlanList(planList);
		
		boolean flag = contractService.contractWrite(contract);
		if(!flag){
			return ERROR;
		}
		contractService.changeState(contract,0);
		
		
		Vehicle vehicle = new Vehicle();
		vehicle.setCarframeNum(contract.getCarframeNum());

		vehicle = vehicleService.selectById(vehicle);
		vehicle.setState(1);
		
		vehicle.setDriverId(contract.getIdNum());
		
		flag &= vehicleService.updateVehicle(vehicle);
			
		int y = 0;
		int m = 0;
		Date contractBeginDate = contract.getContractBeginDate();
		Calendar contractBeginDate_calendar = Calendar.getInstance();
		contractBeginDate_calendar.setTime(contractBeginDate);
		y = contractBeginDate_calendar.get(Calendar.YEAR);
		m = contractBeginDate.getMonth();
		
		if(contractBeginDate.getDate()>26){
			if(m==11){
				y++;
				m=0;
			}else{
				m++;
			}
		}
		
		User user  = (User) session.getAttribute("user");
		for(int i=0 ;i<jarray.size();i++){
			ChargePlan chargePlan = new ChargePlan();
			chargePlan.setFeeType("plan_base_contract");
			chargePlan.setIsClear(false);
			chargePlan.setRegister(user.getUname());
			chargePlan.setContractId(contract.getId());
			chargePlan.setFee(BigDecimal.valueOf(Double.parseDouble(jarray.get(i).toString())));
			if(m>=12){
				y++;
				m=0;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(y, m, 1);
			chargePlan.setTime(calendar.getTime());
			m++;
			flag &= chargeService.addChargePlan(chargePlan);
		}
		if(!flag){
			contractService.changeState(contract,3);
			return ERROR;
		}
		
		if(contract.getContractFrom()==null){
			//自动导入或新车开业   需生成 首付摊销
			try{
			int rentFirst_Month = Integer.parseInt(request.getParameter("rentFirst_Month"));
			
			BigDecimal rentFirst_MonthEach = BigDecimal.valueOf(Double.parseDouble( request.getParameter("rentFirst_MonthEach")));
			for(int i=0;i<rentFirst_Month;i++){
				y = contractBeginDate_calendar.get(Calendar.YEAR);
				m = contractBeginDate.getMonth();
				
				RentFirstDivide divide = new RentFirstDivide();
				divide.setCarframeNum(vehicle.getCarframeNum());
				divide.setMoney(rentFirst_MonthEach);
				divide.setRegister(user.getUid());
				divide.setRegTime(new Date());
				
				if(m>=12){
					y++;
					m=0;
				}
				Calendar calendar = Calendar.getInstance();
				calendar.set(y, m, 1);
				divide.setMonth(calendar.getTime());
				
				contractService.addRentFirstDivide(divide);
			}
			}catch(Exception e){
				e.printStackTrace();
				contractService.changeState(contract,3);
				return ERROR;
			}
		}
		
		return SUCCESS;
	}
	
	public String contractUpdate1(){
		//if(contract.getContractFrom()!=null)
		//	return zhuanbao_contractWrite();
		contract.setPhotoGuarantor(" ");
		
		//String rentArrStr = request.getParameterValues("rentArr");
		
		String rentArr = request.getParameter("rentArr");// rentArrStr.split(",");
		JSONArray jarray = JSONArray.fromObject(rentArr);
	//	System.out.println(jarray.size());
		/*String r[] = new String[96];
		int c=0;
		for(int i=0;i<rentArr.length;i++){
			if(!rentArr[i].equals(""))
				r[c++] = rentArr[i];
		}*/
		//contract.setAccount(new BigDecimal(0));
		
		String planList = request.getParameter("planList");
		contract.setPlanList(planList);
		
		boolean flag = contractService.contractWrite(contract);
		if(!flag){
			return ERROR;
		}		
			
		int y = 0;
		int m = 0;
		Date contractBeginDate = contract.getContractBeginDate();
		Calendar contractBeginDate_calendar = Calendar.getInstance();
		contractBeginDate_calendar.setTime(contractBeginDate);
		y = contractBeginDate_calendar.get(Calendar.YEAR);
		m = contractBeginDate.getMonth();
		
		if(contractBeginDate.getDate()>26){
			if(m==11){
				y++;
				m=0;
			}else{
				m++;
			}
		}
		
		User user  = (User) session.getAttribute("user");
		
		List<ChargePlan> rawList = ObjectAccess.query(ChargePlan.class, "contractId='"+contract.getId()+"' and feeType='plan_base_contract'");
		for (ChargePlan chargePlan : rawList) {
			chargeService.deleteChargePlan(chargePlan);
		}

		for(int i=0 ;i<jarray.size();i++){
			ChargePlan chargePlan = new ChargePlan();
			chargePlan.setFeeType("plan_base_contract");
			chargePlan.setIsClear(false);
			chargePlan.setRegister(user.getUname());
			chargePlan.setContractId(contract.getId());
			chargePlan.setFee(BigDecimal.valueOf(Double.parseDouble(jarray.get(i).toString())));
			if(m>=12){
				y++;
				m=0;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(y, m, 1);
			chargePlan.setTime(calendar.getTime());
			m++;
			flag &= chargeService.addChargePlan(chargePlan);
		}
		
//		if(contract.getContractFrom()==null){
//			//自动导入或新车开业   需生成 首付摊销
//			try{
//			int rentFirst_Month = Integer.parseInt(request.getParameter("rentFirst_Month"));
//			BigDecimal rentFirst_MonthEach = BigDecimal.valueOf(Double.parseDouble( request.getParameter("rentFirst_MonthEach")));
//			for(int i=0;i<rentFirst_Month;i++){
//				y = contractBeginDate_calendar.get(Calendar.YEAR);
//				m = contractBeginDate.getMonth();
//				
//				RentFirstDivide divide = new RentFirstDivide();
//				divide.setCarframeNum(vehicle.getCarframeNum());
//				divide.setMoney(rentFirst_MonthEach);
//				divide.setRegister(user.getUid());
//				divide.setRegTime(new Date());
//				
//				if(m>=12){
//					y++;
//					m=0;
//				}
//				Calendar calendar = Calendar.getInstance();
//				calendar.set(y, m, 1);
//				divide.setMonth(calendar.getTime());
//				
//				contractService.addRentFirstDivide(divide);
//			}
//			}catch(Exception e){
//				e.printStackTrace();
//				contractService.changeState(contract,3);
//				return ERROR;
//			}
//		}
		
		return SUCCESS;
	}
	
	public String contractToExcel(){
		List<List<? extends Serializable>> datalist = new ArrayList<List<? extends Serializable>>();
		List<String> datasrc;
		datasrc = Arrays.asList("contracts");
		
		Short[] states={};
        String[] ss = request.getParameterValues("states");
        if(ss.length==1){
        	ss = ss[0].split(",");
        }
        if(ss!=null){
        	@SuppressWarnings("unchecked")
			List<Short> statelist = (List<Short>) CollectionUtils.collect(Arrays.asList(ss), new Transformer(){

				@Override
				public Object transform(Object input) {
					String str = (String)input;
					return Short.parseShort(str.trim());
				}
        		
        	});
        	states = new Short[statelist.size()];
        	states = statelist.toArray(states);
        }
        
		List<Contract> l = contractService.selectAllByStates(null, contract, vehicle, driver,
				beginDate, endDate, states);
		datalist.add(l);
		request.setAttribute("datasrc", datasrc);
		request.setAttribute("datalist", datalist);
		return SUCCESS;
	}
	
//	private String zhuanbao_contractWrite() {
//		contract.setPhotoGuarantor(" ");
//
//		contract.setAccount(new BigDecimal(0));
//		boolean flag = contractService.contractWrite(contract);
//		if(!flag){
//			return ERROR;
//		}
//		contractService.changeState(contract,0);
//
//		Vehicle vehicle = new Vehicle();
//		vehicle.setCarframeNum(contract.getCarframeNum());
//
//		vehicle = vehicleService.selectById(vehicle);
//		vehicle.setState(1);
//
//		vehicle.setDriverId(contract.getIdNum());
//
//		flag &= vehicleService.updateVehicle(vehicle);
//
//		User user  = (User) session.getAttribute("user");
//		List<ChargePlan> chargePlans = ObjectAccess.query(ChargePlan.class, " contractId='"+contract.getContractFrom()+"'");
//		Contract beforeContract = (Contract)ObjectAccess.getObject("com.dz.module.contract.Contract",contract.getContractFrom());
//
//		for (ChargePlan chargePlan : chargePlans) {
//			if (!chargePlan.isClear()) {
//				ChargePlan plan = new ChargePlan();
//				try {
//					BeanUtils.copyProperties(plan, chargePlan);
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				plan.setId(0);
//				plan.setRegister(user.getUname());
//				plan.setContractId(contract.getId());
//				chargeService.addChargePlan(plan);
//			}
//		}
//
//		String rentGrowEveryMonth = request.getParameter("rentEachMonth");
//		BatchPlan batchPlan = new BatchPlan();
//		batchPlan.setComment("转包上浮");
//		batchPlan.setContractIdList(Arrays.asList(contract.getId()));
//		batchPlan.setStartTime(contract.getContractBeginDate());
//		batchPlan.setEndTime(contract.getContractEndDate());
//		batchPlan.setFee(new BigDecimal(Double.parseDouble(rentGrowEveryMonth)));
//		batchPlan.setFeeType("plan_add_other");
//		batchPlan.setInTime(new Date());
//		batchPlan.setRegister(user.getUname());
//
//		flag &= chargeService.addBatchPlan(batchPlan, false);
//
//		if(!flag){
//			contractService.changeState(contract,3);
//			return ERROR;
//		}
//
//		return SUCCESS;
//	}

	public String contractIdUpdate(){
		Contract c  = (Contract) ObjectAccess.getObject("com.dz.module.contract.Contract", contract.getId());
		c.setContractId(contract.getContractId());
		contractService.contractWrite(c);
		return SUCCESS;
	}
	
	public String contractAbandon(){
		Contract c = contractService.selectById(contract.getId());
	
		c.setAbandonedTime(contract.getAbandonedTime());
		c.setAbandonedUser(contract.getAbandonedUser());
		c.setAbandonedTimeControl(contract.getAbandonedTimeControl());
		c.setState((short) 1);
		
		Vehicle vehicle = new Vehicle();
		vehicle.setCarframeNum(contract.getCarframeNum());
			
		vehicle = vehicleService.selectById(vehicle);
		vehicle.setState(0);
		vehicle.setDriverId("");
		
		vehicleService.updateVehicle(vehicle);
		
		if (contractService.contractAbandon(c)) {
			return "abandoned";
		} else {
			return ERROR;
		}
	}
	
	public String contractSearchAllAvilable(){
		jsonObject = contractService.contractSearchAllAvilable();
		return "jsonObject";
	}
	
	public String contractSearch(){		
		int currentPage = 0;
        String currentPagestr = request.getParameter("currentPage");
        if(currentPagestr == null || "".equals(currentPagestr)){
        	currentPage = 1;
        }else{
        	currentPage=Integer.parseInt(currentPagestr);
        }
        
        Short[] states={};
        String[] ss = request.getParameterValues("states");
        if(ss.length==1){
        	ss = ss[0].split(",");
        }
        if(ss!=null){
        	@SuppressWarnings("unchecked")
			List<Short> statelist = (List<Short>) CollectionUtils.collect(Arrays.asList(ss), new Transformer(){

				@Override
				public Object transform(Object input) {
					String str = (String)input;
					return Short.parseShort(str.trim());
				}
        		
        	});
        	states = new Short[statelist.size()];
        	states = statelist.toArray(states);
        }
        
        Session session = HibernateSessionFactory.getSession();
        
        String hql = "from Contract where state in (:states) ";
        
        if (!StringUtils.isEmpty(dept)&&!dept.startsWith("all")) {
			hql+= String.format("and branchFirm='%s' ", dept);
		}
        
        if (!StringUtils.isEmpty(idNum)) {
			hql+= String.format("and idNum like '%%%s%%' ", idNum);
		}
        
        if (!StringUtils.isEmpty(carNum)) {
			hql+= String.format("and carNum like '%%%s%%' ", carNum);
		}
        
        if (beginDateStart!=null) {
			hql+= String.format("and contractBeginDate >= :%s ", "beginDateStart");
		}
        
        if (beginDateEnd!=null) {
			hql+= String.format("and contractBeginDate <= :%s ", "beginDateEnd");
		}
        
        if (endDateStart!=null) {
			hql+= String.format("and contractEndDate >= :%s ", "endDateStart");
		}
        
        if (endDateEnd!=null) {
			hql+= String.format("and contractEndDate <= :%s ", "endDateEnd");
		}
        
        hql += " order by "+order;
        
        if (BooleanUtils.isFalse(rank)){
        	hql += " desc ";
        }
        
        Query query = session.createQuery(hql);
        Query query2 = session.createQuery("select count(*) "+hql);
        
        query.setParameterList("states", states);
        query2.setParameterList("states", states);
        
        if (beginDateStart!=null) {
        	query.setDate("beginDateStart", beginDateStart);
        	query2.setDate("beginDateStart", beginDateStart);
		}
        
        if (beginDateEnd!=null) {
        	query.setDate("beginDateEnd", beginDateEnd);
        	query2.setDate("beginDateEnd", beginDateEnd);
		}
        
        if (endDateStart!=null) {
        	query.setDate("endDateStart", endDateStart);
        	query2.setDate("endDateStart", endDateStart);
		}
        
        if (endDateEnd!=null) {
        	query.setDate("endDateEnd", endDateEnd);
        	query2.setDate("endDateEnd", endDateEnd);
		}
        
        long count = (long)query2.uniqueResult();
        
        Page page = PageUtil.createPage(15, (int)count, currentPage);
        
        query.setFirstResult(page.getBeginIndex());
		query.setMaxResults(page.getEveryPage());
        
        List<Contract> l = query.list();
        HibernateSessionFactory.closeSession();
        
		request.setAttribute("list", l);
		//request.setAttribute("currentPage", currentPage);
		request.setAttribute("page", page);
		return SUCCESS;
	}
	
	private Date beginDateStart,beginDateEnd,endDateStart,endDateEnd;
	private String dept,carNum,idNum,order;
	private boolean rank;
	
	public Date getBeginDateStart() {
		return beginDateStart;
	}

	public void setBeginDateStart(Date beginDateStart) {
		this.beginDateStart = beginDateStart;
	}

	public Date getBeginDateEnd() {
		return beginDateEnd;
	}

	public void setBeginDateEnd(Date beginDateEnd) {
		this.beginDateEnd = beginDateEnd;
	}

	public Date getEndDateStart() {
		return endDateStart;
	}

	public void setEndDateStart(Date endDateStart) {
		this.endDateStart = endDateStart;
	}

	public Date getEndDateEnd() {
		return endDateEnd;
	}

	public void setEndDateEnd(Date endDateEnd) {
		this.endDateEnd = endDateEnd;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean isRank() {
		return rank;
	}

	public void setRank(boolean rank) {
		this.rank = rank;
	}

	public void contractValidCount() throws IOException{
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		Short[] states={0,-1};
		int total = contractService.selectAllByStatesCount(null, null, null, null, null, states);
		
		out.print(total);
		
		out.flush();
		out.close();
	}
	
	private int addType;
	
	public String contractSelectById(){
		Contract vm  = (Contract) ObjectAccess.getObject("com.dz.module.contract.Contract", contract.getId());
		this.setContract(vm);
		this.driver = (Driver) ObjectAccess.getObject("com.dz.module.driver.Driver", contract.getIdNum());
		
		if(BooleanUtils.isTrue(contract.getGeneByImport())){
			//自动导入的合同
			addType=1;
		}else if(contract.getContractFrom()==null){
			//新车开业
			addType=2;
		}else{
			//车辆转包
			addType=3;
//			List<ChargePlan> chargePlans = ObjectAccess.query(ChargePlan.class, " contractId='"+contract.getContractFrom()+"'");
//			Contract beforeContract = (Contract)ObjectAccess.getObject("com.dz.module.contract.Contract",contract.getContractFrom());
			
//			ChargePlan notclearedMinPlan = Collections.<ChargePlan>min(chargePlans, new Comparator<ChargePlan>(){
//				@Override
//				public int compare(ChargePlan plan1, ChargePlan plan2) {
//					if (!plan1.isClear()) {
//						if (plan2.isClear()) {
//							return -1;
//						}
//						return plan1.getTime().before(plan2.getTime())?-1:1;
//					}
//					if (!plan2.isClear()) {
//						return 1;
//					}
//					return plan1.getTime().before(plan2.getTime())?-1:1;
//				}
//				
//			});
//			
//			DateTime dateTime = new DateTime(notclearedMinPlan.getTime());
//			
//			for (ChargePlan chargePlan : chargePlans) {
//				if(!chargePlan.isClear()){
//					
//					
//				}
//			}
		}
		
		return SUCCESS;
	}
	
	public void selectByCarId() throws IOException {
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		Contract c = contractService.selectByCarId(contract.getCarframeNum());
		
		JSONObject json = JSONObject.fromObject(c);
		
		out.print(json.toString());
		
		out.flush();
		out.close();
	}

	public Object getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(Object jsonObject) {
		this.jsonObject = jsonObject;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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

	public int getAddType() {
		return addType;
	}

	public void setAddType(int addType) {
		this.addType = addType;
	}
	
	
}
