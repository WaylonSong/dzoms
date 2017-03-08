package com.dz.common.dataimport3;

import com.dz.module.charge.BatchPlan;
import com.dz.module.charge.ChargeService;
import com.dz.module.contract.Contract;
import com.dz.module.contract.ContractService;
import com.dz.module.vehicle.Vehicle;
import com.dz.module.vehicle.VehicleApproval;
import com.dz.module.vehicle.VehicleApprovalService;
import com.dz.module.vehicle.VehicleService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author doggy
 *         Created on 2016-07-06.
 */
@Controller("cp")
public class ColdPlayer {
    @Autowired
    public ChargeService chargeService;
    @Autowired
    public ContractService contractService;
    @Autowired
    public VehicleService vehicleService;
    @Autowired
    public VehicleApprovalService vehicleApprovalService;

    public String main() throws Exception{
//        importData(importCarNumFromFile(ColdPlayer.class.getResource("193.txt").getPath()),"plan_add_insurance",new BigDecimal(193));
//        importData(importCarNumFromFile(ColdPlayer.class.getResource("300.txt").getPath()),"plan_base_contract",new BigDecimal(300));
    	importData(importCarNumFromFile(ColdPlayer.class.getResource("2.74.txt").getPath()),"plan_add_other",new BigDecimal(2.74));
    	importData(importCarNumFromFile(ColdPlayer.class.getResource("125.txt").getPath()),"plan_sub_contract",new BigDecimal(125));
        return "success";
    }

    private void importData(List<String> carNums,String feeType,BigDecimal fee) {
        for(String carNum:carNums){
            try{
                Vehicle fuck_v = new Vehicle();
                fuck_v.setLicenseNum(carNum);
                Vehicle real_v = vehicleService.selectByLicenseNum(fuck_v);
                Contract contract = contractService.selectByCarId(real_v.getCarframeNum());
                VehicleApproval va = vehicleApprovalService.queryVehicleApprovalByContractId(contract.getId());
                Date startTime = va.getOperateCardDate();
                DateTime st = new DateTime(startTime);
                Date endTime = contract.getContractEndDate();
                BatchPlan bp = new BatchPlan();
                bp.setFeeType(feeType);
                bp.setFee(fee);
                bp.setStartTime(st.plusDays(3).toDate());
                bp.setEndTime(endTime);
                List<Integer> cids = new ArrayList<>();
                cids.add(contract.getId());
                bp.setContractIdList(cids);
                chargeService.addBatchPlan(bp,false);
            }catch (Exception e){
                System.out.println("Error at:"+carNum);
                e.printStackTrace();
            }
        }
    }


    private static List<String> importCarNumFromFile(String fileName) throws Exception{
        File f = new File(fileName);
        List<String> rs = new ArrayList<>();
        FileInputStream fis = new FileInputStream(f);
        Scanner scanner = new Scanner(fis);
        while(scanner.hasNextLine()){
            String tmp = scanner.nextLine();
            tmp = tmp.trim();
            if(!"".equals(tmp)){
                rs.add(tmp);
                System.out.println(tmp);
            }
        }
        return rs;
    }
}
