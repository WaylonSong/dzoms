package com.dz.module.user;

import com.dz.common.global.BaseAction;


public class RoleAction extends BaseAction{
	
	private Role role;		// 定义值对象
	private RoleService roleService;		// 定义值对象
	public String roleAdd() {
		System.out.println(role);
		
		boolean flag = roleService.addRole(role);
		
		if (!flag) {
			return ERROR;
		}
		
		return "add_success";
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
		
	
}
