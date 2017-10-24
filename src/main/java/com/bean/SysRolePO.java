package com.bean;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sysRole")
public class SysRolePO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;// id主键
	private String roleName;// 角色名称
	private Date createTime;// 创建时间
	private Integer createBy;// 创建人
	private Date updateTime;// 修改时间
	private Integer updateBy;// 修改人
	private Integer state;// 状态0=可用 1=禁用
	private String descr;// 角色描述
	private String roleType;// 角色描述
	private String endtime; // 授权结束时间
	private String roleCode; //
	private String stime;
	private String etime;
	private Integer type;// 角色类型，1表示永久，2表示临时

	@ManyToOne
	@JoinColumn(name = "sysUser_Id", nullable=false)
	private SysUserPO sysUserPO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public SysUserPO getSysUserPO() {
		return sysUserPO;
	}

	public void setSysUserPO(SysUserPO sysUserPO) {
		this.sysUserPO = sysUserPO;
	}
}
