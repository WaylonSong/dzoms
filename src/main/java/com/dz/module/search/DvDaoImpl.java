package com.dz.module.search;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dz.common.factory.HibernateSessionFactory;
import com.dz.common.global.Page;
import com.dz.module.driver.Driver;
import com.dz.module.vehicle.Vehicle;
@Repository(value = "dvDao")
public class DvDaoImpl implements DvDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Dv> selectAll() {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from Dv ");
			List<Dv> list = query.list();
			return list;
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dv> selectByVehicle(Vehicle vehicle) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from Dv where carframeNum=:carframeNum");
			query.setString("carframeNum", vehicle.getCarframeNum());
			return query.list();
			
		} catch (HibernateException e) {
			throw e;
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dv> selectByDriver(Driver driver) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from Dv where idNum=:driverId");
			query.setString("driverId", driver.getIdNum());
			return query.list();
			
		} catch (HibernateException e) {
			throw e;
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public int selectByConditionCount(Dv dv) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql="select count(*) from Dv where 1=1 ";
			
			
			if(!StringUtils.isEmpty(dv.getCarframeNum())){
				sql+="and carframeNum like :carframeNum ";
			}
			
			if(!StringUtils.isEmpty(dv.getDept())){
				sql+="and dept like :dept ";
			}
			
			if(!StringUtils.isEmpty(dv.getIdNum())){
				sql+="and idNum like :idNum ";
			}
			
			if(!StringUtils.isEmpty(dv.getName())){
				sql+="and name like :name ";
			}
			
			if(BooleanUtils.isTrue(dv.getIsInCar())){
				sql+="and isInCar=:isInCar ";
			}
			
			if(!StringUtils.isEmpty(dv.getTeam())){
				sql+="and team like :team ";
			}
			
			if(dv.getState()!=null){
				sql+="and state=:state ";
			}
			
			Query query = session.createQuery(sql);
			
			
			if(!StringUtils.isEmpty(dv.getCarframeNum())){
				query.setString("carframeNum", "%"+dv.getCarframeNum()+"%");
			}
			
			if(!StringUtils.isEmpty(dv.getDept())){
				query.setString("dept", "%"+dv.getDept()+"%");
			}
			
			if(!StringUtils.isEmpty(dv.getIdNum())){
				query.setString("idNum", "%"+dv.getIdNum()+"%");
			}
			
			if(!StringUtils.isEmpty(dv.getName())){
				query.setString("name", "%"+dv.getName()+"%");

			}
			
			if(BooleanUtils.isTrue(dv.getIsInCar())){
				query.setBoolean("isInCar", dv.getIsInCar());
			}
			
			if(!StringUtils.isEmpty(dv.getTeam())){
				query.setString("team", "%"+dv.getTeam()+"%");
			}
			
			if(dv.getState()!=null){
				query.setShort("state", dv.getState());
			}
			
			return Integer.parseInt(query.uniqueResult().toString());
			
		} catch (HibernateException e) {
			throw e;
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dv> selectByCondition(Page page, Dv dv) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			String sql="from Dv where 1=1 ";
			
			if(!StringUtils.isEmpty(dv.getCarframeNum())){
				sql+="and carframeNum like :carframeNum ";
			}
			
			if(!StringUtils.isEmpty(dv.getDept())){
				sql+="and dept like :dept ";
			}
			
			if(!StringUtils.isEmpty(dv.getIdNum())){
				sql+="and idNum like :idNum ";
			}
			
			if(!StringUtils.isEmpty(dv.getName())){
				sql+="and name like :name ";
			}
			
			if(BooleanUtils.isTrue(dv.getIsInCar())){
				sql+="and isInCar=:isInCar ";
			}
			
			if(!StringUtils.isEmpty(dv.getTeam())){
				sql+="and team like :team ";
			}
			
			if(dv.getState()!=null){
				sql+="and state=:state ";
			}
			
			Query query = session.createQuery(sql);	
			
			if(!StringUtils.isEmpty(dv.getCarframeNum())){
				query.setString("carframeNum", "%"+dv.getCarframeNum()+"%");
			}
			
			if(!StringUtils.isEmpty(dv.getDept())){
				query.setString("dept", "%"+dv.getDept()+"%");
			}
			
			if(!StringUtils.isEmpty(dv.getIdNum())){
				query.setString("idNum", "%"+dv.getIdNum()+"%");
			}
			
			if(!StringUtils.isEmpty(dv.getName())){
				query.setString("name", "%"+dv.getName()+"%");

			}
			
			if(BooleanUtils.isTrue(dv.getIsInCar())){
				query.setBoolean("isInCar", dv.getIsInCar());
			}
			
			if(!StringUtils.isEmpty(dv.getTeam())){
				query.setString("team", "%"+dv.getTeam()+"%");
			}
			
			if(dv.getState()!=null){
				query.setShort("state", dv.getState());
			}
			
			if(page!=null){
				query.setFirstResult(page.getBeginIndex());
				query.setMaxResults(page.getEveryPage());
			}
			
			return query.list();
			
		} catch (HibernateException e) {
			throw e;
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
