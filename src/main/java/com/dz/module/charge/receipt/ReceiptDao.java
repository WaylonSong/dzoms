package com.dz.module.charge.receipt;

import java.util.Date;
import java.util.List;

/**
 * @author doggy
 *         Created on 15-12-28.
 */
public interface ReceiptDao {
    boolean deleteRecord(int id);
    boolean addRecord(ReceiptRecord rr);
    ReceiptRecord getRecord(int id);
    List<ReceiptRecord> searchRecordsByProveNum(String proveNum);
    List<ReceiptRecord> searchRecords(Date start, Date end);
    int getNextAvaliable(String proveNum);
}
