package com.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "sysUser")
public class SysUserPO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;// id主键
	private String email;// 邮箱也是登录帐号
	private String userName;// 用户名
	private String passWord;// 登录密码
	private String nickName;// 昵称
	private Integer state;// 状态 0=可用,1=禁用
	private Integer loginCount;// 登录总次数
	private Date loginTime;// 最后登录时间
	private Integer deleted;// 删除状态 0=未删除,1=已删除
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间
	private Integer createBy;// 创建人
	private Integer updateBy;// 修改人
	private String roleStr;// 用户权限, 按","区分
	private Integer orgId;// 组织id
	private String orgName; // 组织名称
	private String commyOrgName; // 党组织名称
	private Integer commyOrgId;// 党组织id
	private String phone; // 电话号码
	private String roleType; // 角色类型 （公务员、党员、人才三种，1-公务员；2-党员；3-人才）
	private Integer isAddGroup; // 是否添加到组中 0:没有添加，1：已添加
	private String IP; // 用户登录IP，记录log时候使用。
	private Integer deptRoleId;// 审核角色ID
	private Integer superAdmin;// 超级管理员0:否，1：是
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCommyOrgName() {
		return commyOrgName;
	}

	public void setCommyOrgName(String commyOrgName) {
		this.commyOrgName = commyOrgName;
	}

	public Integer getCommyOrgId() {
		return commyOrgId;
	}

	public void setCommyOrgId(Integer commyOrgId) {
		this.commyOrgId = commyOrgId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Integer getIsAddGroup() {
		return isAddGroup;
	}

	public void setIsAddGroup(Integer isAddGroup) {
		this.isAddGroup = isAddGroup;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Integer getDeptRoleId() {
		return deptRoleId;
	}

	public void setDeptRoleId(Integer deptRoleId) {
		this.deptRoleId = deptRoleId;
	}

	public Integer getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(Integer superAdmin) {
		this.superAdmin = superAdmin;
	}

}
