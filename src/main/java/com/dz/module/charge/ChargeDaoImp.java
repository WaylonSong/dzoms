package com.dz.module.charge;

import com.dz.common.factory.HibernateSessionFactory;

import com.dz.common.global.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author doggy
 *         Created on 15-11-12.
 */
@Repository(value="chargeDao")
public class ChargeDaoImp implements ChargeDao {

    @Override
    public void addAndDiv(int cid, Date time) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("from ChargePlan where contractId = :cid and isClear = false");
            query.setInteger("cid",cid);
            List<ChargePlan> plans = query.list();
            BigDecimal account = new BigDecimal(0);
            for(ChargePlan cp:plans){
                if(DateUtil.isInOneMonth26(cp.getTime(),time)){
                    String feeType = cp.getFeeType();
                    if(feeType.startsWith("add") || feeType.startsWith("plan_sub")){
                        account = account.add(cp.getFee());
                    }else if(feeType.startsWith("sub") || feeType.startsWith("plan_add")||feeType.startsWith("plan_base")){
                        account = account.subtract(cp.getFee());
                    }
                    session.delete(cp);
                }
            }
            int leftDay = 0;
            if(time.getDay() >= 27){
                leftDay = time.getDay()-26;
            }else{
                leftDay = 4 + time.getDay();
            }
            account = account.multiply(new BigDecimal(leftDay)).divide(new BigDecimal(30));
            ChargePlan cpp = new ChargePlan();
            cpp.setTime(time);
            cpp.setContractId(cid);
            cpp.setIsClear(false);
            cpp.setFeeType("plan_base_contract");
            cpp.setFee(account);
            cpp.setComment("转包自动计算所得");
            session.save(cpp);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Override
    public boolean setCleared(int srcId, Date beginDate) {
        Date start = DateUtil.getNextMonth26(beginDate);
        Session session = null;
        Transaction tx = null;
        boolean flag = false;
        try {
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from ChargePlan where contractId = :cid");
            query.setInteger("cid",srcId);
            List<ChargePlan> plans = query.list();
            for(ChargePlan cp : plans){
                if(DateUtil.isYM1BGYM2(cp.getTime(),start)){
                    cp.setIsClear(true);
                }
            }
            tx.commit();
            flag = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return flag;
    }

    @Override
    public boolean planTransfer(int srcId, Date srcTime,int destId, Date destTime) {
        Session session = null;
        Transaction tx = null;
        boolean flag = false;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("from ChargePlan where contractId = :srcId and isClear = false");
            query.setInteger("srcId",srcId);
            List<ChargePlan> plans = query.list();
            for(ChargePlan cp:plans){
                if(DateUtil.isYM1BGYM2(cp.getTime(),srcTime)){
                    cp.setContractId(destId);
                    cp.setTime(destTime);
                }
            }
            tx.commit();
            flag = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return flag;
    }

    @Override
    public boolean addBatchPlan(BatchPlan batchPlan) {
        Session session = null;
        Transaction tx = null;
        boolean flag = false;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();

            session.save(batchPlan);
            tx.commit();
            flag = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return flag;
    }

    @Override
    public boolean deleteBatchPlan(BatchPlan batchPlan) {
        boolean flag = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            batchPlan = (BatchPlan)session.get(BatchPlan.class,batchPlan.getId());
            session.delete(batchPlan);
            tx.commit();
            flag = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return flag;
    }

    @Override
    public boolean addChargePlan(ChargePlan chargePlan) {
        boolean flag = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            session.save(chargePlan);
            tx.commit();
            flag = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return flag;
    }

    @Override
    public boolean deleteChargePlan(ChargePlan chargePlan) {
        boolean flag = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            chargePlan = (ChargePlan)session.get(ChargePlan.class,chargePlan.getId());
            session.delete(chargePlan);
            tx.commit();
            flag = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return flag;
    }
    //获得该车当月的所有记录
	@Override
    public List<ChargePlan> getAllRecords(int contractId, Date date) {
        List<ChargePlan> list = new ArrayList<ChargePlan>();
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("from ChargePlan where contractId = :contractId");
            query.setInteger("contractId", contractId);
            @SuppressWarnings("unchecked")
			List<ChargePlan> temps = query.list();
            for(ChargePlan plan:temps){
                if(isYearAndMonth(date,plan.getTime())){
                    list.add(plan);
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return list;
    }

    @Override
    public List<ChargePlan> getAll(Date time,String feeType) {
        List<ChargePlan> list = new ArrayList<ChargePlan>();
        Session session = null;
        try {
            session = HibernateSessionFactory.getSession();
            Query query = session.createQuery("from ChargePlan where feeType = :feeType");
            query.setString("feeType",feeType);
            @SuppressWarnings("unchecked")
			List<ChargePlan> temps = query.list();
            for(ChargePlan plan:temps){
                if(isYearAndMonth(time,plan.getTime())){
                    list.add(plan);
                }
            }
        } catch (HibernateException e) {
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return list;
    }

    //根据车还有月份，获得当月未清帐的记录
    @Override
    public List<ChargePlan> getUnclears(int contractId, Date date) {
        List<ChargePlan> list = new ArrayList<ChargePlan>();
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("from ChargePlan where contractId = :contractId and isClear=false");
            query.setInteger("contractId", contractId);
            @SuppressWarnings("unchecked")
			List<ChargePlan> temps = query.list();
            for(ChargePlan plan:temps){
                if(isYearAndMonth(date,plan.getTime())){
                    list.add(plan);
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return list;
    }
    @Override
    public ChargePlan getChargePlanById(int id) {
        Session session = null;
        ChargePlan plan = null;
        try {
            session = HibernateSessionFactory.getSession();
            plan = (ChargePlan)session.get(ChargePlan.class,id);
        } catch (HibernateException e) {
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return plan;
    }

    @Override
    public BatchPlan getBatchPlanById(int id) {
        Session session = null;
        BatchPlan plan = null;
        try {
            session = HibernateSessionFactory.getSession();
            plan = (BatchPlan)session.get(BatchPlan.class,id);
        } catch (HibernateException e) {
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return plan;
    }
    //获得一辆车的所有记录
    @SuppressWarnings("unchecked")
	@Override
    public List<ChargePlan> getACarRecords(int contractId) {
        List<ChargePlan> list = new ArrayList<ChargePlan>();
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("from ChargePlan where contractId = :contractId");
            query.setInteger("contractId", contractId);
            list = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return list;
    }

    @SuppressWarnings("deprecation")
	private boolean isYearAndMonth(Date date1,Date date2){
        if(date1 == null||date2 == null) return false;
        return date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();
    }

    @Override
    public boolean cleared(ChargePlan plan) {
        boolean flag = false;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            ChargePlan cp = (ChargePlan)session.get(ChargePlan.class, plan.getId());
            cp.setIsClear(true);
            tx.commit();
            flag = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return flag;
    }

    @Override
    public List<BatchPlan> searchBatchPlan(final int contractId) {
        List<BatchPlan> batchPlen = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateSessionFactory.getSession();
            tx = (Transaction) session.beginTransaction();
            Query query = session.createQuery("from BatchPlan");
            batchPlen = query.list();
            CollectionUtils.filter(batchPlen, new Predicate() {
                @Override
                public boolean evaluate(Object object) {
                    if(object == null)
                        return false;
                    BatchPlan bp = (BatchPlan)object;
                    if(bp.getContractIdList().contains(contractId))
                        return true;
                    else
                        return false;
                }
            });
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                batchPlen = new ArrayList<>();
            }
            throw e;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return batchPlen;
    }
}
