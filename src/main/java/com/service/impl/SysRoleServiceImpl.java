package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.SysRolePO;
import com.bean.SysUserPO;
import com.repo.SysRoleRepository;
import com.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleRepository sysRoleRepository;

	@Override
	public List<SysRolePO> findBySysUserPO(SysUserPO sysUserPO) {
		List<SysRolePO> poList = sysRoleRepository.findBySysUserPO(sysUserPO);
		return poList;
	}

}
