package com.dz.module.charge.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author doggy
 *         Created on 15-12-28.
 */
@Service
public class ReceiptService {
    @Autowired
    private RollDao rollDao;
    @Autowired
    private ReceiptDao receiptDao;
    @Autowired
    private RemoveRecordDaoImp removeRecordDao;
    public boolean deleteRecord(int id,int startNum,int endNum){
        if(receiptDao.deleteRecord(id)){
            return rollDao.deleteRoll(startNum,endNum);
        }
        return false;
    }
    public boolean inRemove(int id,int startNum,int endNum,String reason){
        removeRecordDao.addOne(transfer(receiptDao.getRecord(id),reason));
        if(receiptDao.deleteRecord(id)){
            return rollDao.deleteRoll(startNum,endNum);
        }
        return false;
    }
    public List<RemoveRecord> searchRemoves(Date startTime,Date endTime){
        return removeRecordDao.searchRecord(startTime,endTime);
    }
    public boolean addRecord(ReceiptRecord receiptRecord){
        if("进货".equals(receiptRecord.getStyle())){
            if(!rollDao.addFromSeg(receiptRecord.getStartNum(),receiptRecord.getEndNum(),new Date().getYear()+1900))
                return false;
            else{
                receiptRecord.setStorage(rollDao.getStorage());
            }
        }else{
            if(!rollDao.markAsUsed(receiptRecord.getStartNum(), receiptRecord.getEndNum()))
                return false;
            else{
                receiptRecord.setStorage(rollDao.getStorage());
            }
        }
        receiptRecord.setPaperNum(receiptRecord.getEndNum()-receiptRecord.getStartNum()+1);
        return receiptDao.addRecord(receiptRecord);
    }
    public List<ReceiptRecord> searchRecordsByProveNum(String proveNum){
        return receiptDao.searchRecordsByProveNum(proveNum);
    }
    @SuppressWarnings("deprecation")
	public List<ReceiptRecord> searchRecords(Date start,Date end){
        if(start == null){
            start = new Date();
            start.setYear(0);
        }
        if(end == null){
            end  = new Date();
            end.setYear(2150);
        }
        List<ReceiptRecord> rrs = receiptDao.searchRecords(start,end);
        System.out.println(rrs);
        return rrs;
    }
    public long getStorage(){
        return rollDao.getStorage();
    }
    public boolean validateIn(int start,int end){
        return rollDao.isValidForIn(start,end);
    }
    public boolean validateSoled(int start,int end,int year){
        return rollDao.isValidForSold(start, end,year);
    }
    public boolean outRemove(int id,int startNum,int endNum,String reason){
        removeRecordDao.addOne(transfer(receiptDao.getRecord(id),reason));
        if(receiptDao.deleteRecord(id)){
                return rollDao.markAsUnUsed(startNum,endNum);
        }
        return false;
    }
    private RemoveRecord transfer(ReceiptRecord record,String reason){
        RemoveRecord remove = new RemoveRecord();
        remove.setReason(reason);
        remove.setAllPrice(record.getAllPrice());
        remove.setCarId(record.getCarId());
        remove.setComment(record.getCarId());
        remove.setEndNum(record.getEndNum());
        remove.setHappenTime(record.getHappenTime());
        remove.setStartNum(record.getStartNum());
        remove.setProveNum(record.getProveNum());
        remove.setRecordTime(new Date());
        remove.setInNum(record.getInNum());
        remove.setPaperNum(record.getPaperNum());
        remove.setRecorder(record.getRecorder());
        remove.setRenter(record.getRenter());
        remove.setStyle(record.getStyle());
        return remove;
    }
    public int getNextAvaliable(String proveNum){
        return receiptDao.getNextAvaliable(proveNum);
    }
}
