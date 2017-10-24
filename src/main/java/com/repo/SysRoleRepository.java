package com.repo;


import java.util.List;

import com.bean.SysRolePO;
import com.bean.SysUserPO;
import com.commons.BaseRepository;

public interface SysRoleRepository extends BaseRepository<SysRolePO, Long> {

	public List<SysRolePO> findBySysUserPO(SysUserPO po);
}