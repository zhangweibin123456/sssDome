package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sysRoleRel")
public class SysRoleRelPO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;
	
	@ManyToOne
	@JoinColumn(name = "sysRole_Id", nullable=false)
	private SysRolePO sysRolePO;
	
	@ManyToOne
	@JoinColumn(name = "sysMenu_Id", nullable=false)
	private SysMenuPO sysMenuPO;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public SysRolePO getSysRolePO() {
		return sysRolePO;
	}

	public void setSysRolePO(SysRolePO sysRolePO) {
		this.sysRolePO = sysRolePO;
	}

	public SysMenuPO getSysMenuPO() {
		return sysMenuPO;
	}

	public void setSysMenuPO(SysMenuPO sysMenuPO) {
		this.sysMenuPO = sysMenuPO;
	}
}
