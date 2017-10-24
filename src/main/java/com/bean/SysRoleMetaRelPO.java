package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sysRole")
public class SysRoleMetaRelPO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer type;//   类型，标记是表还是字段
	private Integer roleid;//   角色id
	private Integer metaid;//   元数据id
	private Integer reltype;//   关联类型，4：表，5：字段
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getMetaid() {
		return metaid;
	}
	public void setMetaid(Integer metaid) {
		this.metaid = metaid;
	}
	public Integer getReltype() {
		return reltype;
	}
	public void setReltype(Integer reltype) {
		this.reltype = reltype;
	}
	
}
