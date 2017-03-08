package com.dz.module.charge;

import com.dz.common.global.DateUtil;
import com.dz.common.global.MD5;
import com.dz.common.global.Page;
import com.dz.common.global.TimePass;
import com.dz.common.other.PageUtil;
import com.dz.module.contract.BankCard;
import com.dz.module.contract.ContractService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author doggy
 *         Created on 15-11-8.
 */
@Controller
@Scope(value = "prototype")
public class ChargeAction extends ActionSupport{
    private static final int EVERYPAGE = 20;
    /**
	 * 
	 */
	private static final long serialVersionUID = -8426604215311846476L;
	private static final String STRING_RESULT = "string_result";
    private static final String JSON_RESULT = "json_result";
    private static final String DISPATCH = "dispatch";
    @Autowired
    private ChargeService service;
    @Autowired
    private ContractService contractService;
    private String jspPage;
    private String nextAction;
    private String ajax_message;
    private Object jsonObject;
    private ChargePlan chargePlan;
    private BatchPlan batchPlan;
    private String licenseNum;
    private Date time;
    private TimePass timePass;
    private String message;
    private String visitType;
    private String jsonStr;
    private String department;
    private String feeType;
    private String recorder;
    private int status;
    private InputStream txtFile;
    private String fileName;
    //是否使用合同结束日期
    private String isToEnd;
    //当前页.
    private int currentPage;
    //总页数.
    private int pageLimit;

    /*********************************************************************************************************************/
    /**************************************Add the method that need page limit here.**************************************/
    /*********************************************************************************************************************/

    //首页显示对账表
    public String mainCharge(){
        Date current = service.getCurrentTime("total");
        if(currentPage == 0)
            currentPage = 1;
        Page page = PageUtil.createPage(EVERYPAGE,(int)contractService.searchAllAvaliableCount(current,"全部"),currentPage);
        pageLimit = page.getTotalPage();
        List<CheckChargeTable> tables = service.getAllCheckChargeTable(current,"全部",page);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("tables",tables);
        jspPage = "show/getCheckChargeTableByDept.jsp";
        return SUCCESS;
    }



    /**
     * 多车单月的计划详情.
     * @return SUCCESS
     */
    public String planDetailMulCar(){
        List<PlanDetail> details = new ArrayList<>();
        if(!(time == null||department==null)) {
            details = service.planDetailMultiplyCar(department,time,null);
        }
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("table",details);
        jspPage = "plan_detail_one_car_show.jsp";
        return SUCCESS;
    }

    //导出银行文件 file:bankfile_export.jsp
    public String exportBankFile(){
        if(currentPage == 0)
            currentPage = 1;
        Page page = PageUtil.createPage(EVERYPAGE,(int)contractService.searchAllAvaliableCount(time,department),currentPage);
        pageLimit = page.getTotalPage();
        List<BankRecord> records = service.exportBankFile(time,department,page);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("bankRecords",records);
        jspPage = "bankfile_export.jsp";
        return SUCCESS;
    }

    //获得某月的对账单
    public String getCheckChargeTable(){
        if(currentPage == 0)
            currentPage = 1;
        Page page = PageUtil.createPage(EVERYPAGE,(int)contractService.searchAllAvaliableCount(time,department),currentPage);
        pageLimit = page.getTotalPage();
        List<CheckChargeTable> tables = service.getAllCheckChargeTable(time,department,page);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("tables",tables);
        jspPage = "check_charge_table.jsp";
        return SUCCESS;
    }


    public String tongji(){
        if(currentPage == 0)
            currentPage = 1;
        Page page = PageUtil.createPage(EVERYPAGE,(int)contractService.searchAllAvaliableCount(time,department),currentPage);
        pageLimit = page.getTotalPage();
        if(licenseNum != null)
            page = null;
        System.out.println(licenseNum);
        List<CheckChargeTable> tables = service.getAllCheckChargeTable(time,department,page);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        CollectionUtils.filter(tables, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                CheckChargeTable cct = (CheckChargeTable) o;
                if (licenseNum != null && !cct.getCarNumber().contains(licenseNum)) return false;
                //欠费
                if (status == 0) {
                    if (cct.getThisMonthTotalOwe().doubleValue() >= 0)
                        return false;
                    //正常
                } else if (status == 1) {
                    if (cct.getThisMonthTotalOwe().doubleValue() < 0)
                        return false;
                    //未交
                } else if (status == 2) {
                    if (cct.getBank().doubleValue() > 0)
                        return false;
                    //已交
                } else if (status == 3) {
                    if (cct.getBank().doubleValue() <= 0)
                        return false;
                }
                return true;
            }
        });
        request.put("tables", tables);
        jspPage="show/tongji.jsp";
        return SUCCESS;
    }
    /***********************************************************************************************************/
    /*********************************************END***********************************************************/
    /***********************************************************************************************************/

    /**
     * 单车多月的计划详情.
     * @return SUCCESS
     */
    public String planDetailOneCar(){
        List<PlanDetail> details = new ArrayList<>();
        if(!(timePass == null||timePass.getStartTime() == null||timePass.getEndTime()==null)) {
            details = service.planDetailOneCar(licenseNum,timePass.getStartTime(),timePass.getEndTime());
        }
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("table",details);
        jspPage = "plan_detail_one_car_show.jsp";
        return SUCCESS;
    }




    public String getCheckChargeTableToExcel(){
        try(InputStream is = new FileInputStream(System.getProperty("com.dz.root")+"charge/check_table.xlsx")) {
            File file = File.createTempFile("对账表", "xls");

            try (OutputStream os = new FileOutputStream(file)) {
                Context context = new Context();

                List<CheckChargeTable> tables = service.getAllCheckChargeTable(time, department,null);

                context.putVar("checks", tables);

                JxlsHelper.getInstance().processTemplate(is, os, context);
                fileName = "对账表.xls";
                this.setTxtFile(new FileInputStream(file));
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
        return "stream";
    }
    //按部门获取对账表
    public String getCheckChargeTableByDept(){
        Date current = service.getCurrentTime(department);
        List<CheckChargeTable> tables = service.getAllCheckChargeTable(current,department,null);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
		Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("tables",tables);
        jspPage = "show/getCheckChargeTableByDept.jsp";
        return SUCCESS;
    }


    //获得单车多月的对账单 file:a_car_check_charge_table

    public String getACarChargeTable(){
        List<CheckTablePerCar> tables = service.getACarChargeTable(licenseNum, timePass);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
		Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("a_car_table",tables);
        jspPage = "a_car_check_charge_table.jsp";
        return SUCCESS;
    }
    //导出单车多月对账表
    public String exportACarChargeTable(){
        try(InputStream is = new FileInputStream(System.getProperty("com.dz.root")+"charge/a_car_check_table.xlsx")) {
            File file = File.createTempFile("单车多月对账表", "xls");

            try (OutputStream os = new FileOutputStream(file)) {
                Context context = new Context();

                List<CheckTablePerCar> tables = service.getACarChargeTable(licenseNum, timePass);

                context.putVar("checks", tables);

                JxlsHelper.getInstance().processTemplate(is, os, context);
                fileName = "单车多月对账表.xls";
                this.setTxtFile(new FileInputStream(file));
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
        return "stream";
    }

    //导出银行文件为txt
    public String exportTxt() throws Exception{
        List<BankRecord> records = service.exportBankFile(time,department,null);
        File f = new File("bankFile.txt");
        PrintWriter pw = new PrintWriter(f);
        for(BankRecord br:records){
            if(br.getMoney().intValue() == 0)
                continue;
            BankCard bc = br.getBankCards().get("hrb");
            String s = br.getLicenseNum()+"|";
            s += br.getDriverName()+"|";
            s += (bc == null?" ":bc.getCardNumber())+"|";
            s += br.getMoney().setScale(3).doubleValue();
            pw.println(s);
        }
        pw.close();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月-"+department+"-银行计划");
        fileName = sdf.format(time)+".txt";
        fileName = new String(fileName.getBytes(),"ISO8859-1");
        txtFile = new FileInputStream(f);
        return "stream";
    }

    //清空脏数据
    public String clearDirtyTmp(){
        service.clearBadBankRecords();
        jspPage = "bankfile_import.jsp";
        return SUCCESS;
    }
    //移动缓存至数据库
    public String fromTmpToSql(){
        service.fromTmpToSql();
        jspPage = "bankfile_import.jsp";
        return SUCCESS;
    }
    //显示数据
    public String showBankRecords(){
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
		Map<String,Object> request = (Map<String,Object>)context.get("request");
        List<BankRecordTmp> brts = service.getBankRecordByTimeAndStatus(time,status);
        request.put("tables",brts);

        System.out.println(brts);
        jspPage = "show/showBankRecoeds.jsp";
        return SUCCESS;
    }
    //获得某个月的所有记录
    public String getAMonthRecords(){
        jsonObject = service.getAMonthRecords(licenseNum,time);
        System.out.println(jsonObject);
        return JSON_RESULT;
    }
    public String getAMonthGetMoney(){
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
		Map<String,Object> request = (Map<String,Object>)context.get("request");
        List<ChargePlan> plans = service.getAMonthRecords(licenseNum,time);
        CollectionUtils.filter(plans, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                ChargePlan cp = (ChargePlan)o;
                if(cp.getFeeType().startsWith("sub")){
                    return true;
                }
                return false;
            }
        });
        request.put("plans",plans);
        jspPage = "show/show_get_money_records.jsp";
        return SUCCESS;
    }
    public String finalClear(){
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
		Map<String,Object> request = (Map<String,Object>)context.get("request");
        boolean result = service.finalClearAll(department);
        if(result){
            message = "操作成功";
        }else{
            message = "操作失败，当前月份的其他部门未清帐";
        }
        request.put("message",message);
        nextAction = "showClearPage";
        return DISPATCH;
    }
    //展示清帐列表
    public String showClearPage(){
        department = department == null?"一部":department;
        Date date = service.getCurrentTime(department);
        System.out.println(date);
        List<BankRecord> unClears = service.getUnClearRecord(date,department,null);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
		Map<String,Object> request = (Map<String,Object>)context.get("request");
        jspPage = "showClearPage.jsp";
        request.put("unClears",unClears);
        request.put("message",request.get("message"));
        return SUCCESS;
    }
    public String getCurrentTime(){
    	if("全部".equals(department))
    		department = "total";
    	jsonObject = service.getCurrentTime(department);
    	return JSON_RESULT;
    }

    public String getCurrentMonth(){
        Date current = service.getCurrentTime(department);
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        ajax_message = year+"年"+month+"月";
        return STRING_RESULT;
    }

    @SuppressWarnings("deprecation")
    private boolean isYearAndMonth(Date date1,Date date2){
        if(date1 == null||date2 == null) return false;
        return date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();
    }

    @SuppressWarnings("deprecation")
    private boolean isYM1BGYM2(Date date1,Date date2){
        int year1 = date1.getYear();
        int month1 = date1.getMonth();
        int year2 = date2.getYear();
        int month2 = date2.getMonth();
        if(year1 > year2) return true;
        if(year1 == year2 && month1 >= month2) return true;
        return false;
    }
    //展示的表格！！！
    //单车多月的台帐
    public String singleCarAndMuiltyMonthCheckShow(){
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        List<CheckChargeTable> tables = new ArrayList<>();
        if(timePass == null || licenseNum == null || timePass.getStartTime() == null ||timePass.getEndTime() == null){

        }else{
            Date start = timePass.getStartTime();
            Date end = timePass.getEndTime();
            while(isYM1BGYM2(end, start)){
                CheckChargeTable cct = service.getSingleCarAndMonthCheckTableByLicenseNum(licenseNum, start);
                if(cct != null){
                    tables.add(cct);
                }
                start = DateUtil.getNextMonth(start);
            }
        }
        jspPage = "show/singleCarAndMuiltyMonthCheckShow.jsp";
        request.put("tables",tables);
        return SUCCESS;
    }
    //单车多月的明细
    public String singleCarAndMuiltyMonthDetailShow(){
        Map<Date,List<ChargePlan>> map = new HashMap<>();
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        Date start = timePass.getStartTime();
        Date end = timePass.getEndTime();
        if(end == null || start ==null);
        else{
            while(isYM1BGYM2(end, start)){
                List<ChargePlan> plans = service.getAMonthRecords(licenseNum, start);

                CollectionUtils.filter(plans,new Predicate(){
                    @Override
                    public boolean evaluate(Object o) {
                        if(o==null)
                            return false;
                        ChargePlan cp = (ChargePlan)o;
                        if(!cp.getFeeType().startsWith("plan"))
                            return false;
                        return true;
                    }
                });
                map.put(start,plans);
                start = DateUtil.getNextMonth(start);
            }
        }
        request.put("map",map);
        jspPage = "show/singleCarAndMuiltyMonthDetailShow.jsp";
        return SUCCESS;
    }
    /**单车单月财务对账表*/
    public String getACarAndAMonthCheckTable(){
        TimePass tp = new TimePass();
        tp.setStartTime(time);
        tp.setEndTime(time);
        List<CheckTablePerCar> tables = service.getACarChargeTable(licenseNum, tp);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("a_car_table",tables);
        jspPage = "show/a_car_a_month_check_charge_table.jsp";
        return SUCCESS;
    }

    /**
     * 收费类型查询.
     * @return
     */
    public String getChargeCount(){
        System.out.println("Invoked.");
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        Map<String,List<ChargePlan>> map = new HashMap<>();
        List<ChargePlan> plans = service.getAll(time,feeType);

        List<ChargePlan> p_cash = new ArrayList<>();
        List<ChargePlan> p_bank = new ArrayList<>();
        List<ChargePlan> p_insurance = new ArrayList<>();
        List<ChargePlan> p_oil = new ArrayList<>();
        List<ChargePlan> p_other = new ArrayList<>();
        BigDecimal bd_cash = new BigDecimal(0.00);
        BigDecimal bd_bank = new BigDecimal(0.00);
        BigDecimal bd_insurance = new BigDecimal(0.00);
        BigDecimal bd_oil = new BigDecimal(0.00);
        BigDecimal bd_other = new BigDecimal(0.00);
        for(ChargePlan p:plans){
            switch (p.getFeeType()){
                case "add_insurance":
                    p_insurance.add(p);
                    bd_insurance = bd_insurance.add(p.getFee());
                    break;
                case "add_bank":
                    p_bank.add(p);
                    bd_bank = bd_bank.add(p.getFee());
                    break;
                case "add_oil":
                    p_oil.add(p);
                    bd_oil = bd_oil.add(p.getFee());
                    break;
                case "add_cash":
                    p_cash.add(p);
                    bd_cash = bd_cash.add(p.getFee());
                    break;
                case "add_other":
                    p_other.add(p);
                    bd_other = bd_other.add(p.getFee());
                    break;
            }
        }
        if(p_oil.size() > 0){
            ChargePlan cp_oil = new ChargePlan();
            cp_oil.setFeeType("total");
            cp_oil.setFee(bd_oil);
            p_oil.add(cp_oil);
            map.put("oil", p_oil);
        }
        if(p_bank.size() > 0){
            ChargePlan cp_bank = new ChargePlan();
            cp_bank.setFeeType("total");
            cp_bank.setFee(bd_bank);
            p_bank.add(cp_bank);
            map.put("bank",p_bank);
        }
        if(p_insurance.size() > 0){
            ChargePlan cp_insurance = new ChargePlan();
            cp_insurance.setFeeType("total");
            cp_insurance.setFee(bd_insurance);
            p_insurance.add(cp_insurance);
            map.put("insurance",p_insurance);
        }
        if(p_cash.size() > 0){
            ChargePlan cp_cash = new ChargePlan();
            cp_cash.setFeeType("total");
            cp_cash.setFee(bd_cash);
            p_cash.add(cp_cash);
            map.put("cash",p_cash);
        }
        if(p_other.size() > 0) {
            ChargePlan cp_other = new ChargePlan();
            cp_other.setFeeType("total");
            cp_other.setFee(bd_other);
            p_other.add(cp_other);
            map.put("other",p_other);
        }
        request.put("map",map);
        System.out.println(map.size());
        jspPage = "chargeCount.jsp";
        return SUCCESS;
    }


    /************** Stable and changeless methods below. **************/

    /**分析银行文件 file:none.jsp*/
    public String analyseFile(){
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        jspPage = "bankfile_import.jsp";
        if(!isYearAndMonth(time,service.getCurrentTime("total"))){
            message = "不在当前清帐年月\n";
            request.put("message",message);
            return SUCCESS;
        }
        JSONArray json = JSONArray.fromObject(jsonStr);
        json.toArray();
        @SuppressWarnings("unchecked")
        List<BankRecord> brs= (List<BankRecord>) CollectionUtils.collect(json, new Transformer() {
            @Override
            public Object transform(Object obj) {
                JSONObject jso = (JSONObject) obj;
                BankRecord ai = new BankRecord();
                ai.setDriverName(jso.getString("name"));
                ai.setLicenseNum(jso.getString("licenseNum"));
                ai.setMoney(BigDecimal.valueOf(Double.parseDouble(jso.getString("Money"))));
                Map<String,BankCard> map = new HashMap<String, BankCard>();
                BankCard bc = new BankCard();
                bc.setCardNumber(jso.getString("cardNum"));
                System.out.println("bc --> "+bc.getCardNumber());
                map.put("hrb",bc);
                ai.setBankCards(map);
                return ai;
            }
        });
        MD5 md5 = MD5.getInstance();
        String md5Str = md5.GetMD5Code(brs.toString());
        boolean isFileExisted = service.isFileExisted(md5Str);
        if(isFileExisted == true){
            message = "导入文件失败，该文件已导入！";
        }else{
            service.importFile(brs,recorder);
            message = " 数据导入成功\n";
            service.writeMd5(md5Str);
        }
        request.put("message",message);
        return SUCCESS;
    }

    /**
     * 根据车号给出这辆车的所有约定变更记录
     * @return SUCCESS
     */
    public String searchBPS(){
        List<BatchPlan> bps = service.searchBatchPlans(licenseNum);

        Collections.sort(bps,new Comparator<BatchPlan>() {
            @Override
            public int compare(BatchPlan o1, BatchPlan o2) {
                if(o1.getStartTime().getTime() >= o2.getStartTime().getTime()){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        for(BatchPlan bp:bps){
            String type = bp.getFeeType();
            if(type.equals("plan_base_contract")){
                bp.setFeeType("合同基本费用");
            }else if(type.equals("plan_add_insurance")){
                bp.setFeeType("保险费用上调");
            }else if(type.equals("plan_sub_insurance")){
                bp.setFeeType("保险费用下降");
            }else if(type.equals("plan_add_contract")){
                bp.setFeeType("合同费用上调");
            }else if(type.equals("plan_sub_contract")){
                bp.setFeeType("合同费用下降");
            }else if(type.equals("plan_add_other")){
                bp.setFeeType("其他费用上调");
            }else if(type.equals("plan_sub_other")){
                bp.setFeeType("其他费用下降");
            }
        }
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        request.put("bps",bps);
        System.out.println(bps);
        jspPage = "bps_show.jsp";
        return SUCCESS;
    }

    /**
     * 获得单车单月的欠款.
     * @return JSON_RESULT.
     */
    public String getOweByLicenseNumAndMonth(){
        jsonObject = service.getSingleCarAndMonthCheckTableByLicenseNum(licenseNum,time);
        return JSON_RESULT;
    }

    /**
     * 欠款清账用.
     * @return SUCCESS.
     */
    public String getOweDeleteList(){
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        List<ChargePlan> plans = service.getAMonthRecords(licenseNum, time);
        CollectionUtils.filter(plans,new Predicate(){
            @Override
            public boolean evaluate(Object o) {
                if(o==null)
                    return false;
                ChargePlan cp = (ChargePlan)o;
                if(!cp.getFeeType().startsWith("add"))
                    return false;
                return true;
            }
        });
        request.put("plans",plans);
        jspPage = "oweDeletePage.jsp";
        return SUCCESS;
    }

    public String addChargePlan(){
        boolean success = service.addChargePlan(chargePlan);
        if(success){
            jspPage = "success.jsp";
        }else{
            jspPage = "error.jsp";
        }
        return SUCCESS;
    }
    public String addChargePlanByLicenseNum(){
        boolean success = service.addChargePlanByLicenseNum(chargePlan,licenseNum);
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        if("get_money".equals(visitType)){
            if(success){
                jspPage = "get_money.jsp";
                message = "已成功取款。";
            }else{
                jspPage = "get_money.jsp";
                message = "取款失败,不在当前清帐月份！";
            }
        }else if("charge_add".equals(visitType)){
            if(success){
                jspPage = "charge_add.jsp";
                message = "添加收入成功。";
            }else{
                jspPage = "charge_add.jsp";
                message = "添加收入失败，不在当前清帐月份！";
            }
        }else{
            if(success){
                jspPage = "success.jsp";
                message = "添加收入成功。";
            }else{
                jspPage = "error.jsp";
                message = "添加收入失败，不在当前清帐月份！";
            }
        }
        request.put("message",message);
        return SUCCESS;
    }
    public String deleteChargePlan(){
        boolean success = service.deleteChargePlan(chargePlan);
        if(success){
            ajax_message = "success";
        }else{
            ajax_message = "error";
        }
        return STRING_RESULT;
    }
    public String addBatchPlan(){
        boolean success = false;
        if("on".equals(isToEnd)){
            batchPlan.setEndTime(null);
            success = service.addBatchPlan(batchPlan, true);
        }else{
            success = service.addBatchPlan(batchPlan, false);
        }
        ActionContext context = ActionContext.getContext();
        @SuppressWarnings("unchecked")
        Map<String,Object> request = (Map<String,Object>)context.get("request");
        jspPage = "batch_add.jsp";
        request.put("message",message);
        return SUCCESS;
    }
    public String addBatchPlanPerCar(){
        boolean success = false;
        if("on".equals(isToEnd)){
            batchPlan.setEndTime(null);
            success = service.addBatchPlanPerCar(batchPlan,licenseNum, true);
        }else{
            success = service.addBatchPlanPerCar(batchPlan,licenseNum, false);
        }
        ActionContext context = ActionContext.getContext();
        context.get("request");
        if(success){
            jspPage = "percar_add.jsp";
            message = "添加成功";
        }else {
            jspPage = "percar_add.jsp";
            message = "添加失败,请重新添加";
        }
        return SUCCESS;
    }
    public String deleteBatchPlan(){
        boolean success = service.deleteBatchPlan(batchPlan);
        if(success){
            ajax_message = "success";
        }else{
            ajax_message = "error";
        }
        return STRING_RESULT;
    }
    //
    public String couldGetMoney(){
        jsonObject = service.couldGetMoney(licenseNum,time);
        return JSON_RESULT;
    }

    /************** Setter and getter methods below. ******************/

    public ChargeService getService() {
        return service;
    }

    public void setService(ChargeService service) {
        this.service = service;
    }

    public ChargePlan getChargePlan() {
        return chargePlan;
    }

    public void setChargePlan(ChargePlan chargePlan) {
        this.chargePlan = chargePlan;
    }

    public String getJspPage() {
        return jspPage;
    }

    public void setJspPage(String jspPage) {
        this.jspPage = jspPage;
    }

    public String getAjax_message() {
        return ajax_message;
    }

    public void setAjax_message(String ajax_message) {
        this.ajax_message = ajax_message;
    }

    public Object getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
    }

    public BatchPlan getBatchPlan() {
        return batchPlan;
    }


    public void setBatchPlan(BatchPlan batchPlan) {

        this.batchPlan = batchPlan;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {

        this.licenseNum = licenseNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public TimePass getTimePass() {
        return timePass;
    }

    public void setTimePass(TimePass timePass) {
        this.timePass = timePass;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public String getMessage() {
        return message;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getDepartment() {
        return department;
    }

    public ChargeAction setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InputStream getTxtFile() {
        return txtFile;
    }

    public void setTxtFile(InputStream txtFile) {
        this.txtFile = txtFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getIsToEnd() {
        return isToEnd;
    }

    public void setIsToEnd(String isToEnd)
    {
        this.isToEnd = isToEnd;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }
}
