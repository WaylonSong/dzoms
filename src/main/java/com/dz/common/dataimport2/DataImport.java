package com.dz.common.dataimport2;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.dz.common.dataimport2.entity.*;
import com.dz.common.factory.HibernateSessionFactory;
import com.dz.common.other.ObjectAccess;
import com.dz.module.contract.*;
import com.dz.module.driver.*;
import com.dz.module.vehicle.*;

public class DataImport {
	public static void main(String[] args) {
		init();
		
		/**
		 * 第1步 ： 导入车辆型号信息
		 */
		//importVehicleMode();
		
		/**
		 * 第2步：导入车辆基本信息
		 */
		//importVehicle();
		
		/**
		 * 第3步：导入购置税信息
		 */
		//importVehicleTax();
		
		/**
		 * 第4步：导入发票信息
		 */
		//importVehicleInvoice();
		
		/**
		 * 第5步：导入保险信息
		 */
		//importInsurance();
		
		/**
		 * 第6步：导入车辆牌照信息
		 */
		//importVehicleLicense();
		
		/**
		 * 第7步：导入驾驶员基本信息
		 */
		//importDriver();
		/**
		 * 承租人信息补充登记
		 */
		//checkContractBeforeMakeApply();
		
		/**
		 * 第8步：自动申请开业
		 */
		//makeApply();
		
		/**
		 * 第9步：进行人车绑定
		 */
		//bindDriverAndVehicle();
		
		/**
		 * 第10步：导入计价器、营运证信息
		 */
		//importVehicleService();
		
		/**
		 * 第11步：导入经营权信息
		 */
		//importVehicleTrade();
		
		/**
		 * 第12步：自动进行剩余的所有审批
		 */
		//doAllApply();
		
		/**
		 * 第13步：自动导入银行卡信息
		 */
//		importBankCard();
		
		/**
		 * 第14步：自动上车
		 */
		driverApply();
	}

	public static SessionFactory sessionFactory;
	
	private static DriverDao driverDao;
	private static VehicleDao vehicleDao;
	private static VehicleApprovalService vehicleApprovalService;
	private static ContractService contractService;
	private static ApplicationContext ctx;
	
	public static void init(){
		Configuration configurate = new Configuration().configure(DataImport.class.getResource ("hibernate.cfg.xml"));
		sessionFactory = configurate.buildSessionFactory();
		
		ctx = new ClassPathXmlApplicationContext("com/dz/common/dataimport2/applicationContext.xml"); 
		driverDao = ctx.getBean(DriverDao.class);
		vehicleDao = ctx.getBean(VehicleDao.class);
		contractService = ctx.getBean(ContractService.class);
		vehicleApprovalService = ctx.getBean(VehicleApprovalService.class);
	}
	
	
	public static void importVehicleMode(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 车辆型号详细表  ");
		List<车辆型号详细表> list = query.list();
		session.close();
		
		for (车辆型号详细表 r : list) {
			VehicleMode vm = new VehicleMode();
			vm.setCarMode(r.get车辆型号());
			vm.setDisplacement(r.get排量());
			vm.setPower(r.get功率());
			vm.setEmission(r.get排放标准());
			vm.setSizeLong(r.get外廓尺寸数据1());
			vm.setSizeWide(r.get外廓尺寸数据2());
			vm.setSizeHigh(r.get外廓尺寸数据3());
			vm.setTireNum(r.get轮胎数());
			vm.setTire(r.get轮胎规格());
			vm.setTreadFront(r.get轮距前());
			vm.setTreadBack(r.get轮距后());
			vm.setWheelBase(r.get轴距());
			vm.setBaseNum(r.get轴数());
			vm.setCorneringAbility(r.get转向形式());
			vm.setWholeWeight(r.get总质量());
			vm.setAllWeight(r.get整备质量());
			vm.setPersonNum(r.get额定载客());
			vm.setFastest(r.get最高车速());
			vm.setEngineMode(r.get发动机型号());
			//vm.set发动机号(r.get发动机号());
			vm.setBrand(r.get厂牌车型());
			vm.setCompany(r.get车辆制造企业名称());
			//vm.set车辆名称(r.get车辆名称());
			vm.setNorWeight(r.get核定载质量());
			vm.setLicenseDate(r.get发证日期());
			vm.setColor(r.get车身颜色());
			vm.setFuel(r.get燃油类型());
			vm.setAverageYearDistance(0.0+r.get年平均行驶里程());
		//	vm.set新车购置价(r.get新车购置价());
			vm.setDistinct(r.get行驶区域());
			vm.setInvoiceAmount(r.get发票金额());
			vm.setTaxAmount(r.get购置税());
			
			vm.setUseway("出租客运");
			ObjectAccess.saveOrUpdate(vm);
		}
	}
	
	public static void importVehicle(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from RawVehicle ");
		List<RawVehicle> list = query.list();
		session.close();
		
		session =  HibernateSessionFactory.getSession();

		for (RawVehicle r : list) {
			//System.out.println(r);
			
			Vehicle vehicle = new Vehicle();
			
			vehicle.setState(0);
			
			vehicle.setEngineNum(r.get发动机号());
			vehicle.setCarframeNum(r.get车架号());
			vehicle.setInDate(r.get购车时间());
			vehicle.setCertifyNum(r.get合格证号());
			
			switch(r.get分公司归属()){
	    	case "大众一公司":
	    		vehicle.setDept("一部");
	    		break;
	    	case "大众二公司":
	    		vehicle.setDept("二部");
	    		break;
	    	case "大众三公司":
	    		vehicle.setDept("三部");
	    		break;
	    	}
			
			vehicle.setCarMode(r.get车辆型号());
			vehicle.setPd(r.get车辆制造日期());
			
			//TODO 提车日该设为多少？ 当前设为 导入日期
			vehicle.setGetCarDate(new Date());
			
			
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 session.saveOrUpdate(vehicle);
				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("车辆无法导入："+r.toString());
				e.printStackTrace();
			}
		}
		HibernateSessionFactory.closeSession();
	}
	
	public static void importVehicleTax(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 购置税 ");
		List<购置税> list = query.list();
		session.close();
		
		

		for (购置税 r : list) {
			//System.out.println(r);
			String cno = r.get车架号();
			
			Vehicle v = ObjectAccess.getObject(Vehicle.class, cno);
		
			if(v==null)
				continue;
			
			v.setTaxNumber(r.get购置税号());
			v.setTaxDate(r.get核发日期());
			v.setTaxMoney(r.get购置税应收金额());
			v.setTaxFrom(r.get发证机关());
			v.setTaxRegister(1);
			v.setTaxRegistTime(r.get登记时间());
			
			session =  HibernateSessionFactory.getSession();
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 session.saveOrUpdate(v);
				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("购置税信息无法导入："+r.toString());
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
		
	}
	
	public static void importVehicleInvoice(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 发票表大众 ");
		List<发票表大众> list = query.list();
		session.close();

		for (发票表大众 r : list) {
			//System.out.println(r);
			String cno = r.get车架号();
			
			Vehicle v = ObjectAccess.getObject(Vehicle.class, cno);
			
			if(v==null)
				continue;
			
			v.setInvoiceNumber(r.get发票号());
			v.setInvoiceDate(r.get开票日期());
			v.setInvoiceMoney(r.get价税合计());
			v.setPurseFrom(r.get销货单位名称());
			v.setInvoiceRegister(1);
			v.setInvoiceRegistTime(r.get登记时间());
			
			session =  HibernateSessionFactory.getSession();
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 session.saveOrUpdate(v);
				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("发票信息无法导入："+r.toString());
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
		
	}
	
	public static void importInsurance(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 车辆保险大众 ");
		List<车辆保险大众> list = query.list();
		session.close();

		for (车辆保险大众 r : list) {
			session =  HibernateSessionFactory.getSession();
			Query query2 = session.createQuery("select count(*) from Vehicle where carframeNum=:cno");
			query2.setString("cno", r.get车架号());
			long count = (Long)query2.uniqueResult();
			if(count == 0)
				continue;
			
			Insurance i = new Insurance();
			
			i.setInsuranceClass(r.get保险类别());
			
			if(i.getInsuranceClass().startsWith("商")){
				i.setInsuranceNum(r.get商业保单号());
			}else{
				i.setInsuranceNum(r.get强险单号());
			}
			
			i.setCarframeNum(r.get车架号());
			i.setBeginDate(r.get起始时间());
			i.setEndDate(r.get终止时间());
			i.setSignDate(r.get签单日期());
			i.setInsuranceMoney(r.get保险费合计小写());
			i.setInsuranceCompany(r.get保险人公司名称());
			
			i.setDriverId("哈尔滨大众交通运输有限责任公司");
			i.setPhone("86661212");
			i.setEnterpriseID("12759066-9");
			i.setAddress("哈尔滨 南岗区征仪路311");
			
			i.setRegistTime(r.get登记日期());
			i.setRegister(1);
			
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 session.saveOrUpdate(i);
				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("保险信息无法导入："+i.toString());
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
	}
	
	public static void importVehicleLicense(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 车辆牌照 ");
		List<车辆牌照> list = query.list();
		session.close();

		for (车辆牌照 r : list) {
			//System.out.println(r);
			String cno = r.get车架号();
			
			Vehicle v = ObjectAccess.getObject(Vehicle.class, cno);
			
			if(v==null)
				continue;
			
			v.setLicenseNum(r.get新车牌号());
			
			//TODO 行驶证注册日期 无法获取 暂设为 导入日期
			v.setLicenseNumRegDate(new Date());
			
			if(r.get牌照类别().startsWith("拍卖")){
				v.setIsNewLicense(true);
				v.setLicensePurseNum(r.get拍卖号());
			}else{
				v.setIsNewLicense(false);
				v.setLicensePurseNum(r.get旧车牌号());
			}

			v.setLicenseRegister(1);
			v.setLicenseRegistTime(r.get登记时间());
			v.setLicenseRegNum(r.get登记证书号());
			
			session =  HibernateSessionFactory.getSession();
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 session.saveOrUpdate(v);
				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("车辆牌照信息无法导入："+r.toString());
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
	}
	
	//TODO 驾驶员登记表 所含数据不全  需额外导出 驾驶员登记表_大众 一表的数据      缺少   年龄、婚姻状况、民族   及其它的一些非必填项
	public static void importDriver(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 驾驶员登记表 ");
		List<驾驶员登记表> list = query.list();
		session.close();
		
		session =  HibernateSessionFactory.getSession();

		for (驾驶员登记表 r : list) {
			//System.out.println(r);
			
			Driver driver = new Driver();
			
			if(r.get承包人().equals(r.get驾驶员())){
				driver.setApplyMatter("承包新车");
			}else{
				driver.setApplyMatter("驾驶员");
			}
			driver.setApplyTime(new Date());
			
			driver.setName(r.get驾驶员());
			
			if("女".equals(r.get驾驶员性别())){
				driver.setSex(false);
			}else{
				driver.setSex(true);
			}
			
			//driver.setDriverClass(r.get驾驶员属性());

			driver.setPhoneNum1(r.get驾驶员手机());

			driver.setIdNum(r.get身份证号());
			driver.setAccountLocation(r.get身份证所在地());
			//driver.set身份证归属区域(r.get身份证归属区域());
			driver.setAddress(r.get现住址());

			driver.setRestTime(r.get驾驶员作息时间());
			driver.setDrivingLicenseNum(r.get驾驶证号());
			driver.setDrivingLicenseDate(r.get初次领驾驶证日期());
			
			if(StringUtils.isNotEmpty(r.get驾驶证类型())){
				driver.setDrivingLicenseType(r.get驾驶证类型());
			}else{
				driver.setDrivingLicenseType("C1");
			}
			driver.setQualificationNum(r.get资格证号());
			driver.setQualificationValidDate(r.get资格证有效日期());
			driver.setQualificationDate(r.get资格证领证日期());
			//driver.set分公司归属(r.get分公司归属());
			driver.setFingerprintNum(Integer.parseInt(r.get指纹编号()));
			driver.setEmployeeNum(r.get员工号());
			
			
			if(StringUtils.isNotEmpty(r.get星级())){
				driver.setStar(r.get星级().length());
			}else{
				driver.setStar(1);
			}
			
			driver.setStatus(3);
			driver.setScore(80);
			
			//TODO 缺 服务保证金 一项，暂默认为 500
			driver.setFuwubaozhengjin(new BigDecimal(500));
			
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 session.saveOrUpdate(driver);
				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("驾驶员信息无法导入："+r.toString());
				e.printStackTrace();
			}
		}
		HibernateSessionFactory.closeSession();
	}
	
	/**
	 * 由于存在 承租人 不在车的情况，需将不在车的承租人信息从总表中导入
	 */
	public static void checkContractBeforeMakeApply(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 合同表  where 终止日期 >=:now and 实际终止日期 is null");
		query.setDate("now", new Date());
		List<合同表> list = query.list();
		session.close();
		
		for (合同表 cr : list) {
			
			String idNum = cr.get身份证号();
			Driver d = ObjectAccess.getObject(Driver.class, idNum);
			if(d==null){
				//System.err.println("该合同的承租人不在驾驶员档案中。\n"+r);
				//continue;
				session = sessionFactory.openSession();
				query = session.createQuery("from 驾驶员登记表大众  where 身份证号 = :idNum");
				query.setString("idNum", idNum);
				query.setMaxResults(1);
				驾驶员登记表大众 r = (驾驶员登记表大众) query.uniqueResult();
				session.close();
				
				if(r==null){
					System.err.println("该合同的承租人不在驾驶员档案中。\n"+cr);
					continue;
				}
				
				Driver driver= new Driver();
				
				
				driver.setApplyMatter("承包新车");
				driver.setApplyTime(new Date());
				driver.setName(r.get姓名());
				
				if("女".equals(r.get性别())){
					driver.setSex(false);
				}else{
					driver.setSex(true);
				}
				
				//driver.setDriverClass(r.get驾驶员属性());

				driver.setPhoneNum1(r.get联系电话1());

				driver.setIdNum(r.get身份证号());
				driver.setAccountLocation(r.get户口所在地());
				//driver.set身份证归属区域(r.get身份证归属区域());
				driver.setAddress(r.get家庭现住址());

				driver.setRestTime(r.get驾驶员作息时间());
				driver.setDrivingLicenseNum(r.get驾驶证号());
				driver.setDrivingLicenseDate(r.get驾驶证初领日期());
				
				if(StringUtils.isNotEmpty(r.get驾驶证类型())){
					driver.setDrivingLicenseType(r.get驾驶证类型());
				}else{
					driver.setDrivingLicenseType("C1");
				}
				driver.setQualificationNum(r.get资格证号());
				driver.setQualificationValidDate(r.get资格证有效日期());
				driver.setQualificationDate(r.get资格证初领日期());
				//driver.set分公司归属(r.get分公司归属());
				driver.setFingerprintNum(Integer.parseInt(r.get指纹编号()));
				driver.setEmployeeNum(r.get员工号());
				
				
				if(StringUtils.isNotEmpty(r.get星级())){
					driver.setStar(r.get星级().length());
				}else{
					driver.setStar(1);
				}
				
				driver.setStatus(3);
				driver.setScore(80);
				
				//TODO 缺 服务保证金 一项，暂默认为 500
				driver.setFuwubaozhengjin(new BigDecimal(500));
				
				session =  HibernateSessionFactory.getSession();
				Transaction tx = null;
				try{
					 tx = session.beginTransaction();
					 session.saveOrUpdate(driver);
					 tx.commit();
				}catch(HibernateException e){
					if(tx!=null)
						tx.rollback();
					System.err.println("驾驶员信息无法导入："+r.toString());
					e.printStackTrace();
				}finally{
					HibernateSessionFactory.closeSession();
				}
			}
			
		}
	}
	
	
	/**
	 * 自动申请开业
	 */
	public static void makeApply(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 合同表  where 终止日期 >=:now and 实际终止日期 is null");
		query.setDate("now", new Date());
		List<合同表> list = query.list();
		session.close();
		
		for (合同表 r : list) {
			
			String idNum = r.get身份证号();
			Driver d = ObjectAccess.getObject(Driver.class, idNum);
			if(d==null){
				System.err.println("该合同的承租人不在驾驶员档案中。\n"+r);
				continue;
			}
			
			//System.out.println(r);
			Contract contract = new Contract();
			VehicleApproval approval = new VehicleApproval();
			
			approval.setCheckType( (short) 0);//开业审批
			approval.setCartype(true);//新车
			//TODO 该项交由绑定车辆时赋值
			//approval.setFueltype(fueltype);
			//TODO　表中无此值  暂设为0
			approval.setOnetimeAfterpay(0l);//首年保险
			approval.setBranchManagerRemark("自动生成的分部经理审批");
			
			contract.setIdNum(r.get身份证号());
			switch(r.get分公司归属()){
	    	case "大众一公司":
	    		contract.setBranchFirm("一部");
	    		break;
	    	case "大众二公司":
	    		contract.setBranchFirm("二部");
	    		break;
	    	case "大众三公司":
	    		contract.setBranchFirm("三部");
	    		break;
	    	}
			contract.setBusinessForm(r.get承包形式().substring(0, 2)); //'承包','挂靠'
			
			contract.setContractId(r.get电子档案号());
			contract.setPenalty(0.0+r.get违约金());
			contract.setRentFirst(r.get预付租金());
			contract.setContractType(r.get合同种类());
			contract.setDeposit(r.get定金());
			contract.setContractBeginDate(r.get起始日期());
			contract.setContractEndDate(r.get终止日期());
			
			approval.setAscription("个人".equals(r.get营运手续归属())); //#{'true':'个人','false':'公司'}
			contract.setRent(r.get月租金());
			contract.setDiscountDays(0);
			
			contract.setDriverRequest("本人申请加入大众公司，按公司的管理规定营运。");
			contract.setGeneByImport(true);
			
			
			boolean flag = vehicleApprovalService.addVehicleApproval(approval, contract);
			if(!flag)
				System.err.println("无法自动申请开业："+r.toString());
		}
	}
	
	/**
	 * 进行人车绑定
	 */
	public static void bindDriverAndVehicle(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 合同表  where 终止日期 >=:now and 实际终止日期 is null");
		query.setDate("now", new Date());
		List<合同表> list = query.list();
		session.close();
		
		for (合同表 r : list) {
			
			String idNum = r.get身份证号();
			Driver d = ObjectAccess.getObject(Driver.class, idNum);
			if(d==null){
				//System.err.println("该合同的承租人不在驾驶员档案中。\n"+r);
				continue;
			}
			
			String cno = r.get车架号();
			Vehicle v = ObjectAccess.getObject(Vehicle.class, cno);
			if(v==null){
				System.err.println("该合同的车架号不在车辆档案中。\n"+r);
				continue;
			}
			
			v.setDriverId(idNum);
			
			Contract c = ObjectAccess.execute("select c from Contract c where c.idNum='"+v.getDriverId()+
					"' and c.state=2 ");
			if(c==null){
				System.err.println("该车的预签合同未生成。\n"+r);
				continue;
			}
			
			c.setCarframeNum(v.getCarframeNum());
			c.setCarNum(v.getLicenseNum());
			
			session = HibernateSessionFactory.getSession();
			Transaction tx = null;
			
			try{
				tx = session.beginTransaction();
				
				session.saveOrUpdate(c);
				session.saveOrUpdate(v);
				
				VehicleApproval approval = (VehicleApproval)session.createQuery("from VehicleApproval c where c.contractId="+c.getId()+
						" and c.checkType=0 ").setMaxResults(1).uniqueResult();
				
				if(v.getOperateCardTime()!=null){
					approval.setOperateCardDate(v.getOperateCardTime());
				}
				
				if(v.getGetCarDate()!=null){
					approval.setGetCarDate(v.getGetCarDate());
				}
				
				if(v.getLicenseNumRegDate()!=null){
					approval.setLicenseRegisterDate(v.getLicenseNumRegDate());
					approval.setOperateApplyDate(new Date());
				}
				
				VehicleMode vmode = (VehicleMode)session.createQuery("from VehicleMode c where c.carMode=:mode")
						.setString("mode", v.getCarMode()).setMaxResults(1).uniqueResult();
				if(vmode!=null)
					approval.setFueltype(vmode.getFuel());
				
				session.saveOrUpdate(approval);
				
				tx.commit();
			}catch(HibernateException e){
				tx.rollback();
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}

		}
	}
	
	//计价器、营运证
	public static void importVehicleService(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 车辆营运 ");
		List<车辆营运> list = query.list();
		session.close();

		for (车辆营运 r : list) {
			//System.out.println(r);
			String cno = r.get车架号();
			
			Vehicle v = ObjectAccess.getObject(Vehicle.class, cno);
			
			if(v==null)
				continue;
			
			v.setOperateCardRegistDate(r.get登记时间());
			v.setOperateCardRegister(1);
			v.setTwiceSupplyDate(r.get二级维护开始日期());
			v.setNextSupplyDate(r.get二级维护结束日期());
			v.setMoneyCountor(r.get计价器号());
			v.setMoneyCountorNextDate(r.get计价器检测开始日期());
			v.setMoneyCountorTime(r.get计价器检测结束日期());
			
			v.setOperateCard(r.get营运证号());
			v.setServiceRightRegistDate(r.get登记时间());
			v.setServiceRightRegister(1);
			v.setOperateCardTime(r.get营运证发放日期());
			
			Contract c = ObjectAccess.execute("select c from Contract c where c.idNum='"+v.getDriverId()+
					"' and c.carframeNum='"+v.getCarframeNum()+"' ");
			
			VehicleApproval approval = null;
			if(c!=null){
				approval = ObjectAccess.execute("from VehicleApproval c where c.contractId="+c.getId()+
						" and c.checkType=0 ");
				approval.setOperateCardDate(v.getOperateCardTime());
			}
			
			session =  HibernateSessionFactory.getSession();
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 
				 session.saveOrUpdate(v);
				 if(approval!=null) session.saveOrUpdate(approval);

				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("计价器、营运证 信息无法导入："+r.toString());
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
	}
	
	//经营权证
	/**
	 * 此处导入经营权证信息
	 * 原导出内容不全   过滤 结束日期 >=now 时 仅有不足200个经营权证有效 现先保留已过期的经营权证 
	 */
	public static void importVehicleTrade(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 车辆经营权 ");
		List<车辆经营权> list = query.list();
		session.close();

		for (车辆经营权 r : list) {
			//System.out.println(r);
			String cno = r.get车架号();
			
			Vehicle v = ObjectAccess.getObject(Vehicle.class, cno);
			
			if(v==null)
				continue;
			
			BusinessLicense b = new BusinessLicense();
			b.setLicenseNum(r.get经营权证号());
			b.setBeginDate(r.get起始日期());
			b.setEndDate(r.get终止日期());
			b.setRegistDate(r.get登记时间());
			b.setRegister(1);
			
			session =  HibernateSessionFactory.getSession();
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 
				 session.saveOrUpdate(b);
				 v.setBusinessLicenseComment(r.get备注());
				 v.setBusinessLicenseId(b.getLicenseNum());
				 session.saveOrUpdate(v);

				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("经营权 信息无法导入："+r.toString());
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
	}
	
	//自动进行剩余的所有审批
	public static void doAllApply(){
		
		
	}
	
	/**
	 * 自动进行证照办领
	 */
	public static void driverApply(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 驾驶员登记表 ");
		List<驾驶员登记表> list = query.list();
		session.close();
		
		for (驾驶员登记表  r : list) {
			String cno = r.get车号();
			
			Vehicle v = ObjectAccess.execute("from Vehicle where licenseNum='"+cno+"'");
			
			if(v==null){
				System.err.println("车辆不存在："+r);
				continue;
			}
			
			String idNum = r.get身份证号();
			Driver d = ObjectAccess.getObject(Driver.class, idNum);
			if(d==null){
				System.err.println("驾驶员不存在:"+r);
				continue;
			}
		
			d.setRestTime(r.get驾驶员作息时间());
			d.setBusinessApplyCarframeNum(v.getCarframeNum());
			d.setBusinessApplyDriverClass(r.get驾驶员属性());
			d.setBusinessApplyTime(new Date());
			d.setBusinessApplyRegistrant(1);
			d.setBusinessApplyRegistTime(new Date());
			
//			d.setBusinessApplyState(1);
			
			d.setBusinessReciveTime(new Date());
			d.setBusinessReciveRegistrant(1);
			d.setBusinessReciveRegistTime(new Date());
			d.setIsInCar(true);
			d.setBusinessApplyState(2);
			d.setDriverClass(d.getBusinessApplyDriverClass());
			d.setCarframeNum(v.getCarframeNum());
			d.setDept(v.getDept());
			
//			ObjectAccess.saveOrUpdate(d);
			//driverService.driverUpdate(d,families);
			
			if("主驾".equals(d.getDriverClass())){
			v.setFirstDriver(d.getIdNum());
			if(d.getIdNum().equals(v.getSecondDriver())){
				v.setSecondDriver(null);
			}
			if(d.getIdNum().equals(v.getThirdDriver())){
				v.setThirdDriver(null);
			}
			if(d.getIdNum().equals(v.getTempDriver())){
				v.setTempDriver(null);
			}
		}else if("副驾".equals(d.getDriverClass())){
			v.setSecondDriver(d.getIdNum());
			if(d.getIdNum().equals(v.getFirstDriver())){
				v.setFirstDriver(null);
			}
			if(d.getIdNum().equals(v.getThirdDriver())){
				v.setThirdDriver(null);
			}
			if(d.getIdNum().equals(v.getTempDriver())){
				v.setTempDriver(null);
			}
		}else if("三驾".equals(d.getDriverClass())){
			v.setThirdDriver(d.getIdNum());
			if(d.getIdNum().equals(v.getFirstDriver())){
				v.setFirstDriver(null);
			}
			if(d.getIdNum().equals(v.getSecondDriver())){
				v.setSecondDriver(null);
			}
			if(d.getIdNum().equals(v.getTempDriver())){
				v.setTempDriver(null);
			}
		}else if("临驾".equals(d.getDriverClass())){
			v.setTempDriver(d.getIdNum());
		}
			
			Driverincar record = new Driverincar(d.getBusinessApplyCarframeNum(),d.getIdNum(),"证照申请",d.getBusinessApplyTime());
			record.setFinished(true);
			record.setDriverClass(d.getBusinessApplyDriverClass());
			
			session =  HibernateSessionFactory.getSession();
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 
				 session.saveOrUpdate(record);
				 session.saveOrUpdate(d);
				 session.saveOrUpdate(v);

				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("无法导入上下车记录："+r.toString());
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
			
		}
	}
	
	private static void importBankCard() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from 银行卡 ");
		List<银行卡 > list = query.list();
		session.close();


		for (银行卡  r : list) {
			//System.out.println(r);
			String cno = r.get车号();
			
			Vehicle v = ObjectAccess.execute("from Vehicle where licenseNum='"+cno+"'");
			if(v==null)
				continue;
			
			Contract c = ObjectAccess.execute("from Contract where carframeNum='"+v.getCarframeNum()+"'");
			if(c==null)
				continue;
			Driver d = ObjectAccess.getObject(Driver.class, c.getIdNum());
			if(d==null)
				continue;
			
			BankCard b = new BankCard();
			
			b.setCarNum(v.getCarframeNum());
			b.setCardNumber(r.get卡号());
			b.setCardClass("哈尔滨银行");
			b.setIdNumber(c.getIdNum());
			b.setIsDefaultPay(false);
			b.setIsDefaultRecive(false);
			b.setOperator(1);
			b.setOpeTime(new Date());
			
			if(!StringUtils.equals(r.get姓名(), d.getName())){
				System.out.println("姓名不一致:\n"+r+" "+d.getName());
			}

			session =  HibernateSessionFactory.getSession();
			Transaction tx = null;
			try{
				 tx = session.beginTransaction();
				 session.saveOrUpdate(b);
				 tx.commit();
			}catch(HibernateException e){
				if(tx!=null)
					tx.rollback();
				System.err.println("银行卡信息无法导入："+r.toString());
				e.printStackTrace();
			}finally{
				HibernateSessionFactory.closeSession();
			}
		}
	}
	
}
