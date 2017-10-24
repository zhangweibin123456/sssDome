package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sysUserGroupRel")
public class SysUserGroupRelPO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sysUser_id", nullable=false)
	private SysUserPO sysUserPO;
	
	@ManyToOne
	@JoinColumn(name = "sysUserGroup_id", nullable=false)
	private SysUserGroupPO sysUserGroupPO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysUserPO getSysUserPO() {
		return sysUserPO;
	}

	public void setSysUserPO(SysUserPO sysUserPO) {
		this.sysUserPO = sysUserPO;
	}

	public SysUserGroupPO getSysUserGroupPO() {
		return sysUserGroupPO;
	}

	public void setSysUserGroupPO(SysUserGroupPO sysUserGroupPO) {
		this.sysUserGroupPO = sysUserGroupPO;
	}
}
