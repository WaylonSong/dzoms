package com.dz.module.search;

import java.util.List;

import com.dz.common.global.Page;
import com.dz.module.driver.Driver;
import com.dz.module.vehicle.Vehicle;

public interface DvDao {
	List<Dv> selectAll();
	List<Dv> selectByVehicle(Vehicle vehicle);
	List<Dv> selectByDriver(Driver driver);
	public int selectByConditionCount(Dv dv);
	public List<Dv> selectByCondition(Page page, Dv dv);
}
