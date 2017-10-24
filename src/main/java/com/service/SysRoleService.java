package com.service;

import java.util.List;

import com.bean.SysRolePO;
import com.bean.SysUserPO;

public interface SysRoleService {

	public List<SysRolePO> findBySysUserPO(SysUserPO sysUserPO);
}
