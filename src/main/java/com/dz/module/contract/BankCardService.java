package com.dz.module.contract;

import com.dz.common.factory.HibernateSessionFactory;
import com.dz.common.global.Page;

import java.util.Date;
import java.util.List;

import com.dz.module.charge.ChargeDao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankCardService {
	@Autowired
	private BankCardDao bankCardDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private ChargeDao chargeDao;

	public boolean bankCardAdd(BankCard card) {
		if (card == null) {
			return false;
		}
		Contract contract = contractDao.selectByCarId(card.getCarNum(), new Date());
		Session s=null;
		Transaction tx = null;
		
		try{
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			if(contract!=null && contract.getContractFrom()!=null && contract.getContractFrom()!=0){
				Query q_move = s.createQuery("update ChargePlan set contractId=:nid where contractId=:oid and isClear=false");
				q_move.setInteger("nid", contract.getId());
				q_move.setInteger("oid", contract.getContractFrom());
				q_move.executeUpdate();
			}
			
//				String carframeNum = card.getCarNum();
//				Contract contract = contractDao.contractSearchRentAvaliable(carframeNum);
//				if(contract != null){
//					Integer contractOldId = contract.getContractFrom();
//					if(!(contractOldId == null || contractOldId == 0)){
//						Date splitTime = contract.getContractBeginDate();
//						if(splitTime.getDay() != 26){
//							chargeDao.planTransfer(contract.getId(),splitTime,contractOldId,splitTime);
//						}
//					}
//				}
			
			
			String hql = "from BankCard where cardNumber=:cid and carNum in (select carframeNum from Vehicle where state!=1) ";
			Query query = s.createQuery(hql);
			query.setString("cid", card.getCardNumber());
			List<BankCard> list = query.list();
			if(list!=null)
				for(BankCard cd : list)
					s.delete(cd);
			
			s.save(card);
			tx.commit();
			return true;
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	public int queryCardCount() {
		return bankCardDao.queryCardCount();
	}
	
	public List<BankCard> bankCardSearch(Page page){
		return bankCardDao.queryAllCard(page);
	}
	
	public BankCard getBankCardByCardNumber(String id) {
		return bankCardDao.getBankCardByCardNumber(id);
	}
	
	public BankCard getBankCardForPayByDriverId(String idNum,String carNum) {
		if (StringUtils.isEmpty(idNum)) {
			return null;
		}
		return bankCardDao.getBankCardForPayByDriverId(idNum,carNum);
	}
	
	public BankCard getBankCardForReceiveByDriverId(String idNum,String carNum) {
		if (StringUtils.isEmpty(idNum)) {
			return null;
		}
		return bankCardDao.getBankCardForRecieveByDriverId(idNum,carNum);
	}
	
	public boolean bankCardUpdate(BankCard card) {
		if (card == null) {
			return false;
		}
		
		return bankCardDao.bankCardUpdate(card);
	}

	public void setBankCardDao(BankCardDao bankCardDao) {
		this.bankCardDao = bankCardDao;
	}
}
