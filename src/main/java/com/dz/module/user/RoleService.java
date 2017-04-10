package com.dz.module.user;

public class RoleService {
	public boolean addRole(Role role){
		if (role == null) {
			return false;
		} 
		
		RoleDao roleDao = new RoleDaoImpl();	
		
		return roleDao.addRole(role);
	}
	
}
