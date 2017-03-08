package com.dz.module.charge;

import com.dz.common.global.DateUtil;
import com.dz.common.global.Page;
import com.dz.common.global.TimePass;
import com.dz.module.contract.BankCard;
import com.dz.module.contract.BankCardDao;
import com.dz.module.contract.Contract;
import com.dz.module.contract.ContractDao;
import com.dz.module.driver.Driver;
import com.dz.module.driver.DriverDao;
import com.dz.module.vehicle.Vehicle;
import com.dz.module.vehicle.VehicleDao;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author doggy
 *         Created on 15-11-12.
 */
@Service
public class ChargeService {
    @Autowired
    private ChargeDao chargeDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private DriverDao driverDao;
    @Autowired
    private BankCardDao bankCardDao;
    @Autowired
    private ClearTimeDao clearTimeDao;
    @Autowired
    private BankFileDao bankFileDao;
    @Autowired
    private BankRecordTmpDao bankRecordTmpDao;

    /*********************************************************************************************************************/
    /**************************************Add the method that need page limit here.**************************************/
    /*********************************************************************************************************************/
    /**
     * added 分页.
     * 多车单月的计划详细(合同+保险+油补+变更)
     * @param dept 搜索的部门
     * @param date
     * @return
     */
    public List<PlanDetail> planDetailMultiplyCar(String dept,Date date,Page page){
        List<PlanDetail> table = new ArrayList<>();
        List<Contract> contractList = contractDao.contractSearchAllAvilable(date,dept,page);
        //TODO:I don't know if it is necessary to filter here
        fileterContract(contractList);
        for(Contract c:contractList){
            int id = c.getId();
            List<ChargePlan> plans = new ArrayList<>();
            plans = chargeDao.getAllRecords(id,date);
            PlanDetail cct = new PlanDetail();
            //set header
            cct.setContractId(id);
            cct.setTime(date);
            Driver d = new Driver();
            d.setIdNum(c.getIdNum());
            Driver driver = driverDao.selectById(d.getIdNum());
            if(driver != null){
                cct.setDriverName(driver.getName());
                cct.setDriverId(driver.getIdNum());
                cct.setDept(driver.getDept());
            }
            //TODO:wait to add department
            if("全部".equals(dept)||c.getBranchFirm().equals(dept)){
                Vehicle vehicle = vehicleDao.selectByFrameId(c.getCarframeNum());
                if(vehicle != null) {
                    cct.setCarNumber(vehicle.getLicenseNum());
                    cct.setDept(vehicle.getDept());
                }
                BigDecimal heton_base = calculatePlan(plans,"plan_base_contract");
                BigDecimal heton = calculatePlan(plans,"contract");
                BigDecimal insurance = calculatePlan(plans,"insurance");
                BigDecimal other = calculatePlan(plans,"other");
                cct.setBaoxian(insurance);
                cct.setHeton(heton_base.add(heton));
                cct.setOther(other);
                cct.setTotal(insurance.add(heton).add(heton_base).add(other));
                //set message
                table.add(cct);
            }
        }
        return table;
    }

    /**
     * added 分页.
     * 导出银行文件.
     * @param time 要导出的银行文件.
     * @param dept 要导出的部门.
     * @return 银行记录列表.
     */
    public List<BankRecord> exportBankFile(Date time,String dept,Page page){
        List<BankRecord> records = new ArrayList<>();
        List<Contract> cls = contractDao.contractSearchAllAvilable(time,dept,page);
        System.out.println("size"+cls.size());
        //TODO:I don't know if it is necessary to filter this contract
        fileterContract(cls);
//        if(time.getYear() > now.getYear()||(time.getYear() == now.getYear() && time.getMonth() > now.getMonth())){
//            return records;
//        }
        for(Contract c:cls){
            BankRecord br = new BankRecord();
            String carFrame = c.getCarframeNum();
            String idNum = c.getIdNum();
            Vehicle vehicle = vehicleDao.selectByFrameId(carFrame);
            if(vehicle == null)continue;
            Driver tmp = new Driver();
            tmp.setIdNum(idNum);
            Driver driver = driverDao.selectById(tmp.getIdNum());
            //TODO add department
            if(driver != null && (dept.equals("全部")||dept.equals(c.getBranchFirm()))){
                BankCard bc = bankCardDao.getBankCardForPayByDriverId(idNum,vehicle.getCarframeNum());
                List<BankCard> bankCards = new ArrayList<BankCard>();
                if(bc!=null){
                    bankCards.add(bc);
                }
                Map<String,BankCard> bcs = new HashMap<>();
                //set Data
                br.setLicenseNum(vehicle.getLicenseNum());
                for(BankCard bc1:bankCards){
                    if(bc1.getCardClass().equals("哈尔滨银行")){
                        bcs.put("hrb",bc1);
                    }else{
                        bcs.put("other",bc1);
                    }
                }
                br.setBankCards(bcs);
                br.setDriverName(driver.getName());
                BigDecimal derserve = getUnClearAdd(c.getId(),time);

                BigDecimal lastMonthLeft = getlastMontAccountLeft(c.getId(),time);
                System.out.println(derserve+"|"+lastMonthLeft);
                if(derserve.add(lastMonthLeft).doubleValue() >= 0){
                    br.setMoney(new BigDecimal(0.00));
                }else{
                    br.setMoney(derserve.add(lastMonthLeft).abs());
                }
                records.add(br);
            }
        }
        return records;
    }


    /**
     * added.
     *获得所有欠费的人员名单
     * @param  time 查询的年月
     */
    public List<BankRecord> getUnClearRecord(Date time,String department,Page page){
        List<BankRecord> records = new ArrayList<>();
        List<Contract> cls = contractDao.contractSearchAllAvilable(time,department,page);
        //TODO:I don't know if it is necessary to filter this contract list
        fileterContract(cls);
        for(Contract c:cls){
            BankRecord br = new BankRecord();
            String carFrame = c.getCarframeNum();
            String idNum = c.getIdNum();
            Vehicle vehicle = vehicleDao.selectByFrameId(carFrame);
            if(vehicle == null)continue;
            Driver tmp = new Driver();
            tmp.setIdNum(idNum);
            Driver driver = driverDao.selectById(tmp.getIdNum());
            //TODO add department
            if(driver != null && true ){
                BankCard bc = bankCardDao.getBankCardForPayByDriverId(idNum,vehicle.getCarframeNum());
                List<BankCard> bankCards = new ArrayList<BankCard>();
                if(bc!=null){
                    bankCards.add(bc);
                }
                Map<String,BankCard> bcs = new HashMap<>();
                //set Data
                br.setLicenseNum(vehicle.getLicenseNum());
                for(BankCard bc1:bankCards){
                    if(bc1.getCardClass().equals("哈尔滨银行")){
                        bcs.put("hrb",bc1);
                    }else{
                        bcs.put("other",bc1);
                    }
                }
                br.setBankCards(bcs);
                br.setDriverName(driver.getName());
                BigDecimal derserve = getUnClearAdd(c.getId(),time);
                BigDecimal lastMonthLeft = getlastMontAccountLeft(c.getId(),time);
                if(derserve.add(lastMonthLeft).doubleValue() >= 0){
                    br.setMoney(new BigDecimal(0.00));
                }else{
                    br.setMoney(derserve.add(lastMonthLeft).abs());
                    records.add(br);
                }
            }
        }
        return records;
    }

    /**
     * Page limit added.
     * 获得一个/全部部门 一个月的财务对账表.
     * @param date 要查询的月份
     * @param dept 查询的部门 若为"全部" 则查询所有
     * @param page 分页
     * @return
     */
    public List<CheckChargeTable> getAllCheckChargeTable(Date date, String dept, Page page){
        List<CheckChargeTable> table = new ArrayList<>();
        List<Contract> contractList = contractDao.contractSearchAllAvilable(date,dept,page);
        //TODO:I don't know if it is necessary to filter here
        fileterContract(contractList);
        for(Contract c:contractList){
            int id = c.getId();
            List<ChargePlan> plans = new ArrayList<>();
//            Date currentMonth = clearTimeDao.getCurrent(c.getBranchFirm());
//            if(isYM1BGYM2(date,currentMonth)){
//                while(isYM1BGYM2(date,currentMonth)){
//                    plans.addAll(chargeDao.getAllRecords(id,currentMonth));
//                    currentMonth = getNextMonth(currentMonth);
//                }
//            }else{
            plans = chargeDao.getAllRecords(id,date);
//            }
            CheckChargeTable cct = new CheckChargeTable();
            //set header
            cct.setContractId(id);
            cct.setTime(date);
            Driver d = new Driver();
            d.setIdNum(c.getIdNum());
            Driver driver = driverDao.selectById(d.getIdNum());
            if(driver != null){
                cct.setDriverName(driver.getName());
                cct.setDriverId(driver.getIdNum());
                cct.setDept(driver.getDept());
            }
            //TODO:wait to add department
            if("全部".equals(dept)||c.getBranchFirm().equals(dept)){
                Vehicle vehicle = vehicleDao.selectByFrameId(c.getCarframeNum());
                if(vehicle != null) {
                    cct.setCarNumber(vehicle.getLicenseNum());
                    cct.setDept(vehicle.getDept());
                }
                //set message
                cct.setBank(calculateItemIn(plans, "bank"));
                cct.setCash(calculateItemIn(plans,"cash"));
                cct.setInsurance(calculateItemIn(plans,"insurance"));
                cct.setOilAdd(calculateItemIn(plans,"oil"));
                cct.setOther(calculateItemIn(plans, "other"));
                cct.setPlanAll(calculateTotalPlan(plans));
                BigDecimal lastMonth = getlastMontAccountLeft(id,date);
                cct.generated(lastMonth);
                table.add(cct);
            }
        }
        return table;
    }

    /***********************************************************************************************************/
    /*********************************************END***********************************************************/
    /***********************************************************************************************************/

    /**
     * 获得某月的所有记录,该方法用于对不同类型的财务收入/支出进行统计
     * @param date 查询的年月
     * @return 记录列表
     */
    public List<ChargePlan> getAll(Date date,String feeType){
        return chargeDao.getAll(date,feeType);
    }

    /**
     * 单车多月计划详情
     * @param licenseNum 要查询的车牌号
     * @param startTime 开始年月
     * @param endTime 结束年月
     * @return
     */
    public List<PlanDetail> planDetailOneCar(String licenseNum,Date startTime,Date endTime){
        List<PlanDetail> table = new ArrayList<>();
        Vehicle tmp = new Vehicle();
        tmp.setLicenseNum(licenseNum);
        Vehicle vehicle = vehicleDao.selectByLicense(tmp);
        if(vehicle == null) return table;
        Contract contract = contractDao.selectByCarId(vehicle.getCarframeNum());
        if(contract == null) return table;
        int startYear = startTime.getYear();
        int startMonth = startTime.getMonth();
        int endYear = endTime.getYear();
        int endMonth = endTime.getMonth();
        while(startYear <= endYear){
            if(startYear == endYear && startMonth > endMonth)break;
            Date d = new Date();
            d.setYear(startYear);
            d.setMonth(startMonth);
            List<ChargePlan> plans = chargeDao.getAllRecords(contract.getId(),d);
            System.out.println(plans);
            System.out.println(contract.getId());
            System.out.println(d.getYear()+"-"+d.getMonth());
            //set base header
            PlanDetail cpc  = new PlanDetail();
            cpc.setTime(d);
            Driver dx = new Driver();
            dx.setIdNum(contract.getIdNum());
            Driver driver = driverDao.selectById(dx.getIdNum());
            if(driver != null){
                cpc.setDriverName(driver.getName());
                cpc.setDriverId(driver.getIdNum());
                cpc.setDept(driver.getDept());
            }
            cpc.setCarNumber(vehicle.getLicenseNum());
            cpc.setDept(vehicle.getDept());
            //数据设置
            BigDecimal heton_base = calculatePlan(plans,"plan_base_contract");
            System.out.println(heton_base);
            BigDecimal heton = calculatePlan(plans,"contract");
            System.out.println(heton);
            BigDecimal insurance = calculatePlan(plans,"insurance");
            BigDecimal other = calculatePlan(plans,"other");
            cpc.setBaoxian(insurance);
            cpc.setHeton(heton_base.add(heton));
            cpc.setOther(other);
            cpc.setTotal(insurance.add(heton).add(heton_base).add(other));
            table.add(cpc);
            if(startMonth < 11){
                startMonth++;
            }else {
                startMonth = 0;
                startYear++;
            }
        }
        return table;

    }


    /**
     * 单车单月的财务对账表获取
     * @param licenseNum 车牌号
     * @param time 要获取的对账表月份
     * @return CheckChargeTable that presen
     */
    public CheckChargeTable getSingleCarAndMonthCheckTableByLicenseNum(String licenseNum,Date time){
        Vehicle vehicle = new Vehicle();
        vehicle.setLicenseNum(licenseNum);
        vehicle = vehicleDao.selectByLicense(vehicle);
        if(vehicle == null) return null;
        Contract contract = contractDao.selectByCarId(vehicle.getCarframeNum());
        if(contract == null) return null;
        List<ChargePlan> plans = chargeDao.getAllRecords(contract.getId(),time);
        CheckChargeTable cct = new CheckChargeTable();
        //set header
        cct.setContractId(contract.getId());
        cct.setTime(time);
        Driver d = new Driver();
        d.setIdNum(contract.getIdNum());
        Driver driver = driverDao.selectById(d.getIdNum());
        if(driver != null){
            cct.setDriverName(driver.getName());
            cct.setDriverId(driver.getIdNum());
            cct.setDept(driver.getDept());
        }
        cct.setDept(vehicle.getDept());
        //set message
        cct.setCarNumber(licenseNum);
        cct.setBank(calculateItemIn(plans, "bank"));
        cct.setCash(calculateItemIn(plans,"cash"));
        cct.setInsurance(calculateItemIn(plans,"insurance"));
        cct.setOilAdd(calculateItemIn(plans,"oil"));
        cct.setOther(calculateItemIn(plans, "other"));
        cct.setPlanAll(calculateTotalPlan(plans));
        BigDecimal lastMonth = getlastMontAccountLeft(contract.getId(),time);
        cct.generated(lastMonth);
        return cct;
    }

    /**
     * 单车多月的财务对账表
     * @param CarId 车牌号
     * @param timePass 时间段
     * @return 单车多月对账表
     */
    @SuppressWarnings("deprecation")
	public List<CheckTablePerCar> getACarChargeTable(String CarId,TimePass timePass){
        List<CheckTablePerCar> table = new ArrayList<>();
        Vehicle tmp = new Vehicle();
        tmp.setLicenseNum(CarId);
        Vehicle vehicle = vehicleDao.selectByLicense(tmp);
        if(vehicle == null) return table;
        Contract contract = contractDao.selectByCarId(vehicle.getCarframeNum());
        if(contract == null) return table;
        if(timePass == null || timePass.getStartTime() == null || timePass.getEndTime()==null){
            return new ArrayList<>();
        }

        int startYear = timePass.getStartTime().getYear();
        int startMonth = timePass.getStartTime().getMonth();
        int endYear = timePass.getEndTime().getYear();
        int endMonth = timePass.getEndTime().getMonth();
        while(startYear <= endYear){
            if(startYear == endYear && startMonth > endMonth)break;
            Date d = new Date();
            d.setYear(startYear);
            d.setMonth(startMonth);
            List<ChargePlan> plans = chargeDao.getAllRecords(contract.getId(),d);
            //set base header
            CheckTablePerCar cpc  = new CheckTablePerCar();
            cpc.setTime(d);
            Driver dx = new Driver();
            dx.setIdNum(contract.getIdNum());
            Driver driver = driverDao.selectById(dx.getIdNum());
            if(driver != null){
                cpc.setDriverName(driver.getName());
                cpc.setDriverId(driver.getIdNum());
                cpc.setDept(driver.getDept());
            }
            cpc.setCarNumber(vehicle.getLicenseNum());
            cpc.setDept(vehicle.getDept());
            //set data
            cpc.setOil(calculateItemIn(plans,"oil"));
            cpc.setInsurance(calculateItemIn(plans,"insurance"));
            cpc.setBank(calculateItemIn(plans,"bank"));
            cpc.setOther(calculateItemIn(plans,"other"));
            cpc.setCash(calculateItemIn(plans,"cash"));
            cpc.setPlanAll(calculateTotalPlan(plans));
            cpc.setRealAll(calculateItemIn(plans, "other").add(calculateItemIn(plans, "insurance")).add(calculateItemIn(plans, "cash")).add(calculateItemIn(plans,"bank")).add(calculateItemIn(plans,"oil")));
            cpc.setLeft(getlastMontAccountLeft(contract.getId(), d));
            cpc.setThisMonthLeft(cpc.getRealAll().subtract(cpc.getPlanAll()));
            table.add(cpc);
            if(startMonth < 11){
                startMonth++;
            }else {
                startMonth = 0;
                startYear++;
            }
        }
        return table;
    }

    /**
     * 封帐操作,无需分页
     * @param dept 部门
     * @return true if success
     */
    public boolean finalClearAll(String dept){
        //如果其他部门不清帐则不往前清帐
        if(!("一部".equals(dept) || "二部".equals(dept) ||"三部".equals(dept))) return  false;
        Date totalTime = clearTimeDao.getCurrent("total");
        Date deptTime = clearTimeDao.getCurrent(dept);
        if(!DateUtil.isYearAndMonth(totalTime,deptTime)) return false;
        List<Contract> contractList = contractDao.contractSearchAllAvilable(deptTime,dept,null);
        //TODO：I don't know if it is necessary to filter
        fileterContract(contractList);
        for(Contract contract:contractList){
            Driver d = new Driver();
            d.setIdNum(contract.getIdNum());
            Driver driver = driverDao.selectById(d.getIdNum());
            //TODO wait to add department
            if(driver != null && dept.equals(contract.getBranchFirm())){
                clear(contract.getId(),deptTime);
            }
        }
        //清账后对整体的清账日期进行再计算
        boolean res = clearTimeDao.plusAMonth(dept);
        Date time1 = clearTimeDao.getCurrent("一部");
        Date time2 = clearTimeDao.getCurrent("二部");
        Date time3 = clearTimeDao.getCurrent("三部");
        if(!DateUtil.isYearAndMonth(totalTime,time1) && !DateUtil.isYearAndMonth(totalTime,time2) && !DateUtil.isYearAndMonth(totalTime,time3)){
            clearTimeDao.plusAMonth("total");
        }else{
            System.out.println(dept);
            System.out.println(res);
            System.out.println(time1);
            System.out.println(time2);
            System.out.println(time3);
            System.out.println(totalTime);
        }
        return true;
    }

    /**
     * 获得某月某车的所有记录
     * @param licenseNum 车牌号
     * @param date 日期
     * @return
     */
    public List<ChargePlan> getAMonthRecords(String licenseNum,Date date){
        List<ChargePlan> plans = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setLicenseNum(licenseNum);
        vehicle = vehicleDao.selectByLicense(vehicle);
        if(vehicle == null){
            return plans;
        }
        Contract contract = contractDao.selectByCarId(vehicle.getCarframeNum());
        if(contract == null || contract.getState() != 0){
            return plans;
        }
        plans = chargeDao.getAllRecords(contract.getId(),date);
        for(ChargePlan plan:plans){
            plan.setBatchPlan(null);
        }
        return plans;
    }

    /**
     * 获得当前可以取出的不同类型的剩余钱款
     * @param licenseNum 车牌号
     * @param time 年月
     * @return 列表中的第一个值为油补进账,第二个值为保险进账,第三个为账户余额
     */
    public List<BigDecimal> couldGetMoney(String licenseNum,Date time){
        List<BigDecimal> moneys = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setLicenseNum(licenseNum);
        vehicle = vehicleDao.selectByLicense(vehicle);
        if(vehicle == null){
            return moneys;
        }
        Contract contract = contractDao.selectByCarId(vehicle.getCarframeNum());
        if(contract == null || contract.getState() != 0){
            return moneys;
        }
        List<ChargePlan> plans = chargeDao.getUnclears(contract.getId(), time);
        BigDecimal oil = calculateItemIn(plans,"oil");
        BigDecimal insurance = calculateItemIn(plans,"insurance");
        BigDecimal account = new BigDecimal(0);
        account = account.add(getUnClearAdd(contract.getId(),time));
        account = account.add(getlastMontAccountLeft(contract.getId(),time));
        moneys.add(oil);
        moneys.add(insurance);
        moneys.add(account);
        return moneys;
    }

    /**
     * 导入银行计划文件并保存到临时表中.
     * @param brs
     * @param recorder
     * @return
     */
    public boolean importFile(List<BankRecord> brs,final String recorder){
        @SuppressWarnings("unchecked")
        List<BankRecordTmp> list = (List<BankRecordTmp>)CollectionUtils.collect(brs, new Transformer() {
            @Override
            public Object transform(Object o) {
                if(o == null) return null;
                BankRecord br = (BankRecord)o;
                BankRecordTmp brt = new BankRecordTmp();
                brt.setStatus(0);
                brt.setLicenseNum(br.getLicenseNum());
                brt.setDriverName(br.getDriverName());
                brt.setInTime(clearTimeDao.getCurrent("total"));
                brt.setRecodeTime(new Date());
                brt.setMoney(br.getMoney());
                brt.setRecorder(recorder);
                BankCard bankCard = (BankCard) br.getBankCards().get("hrb");
                brt.setBankCardNum(bankCard == null?"":bankCard.getCardNumber());
                return brt;
            }
        });
        CollectionUtils.filter(list,new Predicate(){
			@Override
			public boolean evaluate(Object o) {
                if (o == null) return false;
                else return true;
            }
        });
        return bankRecordTmpDao.saveList(list);
    }

    /**
     * 清除错误的银行记录
     * @return true if no exception else false.
     */
    public boolean clearBadBankRecords(){
        return bankRecordTmpDao.clearBadRecord();
    }

    /**
     * 将银行计划从临时表中导入到ChargePlan中,将错误数据进行提取.
     * @return true.
     */
    public boolean fromTmpToSql(){
        List<BankRecordTmp> list = bankRecordTmpDao.selectByTimeAndStaus(clearTimeDao.getCurrent("total"),0);
        List<BankRecordTmp> badRecords = new ArrayList<>();
        List<ChargePlan> cps = new ArrayList<>();
        for(BankRecordTmp brt:list) {
            Vehicle vehicle = new Vehicle();
            vehicle.setLicenseNum(brt.getLicenseNum());
            vehicle = vehicleDao.selectByLicense(vehicle);
            if(vehicle == null){
                badRecords.add(brt);
                continue;
            }
            Contract c = contractDao.selectByCarId(vehicle.getCarframeNum());
            if(c == null){
                badRecords.add(brt);
                continue;
            }
            Driver driver = new Driver();
            driver.setIdNum(c.getIdNum());
            driver = driverDao.selectById(driver.getIdNum());
            if(driver == null || !driver.getName().equals(brt.getDriverName())){
                badRecords.add(brt);
                continue;
            }
            
            BankCard bc=bankCardDao.getBankCardForPayByDriverId(driver.getIdNum(),vehicle.getCarframeNum());
            
            String cardNum = bc.getCardNumber();
            
            if(!cardNum.equals(brt.getBankCardNum())){
                badRecords.add(brt);
                continue;
            }
            ChargePlan cp = new ChargePlan();
            cp.setContractId(c.getId());
            cp.setFeeType("add_bank");
            cp.setFee(brt.getMoney());
            cp.setTime(brt.getInTime());
            cp.setIsClear(false);
            cp.setInTime(brt.getRecodeTime());
            cp.setRegister(brt.getRecorder());
            cps.add(cp);
            chargeDao.addChargePlan(cp);
        }
        bankRecordTmpDao.addBadList(badRecords);
        bankRecordTmpDao.importToSql();
        return true;
    }

    /**
     * 银行的临时记录查询.
     * @param time 要查询的年月
     * @param status 临时记录的状态
     * @return
     */
    public List<BankRecordTmp> getBankRecordByTimeAndStatus(Date time,int status){
        return bankRecordTmpDao.selectByTimeAndStaus(time,status);
    }

    /**
     * 写文件的MD5值.
     * @param md5 银行文件的md5值
     * @return true if this file never imported else false.
     */
    public boolean writeMd5(String md5){
        return bankFileDao.importFile(md5);
    }

    /**
     * 合计计划总额
     * @param plans none
     * @return none
     */
    private BigDecimal calculateTotalPlan(List<ChargePlan> plans){
        BigDecimal totalPlan = new BigDecimal(0);
        for(ChargePlan plan:plans){
            String feeType = plan.getFeeType();
            if(feeType.startsWith("plan")){
                if(feeType.contains("sub")){
                    totalPlan = totalPlan.subtract(plan.getFee());
                }else{
                    totalPlan = totalPlan.add(plan.getFee());
                }
            }
        }
        return totalPlan;
    }
    public Date getCurrentTime(String dept){
        return clearTimeDao.getCurrent(dept);
    }

    /**
     * 合计某一项进账总和
     * @param plans 要合计的所有chargePlan
     * @param feeType 费用类型,只给出中缀即可.
     * @return 合计金额.
     */
    private BigDecimal calculateItemIn(List<ChargePlan> plans,String feeType){
        BigDecimal fee = new BigDecimal(0);
        for(ChargePlan plan:plans){
            if(!plan.getFeeType().startsWith("plan") && plan.getFeeType().contains(feeType)){
                if(plan.getFeeType().contains("add")){
                    fee = fee.add(plan.getFee());
                }else {
                    fee = fee.subtract(plan.getFee());
                }
            }
        }
        return fee;
    }

    /**
     * 计算某个计划项的总金额
     * @param plans 所有要累计的ChargePlan.
     * @param type 计划类型的中缀.
     * @return 合计金额.
     */
    private BigDecimal calculatePlan(List<ChargePlan> plans,String type){
        BigDecimal fee = new BigDecimal(0);
        for(ChargePlan plan:plans){
            if(type.equals("plan_base_contract")){
                if(plan.getFeeType().equals("plan_base_contract"))
                    fee = fee.add(plan.getFee());
                continue;
            }
            if(plan.getFeeType().startsWith("plan") && plan.getFeeType().contains(type)){
                if(type.equals("contract") && plan.getFeeType().equals("plan_base_contract"))
                    continue;
                if(plan.getFeeType().contains("add")){
                    fee = fee.add(plan.getFee());
                }else {
                    fee = fee.subtract(plan.getFee());
                }
            }
        }
        return fee;
    }

    /**
     * 获得上月账户余额
     * @param contractId 要查询的合同id(账户)
     * @param date 查询的月份X,实际返回X的前一个月的账户余额.
     * @return 金额
     */
    private BigDecimal getlastMontAccountLeft(int contractId,Date date){
        BigDecimal left = null;
        Contract contract = contractDao.selectById(contractId);
        Date currentMonth = clearTimeDao.getCurrent(contract.getBranchFirm());
        if(DateUtil.isYM1BGYM2(date,currentMonth)){
            left = contractDao.getAccount(contractId);
        }else{
            List<ChargePlan> plans = chargeDao.getAllRecords(contractId,date);
            for(ChargePlan plan:plans){
                if("last_month_left".equals(plan.getFeeType())){
                    left = plan.getFee();
                }
            }
            if(left == null){
                left = new BigDecimal(0.00);
            }
        }
        return left;
    }


    /**
     * 对单个账户进行清帐
     * @param contractId none
     * @param clearTime none
     * @return none
     */
    private boolean clear(int contractId,Date clearTime){
        BigDecimal account = contractDao.getAccount(contractId);
        //记录上月余额
        ChargePlan lastMonthRecord = new ChargePlan();
        lastMonthRecord.setFee(account);
        lastMonthRecord.setIsClear(true);
        lastMonthRecord.setContractId(contractId);
        lastMonthRecord.setTime(clearTime);
        lastMonthRecord.setFeeType("last_month_left");
        chargeDao.addChargePlan(lastMonthRecord);

        List<ChargePlan> plans = chargeDao.getUnclears(contractId,clearTime);
        for(ChargePlan plan:plans){
            System.out.println(plan.getFeeType() + " test "+plan.getFee());
            String feeType = plan.getFeeType();
            if(feeType.startsWith("add") || feeType.startsWith("plan_sub")){
                account = account.add(plan.getFee());
            }else if(feeType.startsWith("sub") || feeType.startsWith("plan_add") || feeType.startsWith("plan_base")){
                account = account.subtract(plan.getFee());
            }
            chargeDao.cleared(plan);
        }
        contractDao.updateAccount(contractId,account);
        return true;
    }

    /**
     * 获得一个账户到当前月份为止，未结清的金额（不计上月账户）
     * @param contractId 合同id(账户)
     * @param clearTime 清账时间
     * @return 未结清金额
     */
    private BigDecimal getUnClearAdd(int contractId,Date clearTime){
        List<ChargePlan> plans = new ArrayList<>();
        Contract contract = contractDao.selectById(contractId);
        String dept = contract.getBranchFirm();
        if(dept != null){
            Date currentMonth = clearTimeDao.getCurrent(dept);
            if(DateUtil.isYM1BGYM2(clearTime,currentMonth)){
                while(DateUtil.isYM1BGYM2(clearTime,currentMonth)){
                    plans.addAll(chargeDao.getAllRecords(contractId,currentMonth));
                    currentMonth = DateUtil.getNextMonth(currentMonth);
                }
            }
        }
        BigDecimal account = new BigDecimal(0);
        for(ChargePlan plan:plans){
            String feeType = plan.getFeeType();
            if(feeType.startsWith("add") || feeType.startsWith("plan_sub")){
                account = account.add(plan.getFee());
            }else if(feeType.startsWith("sub") || feeType.startsWith("plan_add")||feeType.startsWith("plan_base")){
                account = account.subtract(plan.getFee());
            }
        }
        return account;
    }

    /**
     * 根据车牌号查询一辆车的批计划(约定变更)
     * @param license 车牌号
     * @return 所有与该车有关的批计划
     */
    public List<BatchPlan> searchBatchPlans(String license){
        List<BatchPlan> bps = new ArrayList<BatchPlan>();
        Vehicle vehicle = new Vehicle();
        vehicle.setLicenseNum(license);
        Vehicle v = vehicleDao.selectByLicense(vehicle);
        if(v != null){
            Contract contract = contractDao.selectByCarId(v.getCarframeNum());
            if(contract == null || contract.getId() == null){
                bps = new ArrayList<>();
            }else{
                bps = chargeDao.searchBatchPlan(contract.getId());
            }
        }
        return bps;
    }


    /**
     * OK,please ignore this file check.
     * @param md5 md5check code
     * @return true if this file's md5 is imported else false.
     */
    public boolean isFileExisted(String md5){
        return bankFileDao.isFileImported(md5);
    }


    /**
     * 过滤合同,只显示合同开始日期在清帐日期之前的合同
     */
    private void fileterContract(List<Contract> contractList){
        CollectionUtils.filter(contractList, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                if(object == null) return false;
                Contract contract = (Contract)object;
                Date clearTime = clearTimeDao.getCurrent("total");
                //TODO
                return DateUtil.isYM1BGYM2(clearTime,contract.getContractBeginDate());
            }
        });
    }

    /**
     * 所有跨月份的计划都使用该方法添加
     * generate + casecase.all => 插入了所有数据
     * @param plan
     * @param isToEnd
     * @return true if success else false.
     */
    public boolean addBatchPlan(BatchPlan plan, boolean isToEnd){
        if(plan == null) throw new NullPointerException("the plan shouldn't be null");
        plan.generate(isToEnd);
        System.out.println(plan.getChargePlanList().size());
        chargeDao.addBatchPlan(plan);
        return true;
    }

    /**
     * 给一辆车下多月计划
     * @param plan 批计划
     * @param licenseNum 车牌号
     * @param isToEnd 是否使用
     * @return true if success else false
     */
    public boolean addBatchPlanPerCar(BatchPlan plan,String licenseNum, boolean isToEnd){
        Vehicle vehicle = new Vehicle();
        vehicle.setLicenseNum(licenseNum);
        vehicle = vehicleDao.selectByLicense(vehicle);
        if(vehicle == null){
            return  false;
        }
        Contract contract = contractDao.selectByCarId(vehicle.getCarframeNum());
        if(contract == null || contract.getState() != 0){
            return false;
        }
        List<Integer> cil = new ArrayList<>();
        cil.add(contract.getId());
        plan.setContractIdList(cil);
        return addBatchPlan(plan, isToEnd);
    }


    /**
     * 添加单车单月计划，除了约定变更，所有的其他添加都最终调用该方法,不再具有清帐功能
     * @param plan
     * @return
     */
    public boolean addChargePlan(ChargePlan plan){
        if(plan == null) throw new NullPointerException("the plan shouldn't be null");
        plan.setIsClear(false);
        int contractId = plan.getContractId();
        Contract contract = contractDao.selectById(contractId);
        if(contract == null) return false;
        //对合同由于有旧数据所以不做限制.
        if(!plan.getFeeType().equals("plan_base_contract"))
            if((plan.getFeeType().startsWith("add") || plan.getFeeType().startsWith("sub"))){
                if(!DateUtil.isYearAndMonth(plan.getTime(),clearTimeDao.getCurrent(contract.getBranchFirm())))
                    return false;
            }else{
                plan.setTime(clearTimeDao.getCurrent(contract.getBranchFirm()));
            }
        if(plan.getFee() == null) return false;
        boolean flag = chargeDao.addChargePlan(plan);
        return flag;
    }

    /**
     * 根据车牌号添加一条财务计划,最终调用addChargePlan.
     * @param plan 财务计划
     * @param licenseNum 车牌号
     * @return
     */
    public boolean addChargePlanByLicenseNum(ChargePlan plan,String licenseNum){
        Vehicle vehicle = new Vehicle();
        vehicle.setLicenseNum(licenseNum);
        vehicle = vehicleDao.selectByLicense(vehicle);
        if(vehicle == null){
            return  false;
        }
        Contract contract = contractDao.selectByCarId(vehicle.getCarframeNum());
        if(contract == null || contract.getState() != 0){
            return false;
        }
        plan.setContractId(contract.getId());
        return addChargePlan(plan);
    }

    /**
     * 删除一条财务计划,useless
     * @param plan the plan to delete,it must contains id  attribute.
     * @return true if db success else false.
     */
    public boolean deleteChargePlan(ChargePlan plan){
        if(plan == null) throw new NullPointerException("the plan shouldn't be null");
        plan = chargeDao.getChargePlanById(plan.getId());
        if(plan != null || plan.getIsClear() != true){
            return chargeDao.deleteChargePlan(plan);
        }
        return false;
    }

    /**
     * Actually this method is useless.
     * @param plan
     * @return
     */
    public boolean deleteBatchPlan(BatchPlan plan){
        if(plan == null) throw new NullPointerException("the plan shouldn't be null");
        plan = chargeDao.getBatchPlanById(plan.getId());
        if(plan != null && plan.getStartTime().getTime() > Calendar.getInstance().getTime().getTime()){
            return chargeDao.deleteBatchPlan(plan);
        }
        return false;
    }

    /**
     * 设置从beginDate开始的月份之后的chargePlan为已清账,beginDate为承包日期.
     * @param srcId 合同id
     * @param time 承包日期
     * @return
     */
    public boolean setCleared(int srcId,Date time){
        return chargeDao.setCleared(srcId,time);
    }
    /**
     * 把计划在合同之间进行迁移
     * @param srcId 源合同号id
     * @param srcTime 将>=该年月的未清算合同进行迁移
     * @param destId 目标合同id
     * @param destTime 迁移到的年月
     * @return
     */
    public boolean planTransfer(int srcId,Date srcTime,int destId,Date destTime){
        return chargeDao.planTransfer(srcId,srcTime,destId,destTime);
    }
    public void addAndDiv(int cid,Date time){
        chargeDao.addAndDiv(cid,time);
    }
}
